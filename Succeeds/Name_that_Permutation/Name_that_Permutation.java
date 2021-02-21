import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;
import static java.lang.System.out;

public class Name_that_Permutation {
	BigInteger[] facts = new BigInteger[51];
	ArrayList<Integer> outputList;

	public Name_that_Permutation() {
		Scanner sc = new Scanner(System.in);

		for (int i=0; i<51; i++) {
			factorial(i);
		}

		while (sc.hasNextInt()) {
			int n = sc.nextInt();
			BigInteger k = new BigInteger(sc.next());

			outputList = new ArrayList<>();
			for (int i=0; i<n; i++) {
				outputList.add(i + 1);
			}

			for (int i=0; i<n; i++) {
				int j = k.divide(facts[n-i-1]).intValue();
				k = k.mod(facts[n-i-1]);

				System.out.print(outputList.get(j) + " ");
				outputList.remove(j);
			}
			System.out.println();
		}
	}

	public void factorial(int n) {
		if (n > 0) {
			facts[n] = facts[n-1].multiply(new BigInteger(n + ""));
		}
		facts[0] = BigInteger.ONE;
	}
	
	public static void main(String[] args) {
		new Name_that_Permutation();
	}
}