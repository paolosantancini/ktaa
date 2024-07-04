package dltgroup.dmi.unipg.it.ktaa;

public class User {
    
    Parameters pm = new Parameters();
    GroupManager gm = new GroupManager();

    int x_prime, z, c, x_second, prime_key;
    double beta, u;

    User() {
        x_prime = 4; // "w"
        beta = Math.pow(pm.getG(), x_prime); // "h=g^w"
    }

    public void sendU2GM() {
        u = Math.pow(pm.getG(), pm.getR());
        System.out.println("User sends u to GM: " + u);
        gm.receiveU(u);
    }
    
    public void sendBeta2GM() {
        beta = Math.pow(pm.getG(), x_prime);
        System.out.println("User sends beta to GM: " + beta);
        gm.receiveBeta(beta);
        // requesting c to GM
        c = gm.getC();
    }
    
    public void sendZ2GM() {
        z = pm.getR() + x_prime * c;
        System.out.println("User sendZ2GM: " + z);
        gm.receiveZ(z);
        if (gm.getStatus()) {
            x_second = gm.getX2();
            prime_key = (x_prime+x_second) % pm.getP();
            System.out.println("U prime key: "+prime_key);
        }
    }

}
