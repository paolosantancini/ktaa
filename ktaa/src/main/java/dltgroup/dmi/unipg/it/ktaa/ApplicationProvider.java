package dltgroup.dmi.unipg.it.ktaa;

import java.math.BigInteger;
import java.util.Random;

public class ApplicationProvider {

    Buffer my_buffer;
    Parameters pm;
    Random rnd = new Random();
    BigInteger[] searching_t, tracing_t;
    // max number of requests per user member
    int k = 3;

    ApplicationProvider() {
        my_buffer = Buffer.getIstance();
        my_buffer.k = k;
        pm = Parameters.getIstance();
        searching_t = new BigInteger[k];
        tracing_t = new BigInteger[k];
        setT();
        setL();
    }

    // Setting searching and tracing tag from Zn
    private void setT() {
        System.out.println("AP - Creating searching and tracing tags...");
        for (int i = 0; i < k; i++) {
            // searching tags
            searching_t[i] = pm.groupg[rnd.nextInt(pm.groupg.length)];
            // tracing tags
            tracing_t[i] = pm.groupg[rnd.nextInt(pm.groupg.length)];
        }
        my_buffer.searching_t = searching_t;
        my_buffer.tracing_t = tracing_t;
    }
    
    // set [0,2^(mu+epsilon)]
    private void setL(){
        for (int i = 0; i < pm.max_l; i++) {
            my_buffer.l_set[i] = i;
        }
    }

}
