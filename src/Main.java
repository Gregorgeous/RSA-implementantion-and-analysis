import java.lang.*;
import java.io.*;
import java.util.*;

public class Main {

    private static final String TITLE = "\nIS53012B/S Computer Security coursework\n" +
            " by: Karleyon Hall, Grzegorz Rybak, Lendl Munoz \n" +
            "Please input a single digit from 0 to 2:\n" +
            "\t********************\n" +
            "\t1: Part 1: RSA Program demonstration \n" +
            "\t2: Part 2: RSA-based Protocol demonstration \n" +
            "\t0: Exit program\n" +
            "\t********************\n";


    Main() {
        int selected = -1;
        while (selected != 0) {
            System.out.println(TITLE);
            Scanner s = new Scanner(System.in);
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            try {
                selected = Integer.parseInt(in.readLine());
                switch (selected) {
                    case 1:
                        part1();
                        System.out.println("========== END OF PART 1. RETURNING TO THE MENU ==========");
                        break;
                    case 2:
                        part2();
                        System.out.println("========== END OF PART 2. RETURNING TO THE MENU ==========");
                        break;
                }
            } catch (Exception ex) {
                System.out.println("Typed a wrong character, expected an integer 0,1 or 2. Exiting the program. Run the program again and type the input correctly");
                break;
            }
        }
        System.out.println("Bye!");
    }

    private void part1() {
        System.out.println("You selected part 1: RSA program Demonstration");
        RSAProgram RSADemo = new RSAProgram();
    }

    private void part2() {
        System.out.println("You selected part 2: RSA-based Protocol demonstration");
        Protocol protocol = new Protocol();
        protocol.run();
    }


    public static void main(String[] args) {
        new Main();
    }

}

