public class RSAdemo {
    public static void main(String[] args) {
        int N = 3599;
        publicKey E = new publicKey(3480);

//        System.out.println(E.giveE());
        RSAencrypt cipherText = new RSAencrypt("test string", E, N);
        System.out.println(cipherText.getCipherText());
    }
}
