package dltgroup.dmi.unipg.it.ktaa;

/**
 * DltGroup Unipg Bistarelli Stefano, Mercanti Ivan, Santancini Paolo, Santini
 * Francesco
 */
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

public class MathK {

    BigInteger p,q;
    
    MathK() {
    }

    // Generating rigid number from two safe prime numbers
    public BigInteger getRigitNumber() {

        int BIT_LENGTH = 512;
        BigInteger subtracting = new BigInteger("1");
        BigInteger denominator = new BigInteger("2");
        BigInteger safe_temp;
        Random rand = new SecureRandom();
        Boolean flag = false;
        p= new BigInteger("0");
        q= new BigInteger("0");
        BigInteger rn;      
        
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
    
    /*
    Select a random number "h" between 2 and p-1.
    Compute: h^((p-1)/q) mod p. If this value is not 1 then it is generator.
    We assume that "b"="g". Element "b" from 3.3 of the scientific paper.
    */
    public BigInteger getGenerator() {
        
        BigInteger g = new BigInteger("0");
        
        // DO SOMETHING
        
        return g;
    }
    
    /*
    Get a,a0 as quadratic residue of rigid integer (rn)
    */
    public BigInteger getQR() {
        
        BigInteger a = new BigInteger("0");
        
        // DO SOMETHING
        
        return a;
    }

}
