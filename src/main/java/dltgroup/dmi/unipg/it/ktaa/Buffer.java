package dltgroup.dmi.unipg.it.ktaa;

import java.util.Random;

public class Buffer {

    // unique istance
    private static Buffer instance = null;

    int elleset[], searchtag[][], tracingtag[][];
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
        return elleset[rand.nextInt(1,elleset.length)];
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
