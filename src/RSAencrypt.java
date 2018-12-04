public class RSAencrypt {
    private String cipherText = "";
    private int cipherInt;

    RSAencrypt(String message, RSAProgram E) {
        for(char mText: message.toCharArray()) {
            int mInt = (int) mText;
            int cInt = mInt;

            // taking the int for each character in the message and running it through
            // m^E mod N. then mod'ing that number again so it can be made into ciphertext.
            for(int i = 1; i < E.getPubKey(); i++) {
                cInt = (cInt * mInt) % E.getN();
            }
//            cInt = (cInt % 26) + 65; // '+ 65' so the character will be chosen from A-Z. can be alter later.
            char cText = (char) cInt;
//            System.out.println(cInt);
            cipherText += cInt;
        }
    }

    RSAencrypt(int message, RSAProgram E) {
        int c = message % E.getN();

        for(int i = 1; i < E.getPubKey(); i++) {
            c = (c * message) % E.getN();
        }

        cipherInt = c;
    }

    public String getCipherText() { return cipherText; }

    public int getCipherInt() { return cipherInt; }
}