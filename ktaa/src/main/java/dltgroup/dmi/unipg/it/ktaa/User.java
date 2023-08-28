package dltgroup.dmi.unipg.it.ktaa;

import java.math.BigInteger;

/**
 * DltGroup Unipg Bistarelli Stefano, Mercanti Ivan, Santancini Paolo, Santini
 * Francesco
 */
public class User {

    MathK user_math;
    Buffer bf;
    int x, x1;
    BigInteger alpha, beta;

    User() {
        user_math = new MathK();
        x1 = user_math.getX();
        bf = Buffer.getIstance();
    }

    public void sendCommitC(){
        // write to buffer x1
        bf.setX1(x1);
    }
    
    public void setParams(){
        
       x = (x1+bf.getX2())%bf.getMaxLambda();

       alpha = bf.getA().modPow(BigInteger.valueOf(x), bf.getRigidNumber());
       // proof model b^x
       beta = bf.getB().pow(x);
        
       bf.setAlpha(alpha);
       bf.setBeta(beta);
       // update idlist with one single user i=1
       bf.updateIDLIST(BigInteger.ONE, beta);

    }
    
}
