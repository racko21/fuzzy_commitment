public class fuzzy_witness {       
    public String BitString (int length) {
        StringBuilder fuzzyString = new StringBuilder();
        for (int i = 0; i < length; i++) {
            double a = Math.random();
            if (a <= 0.5) {
                fuzzyString.append("0");
            } else {
                fuzzyString.append("1");
            }
        }
        return fuzzyString.toString();
    }
}