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
        
        System.out.println("X1: "+bf.getX1()+
                "\nMaxLAMBDA: "+bf.getMaxLambda());

System.out.println("*** JOINING ***");

//GM verify user commit C
        if (grpmng.verifyCommitC()) {
            // user read x2 by groupmanager and set other parameters
            
            System.out.println("X2: "+bf.getX2());
            
            myuser.setParams();
            
            System.out.println("Alpha: "+bf.getAlpha()+"\nBeta: "+bf.getBeta());
            
            // GM verify BETA proof
            if (grpmng.verifyBeta()) {
                // GM generate 'e' prime number 
                grpmng.createE();
                grpmng.createA();
                
                System.out.println("e: " + bf.getE()+"\nA: "+ bf.getA_());
                
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
