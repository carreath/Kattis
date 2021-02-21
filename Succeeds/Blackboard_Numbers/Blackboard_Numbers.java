import java.util.*;
import java.io.*;

public class Blackboard_Numbers {
	public Blackboard_Numbers() {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int i=0; i<T; i++) {
			long numerator = 0;
			long denominator = 0;

			String in = sc.next();

			if (in.replaceAll("[01]*", "").equals("")) {
				denominator++;
				numerator += (isPrime(Long.parseLong(in, 2)))? 1: 0;
			}
			if (in.replaceAll("[0-7]*", "").equals("")) {
				denominator++;
				numerator += (isPrime(Long.parseLong(in, 8)))? 1: 0;
			}
			if (in.replaceAll("[0-9]*", "").equals("")) {
				denominator++;
				numerator += (isPrime(Long.parseLong(in, 10)))? 1: 0;
			}
			if (in.replaceAll("[0-9A-F]*", "").equals("")) {
				denominator++;
				numerator += (isPrime(Long.parseLong(in, 16)))? 1: 0;
			}

			if (numerator == 0) {
				System.out.println("0/1");
			} else {
				long gcd = gcd(numerator, denominator);
				System.out.println((numerator/gcd) + "/" + (denominator/gcd));
			}
		}
	}

	public long gcd(long a, long b) {
		if (b == 0) {
			return a;
		}

		return gcd(b, a%b);
	}

	public boolean isPrime(long number) {
		if (number == 2) {
			return true;
		} else if (number == 1 || number % 2 == 0) {
			return false;
		}

		long n = (long) Math.sqrt(number) + 1;
		for (long i=3; i<n; i+=2) {
			if (number % i == 0) {
				return false;
			}
		}

		return true;
	}

	public static void main(String[] args) {
		new Blackboard_Numbers();
	}
}