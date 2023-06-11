package dltgroup.dmi.unipg.it.ktaa;

/**
 * DltGroup Unipg Bistarelli Stefano, Mercanti Ivan, Santancini Paolo, Santini
 * Francesco
 */

import java.math.BigInteger;

public class GroupManager {

    GroupManager() {
    }

    // SETUP phase
    public void setup() {
        // making rigid integer
        rigidInt();
    }

    /* Calculate ridid integer number [v=1024 (2v-bit = 2048) == 256 byte]
    Rigid int number can be factorized into two safe primes of equal length.
    Safe primes are prime numbers of the form p = 2q + 1, 
    where q is also a prime number.  
     */
    private void rigidInt() {
        MathK mt = new MathK();
        BigInteger rn = mt.getRigitNumber();
        
        System.out.println("Rigid Number: "+rn.toString()+" lenght: "+rn.toString().length());
        
    }
}
