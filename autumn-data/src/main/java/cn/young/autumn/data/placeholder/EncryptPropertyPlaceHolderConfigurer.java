package cn.young.autumn.data.placeholder;

import java.io.IOException;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import cn.young.autumn.commons.crypto.AESUtil;

/**
 * @desc:
 * @author: yangfei
 * @date: 2018/11/27 13:08
 * @version: 0.0.1
 */
public class EncryptPropertyPlaceHolderConfigurer extends PropertyPlaceholderConfigurer {
	/**
	 * 日志工具
	 */
	private static final Logger logger = LoggerFactory.getLogger(EncryptPropertyPlaceHolderConfigurer.class);

	private String[] encryptPropNames = { "username", "password" };

	// 缓存所有的属性配置
	private Properties properties;

	public EncryptPropertyPlaceHolderConfigurer(String[] encryptPropNames) {
		this.encryptPropNames = encryptPropNames;
	}

	/**
	 * 开放此方法给需要的业务
	 *
	 * @param key
	 * @return
	 */
	public String getProperty(String key) {
		return resolvePlaceholder(key, properties);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.core.io.support.PropertiesLoaderSupport#
	 * mergeProperties()
	 */
	@Override
	protected Properties mergeProperties() throws IOException {
		Properties mergeProperties = super.mergeProperties();
		properties = new Properties();

		Set<Entry<Object, Object>> es = mergeProperties.entrySet();
		for (Entry<Object, Object> entry : es) {
			String key = (String) entry.getKey();
			int idx = key.lastIndexOf('_');
			String realKey = idx == -1 ? key : key.substring(0, idx);
			if (!properties.containsKey(realKey)) {
				Object value = mergeProperties.get(realKey);
				if (value != null) {
					properties.put(realKey, value);
				} else {
					throw new RuntimeException("impossible empty property for " + realKey);
				}
			}
		}
		return properties;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.beans.factory.config.PropertyResourceConfigurer#
	 * convertProperty(java.lang.String, java.lang.String)
	 */
	@Override
	protected String convertProperty(String propertyName, String propertyValue) {
		if (isEncryptProp(propertyName)) {
			String decryptValue = propertyValue.trim();
			if (null != propertyValue && !"".equals(propertyValue)) {
				String tempDecryptValue;
				try {
					tempDecryptValue = AESUtil.decryptWithBase64(propertyValue.trim(), AESUtil.DEFAULT_SEED);
					if (tempDecryptValue != null) {
						decryptValue = tempDecryptValue;
					} else {
						logger.warn("AES decrypt result is null.");
					}
				} catch (Exception e) {
					logger.error("", e);
				}

			}
			return decryptValue;
		} else {
			return propertyValue;
		}
	}

	/**
	 * 判断是否是加密的属性
	 *
	 * @param propertyName
	 * @return
	 */
	private boolean isEncryptProp(String propertyName) {
		for (String encryptPropName : encryptPropNames) {
			if (encryptPropName.equals(propertyName)) {
				return true;
			}
		}
		return false;
	}

}
