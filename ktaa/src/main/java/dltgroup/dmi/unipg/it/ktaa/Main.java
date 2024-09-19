package dltgroup.dmi.unipg.it.ktaa;

import java.io.UnsupportedEncodingException;

public class Main {

    public static void main(String[] args) throws UnsupportedEncodingException {

        GroupManager gm = GroupManager.getIstance();
        ApplicationProvider ap = new ApplicationProvider();
        // Simulation 1 user per 1 service
        User usr = new User();

        // User calculate alpha and Beta
        usr.calculateAlpha();
        usr.calculateBeta();

        // GM calculate e
        gm.setE();
        // GM calculate A
        gm.calculateA();
        // Now user public key is defined by (alpha, A^e, beta)

// User self check about number of times service request
        while(usr.checkW()){
// User calculates T and T' for auth process
            ap.getService(usr.calculateT_T1(), usr.getW());
        }
        System.out.println("User ended with "+(usr.getW()+1)+" number of requests (w)");
    }

}
