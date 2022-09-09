import java.util.*;
public class Brackets {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder brackets = new StringBuilder(sc.nextLine());
		int n = brackets.length();
		if (n % 2 == 1) {
			System.out.println("impossible");
		} else {
			int openDepth = 0;
			int closeDepth = 0;
			int[] openDepths = new int[n + 3];
			int[] closeDepths = new int[n + 3];
			boolean closes = true;
			boolean opens = true;
			for (int i=0; i<n; i++) {
				if (brackets.charAt(i) == '(') {
					openDepth++;
				} else {
					openDepth--;
				}

				if (brackets.charAt(n - i - 1) == '(') {
					closeDepth--;
				} else {
					closeDepth++;
				}

				if (closeDepth < 0) {
					closes = false;
				}

				if (closes) {
					closeDepths[n - i] = closeDepth;
				} else {
					closeDepths[n - i] = -20000;
				}

				if (openDepth < 0) {
					opens = false;
				}

				if (opens) {
					openDepths[i+1] = openDepth;
				} else {
					openDepths[i+1] = -20000;
				}
			}

			boolean possible = false;
			for (int i=0; i<n; i++) {
				if (possible) break;
				int tempDepth = openDepths[i];
				for (int j=i; j<n; j++) {
					if (brackets.charAt(j) == '(') {
						tempDepth--;
					} else {
						tempDepth++;
					}
					if (tempDepth < 0) {
						break;
					}
					if (closeDepths[j + 2] < 0) {
						continue;
					}
					if (tempDepth == closeDepths[j + 2]) {
						possible = true;
						break;
					}
				}
			}
			if (possible || (opens && closes)) {
				System.out.println("possible");
			} else {
				System.out.print("impossible");
			}
		}
	}
}
