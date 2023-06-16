import java.util.Random;
 

public class Witness {

    //Chance für Bitflip ist 25 * Flip
    int flip = 4;


    public String[][] BiometricPair (int n, int length){
        String[][] biometricPair = new String[n][2];
        for (int i = 0; i < n; i++) {
            biometricPair[i][0] = BitString(length);
            biometricPair[i][1] = bitFlip(biometricPair[i][0]);
        }
        return biometricPair;
    }


    
   
   
   
   
   
   
   
   
   
    public String BitString (int length) {
        StringBuilder bitString = new StringBuilder();
        for (int i = 0; i < length; i++) {
            double a = Math.random();
            if (a <= 0.5) {
                bitString.append("0");
            } else {
                bitString.append("1");
            }
        }
        return bitString.toString();
    }
   
   
   
   
   
   
   
   
   
   
   
   
    
    public String bitFlip (String bitsString){
        StringBuilder String = new StringBuilder(bitsString);
        if (flip >=4) flip = 100;
        for(int i = 0; i < flip; i++){
            Random rand = new Random();
            int index = rand.nextInt(String.length());
            String.setCharAt(index, String.charAt(index) == '0' ? '1' : '0');
            
        }
        return String.toString();
    }
  
}

