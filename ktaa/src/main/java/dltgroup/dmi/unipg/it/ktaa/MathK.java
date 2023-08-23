package dltgroup.dmi.unipg.it.ktaa;

/**
 * DltGroup Unipg Bistarelli Stefano, Mercanti Ivan, Santancini Paolo, Santini
 * Francesco
 */
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class MathK {

    BigInteger p, q;
    int BIT_LENGTH; // parameter "v"
    BigInteger rn;
    StringBuilder sb;
    
    /* Security Parameters
    lambda = 2v+k+epsilon
    gamma = lambda+mu+epsilon+8
    
    v=1024
    epsilon=k=mu=160
    */
    int lambda, gamma;
    int k,epsilon,mu = 160;

    MathK() {
        BIT_LENGTH = 512;
        lambda = 2*BIT_LENGTH+k+epsilon;
        gamma = lambda+mu+epsilon+8;
    }

    /* Generating rigid number from two safe prime numbers we call 
    prime p a safe prime if (p − 1)/2 is also a prime number. 
    We call n a rigid integer if natural number n can be factorized 
    into two safe primes of equal length
     */
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

    /* Generating random string "R" */
    public String getRandomString() {

        int n = 64;

        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        sb = new StringBuilder(n);

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

        return (sb.toString());
    }

    /* Hashing function of random string "R" */
    private static String sha256(final String base) {
        try {
            final MessageDigest digest = MessageDigest.getInstance("SHA-256");
            final byte[] hash = digest.digest(base.getBytes("UTF-8"));
            final StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < hash.length; i++) {
                final String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }

    /*    Get a,a0,b     
    a=a’^2 mod n
    a0=a0’^2 mod n
    b=element of elliptic curve
     */
    public BigInteger[] getQR() {

        BigInteger a, a0, a_1, a0_1, b;

        String myhash = sha256(sb.toString());

        // Converting from String to BidInteger with radix eq 16
        // a* 10 chars lenght and b 44 chars lenght
        a_1 = new BigInteger(myhash.substring(0, 10), 16);
        a0_1 = new BigInteger(myhash.substring(10, 19), 16);
        b = new BigInteger(myhash.substring(19), 16);

        a = a_1.modPow(BigInteger.TWO, rn);
        a0 = a0_1.modPow(BigInteger.TWO, rn);
        
        BigInteger[] values = { a, a0, b };
                
        return values;
        
    }

}
