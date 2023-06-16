import java.security.NoSuchAlgorithmException;

public class demo {

    public static void main(String[] args) throws NoSuchAlgorithmException{

        int fail = 0;
        int succ = 0;
        Witness w = new Witness();
        String[][] biometrics = w.BiometricPair(100, 1001);
        
        for(String[] biometric : biometrics){
            fuzzy_commitment f = new fuzzy_commitment();
            f.commit(biometric[0]);
            f.decommit(biometric[1]);
            if(f.fail == 1){
                fail++;
            }else{
                succ++;
            }
        }
        System.out.println("Fail: " + fail);
        System.out.println("Succ: " + succ);
    }
    
}
