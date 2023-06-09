package dltgroup.dmi.unipg.it.ktaa;

/**
 * DltGroup Unipg
 * Bistarelli Stefano, Mercanti Ivan, Santancini Paolo, Santini Francesco
 */

public class MathK {
    
    boolean flag;
        
    
    MathK(){
        flag = false;
    }
    
    // Fermat Theorem
    public Boolean primeVerify(int n) {
        
        flag = false;
        if (((Math.pow(2,n))%n) == (2%n)) { flag = true; }
        
        return flag;
    }
    
}
