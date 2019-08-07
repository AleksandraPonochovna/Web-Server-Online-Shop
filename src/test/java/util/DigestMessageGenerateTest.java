
package util;

import org.junit.Assert;
import org.junit.Test;

public class DigestMessageGenerateTest extends DigestMessageGenerate {

   @Test
   public void encryptSha256AndSalt1() {
      String password = "admin";
      String salt = "123456";
      String actualPassword = DigestMessageGenerate.encryptSha256AndSalt(password, salt);
      String expectedResult = DigestMessageGenerate.encryptSha256(salt + password);
      Assert.assertEquals(expectedResult, actualPassword);
   }

   private static String encryptSha256(String password) {
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

}
