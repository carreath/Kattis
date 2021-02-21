import java.util.*;
import java.io.*;

public class Pokeball_Fever {
	double P = 0.0;
	double[][] arr = new double[102][102];
	public Pokeball_Fever() {
		Scanner sc = new Scanner(System.in);

		int pokemon = sc.nextInt();
		double chance = sc.nextDouble();
		P = chance;
		System.out.println(wat(pokemon, 100));
	}

	public double wat(int n, int b) {
		if (n == 0) {
			return 0;
		}
		if (arr[n][b] != 0.0) {
			return arr[n][b];
		}
		if (b > 1) {
			arr[n][b] = P * wat(n-1,b-1) + (1-P)*wat(n,b-1);
		} else if (b == 1) {
			arr[n][b] = 0.359804512+wat(n-1,b-1);//(1-P) * wat(n-1,b-1);
		} else {
			arr[n][b] = wat(n-1, 100) + 5;
		}
		return arr[n][b];
	}

	public static void main(String[] args) {
		new Pokeball_Fever();
	}
}