import java.util.*;
import java.io.*;

@SuppressWarnings("unchecked")
public class Invasion {
	public ArrayList<Edge>[] adjList;
	public int N;
	public int M;
	public int A;
	public int K;
	public int[] dist;

	public Invasion() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			//long start = System.currentTimeMillis();

			String[] inputs = br.readLine().split(" ");
			N = Integer.parseInt(inputs[0]);
			M = Integer.parseInt(inputs[1]);
			A = Integer.parseInt(inputs[2]);
			K = Integer.parseInt(inputs[3]);

			if (N == 0 && M == 0 && A == 0 && K == 0) {
				break;
			}

			dist = new int[N];
			adjList = new ArrayList[N];
			for (int i=0; i<N; i++) {
				adjList[i] = new ArrayList<>();
			}

			//System.out.println("time1: " + (System.currentTimeMillis() - start));
			//start = System.currentTimeMillis();

			for (int i=0; i<M; i++) {
				inputs = br.readLine().split(" ");
				int T1 = Integer.parseInt(inputs[0]) - 1;
				int T2 = Integer.parseInt(inputs[1]) - 1;
				int D = Integer.parseInt(inputs[2]);

				Edge e1 = new Edge(T1, D);
				Edge e2 = new Edge(T2, D);
				adjList[T1].add(e2);
				adjList[T2].add(e1);
			} 

			int[] B = new int[A];
			for (int i=0; i<A; i++) {
				B[i] = Integer.parseInt(br.readLine()) - 1;
			}

			//System.out.println("time2: " + (System.currentTimeMillis() - start));
			//start = System.currentTimeMillis();

			int dangerZones = 0;
			int[] dist = new int[N];
			Arrays.fill(dist, Integer.MAX_VALUE);

			StringBuilder sb = new StringBuilder();
			for (int base : B) {
				dangerZones += dijkstra(dist, base);
				sb.append(N - dangerZones + "\n");
			}

			//System.out.println("time3: " + (System.currentTimeMillis() - start));

			System.out.println(sb);
		}
	}

	public int dijkstra(int[] dist, int s) {
		int dangerZones = (dist[s] == Integer.MAX_VALUE)? 1: 0;
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		dist[s] = 0;
		pq.add(new Edge(s, 0));						
		while(!pq.isEmpty()) {
			Edge cur = pq.remove();
			if (dist[cur.node] == 0 && cur.node != s) {
				continue;
			}
			for(Edge next: adjList[cur.node]) {
				if(cur.cost + next.cost < K && 
					cur.cost + next.cost < dist[next.node]) {
					dangerZones += (dist[next.node] == Integer.MAX_VALUE)? 1: 0;
					dist[next.node] = cur.cost + next.cost;
					pq.add(new Edge(next.node, dist[next.node]));
				}
			}
		}
		return dangerZones;
	}

	private class Edge implements Comparable<Edge> {
		public int node, cost;
		
		public Edge(int a, int b) { 
			node = a;	
			cost = b; 
		}
		
		public int compareTo(Edge e) { 
			return cost - e.cost;	
		}
	}

	public static void main(String[] args) throws Exception {
		new Invasion();
	}
}