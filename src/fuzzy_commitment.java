import java.util.ArrayList;
import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;


public class fuzzy_commitment{


    private final ArrayList<ArrayList<String>> commitment;
    private final ecc_hamming ecc;
    public int fail = 0;
    public int succ = 0;

    public fuzzy_commitment(){
        commitment = new ArrayList<ArrayList<String>>();
        ecc = new ecc_hamming();
    }

    public void commit (String data)throws NoSuchAlgorithmException{
        ArrayList<String> f = new ArrayList<>();
        StringBuilder codeword = new StringBuilder();
        StringBuilder eccCodeword = new StringBuilder();
        
        //generate codeword and hash
        key_gen keyGen = new key_gen();
        codeword = new StringBuilder(keyGen.BitString(572));
        String hash = SHA256(codeword.toString());

        //add parity bits
        for(int i = 0; i < codeword.length(); i+=4)
        eccCodeword.append(ecc.addParity(codeword.substring(i,i+4)));

        //generate delta = data XOR Codeword
        String delta = xorStrings(data, eccCodeword.toString());

        f.add(0, hash);
        f.add(1, delta);

        this.commitment.add(f);
    }

    public void decommit (String data)throws NoSuchAlgorithmException{
        StringBuilder codeword = new StringBuilder();
        StringBuilder xor = new StringBuilder();
          
        for(ArrayList<String> f : this.commitment){
            String hash = f.get(0);
            String delta = f.get(1);

            

            //generate codeword
            xor = new StringBuilder(xorStrings(delta, data));

            //remove parity bits
            for(int i = 0; i < xor.length(); i+=7){
                codeword.append(ecc.decode(xor.substring(i,i+7)));
            }

            //generate hash
            String hash2 = SHA256(codeword.toString());

            if(hash.equals(hash2)){
                System.out.println("Decommitment successful");
                succ++;
                return;
            }
        }
        System.out.println("Decommitment failed");
        fail++;
        
    }
        

    public String SHA256(String data) throws NoSuchAlgorithmException{
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(data.getBytes(StandardCharsets.UTF_8));

        return new String(hash);
    }

    public String xorStrings(String inputData, String key){
        StringBuilder xorString = new StringBuilder();

        if(inputData.length() == key.length()){
            for(int i = 0; i < inputData.length(); i++) {
                xorString.append((inputData.charAt(i) ^ key.charAt(i)));
            }
        }
        else
            throw new RuntimeException("Given Strings do not have the same length -> same length needed for XOR-operation \n" +
                    "input data: \n " + inputData + "\n  input data length = " + inputData.length() +
                    "\n key data: \n" + key+ "\n  key length = " + key.length());

        return xorString.toString();
    }
    
}