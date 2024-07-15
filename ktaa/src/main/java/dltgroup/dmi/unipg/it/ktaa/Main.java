package dltgroup.dmi.unipg.it.ktaa;

import java.io.UnsupportedEncodingException;

public class Main {

    public static void main(String[] args) throws UnsupportedEncodingException {

        
        GroupManager gm = GroupManager.getIstance();
        ApplicationProvider ap = new ApplicationProvider();
        User usr = new User();
        
        /*      
        
        // Setting up AP and Buffer values
        ApplicationProvider ap = new ApplicationProvider();

        usr.sendU2GM();
        usr.sendBeta2GM();
        if (usr.sendZ2GM()) {
            usr.doRequest();
            if (ap.receiveTau(usr.sendTau1())) {
                System.out.println("AP says User can communicate. PROOF valid!");
            } else {
                System.out.println("AP says User cannot communicate. PROOF not valid!");
            }
        } else {
            System.out.println("Joining faileture");
        } */

        // If Checks are equal then GM knows U had got right nature
        // (but not the value) of x_prime and sends back x_second.
        // At the end P creates his private key
    }

}
