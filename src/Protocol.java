import java.util.*;

public class Protocol {
    public int[] pKS;
    public int[] pKA;
    public int[] pKB;
    TrustedServerRSA Server;
    RSAProgram Alice;
    RSAProgram Bob;
    private int aliceNonce;
    public int aliceEncryptedNonce;
    private int bobNonce;
    public int bobEncryptedNonce;
    private int aliceNonceDecryptedByBob;
    private int bobNonceDecryptedByAlice;

    Protocol() {
        System.out.println("============================= PART 2 OF THE COURSEWORK =============================");
        System.out.println("STEP 0: Before the actual protocol, we need to create the 'server', 'Alice', and 'Bob' instances");
        this.Server = new TrustedServerRSA();
        this.pKS = Server.getPublicKey();
        System.out.println("=============== CREATING 'ALICE' INSTANCE OF THE RSA PROGRAM =============== ");
        this.Alice = new RSAProgram();
        this.pKA = Alice.getPublicKey();
        System.out.println("=============== NOW WE CREATE 'BOB' INSTANCE OF THE RSA PROGRAM =============== ");
        this.Bob = new RSAProgram();
        this.pKB = Bob.getPublicKey();
        Server.receivePublicKey(Alice.getPublicKey(), "ALICE");
        Server.receivePublicKey(Bob.getPublicKey(), "BOB");
    }

    public boolean run() {
        System.out.println("============ INITIALISATION FINISHED.START OF THE ACTUAL PART OF THE PROTOCOL ============");
        System.out.println("Step 1: Alice asks server for Bob's public key");
        Map<String, int[]> serverResponse = askServerForAPublicKey();
        System.out.println("Step 2: Server sent Alice a signed version of Bob's public key");
        if (!isMessageReallyFromServer(serverResponse)) {
            System.out.println("The message has been intercepted! Digital signature from server doesn't match the original");
            System.out.println("This protocol instance has been compromised, aborting the protocol. Try running the protocol again");
            return false;
        }
        int[] bobsPublicKey = serverResponse.get("unsignedPublicKey");
        System.out.println("Step 3.1: Alice creates her nonce and encrypts it using Bob's public key from the Trusted server");
        this.aliceNonce = createNonce(bobsPublicKey);
        this.aliceEncryptedNonce = RSAEncrypt(this.aliceNonce, bobsPublicKey[0], bobsPublicKey[1]);
        System.out.println("Step 3.2: Alice sends Bob her nonce..");
//        ...
        System.out.println("Step 4: Bob asks server for Alice's public key");
        Map<String, int[]> serverResponse2 = askServerForAPublicKey();
        System.out.println("Step 5: (if you asked server for 'ALICE'): Server sent Bob a signed version of Alice's public key");
        if (!isMessageReallyFromServer(serverResponse2)) {
            System.out.println("The message has been intercepted! Digital signature from server doesn't match the original");
            System.out.println("This protocol instance has been compromised, aborting the protocol. Try running the protocol again");
            return false;
        }
        int[] alicesPublicKey = serverResponse2.get("unsignedPublicKey");
        System.out.println("Step 6.1: Bob encrypts his nonce using Alice's public key from the Trusted server");
        this.bobNonce = createNonce(bobsPublicKey);
        this.bobEncryptedNonce = RSAEncrypt(this.bobNonce, alicesPublicKey[0], alicesPublicKey[1]);
        System.out.println("Step 6.2: Bob decrypts Alice's nonce using his private key");
        this.aliceNonceDecryptedByBob = Bob.RSAIntDecrypt(this.aliceEncryptedNonce);
        if (this.aliceNonceDecryptedByBob != this.aliceNonce) {
            System.out.println("ERROR! ALICE'S ORIGINAL NONCE DOESN'T MATCH WITH WHAT BOB HAS DECRYPTED!");
            System.out.println("Alice's original nonce: " + this.aliceNonce);
            System.out.println("Bob's decrypted nonce: " + this.aliceNonceDecryptedByBob);
            return false;
        } else {
            System.out.println("SUCCESS! WHAT BOB HAS DECRYPTED WITH HIS PRIVATE KEY MATCHES ALICE'S NONCE");
        }
        System.out.println("Step 6.3: Bob sends Alice her decrypted nonce and his encrypted nonce");
//        ...
        System.out.println("Step 7.1: Alice decrypts Bob's nonce using her private key");
        this.bobNonceDecryptedByAlice = Alice.RSAIntDecrypt(this.bobEncryptedNonce);
        if (this.bobNonceDecryptedByAlice != this.bobNonce) {
            System.out.println("ERROR! BOB'S ORIGINAL NONCE DOESN'T MATCH WITH WHAT ALICE HAS DECRYPTED!");
            System.out.println("Bob's original nonce: " + this.bobNonce);
            System.out.println("Alice's decrypted nonce: " + this.bobNonceDecryptedByAlice);
            return false;
        } else {
            System.out.println("SUCCESS! WHAT BOB HAS DECRYPTED WITH HIS PRIVATE KEY MATCHES ALICE'S NONCE");
        }
        System.out.println("Step 7.2: Alice sends Bob's decrypted nonce ");
//        ...
        System.out.println("================ END OF THE PROTOCOL ================");
        return true;
    }

    public int createNonce(int[] publicKey) {
        Scanner s = new Scanner(System.in);
        System.out.println("Please provide a nonce to be encrypted.");
        System.out.println("(NOTE: This algorithm is an academic example and thus for an input it only accepts INTEGER values that are LESS THAN 'N')");
        System.out.println("Please type in the number you want to be a message: ");
        while (true) {
            int message = s.nextInt();
            if (message < publicKey[1]) {
                return message;
            }
            System.out.println("Error! Please provide a correct int smaller than N as the nonce");
        }
    }

    public int RSAEncrypt(int message, int e, int n) {
        GFG rsaNonNaiveEncryption = new GFG(message, e, n);
        return rsaNonNaiveEncryption .result;
    }

    public boolean isMessageReallyFromServer(Map<String, int[]> serverResponse) {
        System.out.println("Comparing the signed public key with the original one ...");
        int[] signedPK = serverResponse.get("signedPublicKey");
        int[] originalPK = serverResponse.get("unsignedPublicKey");
        int[] candidatePK = new int[2];
        candidatePK[0] = this.Server.unSignMessage(signedPK[0]);
        candidatePK[1] = this.Server.unSignMessage(signedPK[1]);
        for (int i = 0; i < 2; i++) {
            if (candidatePK[i] != originalPK[i]) {
                System.out.println("At index:" + i + ": candidatePK doesn't match original pk !");
                System.out.println("Candidate PK value: " + candidatePK[i]);
                System.out.println("Original PK value: " + originalPK[i]);
                return false;
            }
        }
        System.out.println("Decrypted signed PK with Trusted Server's public key matched the original PK.");
        System.out.println("RESULT: MESSAGE VERIFIED TO BE FROM THE TRUSTED SERVER");
        return true;
    }

    public Map<String, int[]> askServerForAPublicKey() {
        Scanner s = new Scanner(System.in);
        System.out.println("[SERVER]: WHO'S PUBLIC KEY SHALL I SEND YOU? (Type 'ALICE' or 'BOB')");
        while (true) {
            String input = s.next().toUpperCase();
//          TODO: Check if the comparison works as expected !
            if (input.equals("ALICE") || input.equals("BOB")) {
                return this.Server.sendPublicKey(input);
            }
            System.out.println("There is no public key of a person called:" + '"' + input + '"' + "on the server. Please, try again with 'ALICE' or 'BOB'");
        }
    }

    class TrustedServerRSA extends RSAProgram {
        private final int NUM_OF_ITERATIONS = 64;
        private int[] PKA;
        private int[] PKB;

        TrustedServerRSA() {
            super();
        }

        @Override
        void providePandQ() {
            System.out.println("Initialising server's RSA procedure... [expected result: server will have it's public and private keys]");
            MillerRabin mr = new MillerRabin();
            drawRandomPAndQ(mr);
        }

        public void receivePublicKey(int[] pk, String whosePublicKey) {
            switch (whosePublicKey) {
                case "ALICE":
                    this.PKA = pk;
                    break;
                case "BOB":
                    this.PKB = pk;
                    break;
                default:
                    System.out.println("This example server cannot store a person called:" + '"' + whosePublicKey + '"' + "Please, try again with either 'ALICE' or 'BOB' ");
                    break;
            }
        }

        public Map<String, int[]> sendPublicKey(String whosePublicKey) {
            int[] publicKey = new int[2];
            switch (whosePublicKey) {
                case "ALICE":
                    publicKey = this.PKA;
                    break;
                case "BOB":
                    publicKey = this.PKB;
                    break;
                default:
                    System.out.println("There is no public key of a person called:" + '"' + whosePublicKey + '"' + "on the server. Please, try again with a different name");
                    break;
            }
            int[] signedPublicKey = new int[2];
            signedPublicKey[0] = signMessage(publicKey[0]);
            signedPublicKey[1] = signMessage(publicKey[1]);
            Map<String, int[]> serverResponse = new HashMap<String, int[]>();
            serverResponse.put("signedPublicKey", signedPublicKey);
            serverResponse.put("unsignedPublicKey", publicKey);
            return serverResponse;
        }

        public int signMessage(int message) {
            GFG nonNaiveMessageSignature = new GFG(message, this.d, this.n);
            return nonNaiveMessageSignature.result;
        }

        public int unSignMessage(int signedMessage) {
            GFG nonNaiveMessageUnsigning = new GFG(signedMessage, this.e, this.n);
            return nonNaiveMessageUnsigning.result;
        }

        /**
         * FUNCTION DESCRIPTION:
         * This func draws numbers p and q.
         * Next, it checks if both nums are sufficiently "far" away
         * from each other in terms of the value.
         * Finally, it performs Rabin-Miller Primality tests on both p & q
         * To ensure almost ~100% guarantee both are prime numbers.
         */
        private void drawRandomPAndQ(MillerRabin mr) {
            boolean pAndQFarAway = false;
            boolean pIsPrime = false;
            boolean qIsPrime = false;
            final double FAR_ENOUGH_FACTOR = 0.33;
            int pCandidate = 0;
            int qCandidate = 0;
            int randomDrawSpace = 45000;
            System.out.println("SPACE: " + randomDrawSpace);
            System.out.println("Randomly drawing p and q passing primality test. PLEASE WAIT ...");
            while (true) {
                System.out.println("NEW TRY");
                pAndQFarAway = false;
                pIsPrime = false;
                qIsPrime = false;
                pCandidate = (int) (Math.random() * randomDrawSpace + 2);
                qCandidate = (int) (Math.random() * randomDrawSpace + 2);
                if (Math.abs(pCandidate - qCandidate) > (int) (FAR_ENOUGH_FACTOR * randomDrawSpace)) {
                    pAndQFarAway = true;
                } else {
                    continue;
                }
                System.out.println("(1) Running primality check on:" + pCandidate);
                pIsPrime = mr.isPrime(pCandidate, this.NUM_OF_ITERATIONS);
                if (!pIsPrime) continue;
                System.out.println("(2) Running primality check on:" + qCandidate);
                qIsPrime = mr.isPrime(qCandidate, this.NUM_OF_ITERATIONS);
                if (qIsPrime) {
                    break;
                }
            }
            this.p = pCandidate;
            this.q = qCandidate;
            System.out.println("Generated p & q. \n p:" + p + "\n q:" + q);
        }
    }
}
