package util;

import dao.impl.BasketDaoImpl;
import org.apache.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

public class DigestMessageGenerate {

    private static final Logger logger = Logger.getLogger(BasketDaoImpl.class);

    public static String encryptSha256AndSalt(String password, String salt) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            digest.update(salt.getBytes(StandardCharsets.UTF_8));
            byte[] encodedHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(encodedHash);
        } catch (NoSuchAlgorithmException e) {
            logger.error("Message Digest don't have an instance.", e);
        }
        return null;
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder();
        for (byte oneByte : hash) {
            String hex = Integer.toHexString(0xff & oneByte);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[6];
        random.nextBytes(bytes);
        return Arrays.toString(bytes);
    }

}
