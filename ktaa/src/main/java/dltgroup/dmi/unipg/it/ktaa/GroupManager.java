package dltgroup.dmi.unipg.it.ktaa;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.Random;

public class GroupManager {

    private static GroupManager instance = null;
    Parameters pm;
    Buffer my_buffer;
    Random rnd = new Random();

    // a1, a1_0 ... directly from Zn
    BigInteger a1, a1_0, b, a, a0;
    String rgm;

    GroupManager() throws UnsupportedEncodingException {
        System.out.println("GM - Setting parameters...");
        pm = Parameters.getIstance();
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
        // select random a', a0', b directly (at the moment)from Zn
        a1 = new BigInteger(128, rnd);
        a1_0 = new BigInteger(128, rnd);
        b = new BigInteger(128, rnd);

// calculating a, a0
        a = a1.pow(2);
        a0 = a1_0.pow(2);

// select random RGM string
        byte[] array = new byte[64];
        new Random().nextBytes(array);
        rgm = new String(array, "UTF-8");
        
        System.out.println("### GM PUBLIC KEY ###");
        System.out.println("a: "+a+"\na0: "+a0+"\nb: "+b+"\nrgm: "+rgm);
        
        my_buffer.setPubKey(pm.getN(), a, a0, b, rgm);
        
    }

    public int getX2(){
        return my_buffer.getRandomLambdaValue();
    }
    
}
