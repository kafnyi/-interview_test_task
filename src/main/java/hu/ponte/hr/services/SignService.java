package hu.ponte.hr.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

@Service
public class SignService {

	private final String PRIVATE_KEY_PATH = "src/main/resources/config/keys/key.private";
	private final String PUBLIC_KEY_PATH = "src/main/resources/config/keys/key.private";

	@Autowired
	public SignService() {
	}

	public String sign(String toSign) {
		try {
			return new String(this.signWithSHA256withRSAAndBase64(toSign));
		} catch (Exception e) {
			System.err.println("Something bad happened :");
			System.out.println(e);
		}
		return null;
	}

	private byte[] signWithSHA256withRSAAndBase64(String toEncode) throws Exception {
		return this.encodeWithBase64(this.encodeWithSHA256WithRSA(toEncode));
	}

	private byte[] encodeWithBase64(byte[] toEncode) {
		byte[] encoded = Base64.getEncoder().encode(toEncode);
		return encoded;
	}

	private byte[] encodeWithSHA256WithRSA(String toEncode) throws Exception {

		byte[] privateKeyBytes;
		try (FileInputStream fis = new FileInputStream(PRIVATE_KEY_PATH)) {
			privateKeyBytes = fis.readAllBytes();
			PKCS8EncodedKeySpec encodedKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			Signature signature = Signature.getInstance("SHA256withRSA");
			signature.initSign(keyFactory.generatePrivate(encodedKeySpec));
			signature.update(toEncode.getBytes(StandardCharsets.UTF_8));
			return signature.sign();
		}
	}

	/**
	 * Felreertettem ezt egy kicsit...
	 */
	//public String decrypt(String toDecrypt) {
	//	try {
	//		return new String(decodeWithBase64(toDecrypt.getBytes()));
	//	} catch (Exception e) {
	//		System.err.println("Something bad happened :");
	//		System.out.println(e);
	//	}
	//	return null;
	//}
	//private String decodeWithSHA256WithRSAAndBase64(byte[] todecode) throws Exception {
	//	return new String(this.decodeWithSHA256WithRSA(decodeWithBase64(todecode)));
//
	//}
//
	//private String decodeWithBase64(byte[] toDecode) {
	//	byte[] decoded = Base64.getDecoder().decode(toDecode);
	//	return new String(decoded);
	//}
//
	//private byte[] decodeWithSHA256WithRSA(String toDecode) throws Exception{
//
	//	byte[] publicKeyBytes;
	//	try (FileInputStream fis = new FileInputStream(PUBLIC_KEY_PATH)){
	//		publicKeyBytes = fis.readAllBytes();
	//		PKCS8EncodedKeySpec encodedKeySpec = new PKCS8EncodedKeySpec(publicKeyBytes);
	//		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
	//		Signature signature = Signature.getInstance("SHA256withRSA");
	//		signature.initVerify(keyFactory.generatePublic(encodedKeySpec));
	//		signature.update(toDecode.getBytes(StandardCharsets.UTF_8));
	//		byte[] signBytes = signature.sign();
	//		return signBytes;
	//	}
	//}
}
