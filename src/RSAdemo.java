public class RSAdemo {
    public static void main(String[] args) {
//        int N = 3599;
//        publicKey E = new publicKey(3480);

//        System.out.println(E.giveE());
//        RSAencrypt cipherText = new RSAencrypt("test string", E, N);
//        System.out.println(cipherText.getCipherText());

        RSAProgram test2 = new RSAProgram();
        System.out.println("n: " + test2.getN());
        System.out.println("phi of n: " + test2.getPhiN());
        System.out.println("public key: " + test2.getPubKey());
        System.out.println("private key: " + test2.getPriKey());

        RSAencrypt cipherText2 = new RSAencrypt(2, test2);
        System.out.println(cipherText2.getCipherText());

        RSAdecrypt messageTest2 = new RSAdecrypt(cipherText2.getCipherText(), test2);
        System.out.println(messageTest2.getMessage());
    }
}
