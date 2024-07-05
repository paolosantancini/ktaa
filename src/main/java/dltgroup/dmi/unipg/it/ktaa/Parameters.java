package dltgroup.dmi.unipg.it.ktaa;

public class Parameters {
    
    // unique istance
    private static Parameters instance = null;
    
    int p, q, r, h, mu, epsilon, ellerange;
    double g;
  
    Parameters() {
        
        // Public Parameters
        
        p = 23; // prime number
        q = 11; // order of G
        r = (p-1)/q; // factor of G
        h = 7; // any number s.t. ℎ^𝑟 not congruent with 1 mod p
        mu = 3;
        epsilon = 8;
        
        ellerange = 2; // any l is between [0,2^(mu+epsilon)]
        for (int i=2;i<=(mu+epsilon);i++) {
         ellerange = 2 * ellerange;
        }
        
        setG();
    
    }
    
    // create object if it doesn't exist
    public static synchronized Parameters getIstance() {
        if (instance == null) {
            instance = new Parameters();
        }
        return instance;
    }
    
    public int getElleRange() {
        return ellerange;
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
