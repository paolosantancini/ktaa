package dltgroup.dmi.unipg.it.ktaa;

/**
 * DltGroup Unipg Bistarelli Stefano, Mercanti Ivan, Santancini Paolo, Santini
 * Francesco
 */
public class Ktaa {

    public static void main(String[] args) {
        Integer values[];

        GroupManager my_keys = new GroupManager();
        my_keys.setup();
        values = my_keys.getValues();

        System.out.println("q: " + values[0].toString() + " p: " + values[1].toString()
                + " n: " + values[2].toString());

    }

}
