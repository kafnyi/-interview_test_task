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

	@Autowired
	public SignService() {
	}

	public String sign(String toSign) {
		try {
			return this.encodeToBase64(this.signWithSHA256withRSA(toSign, PRIVATE_KEY_PATH));
		} catch (Exception e) {
			System.err.println("Something bad happened :");
			System.out.println(e);
		}
		return toSign;
	}

	private String encodeToBase64(String toEncode) {
		byte[] encoded = Base64.getEncoder().encode(toEncode.getBytes());
		return new String(encoded);
	}

	private String signWithSHA256withRSA(String toEncode, String privateKeyPath) throws Exception {
		return this.encodeWithSHA256WithRSA(toEncode);
	}

	private String encodeWithSHA256WithRSA(String toEncode) throws Exception {

		byte[] privateKeyBytes;
		try (FileInputStream fis = new FileInputStream(PRIVATE_KEY_PATH)) {
			privateKeyBytes = fis.readAllBytes();
			PKCS8EncodedKeySpec encodedKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			Signature signature = Signature.getInstance("SHA256withRSA");
			signature.initSign(keyFactory.generatePrivate(encodedKeySpec));
			signature.update(toEncode.getBytes(StandardCharsets.UTF_8));
			byte[] sigBytes = signature.sign();
			return Base64.getEncoder().encodeToString(sigBytes);
		}
	}
}
