public class RSAdecrypt {
    private String message = "";

    private int nLength;
    private String encryptChar = "";

    RSAdecrypt(String cipherText, RSAProgram D) {

        int eInt = Integer.parseInt(cipherText);
        int dInt = eInt;
        for(int j = 1; j < D.getPriKey(); j++) {
            dInt = (dInt * eInt) % D.getN();
        }
        message += dInt;

    }

    public String getMessage() { return message; }
}
