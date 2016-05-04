import java.util.Scanner;
import java.io.IOException;

/**
 *
 * @author sarita joshi
 */
// Implement the Extended form of Euclid GCD algorithm : for two input
// integers a; b, return integers m; n such that GCD(a; b) = m x a + n x b.

public class HW13 {
    // Function that gives the GCD of two numbers, num1 and num2
        static long GCD(long num1, long num2) {
	if(num1 == 0) 
            return num2;
	return GCD(num2%num1, num1);
	}

    // Extended GCD function that gives the roots as required in the question
	static void extendedGCD(long num1, long num2) {
		long x = 0, y = 1;
		long last_x = 1, last_y = 0, temp;
		while(num1 != 0) {
                    long k = num2/num1;
                    long l = num2%num1;
                    num2 = num1;
                    num1 = l;
                    temp = x;
                    x = last_x - k * x;
                    last_x = temp;
                    temp = y;
                    y = last_y - k *y;
                    last_y = temp;
		}
		System.out.println("Values of roots -> x: " + last_x + " y: " + last_y);
	}
        
        public static void main(String[] args) throws IOException {
            // Scanner to read the input numbers from user
		Scanner scanner = new Scanner(System.in);
		System.out.println("ENTER FIRST NUMBER->");
		long num1 = scanner.nextLong();
		System.out.println("ENTER SECOND NUMBER->");
		long num2 = scanner.nextLong();	
		if(num2 > num1) {
			System.out.println("GCD of two numbers " + num1 +" and " + num2 +" is :" + GCD(num1, num2));
			extendedGCD(num1, num2);
		}
		else {
			System.out.println("GCD of two numbers " + num1 +" and " + num2 +" is :" + GCD(num2, num1));
			extendedGCD(num2, num1);
		}
	}
}
