import java.util.*;
import java.io.*;

public class Pick_Up_Sticks {
	public Pick_Up_Sticks() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] line = br.readLine().split(" ");
		int N = Integer.parseInt(line[0]);
		int M = Integer.parseInt(line[1]);

		HashSet<Integer> pile = new HashSet<>();
		int[] numCovering = new int[N];
		ArrayList<ArrayList<Integer>> covering = new ArrayList<>();
		for (int i=0; i<N; i++) {
			covering.add(new ArrayList<>());
		}

		for (int i=0; i<M; i++) {
			line = br.readLine().split(" ");
			int a = Integer.parseInt(line[0]) - 1;
			int b = Integer.parseInt(line[1]) - 1;

			numCovering[b]++;
			covering.get(a).add(b);
		}

		int[] queue = new int[N];
		int head = 0;
		int tail = 0;
		for (int i=0; i<N; i++) {
			pile.add(i);
			if (numCovering[i] == 0) {
				queue[tail++] = i;
			}
		}

		while (head != tail) {
			int current = queue[head++];
			pile.remove(current);
			for (int i : covering.get(current)) {
				numCovering[i]--;
				if (numCovering[i] == 0) {
					queue[tail++] = i;
				}
			}
		}

		if (pile.size() == 0) {
			for (int i=0; i<N; i++) {
				System.out.println((queue[i]+1));
			}
		} else {
			System.out.println("IMPOSSIBLE");
		}
	}

	public static void main(String[] args) throws Exception {
		new Pick_Up_Sticks();
	}
}