package dltgroup.dmi.unipg.it.ktaa;

import java.io.UnsupportedEncodingException;

public class User {

    Parameters pm;
    Buffer my_buffer;
    GroupManager gm;

    int x, x1, x2;

    User() throws UnsupportedEncodingException {
        gm = GroupManager.getIstance();
        pm = Parameters.getIstance();
        my_buffer = Buffer.getIstance();
        setPrimaryK();
    }

    private void setPrimaryK() {
        x1 = my_buffer.getRandomLambdaValue();
        x2 = gm.getX2();
        x = (x1+x2) % pm.lambda_max;
        System.out.println("User - Calculating primary key (x): "+x);
    }

}
