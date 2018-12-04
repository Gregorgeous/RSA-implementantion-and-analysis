public class RSAdecrypt {
    private String message = "";
    private int messageInt;

    private int nLength;
    private String encryptChar = "";

    RSAdecrypt(int cipherInt, RSAProgram D) {

        int eInt = cipherInt;
        int dInt = eInt;
        for(int j = 1; j < D.getPriKey(); j++) {
            dInt = (dInt * eInt) % D.getN();
        }
        messageInt = dInt;

    }

    public String getMessage() { return message; }
    public int getMessageInt() { return messageInt; }
}
