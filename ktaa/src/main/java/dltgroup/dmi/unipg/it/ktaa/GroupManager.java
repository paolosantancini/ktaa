package dltgroup.dmi.unipg.it.ktaa;

/**
 * DltGroup Unipg Bistarelli Stefano, Mercanti Ivan, Santancini Paolo, Santini
 * Francesco
 */
import java.math.BigInteger;

public class GroupManager {

    MathK mt; 
    Buffer bf;

    GroupManager() {
        mt = new MathK();
        bf = Buffer.getIstance();
    }

    // SETUP phase
    public void setup() {
        // making rigid integer
        grouppubkey();
    }

    /* Calculate ridid integer number [v=1024 (2v-bit = 2048) == 256 byte]
    Rigid int number can be factorized into two safe primes of equal length.
    Safe primes are prime numbers of the form p = 2q + 1, 
    where q is also a prime number.  
     */
    private void grouppubkey() {

        String random_string;

        // Get rigid number
        BigInteger rn = mt.getRigitNumber();
        BigInteger[] qr = null;

        // Get random string
        random_string = mt.getRandomString();
        bf.setRgm(random_string);

        qr = mt.getQR();
        bf.setA(qr[0]);
        bf.setA0(qr[1]);
        bf.setB(qr[2]);
        bf.setRn(rn);
        bf.setRgm(random_string);       

    }

    public boolean verifyCommitC() {
        boolean flag = false;
        
        if (mt.verifyX(bf.x1)) {
            int x2 = mt.getX();
            // set x2 to buffer
            bf.setX2(x2);
            flag = true;
        }
        
        return (flag);
        
    }
    
    // proof for beta: b^[x1+x2 mod (2^LAMBDA)]
        public boolean verifyBeta() {
        boolean flag = false;
        
        int x = (bf.getX1()+bf.getX2())%bf.getMaxLambda();
        
        if (bf.getB().pow(x).equals(bf.idlist[1])) {
            flag = true;
        }
        
        return (flag);
        
    }
        
    // generate 'e' prime number as (2^tau,2^tau+2^lambda)
    public void createE(){
        bf.setE(mt.getE());
    }
    
     // A = (alpha * a0)^(1/e) mod n
    // generate A as the number that elevated to "e" 
    // is equal to (alpha*a0) mod n
    public void createA(){
        bf.setA_(mt.getA());
    }

}
