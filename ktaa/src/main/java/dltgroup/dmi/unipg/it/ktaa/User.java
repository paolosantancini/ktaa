package dltgroup.dmi.unipg.it.ktaa;

import java.io.UnsupportedEncodingException;

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

}
