import java.util.*;
import java.io.*;

public class Game_Design {
	public int[][] grid;
	public int[] xCoords;
	public int[] yCoords;
	public int ballX;
	public int ballY;
	public char[] moves;
	public int[] moveX;
	public int[] moveY;
	public int gridSize = 200;
	public int gridMax = 100;

	public Game_Design() {
		Scanner sc = new Scanner(System.in);
		grid = new int[gridSize][gridSize];

		moves = sc.nextLine().toCharArray();
		xCoords = new int[moves.length];
		yCoords = new int[moves.length];
		moveX = new int[moves.length];
		moveY = new int[moves.length];
		ballX = 0;
		ballY = 0;

		if (doMove(0)) {

		} else {
			System.out.println("impossible");
		}
	}

	public boolean doMove(int move) {
		if (move == moves.length -1) {
			switch (moves[move]) {
				case 'U':
					for (int i=ballY+gridMax; i<gridSize; i++) {
						if (grid[ballX+gridMax][i] == 0) {
							ballX *= -1;
							ballY = -(i - gridMax);
							return true;
						} else if (grid[ballX+gridMax][i] == 2) {
							return false;
						}
					}
					break;
				case 'D':
					for (int i=ballY+gridMax; i>=0; i--) {
						if (grid[ballX+gridMax][i] == 0) {
							ballX *= -1;
							ballY = -(i - gridMax);
							return true;
						} else if (grid[ballX+gridMax][i] == 2) {
							return false;
						}
					}
					break;
				case 'L':
					for (int i=ballX+gridMax; i>=0; i--) {
						if (grid[i][ballY+gridMax] == 0) {
							ballY *= -1;
							ballX = -(i - gridMax);
							return true;
						} else if (grid[i][ballY+gridMax] == 2) {
							return false;
						}
					}
					break;
				case 'R':
					for (int i=ballX+gridMax; i<200; i++) {
						if (grid[i][ballY+gridMax] == 0) {
							ballY *= -1;
							ballX = -(i - gridMax);
							return true;
						} else if (grid[i][ballY+gridMax] == 2) {
							return false;
						}
					}
					break;
			}
			return false;
		} else {
			int distance = 1;
			boolean done = false;
			while (distance <= 10 && !done) {
				switch (moves[move]) {
				case 'U':
					int[] backup = grid[ballX+gridMax];
					for (int i=ballY+gridMax; i<gridSize; i++) {
						if (grid[ballX+gridMax][i] == 0) {
							ballX *= -1;
							ballY = -(i - gridMax);
							distance++;
							if (grid[ballX+gridMax][i+1] == 1) {
								return false;
							}
							grid[ballX+gridMax][i] = 1;
							grid[ballX+gridMax][i+1] = 2;
							doMove(move + 1);
						} else if (grid[ballX+gridMax][i] == 2) {
							return false;
						}
					}
					break;
				case 'D':
					for (int i=ballY+gridMax; i>=0; i--) {
						if (grid[ballX+gridMax][i] == 0) {
							ballX *= -1;
							ballY = -(i - gridMax);
							return true;
						} else if (grid[ballX+gridMax][i] == 2) {
							return false;
						}
					}
					break;
				case 'L':
					for (int i=ballX+gridMax; i>=0; i--) {
						if (grid[i][ballY+gridMax] == 0) {
							ballY *= -1;
							ballX = -(i - gridMax);
							return true;
						} else if (grid[i][ballY+gridMax] == 2) {
							return false;
						}
					}
					break;
				case 'R':
					for (int i=ballX+gridMax; i<200; i++) {
						if (grid[i][ballY+gridMax] == 0) {
							ballY *= -1;
							ballX = -(i - gridMax);
							return true;
						} else if (grid[i][ballY+gridMax] == 2) {
							return false;
						}
					}
					break;
			}
			}
		}
	}

	public static void main(String[] args) {
		new Game_Design();
	}
}