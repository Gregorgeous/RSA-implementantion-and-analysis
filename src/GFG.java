/** DISCLAIMER: THE FOLLOWING PIECE OF CODE IN THIS CLASS WAS NOT WRITTEN BY THE GROUP
 *  CODE TAKEN FROM: https://www.geeksforgeeks.org/multiply-large-integers-under-large-modulo/
 ** Java Program to perform the RSA encryption/decryption formula when the exponent is really big and overflows the 'int' datatype.
 **/

import java.io.*;

class GFG
{
    public int result;
    // Function to find power
    private static int power(long x,
                              long y, long p)
    {
        long res = 1; // Initialize result

        // Update x if it is more
        // than or equal to p
        x = x % p;

        while (y > 0)
        {
            // If y is odd, multiply
            // x with the result
            if ((y & 1) > 0)
                res = (res * x) % p;

            // y must be even now
            y = y >> 1; // y = y/2
            x = (x * x) % p;
        }
        return (int) res;
    }

    GFG(int m, int power, int mod)
    {
        String sPower = String.valueOf(power);

        int remainderB = 0;

        for (int i = 0; i < sPower.length(); i++)
            remainderB = (remainderB * 10 +
                    sPower.charAt(i) - '0') %
                    (mod - 1);

        this.result = power(m, remainderB, mod);
    }
} 