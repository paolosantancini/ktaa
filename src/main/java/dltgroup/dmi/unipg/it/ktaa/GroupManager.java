package dltgroup.dmi.unipg.it.ktaa;

public class GroupManager {

    Parameters pm = Parameters.getIstance();
    Buffer my_buffer;
    int c, a, z, x_second, beta;
    double u;
    boolean status;

    GroupManager() {
        c = 2;
        x_second = 6;
        my_buffer = Buffer.getIstance();
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
    
    public void receiveBeta(int param) {
        beta = param;
    }
    
    private boolean check() {
        
        Boolean flag = false;
        
        double[] checks = {Math.pow(pm.getG(), z), u * (Math.pow(beta, c))};
        
        if (checks[0] == checks[1]) { 
            flag = true; 
            // puiblish user beta value to public list
            my_buffer.setBeta(beta);
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
