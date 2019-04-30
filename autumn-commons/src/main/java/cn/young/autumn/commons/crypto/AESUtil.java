package cn.young.autumn.commons.crypto;

import java.io.InputStream;
import java.io.OutputStream;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @desc: AES加解密工具
 * @author: yangfei
 */
public class AESUtil {
	/**
	 * the logger halo
	 */
	private static final Logger logger = LoggerFactory.getLogger(AESUtil.class);

	/**
	 * 加解密种子
	 */
	public static final String DEFAULT_SEED = "audit111";

	/**
	 * 指定加密算法
	 */
	private final static String ALGORITHM = "AES";

	/**
	 * the transformation
	 */
	private final static String DEFAULT_TRANSFORMATION = "AES/CBC/PKCS5Padding";

	/**
	 * the default iv
	 */
	private final static String DEFAULT_IV = "0102030405060708";

	/**
	 * 使用种子初始化加解密秘钥
	 *
	 * @param seed
	 *            加解密种子
	 * @return SecretKey对象
	 */
	private static final SecretKey initKey(String seed) {
		if (seed == null) {
			throw new NullPointerException("seed is null.");
		}
		byte[] byteKey = new byte[16];
		if (seed.getBytes().length < 16) {
			for (int i = 0; i < seed.getBytes().length; i++) {
				byteKey[i] = seed.getBytes()[i];
			}
			for (int i = seed.getBytes().length; i < 16; i++) {
				byteKey[i] = 0;
			}
		} else {
			for (int i = 0; i < 16; i++) {
				byteKey[i] = seed.getBytes()[i];
			}
		}
		return new SecretKeySpec(byteKey, ALGORITHM);
	}

	/**
	 * AES加密
	 *
	 * @param text
	 *            明文
	 * @param seed
	 *            加密/解密种子
	 * @return 经过AES加密的密文
	 * @throws Exception 
	 */
	public static final byte[] encrypt(String text, String seed) throws Exception {
		SecretKey skeySpec = initKey(seed);
		try {
			if (skeySpec == null) {
				throw new IllegalArgumentException("SecretKeySpec is null");
			}
			// "算法/模式/补码方式"
			Cipher cipher = Cipher.getInstance(DEFAULT_TRANSFORMATION);
			IvParameterSpec iv = new IvParameterSpec(DEFAULT_IV.getBytes());
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
			byte[] encrypted = cipher.doFinal(text.getBytes("utf-8"));
			return encrypted;
		} catch (Exception e) {
			throw new Exception(e);
		}

	}

	/**
	 * 带有Base64转码的AES加密
	 *
	 * @param cleartext
	 *            明文
	 * @param seed
	 *            加密/解密种子
	 * @return 经过AES加密和Base64转码后的密文
	 * @throws Exception 
	 */
	public static final String encryptWithBase64(String cleartext, String seed) throws Exception {
		byte[] temp = encrypt(cleartext, seed);
		return new String(Base64.encode(temp));
	}

	/**
	 * 经过AES解密方法
	 *
	 * @param ciphertext
	 *            密文
	 * @param seed
	 *            加密/解密
	 * @return 解密后的明文
	 * @throws Exception 
	 */
	public static final String decrypt(byte[] ciphertext, String seed) throws Exception {
		SecretKey skeySpec = initKey(seed);

		String cleartext = null;
		try {
			if (skeySpec == null) {
				throw new IllegalArgumentException("SecretKeySpec is null");
			}
			// "算法/模式/补码方式"
			Cipher cipher = Cipher.getInstance(DEFAULT_TRANSFORMATION);
			IvParameterSpec iv = new IvParameterSpec(DEFAULT_IV.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] original = cipher.doFinal(ciphertext);
			cleartext = new String(original, "utf-8");
			return cleartext;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * 经过Base64解码和AES解密
	 *
	 * @param ciphertext
	 *            密文
	 * @param seed
	 *            加密/解密种子
	 * @return 解密后的明文
	 * @throws Exception 
	 */
	public static final String decryptWithBase64(String ciphertext, String seed) throws Exception {
		byte[] encrypted = Base64.decode(ciphertext.toCharArray());
		return decrypt(encrypted, seed);
	}

	/**
	 * AES加密,请务必在调用此方法后关闭input和output
	 *
	 * @param input
	 *            输入明文流
	 * @param output
	 *            输出密文流
	 * @param seed
	 *            加密/解密种子
	 * @return 经过AES加密的密文
	 * @throws Exception 
	 */
	public static final void encrypt(InputStream input, OutputStream output, String seed) throws Exception {
		SecretKey skeySpec = initKey(seed);
		try {
			if (skeySpec == null) {
				throw new IllegalArgumentException("SecretKeySpec is null");
			}
			// "算法/模式/补码方式"
			Cipher cipher = Cipher.getInstance(DEFAULT_TRANSFORMATION);
			IvParameterSpec iv = new IvParameterSpec(DEFAULT_IV.getBytes());
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
			CipherInputStream cis = new CipherInputStream(input, cipher);
			byte[] buffer = new byte[1024];
			int r;
			while ((r = cis.read(buffer)) > 0) {
				output.write(buffer, 0, r);
			}
			input.close();
			cis.close();
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * 经过AES解密方法,请务必在调用此方法后关闭input和output
	 *
	 * @param input
	 *            输入密文流
	 * @param output
	 *            输出明文流
	 * @param seed
	 *            加密/解密
	 * @return 解密后的明文
	 * @throws Exception 
	 */
	public static final void decrypt(InputStream input, OutputStream output, String seed) throws Exception {
		SecretKey skeySpec = initKey(seed);
		try {
			if (skeySpec == null) {
				throw new IllegalArgumentException("SecretKeySpec is null");
			}
			// "算法/模式/补码方式"
			Cipher cipher = Cipher.getInstance(DEFAULT_TRANSFORMATION);
			IvParameterSpec iv = new IvParameterSpec(DEFAULT_IV.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			CipherOutputStream cos = new CipherOutputStream(output, cipher);
			byte[] buffer = new byte[1024];
			int r;
			while ((r = input.read(buffer)) >= 0) {
				cos.write(buffer, 0, r);
			}
			input.close();
			cos.close();
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

}
