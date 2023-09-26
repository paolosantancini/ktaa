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
        System.out.println("*** SETUP ***");
        System.out.println("a: " + bf.getA() + "\na0: " + bf.getA0() + "\nb: "
                + bf.getB() + "\nRigid number: " + bf.getRigidNumber()
                + "\nRandom string: " + bf.getRgm());

        /**
         * ***************
         */
        /* JOINING PHASE */
        /**
         * ***************
         */
        myuser.sendCommitC();

        System.out.println("X1: " + bf.getX1()
                + "\nMaxLAMBDA: " + bf.getMaxLambda());

        System.out.println("*** JOINING ***");

//GM verify user commit C
        if (grpmng.verifyCommitC()) {
            // user read x2 by groupmanager and set other parameters

            System.out.println("X2: " + bf.getX2());

            myuser.setParams();

            System.out.println("Alpha: " + bf.getAlpha() + "\nBeta: " + bf.getBeta());

            // GM verify BETA proof
            if (grpmng.verifyBeta()) {
                // GM generate 'e' prime number 
                grpmng.createE();
                //grpmng.createA(); at the moment working by (alpha,A^e,beta)
                // seem to be 'impossibile' to discover A

                System.out.println("e: " + bf.getE() + "\nA: " + bf.getA_());

                if (myuser.verifyFinalEquation()) {
                    /**
                     * ********************
                     */
// BOUND AND AUTH PHASE
                    /**
                     * *******************
                     */
                    System.out.println("Final equation OK!");

                    // From slides 10 to 12 of presentation
                    //
                    // AP compute four random integer:  
                    // one search tag (t), secondth tracing tag (t'), l, l*
                    // User compute two tracing tag by receiving t' (tau', v')
                    // 
                    // AP verify quation (tau'/v') = beta^(l-l*)
                    // if OK then add specific counter in logs
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
