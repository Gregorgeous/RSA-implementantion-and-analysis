import java.util.*; //  allows me to use a scanner


public class PrimeNumberGenerator {
	
	//some function to check if number entered is prime - done
	//convert the function to check if p is prime and store it - stored globally, might need to change
	//convert the function to check if q is prime and store it - stored globally, might need to change
	// multiply the primes p and q to generate a number n - done
	// find the totient of n - done

	static int p;
	static int q;


	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.print("please enter the value for p, Make sure it is a PRIME!: ");
		p = s.nextInt();
		System.out.print("please enter the value for q, Make sure it is also a PRIME!: ");
		q = s.nextInt();
		if(isPrime(p) && isPrime(q)) {
			System.out.println("p is: "+ p +" and q is: " + q +". both are Primes. ");
		}else if(isPrime(p) && !isPrime(q)){
			System.out.println("p is: "+ p + " and q is: "+ q +". q is not a prime, please make sure both p and q are Primes. ");
		}
		else if(!isPrime(p) && isPrime(q)) {
			System.out.println("p is: "+ p + " and q is: "+ q +". p is not a prime, please make sure both p and q are Primes. ");
		}else {
			System.out.println(" and p is: "+ p + "q is: "+ q +". Both are not Primes, please enter a different number. ");

		}
		
		// need an if statement to control when this happens
		// should only multiply if both p and q are Primes
		int  n = p*q;
		if(isPrime(p) && isPrime(q)) {
			System.out.println("the value of n is: "+n);
			System.out.println("the totient of n is: "+getTotient(p,q));
		}
		else {
			System.out.println("p and q are not primes");
		}
		 
		//System.out.println("the totient of n is: "+getTotient(p,q));
		//System.out.println(4*6);
	}

	public static boolean isPrime(int p){
		if(p <= 1) {
			return false;
		}
			for(int i = 2; i < Math.sqrt(p);i++) {
				if(p % i == 0) {
					return false;
				}
			}
		return true;
		}
	
	// Euler's Totient is a smaller value of n, the calculation being p-1 * q-1
	public static int getTotient(int p, int q) {
		int pN = p-1;
		int qN = q-1;
		int phiN = pN * qN;
		return phiN;
	}		
	

}

