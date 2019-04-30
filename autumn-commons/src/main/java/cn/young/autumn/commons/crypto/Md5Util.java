package cn.young.autumn.commons.crypto;

import java.security.MessageDigest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @desc: MD5摘要工具
 * @author: yangfei
 */
public class Md5Util {

    /**
     * the logger
     */
    private static final Logger logger = LoggerFactory.getLogger(Md5Util.class);

    /**
     * 字符串转MD5
     *
     * @param plainText 文本字符串
     * @return MD5码
     */
    public static String md5(String plainText) {
        StringBuffer buf = new StringBuffer();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes("UTF-8"));
            byte b[] = md.digest();
            int i;
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
        } catch (Exception e) {
            logger.error("md5 error!{}", e);
        }
        return buf.toString();
    }
}
