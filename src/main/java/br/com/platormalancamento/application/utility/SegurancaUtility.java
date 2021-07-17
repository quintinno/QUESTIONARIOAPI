package br.com.platormalancamento.application.utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

public class SegurancaUtility {
	
	public static String codificarMD5(String parametro) {
		MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(parametro.getBytes());
			byte[] digest = messageDigest.digest();
			return DatatypeConverter.printHexBinary(digest).toLowerCase();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public static Boolean decodificarMD5(String A, String B) {
		MessageDigest messageDigestA;
		MessageDigest messageDigestB;
		try {
			messageDigestA = MessageDigest.getInstance("MD5"); messageDigestA.update(A.getBytes());
			messageDigestB = MessageDigest.getInstance("MD5"); messageDigestB.update(B.getBytes());
			byte[] digestA = messageDigestA.digest();
			byte[] digestB = messageDigestB.digest();
			return DatatypeConverter.printHexBinary(digestA).equals(DatatypeConverter.printHexBinary(digestB));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}	
		return false;
	}

}
