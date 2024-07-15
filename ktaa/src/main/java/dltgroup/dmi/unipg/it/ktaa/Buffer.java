package dltgroup.dmi.unipg.it.ktaa;

import java.math.BigInteger;
import java.util.Random;

public class Buffer {

    // unique istance
    private static Buffer instance = null;
    Random rnd;
    Parameters pm;
    BigInteger n, a, a0, b;
    String rgm;
    int lambda_max;
    int[] lambda_set;

    // constructor
    public Buffer() {
        pm = Parameters.getIstance();
        rnd = new Random();
        lambda_max = pm.lambda_max;
        lambda_set = new int[lambda_max]; // (0,2^lambda)
        setLambdaSet();
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
    
    public int getRandomLambdaValue(){
        return lambda_set[rnd.nextInt(0, lambda_max)];
    }

}
