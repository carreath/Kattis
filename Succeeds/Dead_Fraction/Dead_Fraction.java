import java.util.*;
import java.io.*;

public class Dead_Fraction {
	public Dead_Fraction() {
		Scanner sc = new Scanner(System.in);

		String in = "";
		while ((in = sc.nextLine()).contains("...")) {
			String decimalPart = in.substring(0, in.length() - 3);
			double decimal = Double.parseDouble(decimalPart);

			long minNumerator = Long.MAX_VALUE;
			long minDenominator = Long.MAX_VALUE;

			int numDigits = decimalPart.length() - 2;
			long rightSide = (long)Math.round(decimal * Math.pow(10, numDigits));
			long middle = rightSide;
			for (int i=1; i<=numDigits; i++) {
				middle /= 10;
				long numerator = rightSide - middle;
				long denominator = (long)Math.pow(10, numDigits - i)*((long)Math.pow(10, i) - 1);
				long gcd = gcd(numerator, denominator);
				if (denominator/gcd < minDenominator) {
					minNumerator = numerator/gcd;
					minDenominator = denominator/gcd;
				}
			}

			System.out.println(minNumerator + "/" + minDenominator);
		}
	}

	public long gcd(long a, long b) {
		if (a == 0) {
			return b;
		}
		return gcd(b%a, a);
	}

	public static void main(String[] args) {
		new Dead_Fraction();
	}
}