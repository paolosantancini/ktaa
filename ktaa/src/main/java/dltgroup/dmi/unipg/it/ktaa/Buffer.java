package dltgroup.dmi.unipg.it.ktaa;

import java.math.BigInteger;
import java.util.Random;

public class Buffer {

    // unique istance
    private static Buffer instance = null;
    Random rnd;
    Sys pm;
    BigInteger n, a, a0, b, alpha, beta;
    BigInteger[] searching_t, tracing_t, tag, tag1;
    String rgm;
    int lambda_max, gamma_max, gamma_min;
    int[] lambda_set, gamma_set, l_set;
    
    // constructor
    public Buffer() {
        pm = Sys.getIstance();
        rnd = new Random();
        lambda_max = pm.lambda_max;
        lambda_set = new int[lambda_max]; // (0,2^lambda)
        l_set = new int[pm.max_l]; // (0,2^(mu+epsilon))
        gamma_max = pm.gamma_max;
        gamma_min = pm.gamma_min;
        gamma_set = new int[gamma_max]; // (2^lambda,2^tau+2^lambda)
        tag = new BigInteger[pm.k];
        tag1 = new BigInteger[pm.k];
        setLambdaSet();
        setGammaSet();
    }

    // create object if it doesn't exist
    public static synchronized Buffer getIstance() {
        if (instance == null) {
            instance = new Buffer();
        }
        return instance;
    }

    // SET GM PUBKEY
    public void setPubKey(BigInteger n, BigInteger a, BigInteger a0, BigInteger b, String rgm) {
        this.a = a;
        this.a0 = a0;
        this.n = n;
        this.rgm = rgm;
        this.b = b;
    }
    
    private void setLambdaSet() {
        for (int i=0;i<lambda_max;i++) {
            lambda_set[i] = i;
        }
    }
    
    private void setGammaSet() {
        for (int i=gamma_min;i<gamma_max;i++) {
            gamma_set[i] = i;
        }
    }
    
    public int getRandomLambdaValue(){
        return lambda_set[rnd.nextInt(0, lambda_max)];
    }
    
    public void setAlpha(BigInteger value) {
        alpha = value;
    }
    
    public void setBeta(BigInteger value) {
        beta = value;
        // Beta is public and should be attached on an user index
        // (in this case we have just 1 trivial user
    }

    public boolean isPrime(int num)
    {
        if(num<=1)
        {
            return false;
        }
       for(int i=2;i<=num/2;i++)
       {
           if((num%i)==0)
               return  false;
       }
       return true;
    }
    
    // i=0 searching tag, i=1 tracing tag
    public BigInteger getT(int i) {
        if (i == 0) {
         return searching_t[rnd.nextInt(pm.k)];   
        } else {
         return tracing_t[rnd.nextInt(pm.k)];   
        }
    }
    
}
