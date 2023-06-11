package dltgroup.dmi.unipg.it.ktaa;

import java.math.BigInteger;

/**
 * DltGroup Unipg Bistarelli Stefano, Mercanti Ivan, Santancini Paolo, Santini
 * Francesco
 */
public class Keys {

    private BigInteger q, p, rigid_integer;
    int BIT_LENGTH;

    Keys() {
        BIT_LENGTH = 2048;
    }
}

// Setting ridid integer number by GM
public void setRigidInteger(BigInteger q_, BigInteger p_, BigInteger ri) { 
        q = q_;
        p = p_;
        rigid_integer = ri;
    }
    
    // Getting ridid integer number to main
    public BigInteger getRigidInteger(){ 
        return rigid_integer; 
    }
    
public BigInteger getQ(){ 
        return q; 
    }

public BigInteger getP(){ 
        return p; 
    }

}
