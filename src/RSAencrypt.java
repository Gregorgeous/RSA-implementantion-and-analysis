public class RSAencrypt {
    private String cipherText = "";

    RSAencrypt(String message, publicKey E, int N) {
        for(char mText: message.toCharArray()) {
            int mInt = (int) mText;
            int cInt = mInt;

            // taking the int for each character in the message and running it through
            // m^E mod N. then mod'ing that number again so it can be made into ciphertext.
            for(int i = 0; i < E.pubKey(); i++) {
                cInt = (cInt * mInt) % N;
            }
//            cInt = (cInt % 26) + 65; // '+ 65' so the character will be chosen from A-Z. can be alter later.
            char cText = (char) cInt;
            System.out.println(cInt);
            cipherText += cInt;
        }
    }

    public String getCipherText() {
        return cipherText;
    }
}