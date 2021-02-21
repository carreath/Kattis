import java.util.*;
import java.io.*;

public class Bike_Gears {
	public Bike_Gears() {
		Scanner sc = new Scanner(System.in);

		int F = sc.nextInt();
		int B = sc.nextInt();

		int[] front = new int[F];
		for (int i=0; i<F; i++) {
			front[i] = sc.nextInt();
		}

		int[] back = new int[B];
		for (int i=0; i<B; i++) {
			back[i] = sc.nextInt();
		}

		ArrayList<Gear> gears = new ArrayList<>();
		for (int i=F-1; i>=0; i--) {
			for (int j=0; j<B; j++) {
				gears.add(new Gear(front[i], back[j]));
			}
		}
		Collections.sort(gears);

		for (Gear gear : gears) {
			System.out.println(gear);
		}
	}

	public static void main(String[] args) {
		new Bike_Gears();
	}

	private class Gear implements Comparable<Gear> {
		long f;
		long b;
		long numerator;

		public Gear(int f, int b) {
			this.f = (long)f;
			this.b = (long)b;
		}

		public int compareTo(Gear other) {
			numerator = f*other.b;
			other.numerator = other.f*b;

			if (numerator < other.numerator) {
				return -1;
			} else if (numerator == other.numerator) {
				return 0;
			} else {
				return 1;
			}
		}

		public double ratio() {
			return (double)f/(double)b;
		}

		public String toString() {
			return "(" + f + "," + b + ")";
		}
	}
}