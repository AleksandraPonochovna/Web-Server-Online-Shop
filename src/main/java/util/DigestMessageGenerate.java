package util;

import dao.impl.BasketDaoImpl;
import org.apache.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigestMessageGenerate {

    private static final Logger logger = Logger.getLogger(BasketDaoImpl.class);

    public static String sha256ToHex(String password) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
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

}
