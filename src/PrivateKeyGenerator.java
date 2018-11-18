public class PrivateKeyGenerator {

/** FUNCTION DESCRIPTION:
 * Generates a private key "d" by using a Euclidean Extended Algorithm.
 * Follows the procedure from this video: https://youtu.be/Z8M2BTscoD4?t=668
 * @param e is the value e from the user's public key; we assume the public key is a pair of values (e, n).
 * @param phi is the totient of n, in other words, it's phi(n) [phi of n].
 * @return whatever is the value of 'row2Right' variable as soon as row2Left becomes 1 .
 */
    public static int generatePrivateKey (int e, int phi){
        int row1Left = phi;
        int row1Right = phi;
        int row2Left = e;
        int row2Right = 1;

        while (row2Left != 1){
            int intDivision = row1Left/ row2Left;
            int reducerLeft = row2Left * intDivision; int reducerRight = row2Right * intDivision;
            int newNumLeft = row1Left - reducerLeft; int newNumRight = row1Right - reducerRight;
//          If ever newly created nums turn negative, perform x mod phi on them to turn them positive again.
            if (newNumLeft < 0) {
                newNumLeft %= phi;
//              This loop is necessary because Java takes the negative modulus of the value...
//              Example: -34 (mod 40) and 6 (mod 40) are the same. We want the positive one but Java gives the negative one
//              Thus we keep adding the whole modulus number to newNum until it becomes negative.
//              Example: -34 + 40 = 6 just as -34 % 40 = 6.
                while (newNumLeft  < 0)  newNumLeft += phi;
            }
            if (newNumRight < 0) {
                newNumRight %= phi;
                while (newNumRight  < 0)  newNumRight+= phi;
            }
            row1Left = row2Left; row1Right = row2Right;
            row2Left = newNumLeft; row2Right = newNumRight;
        }
        return row2Right;
    }

    public static void main(String[] args)
    {
//      TODO: Integrate it with the other components.
        int e = 7; int phi = 40; // TODO: this is temporary until not integrated with Scanner program asking for p & q params
        System.out.println(generatePrivateKey (e, phi));
    }
}
