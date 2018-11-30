import java.util.*;

public class RSAProgram {
    int p;
    int q;
    int n;
    int phiN;
    int e;
    int d;

    RSAProgram() {
        providePandQ();
        //======================= N  ======================================
        this.n = generateN();
        //======================= Totient (phi) of N  =====================
        this.phiN = getTotient(p, q);
        //====================== Public key  ==============================
        this.e = generateE(this.phiN);
        //====================== Private key ==============================
        this.d = generatePrivateKey(this.e, this.phiN);
    }

    public int RSAIntDecrypt (int cipher) {
        return (int) Math.pow(cipher, this.d) % this.n;
    }

    public int[] getPublicKey(){
        return new int [] {this.e, this.n};
    }

    int generateN(){
        System.out.println("Step 2: Program generates 'n' from the given p & q");
        int n = this.p * this.q;
        System.out.println("n = " + n);
        return n;
    }

    void providePandQ(){
        System.out.println("STEP 1: PROVIDE P AND Q");
        System.out.println("Instructions: Please provide integers p & q such that both of them are prime numbers.\n " +
                "(This program presents an academic version of the RSA, therefore accepts only Integers up to a value: 2,147,483,647)");
        Scanner s = new Scanner(System.in);
        while (true) {
            System.out.print("please enter the value for p, Make sure it is a PRIME!: ");
            this.p = s.nextInt();
            System.out.print("please enter the value for q, Make sure it is also a PRIME!: ");
            this.q = s.nextInt();
            if (isPrime(p) && isPrime(q)) {
                System.out.println("p is: " + p + " and q is: " + q + ". both are Primes. ");
                break;
            } else if (isPrime(p) && !isPrime(q)) {
                System.out.println("p is: " + p + " and q is: " + q + ". q is not a prime, please make sure both p and q are Primes. ");
            } else if (!isPrime(p) && isPrime(q)) {
                System.out.println("p is: " + p + " and q is: " + q + ". p is not a prime, please make sure both p and q are Primes. ");
            } else {
                System.out.println(" and p is: " + p + "q is: " + q + ". Both are not Primes, please enter a different number. ");
            }
        }
    }

    private boolean isPrime(int p) {
        if (p <= 1) {
            return false;
        }
        for (int i = 2; i < Math.sqrt(p); i++) {
            if (p % i == 0) {
                return false;
            }
        }
        return true;
    }

    // Euler's Totient is a smaller value of n, the calculation being p-1 * q-1
    private int getTotient(int p, int q) {
        System.out.println("Step 3: Program generates totient of n (also called 'phi of n')\n" +
                "phi(n) is a product of (p-1) * (q-1) ");
        int pN = p - 1;
        int qN = q - 1;
        phiN = pN * qN;
        System.out.println("Phi(n) = " + phiN);
        System.out.println("(*NOTE*: this information is confidential and made as such \n " +
                "by the program by labelling both the function and the end variable as 'private'. \n" +
                "It is outputed here only for a demonstration of how the program works) ");
        return phiN;
    }


    private int generateE(int r) { // r being Totient of N.
        System.out.println("Step 4: Program generates e used in composing the public key: PK =(e,n)");
                // standard public key as it is a large prime number.
        int E = 65537;

        // if 65537 is too large then we will half it until it is smaller than r.
        while (E >= r) {
            E = E / 2;
        }
        // increment until E is a prime number.
        while (!(isPrime(E) && gcd(r, E) == 1)) {
            E += 1;
        }

        System.out.println("e = " + E);
        return E;
    }

    private int gcd(int r, int E) {
        if (E == 0) {
            return r;
        }
        return gcd(E, r % E);
    }


    /**
     * FUNCTION DESCRIPTION:
     * Generates a private key "d" by using a Euclidean Extended Algorithm.
     * Follows the procedure from this video: https://youtu.be/Z8M2BTscoD4?t=668
     *
     * @param e   is the value e from the user's public key; we assume the public key is a pair of values (e, n).
     * @param phi is the totient of n, in other words, it's phi(n) [phi of n].
     * @return whatever is the value of 'row2Right' variable as soon as row2Left becomes 1 .
     */
    private static int generatePrivateKey(int e, int phi) {
        System.out.println("Step 5: Program generates a private key d by using Extended Euclidean Algorithm \n" +
                "and the previously generated e and phi(n)");
        int row1Left = phi;
        int row1Right = phi;
        int row2Left = e;
        int row2Right = 1;

        while (row2Left != 1) {
            int intDivision = row1Left / row2Left;
            int reducerLeft = row2Left * intDivision;
            int reducerRight = row2Right * intDivision;
            int newNumLeft = row1Left - reducerLeft;
            int newNumRight = row1Right - reducerRight;
//          If ever newly created nums turn negative, perform x mod phi on them to turn them positive again.
            if (newNumLeft < 0) {
                newNumLeft %= phi;
//              This loop is necessary because Java takes the negative modulus of the value...
//              Example: -34 (mod 40) and 6 (mod 40) are the same. We want the positive one but Java gives the negative one
//              Thus we keep adding the whole modulus number to newNum until it becomes negative.
//              Example: -34 + 40 = 6 just as -34 % 40 = 6.
                while (newNumLeft < 0) newNumLeft += phi;
            }
            if (newNumRight < 0) {
                newNumRight %= phi;
                while (newNumRight < 0) newNumRight += phi;
            }
            row1Left = row2Left;
            row1Right = row2Right;
            row2Left = newNumLeft;
            row2Right = newNumRight;
        }
        System.out.println("d = " + row2Right);
        System.out.println("(*NOTE*: this information is confidential and made as such \n " +
                "by the program by labelling both the function and the end variable as 'private'. \n" +
                "It is outputed here only for a demonstration of how the program works) ");
        return row2Right;
    }

    public int getPubKey() { return e; }

    public int getPriKey() { return d; }

    public int getN() { return n; }

    public int getPhiN() { return phiN; }
}
