package dltgroup.dmi.unipg.it.ktaa;

import java.math.BigInteger;

public class User {
    
    Parameters pm = Parameters.getIstance();
    Buffer my_buffer = Buffer.getIstance();
    GroupManager gm = new GroupManager();

    int x_prime, z, c, x_second, prime_key, beta;
    double u; 
    BigInteger tau;
    BigInteger tau_prime;

    User() {
        x_prime = 4; // "w"
        //beta = Math.pow(pm.getG(), x_prime); // "h=g^w"
        beta = 1;
        for (int i=1;i<=(x_prime);i++) {
         beta = beta * pm.getG();
        }
    }

    public void sendU2GM() {
        u = Math.pow(pm.getG(), pm.getR());
        System.out.println("User sends u to GM: " + u);
        gm.receiveU(u);
    }
    
    public void sendBeta2GM() {
        beta = 1; // any l is between [0,2^(mu+epsilon)]
        for (int i=1;i<=x_prime;i++) {
         beta = beta * pm.getG();
        }
        System.out.println("User sends beta to GM: " + beta);
        gm.receiveBeta(beta);
        // requesting c to GM
        c = gm.getC();
    }
    
    public boolean sendZ2GM() {
        z = pm.getR() + x_prime * c;
        System.out.println("User sendZ2GM: " + z);
        gm.receiveZ(z);
        if (gm.getStatus()) {
            x_second = gm.getX2();
            prime_key = (x_prime+x_second) % pm.getP();
            System.out.println("U prime key: "+prime_key);
        }
        return(gm.getStatus());
    }
    
    // Request a search tag and calculate tau
    // Request a tracing tag and calculate tau_prime
    public void doRequest() {
        BigInteger s_tag = BigInteger.valueOf(my_buffer.getSearchTag());
        BigInteger t_tag = BigInteger.valueOf(my_buffer.getTracingTag());
        BigInteger beta_ = BigInteger.valueOf(beta);
        BigInteger expo = beta_.pow(my_buffer.getElle());        


        tau =  s_tag.pow(prime_key);
        tau_prime =  t_tag.pow(prime_key).multiply(expo);
        
        System.out.println("Beta: "+beta+"\nPrime_key: "+prime_key+"\ns_tag: "+s_tag+"\nt_tag: "+t_tag+"\nTau: "+tau+"\nTau_prime: "+tau_prime);
    }
}
