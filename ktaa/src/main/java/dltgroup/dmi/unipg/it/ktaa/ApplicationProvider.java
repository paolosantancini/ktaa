package dltgroup.dmi.unipg.it.ktaa;

/**
 * DltGroup Unipg
 * Bistarelli Stefano, Mercanti Ivan, Santancini Paolo, Santini Francesco
 */

public class ApplicationProvider {
    
    MathK mt;
    Buffer bf;
    
    ApplicationProvider(){
    mt = new MathK();
    bf = Buffer.getIstance();
    }
    
    public void setl() {
        bf.setl(mt.getl());
    }
    
}
