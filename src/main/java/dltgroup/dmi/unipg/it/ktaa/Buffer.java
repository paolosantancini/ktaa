package dltgroup.dmi.unipg.it.ktaa;

import java.util.Random;

public class Buffer {

    // unique istance
    private static Buffer instance = null;

    int elleset[], searchtag[][], tracingtag[][], beta, l_save, l1_save;
    Random rand = new Random();

    // constructor
    public Buffer() {
    }

    // create object if it doesn't exist
    public static synchronized Buffer getIstance() {
        if (instance == null) {
            instance = new Buffer();
        }
        return instance;
    }

    public void setBeta(int param) {
        beta = param;
    }
    
    public int getBeta() {
        return beta;
    }
    
    public void setElle(int v[]) {
        elleset = v;
    }

    // copy tags value only (not status)
    public void setSearchTag(int v[][]) {
        searchtag = v;
    }

    // copy tags value only (not status)
    public void setTracingTag(int v[][]) {
        tracingtag = v;
    }

    public int getElle() {
        int l = elleset[rand.nextInt(1,elleset.length)]; 
        // save value
        
        return l;
    }
    
    public void saveElle(int index, int value) {
        if (index == 0) l_save = value;
        else l1_save = value;
    }
    
    public int getSearchTag() {
        // get last not used value
        int i = 0, st = 0;
        while (i<searchtag.length) {
            if (searchtag[i][1] == 0) { st = searchtag[i][0]; }
            i++;
        }
        return st;
    }
    
    public int getTracingTag() {
        // get last not used value
        int i = 0, st = 0;
        while (i<tracingtag.length) {
            if (tracingtag[i][1] == 0) { st = tracingtag[i][0]; }
            i++;
        }
        return st;
    }

}
