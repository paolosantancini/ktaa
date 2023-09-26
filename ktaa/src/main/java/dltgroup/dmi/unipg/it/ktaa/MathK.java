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
    int LAMBDA, TAU, K, EPSILON, MU;
    Random rand = new SecureRandom();
    Buffer bf;

    MathK() {
        BIT_LENGTH = 8; // 512
        LAMBDA = TAU = 4; // 8
        K = EPSILON = MU = 16; // 160
        bf = Buffer.getIstance();
        bf.setMaxLambda((int) Math.pow(2, LAMBDA));
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
        // a* 20 chars lenght and b 44 chars lenght        
        a_1 = new BigInteger(myhash.substring(0, 20), 16);
        a0_1 = new BigInteger(myhash.substring(20, 39), 16);
        b = new BigInteger(myhash.substring(39), 16);

        a = a_1.modPow(BigInteger.TWO, rn);
        a0 = a0_1.modPow(BigInteger.TWO, rn);

        BigInteger[] values = {a, a0, b};

        return values;

    }

    // return an integer of lambda group (0,2^lambda)
    public int getX() {
        int myrand = 0;

        while (myrand < 10) {
            myrand = rand.nextInt((int) Math.pow(2, LAMBDA));
        }

        return (myrand);
    }

    // return 'e' prime number as integer between (2^tau,2^tau+2^lambda)
    public int getE() {
        int myval = 0;
        boolean flag = false;
        int maxval = (int) Math.pow(2, TAU) + (int) Math.pow(2, LAMBDA);

        while (flag == false) {
            // max_lambda < e < max_tau_lambda
            while (myval < bf.getMaxLambda()) {
                myval = (rand.nextInt(maxval));
            }
            // verify 'e' prime number
            if ((Math.pow(2, myval) % myval) == (2 % myval)) {
                flag = true;
            } else {
                myval = 0;
            }
        }

        return myval;

    }

     // A = (alpha * a0)^(1/e) mod n
    // generate A as the number that elevated to "e" 
    // is equal to (alpha*a0) mod n
    public BigInteger getA() {
        BigInteger A = BigInteger.ZERO, image;
        Boolean flag = false;

        // element of codomain
        image = (bf.getAlpha().multiply(bf.getA0())).mod(bf.getRigidNumber());
        
        // searching for A as the number that elevated to "e" is equal to "image"
        while(flag == false) {
            A = A.add(BigInteger.ONE);
            if (A.pow(bf.getE()) == image) { flag = true; }
            System.out.println("A: "+A+" e: "+bf.getE()+" img: "+ image);
        }
        
        return (A);

    }

    // proof for commit C
    public boolean verifyX(int value) {

        boolean flag = false;

        if (value < (bf.getMaxLambda())) {
            flag = true;
        }

        return (flag);
    }
    
    // return AP l as value between 0 and 2^(mu+epsilon)
    public int getl() {
               
        return(rand.nextInt(0, (int) Math.pow(2,MU + EPSILON)));
    }

}
