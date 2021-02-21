import java.util.*;
import java.io.*;

public class Parallel_Analysis {
	public Scanner sc;
	public Parallel_Analysis() {
		sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int i=0; i<T; i++) {
			System.out.println((i+1) + " " + analyze());
		}
	}

	public String analyze() {
		int N = sc.nextInt();

		int best = 0;
		HashMap<Integer, Integer> instruction = new HashMap<>();
		for (int i=0; i<N; i++) {
			int M = sc.nextInt();
			int localBest = 0;
			int item = 0;
			for (int j=0; j<M-1; j++) {
				item = sc.nextInt();
				if (instruction.containsKey(item)) {
					localBest = Math.max(localBest, instruction.get(item));
				}
			}
			item = sc.nextInt();
			localBest++;
			instruction.put(item, localBest);
			best = Math.max(best, localBest);
		}

		return best + "";
	}

	public static void main(String[] args) {
		new Parallel_Analysis();
	}
}