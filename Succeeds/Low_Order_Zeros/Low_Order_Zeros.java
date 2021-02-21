import java.util.*;
import java.io.*;

public class Low_Order_Zeros {
	private final int base[] = {1, 1, 2, 6, 4, 2, 2, 4, 2, 8};

	public Low_Order_Zeros() {
		Scanner sc = new Scanner(System.in);

		int i = 0;
		while ((i = sc.nextInt()) != 0) {
			System.out.println(lastNonZeroDigit(i));
		}
	}

	public int lastNonZeroDigit(int i) {
		if (i < 10) {
			return base[i];
		}

		int numFives = i/5;
		int numTens = i/10;
		int remainder = i%10;
		int missingTwos = 6 - 2 * (numTens%2);

		return (missingTwos * lastNonZeroDigit(numFives) * lastNonZeroDigit(remainder)) % 10;
	}

	public static void main(String[] args) {
		new Low_Order_Zeros();
	}
}