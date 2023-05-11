import java.util.*;

public class ecc_hamming {

    public static void main(String[] args) {
        String data = "1011001";
        ecc_hamming ecc = new ecc_hamming();
        String parityString = ecc.addParity(data);
        System.out.println("Data: " + data);
        System.out.println("Parity String: " + parityString);
        System.out.println("Parity Bits: " + ecc.parityBits);
    }

    private final ArrayList<Integer> parityBits = new ArrayList<>();
    
    public String addParity(String data) {
        
        StringBuilder bitString = new StringBuilder(data);
        StringBuilder parityString = new StringBuilder();
        
        int len = data.length();
        int n = 0; //number of parity bits

        //calc number of parity bits
        int r = 0;
        while (len > Math.pow(2,r)-(r+1)) {
            n++;
            r++;
        }
        
        //generate parityString, parity bits with default value 0
        int j = 0;
        int index = data.length()-1;
        for (int i = 0; i < len + n; i++) {
            if (Math.pow(2, j) == i + 1) {
                parityString.insert(0, '0');
                j++;
            } else {
                parityString.insert(0, bitString.charAt(index));
                index--;
            }
        }
  



        /*
         * k=0
         * paritypos = 2^0 = 1
         * i = 2
         * bin = 0011
         * 10101000100
         * parity=1
         * 
         * 
         */



        //calc parity bits
        int parity = 0;
        int paritypos = 0;
        for(int k = 0; k < n; k++) {
            paritypos = (int) Math.pow(2, k);
            for(int i = 0; i < parityString.length(); i++) {
                String bin = "000"+ Integer.toBinaryString(i+1);
                if(bin.charAt(bin.length()-1-k) == '1') {
                    if(parityString.charAt(parityString.length()-1-i) == '1') {
                        parity++;
                    }
                    
                }        
            }
            if(parity % 2 == 0) {
                parityString.setCharAt(parityString.length()-1-paritypos, '0');
            } else {
                parityString.setCharAt(parityString.length()-1-paritypos, '1');
            } 
            parity = 0;  
            
        }
        
        return parityString.toString();
    }

}