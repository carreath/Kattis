import java.util.*;
import java.io.*;

public class Settlers_of_Catan {
	private int[] tileCounts = new int[5];
	private int[] tiles;

	public Settlers_of_Catan() {
		Scanner sc = new Scanner(System.in);

		initArrays();

		int c = sc.nextInt();
		for (int i=0; i<c; i++) {
			int n = sc.nextInt();
			System.out.println(tiles[n-1]);
		}
	} 

	public void initArrays() {
		tileCounts[0] = 1;
		tileCounts[1] = 2;
		tileCounts[2] = 2;
		tileCounts[3] = 1;
		tileCounts[4] = 1;

		tiles = new int[10034];
		tiles[0] = 1;
		tiles[1] = 2;
		tiles[2] = 3;
		tiles[3] = 4;
		tiles[4] = 5;
		tiles[5] = 2;
		tiles[6] = 3;

		int distance = 6;
		int layer = 1;
		int nextLayer = 7;
		for (int i=7; i<10000; i++) {
			if (i == nextLayer) {
				distance = layer * 6;
				layer++;
				nextLayer += layer*6;
				tiles[i] = getTile(tiles[i-1], tiles[i-distance], -1);
				distance++;
				for (int j=0; j<layer-2; j++) {
					i++;
					tiles[i] = getTile(tiles[i-1], tiles[i-distance+1], tiles[i-distance]);
				}
			} else if (i == nextLayer - 1) {
				tiles[i] = getTile(tiles[i-1], tiles[i-distance+1], tiles[i-distance]);
			} else {
				tiles[i] = getTile(tiles[i-1], tiles[i-distance], -1);
				distance++;
				for (int j=0; j<layer-1; j++) {
					i++;
					tiles[i] = getTile(tiles[i-1], tiles[i-distance+1], tiles[i-distance]);
				}
			}
		}
	}

	public int getTile(int tile1, int tile2, int tile3) {
		int min = -1;
		for (int i=0; i<5; i++) {
			if (i != tile1-1 && i != tile2-1 && i != tile3-1) {
				if (min == -1 || tileCounts[i] < tileCounts[min-1]) {
					min = i+1;
				}
			}
		}
		tileCounts[min-1]++;
		return min;
	}

	public static void main(String[] args) {
		new Settlers_of_Catan();
	}
}