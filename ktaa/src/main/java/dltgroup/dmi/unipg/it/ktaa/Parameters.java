package dltgroup.dmi.unipg.it.ktaa;

import java.math.BigInteger;
import java.util.Random;

public class Parameters {

    // unique istance
    private static Parameters instance = null;

    Random rnd = new Random();
    // (p1,p2) GM secret key pair
    // n rigid integer number
    // p (Zp) module of G group
    BigInteger p, p1, p2, n; 
    BigInteger[] groupg; // elements of G
    int tau_min;
    int lambda_max = tau_min = (int) Math.pow(2, 16); // max number of elements of x set (2^lambda)
    // max number of elements of e set (2^tau+2^lambda)
    // in this case lambda = tau
    int tau_max = lambda_max*2; 

    Parameters() {

        getRigidNumber();
        createGroupG();

    }

    // create object if it doesn't exist
    public static synchronized Parameters getIstance() {
        if (instance == null) {
            instance = new Parameters();
        }
        return instance;
    }

    private void getRigidNumber() {
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

        System.out.println("p1: " + p1);
        System.out.println("p2: " + p2);
        System.out.println("n: " + n);

    }

    // Creating multiplicative group module p
    private void createGroupG() {
        p = BigInteger.ZERO;
        BigInteger p_1, q = BigInteger.ZERO, g, h = new BigInteger(2,rnd);
        int r = 2; // secondth factor

        System.out.print("Creating group G with ");

        while (!q.isProbablePrime(1)) {
            p = BigInteger.probablePrime(8, rnd);
            p_1 = p.subtract(BigInteger.ONE);
            q = p_1.divide(BigInteger.TWO);
        }
        // generator of G
        g =h.pow(r).mod(p);

        groupg = new BigInteger[q.intValue()];

        for (int i = 0; i < q.intValue(); i++) {
            groupg[i] =g.pow(i).mod(p);
        }

        System.out.println(groupg.length + " elements (p=" + p + ", q=" + q + ", g="+g+", h="+h+")");

    }

    private boolean isprime(BigInteger p) {

        return p.isProbablePrime(1); // 50% of tolerance

    }

    public BigInteger getN() {
        return n;
    }

}
