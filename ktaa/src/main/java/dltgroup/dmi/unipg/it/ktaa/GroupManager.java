package dltgroup.dmi.unipg.it.ktaa;

/**
 * DltGroup Unipg Bistarelli Stefano, Mercanti Ivan, Santancini Paolo, Santini
 * Francesco
 */
public class GroupManager {

    Keys my_keys;

    GroupManager() {
        my_keys = new Keys();
    }

    // SETUP phase
    public void setup() {
        // making rigid integer
        my_keys.setRigidInteger(rigidInt());
    }

    // Calculate ridid integer number
    // v=1024 (2v-bit = 2048) == 256 byte
    private Integer rigidInt() {

        Integer my_rigid_int = 0;

        return my_rigid_int;

    }
  
    // Getting calculated values
    public Integer[] getValues() {
        
        Integer[] values = {};
        values[0] = my_keys.getRigidInteger();
        
        System.out.println(values[0].toString());
        
        return(values);
    }

}
