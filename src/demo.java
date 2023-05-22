import java.security.NoSuchAlgorithmException;

public class demo {

    public static void main(String[] args) throws NoSuchAlgorithmException{

        Witness w = new Witness();
        String[][] biometrics = w.BiometricPair(100, 1750);
        
        for(String[] biometric : biometrics){
            fuzzy_commitment f = new fuzzy_commitment();
            f.commit(biometric[0]);
            f.decommit(biometric[0]);
        }
    }
    
}
