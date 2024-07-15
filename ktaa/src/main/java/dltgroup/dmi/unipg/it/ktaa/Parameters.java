package dltgroup.dmi.unipg.it.ktaa;

import java.math.BigInteger;
import java.util.Random;

public class Parameters {

    // unique istance
    private static Parameters instance = null;

    BigInteger p1, p2, n; // (p1,p2) GM secret key pair
    int lambda_max = 2^16;

    Parameters() {

        getRigidNumber();

    }

    // create object if it doesn't exist
    public static synchronized Parameters getIstance() {
        if (instance == null) {
            instance = new Parameters();
        }
        return instance;
    }

    private void getRigidNumber() {
        Random rnd = new Random();
        BigInteger temp = BigInteger.ZERO;

        // set p1
        while (isprime(temp) == false) {
            p1 = BigInteger.probablePrime(128, rnd);
            temp = p1.subtract(BigInteger.ONE);
            temp = temp.divide(BigInteger.TWO);
        }

        // set p2
        temp = BigInteger.ZERO;
        while (isprime(temp) == false) {
            p2 = BigInteger.probablePrime(128, rnd);
            temp = p2.subtract(BigInteger.ONE);
            temp = temp.divide(BigInteger.TWO);
        }
        
        // set n
        n = p1.multiply(p2);
        
        System.out.println("p1: "+p1);
        System.out.println("p2: "+p2);
        System.out.println("n: "+n);

    }

    private boolean isprime(BigInteger p) {

        return p.isProbablePrime(1); // 50% of tolerance
        
    }
    
    public BigInteger getN() {
        return n;
    }

}
