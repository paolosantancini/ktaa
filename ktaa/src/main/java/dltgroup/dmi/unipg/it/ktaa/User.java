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
       System.out.println("X: "+x);

       alpha = bf.getA().modPow(BigInteger.valueOf(x), bf.getRigidNumber());
       // proof model b^x
       beta = bf.getB().pow(x);
        
       bf.setAlpha(alpha);
       bf.setBeta(beta);
       // update idlist with one single user i=1
       bf.updateIDLIST(BigInteger.ONE, beta);

    }
    
    // proof to validate: (a^x)*a0 mod n = (alpha*a0) mod n
    // if A^e =\alpha a_0 mod n, then A^e =a^x a_0 mod n
    public boolean verifyFinalEquation(){
        Boolean flag = false;
        BigInteger a0, A, leftmember, rightmember;
        
        a0 = bf.getA0();
        A = bf.getA().pow(x);
        A = A.multiply(a0);
        leftmember = A.mod(bf.getRigidNumber());
        
        rightmember = bf.getAlpha().multiply(a0);
        rightmember = rightmember.mod(bf.getRigidNumber());
        
        if (leftmember.equals(rightmember)) {
            flag = true;
        }
        
        return flag;
    }
    
}
