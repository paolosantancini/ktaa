package dltgroup.dmi.unipg.it.ktaa;

/**
 * DltGroup Unipg Bistarelli Stefano, Mercanti Ivan, Santancini Paolo, Santini
 * Francesco
 */

public class Keys {
    
    private final Integer rigid_integer[];
    
    Keys(){this.rigid_integer = new Integer[]{0, 0, 0};
}
    
    // Setting ridid integer number by GM
    public void setRigidInteger(Integer ri[]){ 
        rigid_integer[0] = ri[0];
        rigid_integer[1] = ri[1];
        rigid_integer[2] = ri[2];
    }
    
    // Getting ridid integer number to main
    public Integer[] getRigidInteger(){ 
        return rigid_integer; 
    }
    
}
