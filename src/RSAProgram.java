import java.util.*; //  allows me to use a scanner

public class RSAProgram {
    private int p;
    private int q;
    private int n;
    private int phiN;
    private int e;
    private int d;

    //    static private boolean inputCorrect = false;
    RSAProgram() {
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
        //=======================Totient (phi) of N =======================
        this.n = p * q;
        this.phiN = getTotient(p, q);
        System.out.println("The totient of n is: " + phiN);
        //====================== Public key  ==============================
        this.e = generateE(this.phiN);
        //====================== Private key ==============================
        this.d = generatePrivateKey(this.e, this.phiN);
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
        int pN = p - 1;
        int qN = q - 1;
        return pN * qN;
    }


    private int generateE(int r) { // r being Totient of N.
//        System.out.println("Totient of N is " + r);

        // standard public key as it is a large prime number.
        int E = 65537;

        // if 65537 is too large then we will half it until it is smaller than r.
        while (E >= r) {
            E = E / 2;
        }
        // increment until E is a prime number.
        while (!(isPrime(E))) {
            E += 1;
        }

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
    public static int generatePrivateKey(int e, int phi) {
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
        return row2Right;
    }
}
