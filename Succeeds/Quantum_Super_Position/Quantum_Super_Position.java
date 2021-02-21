import java.util.*;
import java.io.*;

public class Quantum_Super_Position {
	public Quantum_Super_Position() {
		Scanner sc = new Scanner(System.in);

		int N1 = sc.nextInt();
		int N2 = sc.nextInt();
		int M1 = sc.nextInt();
		int M2 = sc.nextInt();

		boolean[][] directions1 = new boolean[N1][M1+1];
		int[][] map1 = new int[N1][M1];
		int[] neighbors1 = new int[N1];

		boolean[][] directions2 = new boolean[N2][M2+1];
		int[][] map2 = new int[N2][M2];
		int[] neighbors2 = new int[N2];

		for (int i=0; i<M1; i++) {
			int node = sc.nextInt() - 1;
			map1[node][neighbors1[node]++] = sc.nextInt() - 1;
		}
		for (int i=0; i<M2; i++) {
			int node = sc.nextInt() - 1;
			map2[node][neighbors2[node]++] = sc.nextInt() - 1;
		}

		directedTopologicalSort(map1, directions1, neighbors1, N1, M1);
		directedTopologicalSort(map2, directions2, neighbors2, N2, M2);

		HashSet<Integer> counts = countPaths(directions1, directions2, N1, N2, M1, M2);

		int Q = sc.nextInt();
		for (int i=0; i<Q; i++) {
			int q = sc.nextInt();
			if (counts.contains(q)) {
				System.out.println("Yes");
			} else {
				System.out.println("No");
			}
		}
	}

	public HashSet<Integer> countPaths(boolean[][] directions1, boolean[][] directions2, int N1, int N2, int M1, int M2) {
		HashSet<Integer> counts = new HashSet<>();
		for (int i=0; i<M1+1; i++) {
			for (int j=0; j<M2+1; j++) {
				if (directions1[N1-1][i]) {
					if (directions2[N2-1][j]) {
						counts.add(i+j);
					}
				}
			}	
		}
		return counts;
	}

	// Kahns Algorithm to get the directions array in order to count steps between the two graphs
	public void directedTopologicalSort(int[][] map, boolean[][] directions, int[] neighbors, int N, int M) {
		int[] degrees = new int[N];
		for (int i=0; i<N; i++) {
			for (int j=0; j < neighbors[i]; j++) {
				degrees[map[i][j]]++;
			}
		}

		int[] queue = new int[N];
		int head = 0;
		int tail = 1;
		directions[0][0] = true;

		while (head != tail) {
			int node = queue[head++];

			for (int i=0; i<neighbors[node]; i++) {
				int neighbor = map[node][i];
				for (int j = 0; j<M; j++) {
					if (directions[node][j]) {
						directions[neighbor][j+1] = true;
					}
				}

				degrees[neighbor]--;
				if (degrees[neighbor] == 0) {
					queue[tail++] = neighbor;
				}
			}
		}
	}

	public static void main(String[] args) {
		new Quantum_Super_Position();
	}
}
