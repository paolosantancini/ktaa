package dltgroup.dmi.unipg.it.ktaa;

import java.io.UnsupportedEncodingException;
import java.util.Random;

public class User {

    Parameters pm;
    Buffer my_buffer;
    GroupManager gm;

    int x, x1, x2;

    User() throws UnsupportedEncodingException {
        gm = GroupManager.getIstance();
        pm = Parameters.getIstance();
        my_buffer = Buffer.getIstance();
        setPrimaryK();
    }

    private void setPrimaryK() {
        x1 = my_buffer.getRandomLambdaValue();
        x2 = gm.getX2();
        x = (x1+x2) % pm.lambda_max;
        System.out.println("User - Calculating primary key (x): "+x);
    }
    
    public void calculateAlpha() {
        // a^x mod n
        my_buffer.setAlpha((my_buffer.a.pow(x).mod(my_buffer.n)));
        System.out.println("User - calculating Alpha: "+my_buffer.alpha);
    }
    
    public void calculateBeta() {
        // b^x mod p
        my_buffer.setBeta(my_buffer.b.pow(x).mod(pm.p));
        System.out.println("User - calculating Beta: "+my_buffer.beta+" with p="+pm.p+" b="+my_buffer.b+" x="+x);
    }

    public void setT() {
        Random rnd = new Random();
        
        int l = my_buffer.l_set[rnd.nextInt(pm.max_l)];
        
        my_buffer.Tag = my_buffer.getT(0).pow(x).mod(pm.p);
        my_buffer.Tag1 = my_buffer.getT(1).multiply(my_buffer.b.pow(l)).mod(pm.p);
        
        System.out.println("User - calculating T: "+my_buffer.Tag+" T':"+my_buffer.Tag1);
        
    }
    
}
