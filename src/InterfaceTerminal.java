import java.lang.*;
import java.io.*;

public class InterfaceTerminal {
	
	private static final String TITLE = "\n2910326 Computer Security coursework\n"+
										" by Karleyon_Hall_033449994, Grzgorz_Rybak_03, Lendl_Munoz_03 \n"+ //firstname-FAMILYNAME_SRN\n\n"+
										"\t********************\n"+
										"\t1. Part 1 \n" +
										"\t2. Part 2 \n"+
										"\t0. Exit \n"+
										"\t********************\n"+
										"Please input a single digit (0-2):\n";
	
	
	InterfaceTerminal(){
		int selected=-1;
		while (selected !=0) {
			System.out.println(TITLE);
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			try {
					selected = Integer.parseInt(in.readLine());
					switch(selected) {
					case 1: part1();
					break;
					case 2: part2();
					break;
				} }
				catch(Exception ex) {} } // end while
				System.out.println("Bye!"+"You have left the program");
				}
				// Modify the types of the methods to suit your purposes...
				private void part1() {
				System.out.println("in part1");
				/*part A
				 * need to instantiate certain instances so that the program can be run
				 * i am unsure of what they are
				*/
				}
				private void part2() {
				System.out.println("in part2");
				/*part B
				 * this should contain just a call to generate an output to the terminal
				 * all wrapped up in a single call
				 * protocal.run()
				*/
				}
				
		
		
	
	public static void main (String[] args) {
		new InterfaceTerminal();
	}
	
}
