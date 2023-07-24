package dltgroup.dmi.unipg.it.ktaa;

/**
 * DltGroup Unipg Bistarelli Stefano, Mercanti Ivan, Santancini Paolo, Santini
 * Francesco
 */
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

public class MathK {

    BigInteger p, q;
    int BIT_LENGTH;
    BigInteger rn;

    MathK() {
        BIT_LENGTH = 512;
    }

    // Generating rigid number from two safe prime numbers
    public BigInteger getRigitNumber() {

        BigInteger subtracting = new BigInteger("1");
        BigInteger denominator = new BigInteger("2");
        BigInteger safe_temp;
        Random rand = new SecureRandom();
        Boolean flag = false;
        p = new BigInteger("0");
        q = new BigInteger("0");

        while (flag == false) {
            p = BigInteger.probablePrime(BIT_LENGTH / 2, rand);
            safe_temp = p.subtract(subtracting);
            safe_temp = safe_temp.divide(denominator);
            flag = safe_temp.isProbablePrime(BIT_LENGTH / 2);
        }

        flag = false;
        while (flag == false) {
            q = BigInteger.probablePrime(BIT_LENGTH / 2, rand);
            safe_temp = q.subtract(subtracting);
            safe_temp = safe_temp.divide(denominator);
            flag = safe_temp.isProbablePrime(BIT_LENGTH / 2);
        }

        rn = p.multiply(q);

        return rn;
    }

    private long getRandomString() {

        int n = 64;

        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return Math.abs(sb.toString().hashCode());
    }

    /*
    Get a,a0,b
     */
    public void getQR() {

        for (int i = 0; i < 10; i++) {
            System.out.println(getRandomString());
        }

    }

}
