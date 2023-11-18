import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Random;

public class RabinModel {

    private final Random random = new SecureRandom();
    private static final BigInteger TWO = BigInteger.valueOf(2);
    private static final BigInteger THREE = BigInteger.valueOf(3);
    private static final BigInteger FOUR = BigInteger.valueOf(4);

    private BigInteger privateKey1;
    private BigInteger privateKey2;
    private BigInteger publicKey;

    private static final String paddingText = "00000";

    public static String getPaddingText() {
        return paddingText;
    }

    public void generateKey(int bigLength) {

        privateKey1 = blumPrime(bigLength / 2);
        privateKey2 = blumPrime(bigLength / 2);

        publicKey = privateKey1.multiply(privateKey2);
    }

    public BigInteger encrypt(String plainText) {
        String plainTextPadded = paddingText + plainText;
        BigInteger plainTextInBig = new BigInteger(plainTextPadded.getBytes());

        return plainTextInBig.modPow(TWO, publicKey);
    }

    public  BigInteger[] decrypt(BigInteger c) {
        BigInteger N = privateKey1.multiply(privateKey2);
        BigInteger p1 = c.modPow(privateKey1.add(BigInteger.ONE).divide(FOUR), privateKey1);
        BigInteger p2 = privateKey1.subtract(p1);
        BigInteger q1 = c.modPow(privateKey2.add(BigInteger.ONE).divide(FOUR), privateKey2);
        BigInteger q2 = privateKey2.subtract(q1);

        BigInteger[] ext = Gcd(privateKey1, privateKey2);
        BigInteger y_p = ext[1];
        BigInteger y_q = ext[2];

        BigInteger d1 = y_p.multiply(privateKey1).multiply(q1).add(y_q.multiply(privateKey2).multiply(p1)).mod(N);
        BigInteger d2 = y_p.multiply(privateKey1).multiply(q2).add(y_q.multiply(privateKey2).multiply(p1)).mod(N);
        BigInteger d3 = y_p.multiply(privateKey1).multiply(q1).add(y_q.multiply(privateKey2).multiply(p2)).mod(N);
        BigInteger d4 = y_p.multiply(privateKey1).multiply(q2).add(y_q.multiply(privateKey2).multiply(p2)).mod(N);

        return new BigInteger[]{d1, d2, d3, d4};
    }

    public String mostProbable(BigInteger[] cipherTexts)
    {
        String finalMessage = "";
        int i = 1;
        for (BigInteger b : cipherTexts) {
            String dec = new String(b.toByteArray());
            if (dec.contains(paddingText)) {
                finalMessage = dec;
            }
            i++;
        }
        System.out.println("Most Probable " + finalMessage.substring(5));
        return finalMessage.substring(paddingText.length());
    }



    public  BigInteger[] Gcd(BigInteger a, BigInteger b) {
        BigInteger s = BigInteger.ZERO;
        BigInteger old_s = BigInteger.ONE;
        BigInteger t = BigInteger.ONE;
        BigInteger old_t = BigInteger.ZERO;
        BigInteger r = b;
        BigInteger old_r = a;
        while (!r.equals(BigInteger.ZERO)) {
            BigInteger q = old_r.divide(r);
            BigInteger tr = r;
            r = old_r.subtract(q.multiply(r));
            old_r = tr;

            BigInteger ts = s;
            s = old_s.subtract(q.multiply(s));
            old_s = ts;

            BigInteger tt = t;
            t = old_t.subtract(q.multiply(t));
            old_t = tt;
        }
        return new BigInteger[]{old_r, old_s, old_t};
    }


    public void setKeys(BigInteger p, BigInteger q) {
        privateKey1 = p;
        privateKey2 = q;
        publicKey = p.multiply(q);
    }

    public BigInteger blumPrime(int bigLength) {
        BigInteger p;
        do {
            p = BigInteger.probablePrime(bigLength, random);
        } while (!p.mod(FOUR).equals(THREE));
        return p;
    }

    public BigInteger getPrivateKey1() {
        return privateKey1;
    }

    public BigInteger getPrivateKey2() {
        return privateKey2;
    }

    public BigInteger getPublicKey() {
        return publicKey;
    }
}
