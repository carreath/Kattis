import java.util.*;
import java.io.*;

public class Odd_And_Even_Zeroes {
	private final long[] powersOfFive = new long[]{1l,5l,25l,125l,625l,3125l,15625l,78125l,390625l,
									1953125l,9765625l,48828125l,244140625l,1220703125l,6103515625l,
									30517578125l,152587890625l,762939453125l,3814697265625l,
									19073486328125l,95367431640625l,476837158203125l,2384185791015625l,
									11920928955078125l,59604644775390625l,298023223876953125l};
	
	public Odd_And_Even_Zeroes() {
		Scanner sc = new Scanner(System.in);

		// Calculates the number of even digits in a range from 0 to (5^i - 1)
		// [10,14] ==> 14 ~= 5^1.6, i=1, range has 5 even digits
		// [0,4] also has 5
		// but [5,9] has 0 because of the following:
		// There is a parity flip for each multiple of 5
		// odd powers of 5 flip the parity by a multiple of 5 on the previous count
		// Even powers of the range all increases by 3*evenDigitsInRange[i-1] + 2 * (5^i - evenDigitsInRange[i-1])
		// therefore, ranges [0,4], and [10,14] have 5 even trailing zero numbers each
		// and ranges[0,24],[25,49],[50,74] all have 15 (5^2)
		// The next ranges [0,124],[125,249],[250,324] alternate 0, 75 (5^3)
		// next range all have 325 (5^4)
		// etc.. 1625 alternating, 7875 same
		// Odd powers alternate
		// Even powers stay the same
		long[] evenDigitsInRange = new long[27];
	    evenDigitsInRange[0] = 1;
	    for (int i = 1; i < 27; i++) {
	      if (i % 2 == 1) {
	        evenDigitsInRange[i] = 5 * evenDigitsInRange[i - 1];
	      } else {
	        evenDigitsInRange[i] = 3 * evenDigitsInRange[i - 1] + 2 * ((long) Math.pow(5, i - 1) - evenDigitsInRange[i - 1]);
	      }
	    }

	    System.out.println(Arrays.toString(evenDigitsInRange));

		long n = 0;
		while ((n=sc.nextLong())!=-1) {
			// Find power parity of n
			int power = 0;
			for (int i=0; i<powersOfFive.length && n > 4; i++) {
				if (n < powersOfFive[i]) {
					power = i-1;
					break;
				}
				if (i == powersOfFive.length - 1) {
					power = i;
				}
			}

			long count = 0;
			boolean even = true;
			while (power >= 0) {
				long base5Digit = n/powersOfFive[power];
				for (int i=0; i < base5Digit; i++) {
					if (even) {
						count += evenDigitsInRange[power];
					} else {
						count += (long)(Math.pow(5, power)) - evenDigitsInRange[power];
					}
					if (power % 2 == 1) {
						even = !even;
					}
				}

				n -= powersOfFive[power] * base5Digit;
				power--;
			}
			if (even) {
				count++;
			}

			System.out.println(count);
		}
	}

	public static void main(String[] args) {
		new Odd_And_Even_Zeroes();
	}
}