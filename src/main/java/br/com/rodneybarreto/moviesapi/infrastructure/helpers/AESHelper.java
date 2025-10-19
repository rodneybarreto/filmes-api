package br.com.rodneybarreto.moviesapi.infrastructure.helpers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AESHelper {

    private static final String ALGORITHM = "AES";
    private static final String SECRET_KEY = "TheMatrix123TheBourneIdentity123";

    public static String encrypt(String openValue) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            return Base64.getEncoder().encodeToString(cipher.doFinal(openValue.getBytes()));
        }
        catch (Exception e) {
            throw new RuntimeException("Error while encrypting value");
        }
    }

    public static String decrypt(String encryptedValue) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedValue)));
        }
        catch (Exception e) {
            throw new RuntimeException("Error while decrypting value");
        }
    }

}
