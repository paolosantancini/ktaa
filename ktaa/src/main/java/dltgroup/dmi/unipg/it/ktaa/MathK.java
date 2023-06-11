package dltgroup.dmi.unipg.it.ktaa;

/**
 * DltGroup Unipg Bistarelli Stefano, Mercanti Ivan, Santancini Paolo, Santini
 * Francesco
 */
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

public class MathK {

    MathK() {
    }

    // Fermat Theorem
    public BigInteger getRigitNumber() {

        int BIT_LENGTH = 2048;
        Random rand = new SecureRandom();
        BigInteger p = BigInteger.probablePrime(BIT_LENGTH / 2, rand);
        BigInteger q = BigInteger.probablePrime(BIT_LENGTH / 2, rand);
        BigInteger rn = p.multiply(q);

        return rn;
    }

}
