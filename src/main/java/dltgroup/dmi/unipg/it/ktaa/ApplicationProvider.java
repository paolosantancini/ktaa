package dltgroup.dmi.unipg.it.ktaa;

import java.math.BigInteger;
import java.util.Random;

public class ApplicationProvider {

    Buffer my_buffer;
    Parameters pm;
    Random rand = new Random();
    int k = 3;

    ApplicationProvider() {
        my_buffer = Buffer.getIstance();
        pm = Parameters.getIstance();
        setElleVect();
        setSearchTag(); // col_1=value, col_2=status (0,1=used)
        setTracingTag(); // col_1=value, col_2=status (0,1=used)
    }

    private void setElleVect() {
        int[] elle = new int[pm.getElleRange()];
        for (int i = 0; i < pm.getElleRange(); i++) {
            elle[i] = i;
        }
        my_buffer.setElle(elle);
    }

    private void setSearchTag() {
        int[][] searchtag = new int[k][2];
        for (int i = 0; i < k; i++) {
            searchtag[i][0] = rand.nextInt(3, 10);
            searchtag[i][1] = 0;
        }
        my_buffer.setSearchTag(searchtag);
    }

    private void setTracingTag() {
        int[][] tracingtag = new int[k][2];
        for (int i = 0; i < k; i++) {
            // 0 = not used. This value is in second column
            tracingtag[i][0] = rand.nextInt(3, 10);
            tracingtag[i][1] = 0;
        }
        my_buffer.setTracingTag(tracingtag);
    }

    public boolean receiveTau(BigInteger t, BigInteger t1){
        boolean check_status = false;
        BigInteger tau = t;
        BigInteger tau1 = t1;
        BigInteger v_prime, beta_check;
        BigInteger beta = BigInteger.valueOf(my_buffer.getBeta());
        int expo = 0, l1;
        
        l1 = my_buffer.getElle();
        my_buffer.saveElle(1, l1);
        v_prime = tau.multiply(beta.pow(expo));
        beta_check = tau1.divide(v_prime);
        expo = 1 / (my_buffer.l_save-l1);
        beta_check = beta_check.pow(expo);
        
        if (beta.equals(beta_check)) check_status = true;
        
        System.out.println("Beta "+beta+" Beta_check: "+beta_check);
        
        return check_status;
    }
}
