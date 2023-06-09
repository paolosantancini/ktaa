package dltgroup.dmi.unipg.it.ktaa;

import java.util.Random;

/**
 * DltGroup Unipg Bistarelli Stefano, Mercanti Ivan, Santancini Paolo, Santini
 * Francesco
 */
public class GroupManager {

    Keys my_keys;
    MathK my_math;

    GroupManager() {
        my_keys = new Keys();
        my_math = new MathK();
    }

    // SETUP phase
    public void setup() {
        // making rigid integer
        my_keys.setRigidInteger(rigidInt());
    }

    /* Calculate ridid integer number [v=1024 (2v-bit = 2048) == 256 byte]
    Rigid int number can be factorized into two safe primes of equal length.
    Safe primes are prime numbers of the form p = 2q + 1, 
    where q is also a prime number.  
     */
    private Integer[] rigidInt() {

        Integer p = 0, q = 0;
        Integer my_rigid_int[] = {0, 0, 0};
        Random rnd = new Random();
        boolean flag = false;

        while (flag == false) {
            while (flag == false) {
                // calculating q
                while (flag == false) {
                    q = 1 + rnd.nextInt(100000);
                    flag = my_math.primeVerify(q);
                }
                my_rigid_int[0] = q;
                // calculating p
                p = 2 * q + 1;
                flag = my_math.primeVerify(p);

            }
            my_rigid_int[1] = p;

            flag = false;
            // veryfing lenght of safe primes
            if (q.toString().length() == p.toString().length()) {
                flag = true;
            }

        }
        my_rigid_int[2] = p * q;

        return my_rigid_int;

    }

    // Getting calculated values
    public Integer[] getValues() {

        Integer[] values = my_keys.getRigidInteger();

        return (values);

    }

}
