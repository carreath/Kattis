import java.util.*;
import java.io.*;

public class Prinova {
	public int[] P;

	public Prinova() {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		P = new int[N];
		for (int i=0; i<N; i++) {
			P[i] = sc.nextInt();
		}

		Arrays.sort(P);

		int A = sc.nextInt();
		int B = sc.nextInt();

		int lowerA = A + (A+1) % 2;
		int upperB = B - (B+1) % 2;

		int maxName = lowerA;
		int upperBound = Math.abs(checkEqn(lowerA) - lowerA);
		int lowerBound = Math.abs(checkEqn(upperB) - upperB);

		if (lowerBound > upperBound) {
			upperBound = lowerBound;
			maxName = upperB;
		}

		for (int i=1; i<N; i++) {
			int name = P[i] + P[i-1];
			name /= 2;

			if (name % 2 == 0) {
				name--;
			}
			if (name < A) {
				continue;
			}
			if (name > B) {
				break;
			}
			int val = Math.abs(name - P[i-1]);
			if (val > upperBound) {
				upperBound = val;
				maxName = name;
			}
		}

		System.out.println(maxName);
	}

	public int checkEqn(int X) {
		int upperBound = Integer.MAX_VALUE;
		int maxName = X;
		for (int p : P) {
			if (Math.abs(X - p) < upperBound) {
				upperBound = Math.abs(X - p);
				maxName = p;
			}
		}
		return maxName;
	}

	public static void main(String[] args) {
		new Prinova();
	}
}