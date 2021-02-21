import java.util.*;
import java.io.*;

public class The_Citrus_Intern {
	
	public The_Citrus_Intern() {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();

		int[] cost = new int[N];
		int[] numBosses = new int[N];
		int[][] subordinates = new int[N][];
		long[][] bribeCosts = new long[N][3];
		for (int i=0; i<N; i++) {
			bribeCosts[i][0] = Long.MAX_VALUE;
			bribeCosts[i][1] = Long.MAX_VALUE;
			bribeCosts[i][2] = Long.MAX_VALUE;
		}

		for (int i=0; i<N; i++) {
			cost[i] = sc.nextInt();
			int t = sc.nextInt();
			subordinates[i] = new int[t];
			for (int j=0; j<t; j++) {
				int subID = sc.nextInt();
				numBosses[subID]++;
				subordinates[i][j] = subID;
			}
		}

		// Find the boss
		int root = 0;
		for (int i=0; i<N; i++) {
			if (numBosses[i] == 0) {
				root = i;
				break;
			}
		}

		getCost(subordinates, cost, bribeCosts, root);

		// Choose the min bribe cost
		System.out.println(Math.min(bribeCosts[root][0], bribeCosts[root][2]));
	}

	public void getCost(int[][] subordinates, int[] cost, long[][] bribeCosts, int current) {
		// Base Case Leaf Members
		// [0] will be the cost to bribe every child
		// [1] will be the cost to bribe the current member
		// [2] will be the cost to bribe the current members boss 
		if (subordinates[current].length == 0) {
			bribeCosts[current][0] = cost[current];
			bribeCosts[current][1] = 0;
			bribeCosts[current][2] = cost[current];
			return;
		}

		for (int child : subordinates[current]) {
			getCost(subordinates, cost, bribeCosts, child);
		}	

		// Calculate cost to bribe subordinates
		long tempCost1 = cost[current];
		for (int child : subordinates[current]) {
			tempCost1 += bribeCosts[child][1];
		}
		bribeCosts[current][0] = tempCost1;

		// Calculate cost to bribe the current member
		long tempCost2 = 0;
		for (int child : subordinates[current]) {
			tempCost2 += Math.min(bribeCosts[child][0], bribeCosts[child][2]);
		}
		bribeCosts[current][1] = tempCost2;

		// Calculate the cost to bribe the current members boss
		long tempCost3 = Long.MAX_VALUE;
		for (int child : subordinates[current]) {
			long cost2 = tempCost2 - Math.min(bribeCosts[child][0], bribeCosts[child][2]);
			cost2 += bribeCosts[child][0];
			tempCost3 = Math.min(tempCost3, cost2);
		}
		bribeCosts[current][2] = tempCost3;
	}

	public static void main(String[] args) {
		new The_Citrus_Intern();
	}
}