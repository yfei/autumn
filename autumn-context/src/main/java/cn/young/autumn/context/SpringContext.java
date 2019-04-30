package cn.young.autumn.context;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;

public class SpringContext implements ApplicationContextAware {

	/**
	 * the Logger
	 */
	private Logger logger = LoggerFactory.getLogger(SpringContext.class);

	/**
	 * the spring application context
	 */
	private static ApplicationContext appContext;

	/**
	 * the properties reader.
	 */
	private static Properties properties;

	public SpringContext() {
		logger.info("Construct Spring Context.");
	}

	public SpringContext(String[] propertiesFile) {
		for (String propertyFile : propertiesFile) {
			logger.info("Construct Spring Context with properties file {}.", propertyFile);
			try {
				Resource resource = new ClassPathResource(propertyFile);
				File file = resource.getFile();
				if (!file.exists()) {
					logger.warn("property file{} not exist!", file.getPath());
				} else {
					properties = new Properties();
					properties.load(new FileInputStream(file));
					logger.debug("property file loaded.");
				}
			} catch (Exception e) {
				logger.error("property file {} load error!", propertyFile);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.springframework.context.ApplicationContextAware#setApplicationContext
	 * (org .springframework.context.ApplicationContext)
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		logger.info("set application context by spring.");
		appContext = applicationContext;
	}

	/**
	 * get ApplicationContext.
	 *
	 * @return the spring application context.
	 */
	public static ApplicationContext getApplicationContext() {
		Assert.notNull(appContext, "the applicationContext is null");
		return appContext;
	}

	/**
	 * get spring bean by beanName.
	 *
	 * @param beanName
	 *            the bean name.
	 * @return the bean instance.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String beanName) {
		Assert.notNull(appContext, "the applicationContext is null");
		return (T) appContext.getBean(beanName);
	}

	/**
	 * 获取配置文件(properties文件)的内容
	 *
	 * @param key
	 *            the key.
	 * @return the result.如果key不存在，返回null.
	 */
	public static String getProperties(String key) {
		return properties.getProperty(key);
	}

	/**
	 * 获取配置文件(properties文件)的内容
	 *
	 * @param key
	 *            the key.
	 * @param defaultValue
	 *            the defaultValue.
	 * @return the result.如果key不存在，返回defaultValue.
	 */
	public static String getProperties(String key, String defaultValue) {
		return properties.getProperty(key, defaultValue);
	}

}
