package TestRC5;

import RC5.RC5Algorithm;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Created by Matthew on 29.11.2016.
 */
public class TestRC5 extends TestCase {

    private Stream stream;
    private byte[] expected;
    private String data;

    public void setUp() {

        this.data = "12";
        this.expected = new byte[] {-109, -75, 8, -1, 28, -68, -24, 46, -40, -49, 22, -18, 115, -27, -69, -10};
        System.out.println("Test data: " + data);
        TestRC5 testRC5 = new TestRC5();
        this.stream = Stream.of(data);
    }

    public void testRC5() {

        Stream keyWR = Arrays.stream(new String[]{"1", "64", "1"});
        RC5Algorithm rc5 = new RC5Algorithm(keyWR);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        rc5.encrypt(stream, out);

        byte[] actual = out.toByteArray();

        System.out.print("Encrypt: ");
        for (byte i : actual)
        {
            System.out.print(i + " ");
        }

        System.out.println();
        assertEquals(new String(expected), new String(actual));

        ByteArrayOutputStream outD = new ByteArrayOutputStream();
        rc5.decrypt(Stream.of(actual), outD);
        String decr = new String(outD.toByteArray()).trim();

        System.out.println("Decrypt: " + decr);
        assertEquals(data, decr);

    }




}
