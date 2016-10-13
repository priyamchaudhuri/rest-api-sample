package org.tat.api.core.crypto;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;
import org.springframework.util.SerializationUtils;

public class MD5Util {
	private static final Logger logger = Logger.getLogger(MD5Util.class);

	public static String hash(Object object) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");

		byte[] data = SerializationUtils.serialize(object);
		md.update(data);

		byte byteData[] = md.digest();

		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			String hex = Integer.toHexString(0xff & byteData[i]);
			if (hex.length() == 1)
				hexString.append('0');
			hexString.append(hex);
		}
		if (logger.isDebugEnabled())
			logger.debug("Digest(in hex format):: " + hexString.toString());

		return hexString.toString();
	}

	public static String hash(String string) throws NoSuchAlgorithmException {

		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(string.getBytes());

		byte byteData[] = md.digest();

		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			String hex = Integer.toHexString(0xff & byteData[i]);
			if (hex.length() == 1)
				hexString.append('0');
			hexString.append(hex);
		}
		if (logger.isDebugEnabled())
			logger.debug("Digest(in hex format):: " + hexString.toString());

		return hexString.toString();
	}

	public static String checksum(File file) throws NoSuchAlgorithmException,
			IOException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		FileInputStream fis = new FileInputStream(file);

		byte[] dataBytes = new byte[1024];

		int nread = 0;
		while ((nread = fis.read(dataBytes)) != -1) {
			md.update(dataBytes, 0, nread);
		}
		;
		fis.close();
		byte[] mdbytes = md.digest();

		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < mdbytes.length; i++) {
			String hex = Integer.toHexString(0xff & mdbytes[i]);
			if (hex.length() == 1)
				hexString.append('0');
			hexString.append(hex);
		}
		if (logger.isDebugEnabled())
			logger.debug("Digest(in hex format):: " + hexString.toString());
		return hexString.toString();
	}

	public static void main(String[] args) {
		try {
			// hash with string
			hash("satish");

			// hash with object
			Error error = new Error("400-102:I am a fool");
			hash(error);

			// checksum for a file
			File file = new File(
					"C:\\NotBackedUp\\workspaceRest\\rest-api-sample\\core\\src\\main\\java\\org\\tat\\api\\core\\exceptions\\InvalidFieldNameException.java");
			checksum(file);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
