import java.util.*;
import java.io.*;

public class Subway_Tree_System {
	public Subway_Tree_System() {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		for (int i=0; i<N; i++) {
			String path1 = sc.next();
			String path2 = sc.next();
			
			if (solve(path1, path2)) {
				System.out.println("same");
			} else {
				System.out.println("different");
			}
		}
	}

	public boolean solve(String path1, String path2) {
		int[][] tree1 = getTree(path1);
		int[][] tree2 = getTree(path2);

		//for (int[] a : tree1) {
		//	System.out.println(Arrays.toString(a));
		//}
		String route1 = getRoute(tree1, 0);
		String route2 = getRoute(tree2, 0);

		if (route1.equals(route2)) {
			return true;
		} else {
			return false;
		}

		/*
		int matchCount = 0;
		boolean[] visited = new boolean[routes2.length];
		for (String s : routes1) {
			for (int i=0; i<routes2.length; i++) {
				if (!visited[i] && s.equals(routes2[i])) {
					visited[i] = true;
					matchCount++;
				}
			}
		}

		if (matchCount == routes1.length) {
			return true;
		} else {
			return false;
		}*/
	}

	public int[][] getTree(String path) {
		int size = path.length() / 2 + 1;
		int[][] tree = new int[size][size];
		int[] parents = new int[size];

		int current = 0;
		int routeCount = 0;
		int[] counts = new int[size];
		for (char c : path.toCharArray()) {
			if (c == '0') {
				routeCount++;
				tree[current][counts[current]] = routeCount;
				parents[routeCount] = current;
				counts[current]++;
				current = routeCount;
			} else {
				current = parents[current];
			}
		}
		//System.out.println(Arrays.toString(parents));

		return tree;
	}

	public String getRoute(int[][] tree, int current) {
		if (tree[current][0] == 0) {
			//System.out.println(current + " Base Case");
			return "1 ";
		}

		int size = 0;
		for (int node : tree[current]) {
			if (node == 0) {
				break;
			} else {
				size++;
			}
		}

		int totalChildren = 0;
		int routeCount = 0;
		String[] routes = new String[size];
		for (int i=0; i<size; i++) {
			int nextRoute = tree[current][i];
			//System.out.println(nextRoute + ": ");
			routes[routeCount] = getRoute(tree, nextRoute);
			totalChildren += Integer.parseInt(routes[routeCount++].split(" ")[0]);
		}

		//System.out.println(Arrays.toString(routes));
		Arrays.sort(routes);
		//System.out.println(Arrays.toString(routes));

		String finalRoute = totalChildren + "";
		for (String route : routes) {
			finalRoute += " " + route;
		}

		//System.out.println(finalRoute);
		return finalRoute;
	}

	private class Route {
		public String[] routes;

		public Route(String[] routes) {
			this.routes = routes;
		}
	}

	public static void main(String[] args) {
		new Subway_Tree_System();
	}
}