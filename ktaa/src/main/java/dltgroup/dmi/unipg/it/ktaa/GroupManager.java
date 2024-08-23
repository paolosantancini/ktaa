package dltgroup.dmi.unipg.it.ktaa;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.Random;

public class GroupManager {

    private static GroupManager instance = null;
    Sys pm;
    Buffer my_buffer;
    Random rnd = new Random();

    // a1, a1_0 ... directly from Zn
    BigInteger a1, a1_0, b, a, a0, ae;
    String rgm;
    int e;

    GroupManager() throws UnsupportedEncodingException {
        System.out.println("GM - Setting parameters...");
        pm = Sys.getIstance();
        my_buffer = Buffer.getIstance();
        // set pub key (n, RGM, a, a0, b)
        setPubKey();
    }

    public static synchronized GroupManager getIstance() throws UnsupportedEncodingException {
        if (instance == null) {
            instance = new GroupManager();
        }
        return instance;
    }

    private void setPubKey() throws UnsupportedEncodingException {
        // selected random directly from Zn
        a1 = new BigInteger(128, rnd);
        a1_0 = new BigInteger(128, rnd);
        // selected by G
        b = pm.groupg[rnd.nextInt(pm.groupg.length)];

// calculating a, a0
        a = a1.pow(2);
        a0 = a1_0.pow(2);

// select random RGM string
        byte[] array = new byte[64];
        new Random().nextBytes(array);
        rgm = new String(array, "UTF-8");

        System.out.println("### GM PUBLIC KEY ###");
        System.out.println("a: " + a + "\na0: " + a0 + "\nb: " + b + "\nrgm: " + rgm);

        my_buffer.setPubKey(pm.getN(), a, a0, b, rgm);

    }

    public int getX2() {
        return my_buffer.getRandomLambdaValue();
    }

    public void setE() {
        for (int i = pm.gamma_min; i < pm.gamma_max; i++) {
            if (my_buffer.isPrime(my_buffer.gamma_set[i])) {
                e = my_buffer.gamma_set[i];
                // delete used element
                my_buffer.gamma_set[i] = 0;
                // force exit
                i = pm.gamma_max;
            }
        }
        System.out.println("GM - calculating e: "+e);
    }
    
    public void calculateA() {
        // A^e = (alpha * a0) mod n
        ae = my_buffer.alpha.multiply(my_buffer.a0).mod(my_buffer.n);
        System.out.println("GM - calculating A^e: "+ae);
    }

}
