package md5Test;

import junit.framework.TestCase;
import md5.MD5;

import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.stream.Stream;

/**
 * Created by Matthew on 25.11.2016.
 */
public class md5Test extends TestCase {

    private String expected, data;

    public void setUp() {

        data = "TestString";

        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(data.getBytes(), 0, data.getBytes().length);
            this.expected = new BigInteger(1, digest.digest()).toString(16).toUpperCase();
            System.out.println("Standard hash: " + this.expected);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public void testMD5() {

        MD5 md5 = new MD5();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        md5.getHash(Stream.of(data), out);
        String actual = new String(out.toByteArray());
        System.out.println("testMD5 hash: " + actual);
    }
}
