import java.util.*;
import java.io.*;

public class Power_Eggs {
	public Power_Eggs() {
		Scanner sc = new Scanner(System.in);

		long[][] powers = new long[33][33];
		for (int i=0; i<33; i++) {
			powers[i][0] = 1;
			powers[0][i] = 1;
		}
		
		for (int i=1; i<33; i++) {
			for (int j=1; j<33; j++) {
				powers[i][j] = powers[i-1][j-1] + powers[i-1][j];
			}
		}

		long T = sc.nextInt();
		for (int i=0; i<T; i++) {
			long N = sc.nextInt();
			long K = sc.nextInt();

			long min = 0;
			long max = 32;
			while (min <= max) {
				long middle = (max + min) / 2;
				if (N <= powers[(int)middle][(int)K] - 1) {
					max = middle - 1;
				} else {
					min = middle + 1;
				}
			}

			if (min >= 33) {
				System.out.println("Impossible");
			} else {
				System.out.println(min);
			}
		}
	}

	public static void main(String[] args) {
		new Power_Eggs();
	}
}