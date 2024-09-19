package dltgroup.dmi.unipg.it.ktaa;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.Random;

public class User {

    Sys pm;
    Buffer my_buffer;
    GroupManager gm;
    Random rnd;

    int x, x1, x2, w;

    User() throws UnsupportedEncodingException {
        gm = GroupManager.getIstance();
        pm = Sys.getIstance();
        my_buffer = Buffer.getIstance();
        rnd = new Random();
        w = 0; // internal counter
        setPrimaryKey();
    }

    private void setPrimaryKey() {
        x1 = my_buffer.getRandomLambdaValue();
        x2 = gm.getX2();
        x = (x1 + x2) % pm.lambda_max;
        System.out.println("User - Calculating primary key (x): " + x);
    }

    public void calculateAlpha() {
        // a^x mod n
        my_buffer.setAlpha((my_buffer.a.pow(x).mod(my_buffer.n)));
        System.out.println("User - calculating Alpha: " + my_buffer.alpha);
    }

    public void calculateBeta() {
        // b^x mod p
        my_buffer.setBeta(my_buffer.b.pow(x).mod(pm.p));
        System.out.println("User - calculating Beta: " + my_buffer.beta + " with p=" + pm.p + " b=" + my_buffer.b + " x=" + x);
    }

    public BigInteger[] calculateT_T1() {

        int l = my_buffer.l_set[rnd.nextInt(pm.max_l)];

        BigInteger[] t = new BigInteger[2];

        t[0] = my_buffer.getT(0).pow(x).mod(pm.p); // T
        t[1] = my_buffer.getT(1).multiply(my_buffer.b.pow(l)).mod(pm.p); // T'

        System.out.println("User - sends to AP: T=" + t[0] + " T'=" + t[1]);

        return t;

    }

    public boolean checkW() {
        boolean flag = false;
        if (w < pm.k) {
            flag = true;
            w++;
        }
        return flag;
    }
    
    public int getW() {
        return (w-1);
    }

}
