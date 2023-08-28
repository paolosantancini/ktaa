package dltgroup.dmi.unipg.it.ktaa;

/**
 * DltGroup Unipg Bistarelli Stefano, Mercanti Ivan, Santancini Paolo, Santini
 * Francesco
 */
public class Ktaa {

    public static void main(String[] args) {

        GroupManager grpmng = new GroupManager();
        User myuser = new User();
        Buffer bf = Buffer.getIstance();

        // setup phase
        grpmng.setup();
        // joining phase
        myuser.sendCommitC();
        //GM verify user commit C
        if (grpmng.verifyCommitC()) {
            // user read x2 by groupmanager
            myuser.setParams();
            if (grpmng.verifyBeta()) {
                System.out.println("Alpha: " + bf.getAlpha() + " Beta: " + bf.getBeta());
            } else {
                System.out.println("Something goes wrong over verifing BETA!!!");
            }
        } else {
            System.out.println("Something goes wrong over verifing commit C!!!");
        }

    }

}
