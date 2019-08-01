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

}