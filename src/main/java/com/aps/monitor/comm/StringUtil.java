package com.aps.monitor.comm;

import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class StringUtil {

	/**
	 * 字符串是否为null或""
	 * 
	 * @Title: isNullOrEmpty
	 * @Description: TODO
	 * @param: @param value
	 * @param: @return
	 * @return: boolean
	 * @throws
	 * @since 1.0.0
	 */
	public static boolean isNullOrEmpty(String value) {
		return (null == value || "".equals(value));
	}

	/**
	 * 字符右对齐(左边填充)
	 * 
	 * @param opar
	 * @param ilen
	 * @param sadd
	 * @return
	 */
	public static String getLpad(Object opar, int ilen, String sadd) {

		return getLpad(opar, ilen, sadd, false);
	}

	/**
	 * 字符右对齐(左边填充)
	 * 
	 * @param opar
	 * @param ilen
	 * @param sadd
	 * @param bcut
	 *            是否移除超出部分
	 * @return
	 */
	public static String getLpad(Object opar, int ilen, String sadd, boolean bcut) {
		String sreturn = String.valueOf(opar);

		if (getStringLen(sreturn) > ilen) {
			if (bcut)
				return sreturn.substring(0, ilen);
			else
				return sreturn;
		} else {
			while (getStringLen(sreturn) < ilen) {
				sreturn = sadd + sreturn;
			}
		}

		return sreturn;
	}

	/**
	 * 字符左对齐(右边填充)
	 * 
	 * @param opar
	 * @param ilen
	 * @param sadd
	 * @return
	 */
	public static String getRpad(Object opar, int ilen, String sadd) {

		return getRpad(opar, ilen, sadd, false);
	}

	/**
	 * 
	 * @param opar
	 * @param ilen
	 * @param sadd
	 * @param bcut
	 *            是否移除超出部分
	 * @return
	 */
	public static String getRpad(Object opar, int ilen, String sadd, boolean bcut) {
		String sreturn = String.valueOf(opar);

		if (getStringLen(sreturn) > ilen) {
			if (bcut)
				return sreturn.substring(0, ilen);
			else
				return sreturn;
		} else {
			while (getStringLen(sreturn) < ilen) {
				sreturn = sreturn + sadd;
			}
		}

		return sreturn;
	}

	/**
	 * 按系统编码取得字符的长度(字节数)
	 * 
	 * @param spar
	 * @return
	 */
	public static int getStringLen(String spar) {
		return getStringLen(spar, Charset.defaultCharset());
	}

	/**
	 * 按编码取得字符的长度(字节数)
	 * 
	 * @param spar
	 * @param charset
	 * @return
	 */
	public static int getStringLen(String spar, Charset charset) {

		return spar.getBytes(charset).length;
	}

	/**
	 * 加密字符串
	 * 
	 * @param txt
	 * @param txtkey
	 * @return
	 */
	public static String desEncryptStr(String txt, String txtkey) {
		try {
			IvParameterSpec zeroIv = new IvParameterSpec(new byte[8]);
			SecretKeySpec key = new SecretKeySpec(txtkey.getBytes("UTF-8"), "DES");
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
			byte[] encryptedData = cipher.doFinal(txt.getBytes("UTF-8"));
			return new BASE64Encoder().encode(encryptedData);

		} catch (InvalidKeyException e) {
			return null;
		} catch (NoSuchAlgorithmException e) {
			return null;
		} catch (NoSuchPaddingException e) {
			return null;
		} catch (IllegalBlockSizeException e) {
			return null;
		} catch (BadPaddingException e) {
			return null;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 解密字符串
	 * 
	 * @param txt
	 * @param txtkey
	 * @return
	 */
	public static String desDecryptStr(String txt, String txtkey) {

		try {
			byte[] byteMi = new BASE64Decoder().decodeBuffer(txt);
			IvParameterSpec zeroIv = new IvParameterSpec(new byte[8]);
			SecretKeySpec key = new SecretKeySpec(txtkey.getBytes("UTF-8"), "DES");
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
			byte decryptedData[] = cipher.doFinal(byteMi);
			return new String(decryptedData, "UTF-8");

		} catch (InvalidKeyException e) {
			return null;
		} catch (NoSuchAlgorithmException e) {
			return null;
		} catch (NoSuchPaddingException e) {
			return null;
		} catch (IllegalBlockSizeException e) {
			return null;
		} catch (BadPaddingException e) {
			return null;
		} catch (Exception e) {
			return null;
		}
	}
}
