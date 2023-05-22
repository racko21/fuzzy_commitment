

public class ecc_hamming {

    public static void main(String[] args) {
        
        System.out.println("4/7 Hamming-Code");
        String data = "0011";      
        ecc_hamming ecc = new ecc_hamming();
        String parityString = ecc.addParity(data);
        System.out.println("Data: " + data);
        System.out.println("Parity String: " + parityString);
        System.out.println("Decoded: " + ecc.decode(parityString));

        System.out.println();
        System.out.println();

        System.out.println("11/15 Hamming-Code");
        String data2 = "10101000100";
        ecc_hamming ecc2 = new ecc_hamming();
        String parityString2 = ecc.addParity(data2);
        System.out.println("Data: " + data2);
        System.out.println("Parity String: " + parityString);
        System.out.println("Decoded: " + ecc2.decode(parityString2));
        

    }
    
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

        //calc parity bits
        int parity = 0;
        int paritypos = 0;
        for(int k = 0; k < n; k++) {
            paritypos = (int) Math.pow(2, k);
            for(int i = 0; i < parityString.length(); i++) {
                String bin = toBinary( i+1, 16);
                if(bin.charAt(bin.length()-1-k) == '1') {
                    if(parityString.charAt(parityString.length()-1-i) == '1') {
                        parity++;
                    }
                    
                }        
            }
            if(parity % 2 == 0) {
                parityString.setCharAt(parityString.length()-paritypos, '0');
            } else {
                parityString.setCharAt(parityString.length()-paritypos, '1');
            } 
            parity = 0;  
            
        }
        
        return parityString.toString();

        
    }

    public String decode (String data) {
        
        StringBuilder parityString = new StringBuilder(data);
        StringBuilder newParity = new StringBuilder();
        int n = 0; //number of parity bits

        //calc number of parity bits
        int r = 0;
        while (parityString.length() > Math.pow(2,r)-(r+1)) {
            n++;
            r++;
        }

        //calc parity bits
        int parity = 0;
        for(int k = 0; k < n; k++) {
            for(int i = 0; i < parityString.length(); i++) {
                String bin = toBinary( i+1, 16);
                if(bin.charAt(bin.length()-1-k) == '1') {
                    if(parityString.charAt(parityString.length()-1-i) == '1') {
                        parity++;
                    }
                    
                }        
            }
            if(parity % 2 == 0) {
                newParity.append('0');
            } else {
                newParity.append('1');
            } 

            parity = 0;  
            
        }

        // calc position of error
        int errorPos = Integer.parseInt(newParity.toString(), 2);

        //correct error
        if(errorPos != 0) {
            if(parityString.charAt(parityString.length()-errorPos) == '1') {
                parityString.setCharAt(parityString.length()-errorPos, '0');
            } else {
                parityString.setCharAt(parityString.length()-errorPos, '1');
            }
        }
        
        //remove parity bits
        int paritypos = 0;
        int index = (int) Math.pow(2, paritypos)-1;
        while(index < parityString.length()) {
            parityString.deleteCharAt(parityString.length()-1-index+paritypos);
            paritypos++;
            index = (int) Math.pow(2, paritypos)-1;
        }

        return parityString.toString();
    }
    
    public static String toBinary(int x, int len)
    {
        StringBuilder result = new StringBuilder();
 
        for (int i = len - 1; i >= 0 ; i--)
        {
            int mask = 1 << i;
            result.append((x & mask) != 0 ? 1 : 0);
        }
 
        return result.toString();
    }




        
}