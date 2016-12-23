package testDsRSA;

import junit.framework.TestCase;
import md5.MD5;
import dc.DigtlSigntrRSA;
import java.io.ByteArrayOutputStream;
import java.util.stream.Stream;

/**
 * Created by Matthew on 28.11.2016.
 */
public class TestDS extends TestCase {

    private String data;
    private Stream pubK, prvK, n;
    private byte[] bytesDS;

    public void setUp() {

        data = "TestString";
        DigtlSigntrRSA ds = new DigtlSigntrRSA(Stream.of(1024));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ds.encrypt(Stream.of(data), out);
        prvK = Stream.of(ds.getPrivateKey());
        pubK = Stream.of(ds.getPublicKey());
        n = Stream.of(ds.getN());
        bytesDS = out.toByteArray();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < bytesDS.length; i++)
        {
            sb.append(String.format("%X", bytesDS[i]));
        }

        System.out.println("Digital signature: " + sb);
    }

    public void testDigitalSignature(){

        MD5 md5 = new MD5();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        md5.getHash(Stream.of(data), out);
        String expected = new String(out.toByteArray());
        System.out.println("Hash of data: " + expected);

        DigtlSigntrRSA ds = new DigtlSigntrRSA(pubK, prvK, n);
        ByteArrayOutputStream outHashDS = new ByteArrayOutputStream();
        ds.decrypt(Stream.of(bytesDS), outHashDS);
        String actual = new String(outHashDS.toByteArray());
        System.out.println("Hash of Digital signature: " + actual);
        assertEquals(expected, actual);
    }
}
