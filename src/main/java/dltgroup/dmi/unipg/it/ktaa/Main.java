package dltgroup.dmi.unipg.it.ktaa;

public class Main {

    public static void main(String[] args) {

      
    User usr = new User();
    // Setting up AP and Buffer values
    ApplicationProvider ap = new ApplicationProvider();
    
    usr.sendU2GM();
    usr.sendBeta2GM();
    if (usr.sendZ2GM()) {
        usr.doRequest();
    } else {
        System.out.println("Joining faileture");
    }
    
    
    // If Checks are equal then GM knows U had got right nature
    // (but not the value) of x_prime and sends back x_second.
    // At the end P creates his private key
    
    }
    

}
