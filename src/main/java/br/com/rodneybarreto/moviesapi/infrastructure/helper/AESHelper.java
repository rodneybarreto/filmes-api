package br.com.rodneybarreto.moviesapi.infrastructure.helper;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;

@Component
@NoArgsConstructor
public class AESHelper {

    private static final String ALGORITHM = "AES";
    private static final String CIPHER_TRANSFORMATION = "AES/CBC/PKCS5Padding";
    private static final int KEY_LENGTH = 256;
    private static final int ITERATION_COUNT = 65536;
    private static final int IV_LENGTH_BYTES = 16; // AES block size

    // IMPORTANT: These should be stored securely outside the code,
    // e.g., in environment variables or a secrets manager.
    @Value("${app.secret.key}")
    private String secretKey;

    @Value("${app.secret.salt}")
    private String secretSalt;

    private SecretKey getSecretKey() throws GeneralSecurityException {
        if (secretKey == null || secretSalt == null) {
            throw new IllegalStateException("Secret key password and salt must be set as environment variables.");
        }
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), secretSalt.getBytes(), ITERATION_COUNT, KEY_LENGTH);
        return new SecretKeySpec(factory.generateSecret(spec).getEncoded(), ALGORITHM);
    }

    public String encrypt(String openValue) {
        try {
            SecretKey key = getSecretKey();
            Cipher cipher = Cipher.getInstance(CIPHER_TRANSFORMATION);

            // Generate a random IV
            byte[] iv = new byte[IV_LENGTH_BYTES];
            new SecureRandom().nextBytes(iv);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

            cipher.init(Cipher.ENCRYPT_MODE, key, ivParameterSpec);
            byte[] encryptedBytes = cipher.doFinal(openValue.getBytes(StandardCharsets.UTF_8));

            // Prepend IV to the ciphertext for use during decryption
            byte[] combined = new byte[iv.length + encryptedBytes.length];
            System.arraycopy(iv, 0, combined, 0, iv.length);
            System.arraycopy(encryptedBytes, 0, combined, iv.length, encryptedBytes.length);

            return Base64.getEncoder().encodeToString(combined);
        }
        catch (GeneralSecurityException e) {
            // Log the exception e for debugging
            throw new RuntimeException("Error while encrypting value", e);
        }
    }

    public String decrypt(String encryptedValue) {
        try {
            SecretKey key = getSecretKey();
            Cipher cipher = Cipher.getInstance(CIPHER_TRANSFORMATION);

            byte[] combined = Base64.getDecoder().decode(encryptedValue);

            // Extract IV from the beginning of the combined array
            byte[] iv = new byte[IV_LENGTH_BYTES];
            System.arraycopy(combined, 0, iv, 0, iv.length);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

            cipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);

            byte[] decryptedBytes = cipher.doFinal(combined, iv.length, combined.length - iv.length);
            return new String(decryptedBytes, StandardCharsets.UTF_8);
        }
        catch (GeneralSecurityException e) {
            // Log the exception e for debugging
            throw new RuntimeException("Error while decrypting value", e);
        }
    }

}
