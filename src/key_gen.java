
public class key_gen {       
    public String BitString (int length) {
        StringBuilder keystring = new StringBuilder();
        for (int i = 0; i < length; i++) {
            double a = Math.random();
            if (a <= 0.5) {
                keystring.append("0");
            } else {
                keystring.append("1");
            }
        }
        return keystring.toString();
    }
}