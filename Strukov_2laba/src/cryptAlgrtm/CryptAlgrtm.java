package cryptAlgrtm;


import cryptAlg.DigtlSigntrRSA;
import cryptAlg.MD5;
import cryptAlg.RC5Algorithm;
import cryptAlg.RSA;

import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Created by Matthew on 22.12.2016.
 */
public class CryptAlgrtm {

    public String runRC5(String text, String R, String key) {

        Stream keyWR = Arrays.stream(new String[]{key, "64", R});
        RC5Algorithm rc5 = new RC5Algorithm(keyWR);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        rc5.encrypt(Stream.of(text), out);
        byte[] actual = out.toByteArray();

        ByteBuffer hash = ByteBuffer.allocate(actual.length).order(ByteOrder.LITTLE_ENDIAN);
        hash.put(actual);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < hash.array().length; i++)
        {
            sb.append(String.format("%02X", hash.array()[i] & 0xFF));
        }

        return sb.toString();
    }

    public String runRSA(String pubK, String prvK, String lenKey, String mod, String data) {

        RSA rsa = null;

        if (lenKey != "")
        {
            rsa = new RSA(Stream.of(Integer.parseInt(lenKey)));
        }
        else if (pubK != "" && lenKey != "")
        {
            rsa = new RSA(Stream.of(new BigInteger(pubK)), Stream.of(Integer.parseInt(lenKey)));
        }
        else if(pubK != "" && prvK != "" && mod != "")
        {
            rsa = new RSA(Stream.of(new BigInteger(pubK)), Stream.of(new BigInteger(prvK)), Stream.of(new BigInteger(mod)));
        }
        else
        {
            return  "Pashalka)0)0)";
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        rsa.encrypt(Stream.of(data), out);
        byte[] actual = out.toByteArray();

        ByteBuffer hash = ByteBuffer.allocate(actual.length).order(ByteOrder.LITTLE_ENDIAN);
        hash.put(actual);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < hash.array().length; i++)
        {
            sb.append(String.format("%02X", hash.array()[i] & 0xFF));
        }
        return sb.toString();
    }

    public String runMD5(String data) {

        MD5 md5 = new MD5();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        md5.getHash(Stream.of(data), out);

        return new String(out.toByteArray());
    }

    public String runDS(String data) {

        DigtlSigntrRSA ds = new DigtlSigntrRSA(Stream.of(1024));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ds.encrypt(Stream.of(data), out);

        byte[] actual = out.toByteArray();

        ByteBuffer hash = ByteBuffer.allocate(actual.length).order(ByteOrder.LITTLE_ENDIAN);
        hash.put(actual);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < hash.array().length; i++)
        {
            sb.append(String.format("%02X", hash.array()[i] & 0xFF));
        }
        return sb.toString();
    }
}
