public class publicKey {
    private int E;
    private int totientN;
    public publicKey(int r) {
        totientN = r;
        E = generateE(totientN);
    }

    public int pubKey() {
        return E;
    }

    public int totN() {
        return totientN;
    }

    private int generateE(int r) { // r being Totient of N.
//        System.out.println("Totient of N is " + r);

        // standard public key as it is a large prime number.
        int E = 65537;

        // if 65537 is too large then we will half it until it is smaller than r.
        while(E >= r) {
            E = E / 2;
        }
        // increment until E is a prime number.
        while(!(isPrime(E))) {
            E += 1;
        }

        return E;
    }

    // function for checking the global common denominator.
    private int gcd(int r, int E) {
        if(E == 0) {
            return r;
        }
        return gcd(E, r%E);
    }

    private boolean isPrime(int n) {
        if(n%2 == 0) return false;
        for(int i = 3; i*i <= n; i += 2) {
            if(n % i == 0) return false;
        }
//        System.out.println(n + "is Prime.");
        return true;
    }
}
