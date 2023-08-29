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

        /**
         * ***************
         */
        /* SETUP PHASE    */
        /**
         * ***************
         */
        grpmng.setup();
        System.out.println("a: " + bf.getA() + " a0: " + bf.getA0() + " b: "
                + bf.getB() + " Rn: " + bf.getRigidNumber()
                + " Rgm: " + bf.getRgm());

        /**
         * ***************
         */
        /* JOINING PHASE */
        /**
         * ***************
         */
        myuser.sendCommitC();
        //GM verify user commit C
        if (grpmng.verifyCommitC()) {
            // user read x2 by groupmanager and set other parameters
            myuser.setParams();
            // GM verify BETA proof
            if (grpmng.verifyBeta()) {
                // GM generate 'e' prime number 
                grpmng.createE();
                grpmng.createA();
                System.out.println("Alpha: " + bf.getAlpha()
                        + " Beta: " + bf.getBeta() + " A: "
                        + bf.getA_()
                        + " E: " + bf.getE());
                if (myuser.verifyFinalEquation()) {
                    // OK. Follow BOUND AND AUTH phases
                } else {
                    System.out.println("Something was wrong over verifying final equation!!!");
                }
            } else {
                System.out.println("Something was wrong over verifying BETA!!!");
            }
        } else {
            System.out.println("Something was wrong over verifying commit C!!!");
        }

    }

}
