package dltgroup.dmi.unipg.it.ktaa;

import java.math.BigInteger;
import java.util.Random;

public class ApplicationProvider {

    Buffer my_buffer;
    Sys pm;
    Random rnd = new Random();
    BigInteger[] searching_t, tracing_t;

    ApplicationProvider() {
        my_buffer = Buffer.getIstance();
        pm = Sys.getIstance();
        searching_t = new BigInteger[pm.k];
        tracing_t = new BigInteger[pm.k];
        setT();
        setLset();
    }

    // Setting searching and tracing tag from Zp
    private void setT() {
        System.out.println("AP - Creating searching and tracing tags...");
        for (int i = 0; i < pm.k; i++) {
            // searching tags
            searching_t[i] = pm.groupg[rnd.nextInt(pm.groupg.length)];
            // tracing tags
            tracing_t[i] = pm.groupg[rnd.nextInt(pm.groupg.length)];
        }
        my_buffer.searching_t = searching_t;
        my_buffer.tracing_t = tracing_t;
    }

    // set [0,2^(mu+epsilon)]
    private void setLset() {
        for (int i = 0; i < pm.max_l; i++) {
            my_buffer.l_set[i] = i;
        }
    }

    // get service by k-time user request and user personal counter
    public boolean getService(BigInteger[] t, int c) {
        boolean flag = true;

        // checking used searching tag
        for (int i = 0; i < pm.k; i++) {
            if (my_buffer.tag[i] != null) {
                if (my_buffer.tag[i].equals(t[0])) {
                    flag = false;
                }
            }
        }
        if (flag) {
            my_buffer.tag[c] = t[0];
            my_buffer.tag1[c] = t[1];
            System.out.println("User " + my_buffer.beta + " access granted!");
        } else {
            System.out.println("User searching tag " + t[0] + " used... access denied!");
            /*
            IN THIS CASE WE CONSIDER 1 USER PER 1 (k-time) SERVICE REQUEST. IN REALITY WE SHOULD 
            CONSIDER MULTI-USER REQUESTS AND AN IDENTIFICATION OF MALICIOUS USERS BY VERIFYNG
            (T1' / T2')^(1/(L-L')) WITH T1' AND T2' TRACING TAG
             */
        }

        return flag;
    }

}
