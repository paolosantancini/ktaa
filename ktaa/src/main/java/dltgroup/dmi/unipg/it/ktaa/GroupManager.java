package dltgroup.dmi.unipg.it.ktaa;

/**
 * DltGroup Unipg Bistarelli Stefano, Mercanti Ivan, Santancini Paolo, Santini
 * Francesco
 */

import java.math.BigInteger;

public class GroupManager {

    MathK mt;
    
    GroupManager() {
        mt = new MathK();
    }

    // SETUP phase
    public void setup() {
        // making rigid integer
        groupkey();
    }

    /* Calculate ridid integer number [v=1024 (2v-bit = 2048) == 256 byte]
    Rigid int number can be factorized into two safe primes of equal length.
    Safe primes are prime numbers of the form p = 2q + 1, 
    where q is also a prime number.  
     */
    private void groupkey() {
        
        String random_string;
        
        // Get rigid number
        BigInteger rn = mt.getRigitNumber();
        BigInteger[] qr = null;
               
        // Get random string
        random_string = mt.getRandomString();
        
        qr = mt.getQR();

        // n, Rgm, a, a0, b
        System.out.println(rn.toString()+" - "+random_string+" - "+qr[0]+" - "+qr[1]+" - "+qr[2]);
        
    }
}
