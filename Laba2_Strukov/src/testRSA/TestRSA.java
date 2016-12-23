package testRSA;

import junit.framework.TestCase;
import rsa.RSA;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.util.stream.Stream;

/**
 * Created by Matthew on 11.11.2016.
 */
public class TestRSA extends TestCase {

    private String data, expected;
    private Stream prvK, pubK, n;

    public void setUp() {

        data = "1";
        expected = "121";
        System.out.println("Test string: " + data);
        prvK = Stream.of(new BigInteger("11"));
        pubK = Stream.of(new BigInteger("491"));
        n = Stream.of(new BigInteger("589"));
    }

    public void testRSA(){

        RSA rsa = new RSA(prvK, pubK, n);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        rsa.encrypt(Stream.of(data), out);
        byte[] actual = out.toByteArray();

        StringBuilder sb = new StringBuilder();
        for (byte i : actual)
        {
            sb.append(String.format("%d", i));
        }

        System.out.print("Encrypt: " + sb);
        System.out.println();
        assertEquals(expected, sb.toString());

        ByteArrayOutputStream outD = new ByteArrayOutputStream();
        rsa.decrypt(Stream.of(actual), outD);
        String decr = new String(outD.toByteArray()).trim();

       System.out.println("Decrypt: " + decr);
        assertEquals(data, decr);
    }
}
