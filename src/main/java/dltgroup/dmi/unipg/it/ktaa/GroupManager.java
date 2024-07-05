package dltgroup.dmi.unipg.it.ktaa;

public class GroupManager {

    Parameters pm = Parameters.getIstance();
    int c, a, z, x_second;
    double u, beta;
    boolean status;

    GroupManager() {
        c = 2;
        x_second = 6;
    }

    public int getC() {
        return c;
    }
    
    public void receiveZ(int param) {
        z = param;
        status = check();
    }
    
    public void receiveU(double param) {
        u = param;
    }
    
    public void receiveBeta(double param) {
        beta = param;
    }
    
    private boolean check() {
        
        Boolean flag = false;
        
        double[] checks = {Math.pow(pm.getG(), z), u * (Math.pow(beta, c))};
        
        if (checks[0] == checks[1]) { 
            flag = true; 
            /*
            TODO: puiblish user beta value to public list
            */
            Buffer my_buffer = Buffer.getIstance();
        }
        
        System.out.println("g^z= "+checks[0]+" "+"u*(beta^c)= "+checks[1]+ " CHECK STATUS: "+flag);
        
        return flag;
        
    }
    
    public boolean getStatus() {
        return status;
    }
    
    public int getX2() {
        return x_second;
    }
    
}
