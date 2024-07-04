package dltgroup.dmi.unipg.it.ktaa;

public class Parameters {
    
    int p, q, r, h;
    double g;
  
    Parameters() {
        
        // Public Parameters
        
        p = 23; // prime number
        q = 11; // order of G
        r = (p-1)/q; // factor of G
        h = 7; // any number s.t. ℎ^𝑟 not congruent with 1 mod p
        setG();
    
    }
    
    public int getP() {
        return p;
    }
    
    public int getQ() {
        return q;
    }
    
    private void setG() {
        // g = (h^r) mod p
        g = Math.pow(h, r) % p;
    }
    
    public double getG() {
        return g;
    }
    
    public int getR() {
        return r;
    }
    
}
