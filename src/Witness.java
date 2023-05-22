import java.util.Random;

public class Witness {

    public String[][] BiometricPair (int n, int length){
        String[][] biometricPair = new String[n][2];
        for (int i = 0; i < n; i++) {
            biometricPair[i][0] = BitString(length);
            biometricPair[i][1] = fuzzyString(biometricPair[i][0], 0);
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

    public String fuzzyString (String bitString, double prob){
        Random rand = new Random();
        StringBuilder fuzzyString = new StringBuilder();
        for (int i = 0; i < bitString.length(); i++) {
            if(rand.nextDouble()< prob){
                fuzzyString.append(bitString.charAt(i) == '0' ? '1' : '0');
            }
            else{
                fuzzyString.append(bitString.charAt(i));
            }
        }
        return fuzzyString.toString();
    }
} 