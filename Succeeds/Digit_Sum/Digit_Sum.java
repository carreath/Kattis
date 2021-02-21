import java.util.*;
import java.io.*;

public class Digit_Sum {
	private final long[] base = new long[]{0,1,3,6,10,15,21,28,36,45};

	public Digit_Sum() {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		long prev = 0;
		for (int i=0; i<T; i++) {
			long a = sc.nextLong();
			long b = sc.nextLong();
			
			System.out.println((sum(b) - sum(a-1)));
		}
	}

	public long sum(long num) {
		if (num <= 0) {
			return 0;
		}

		long sum = 0;
		long position = 1;
		long remainder = 0;
		while (num/10 != 0) {
			long q = num/10;
			long r = num%10;
			sum += position * base[9] * q + r * (remainder + 1);

			if (r != 0) {
				sum += base[(int)(r-1)]*position;
			}

			num = q;
			remainder += position * r;
			position *= 10;
		}
		sum += position * base[(int)num-1] + num * (remainder + 1);
		return sum;
	}

	public static void main(String[] args) {
		new Digit_Sum();
	}
}