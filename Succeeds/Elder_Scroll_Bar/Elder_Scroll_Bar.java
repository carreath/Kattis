import java.util.*;
import java.io.*;

public class Elder_Scroll_Bar {
	public Elder_Scroll_Bar() {
		Scanner sc = new Scanner(System.in);

		int W = sc.nextInt();
		int H = sc.nextInt();
		int F = sc.nextInt();
		int N = sc.nextInt();

		String[] lines = new String[30000];
		String currentLine = "";
		int tail = 0;

		while (sc.hasNext()) {
			String word = sc.next();
			if (word.length() + currentLine.length() + 1 > W) {
				if (currentLine.length() > 0) {
					currentLine = currentLine.trim();
					lines[tail] = currentLine + repeat(" ", W - currentLine.length());
					currentLine = "";
					tail++;
				}

				if (word.length() >= W) {
					// Own Line
					lines[tail] = word.substring(0,W);
					tail++;
				} else {
					//New Line
					currentLine = word;
				}
			} else {
				currentLine += " " + word;
				currentLine = currentLine.trim();
			}
		}
		if (currentLine.length() > 0) {
			currentLine = currentLine.trim();
			lines[tail] = currentLine + repeat(" ", W - currentLine.length());
			currentLine = "";
			tail++;
		}

		int T = (F * (H - 3)) / (tail - H);
		for (int i=-1; i<=H; i++) {
			if (i == -1 || i == H) {
				System.out.println("+" + repeat("-", W) + "+-+");
			} else {
				String end = "| |";
				if (i == 0) {
					end = "|^|";
				} else if (i == H-1) {
					end = "|v|";
				} else if (i-1 == T) {
					end = "|X|";
				}

				System.out.println("|" + lines[i + F] + end);
			}
		}
	}

	public String repeat(String str, int reps) {
		String out = "";
		while (reps > 0) {
			out += str;
			reps--;
		}
		return out;
	}

	public static void main(String[] args) {
		new Elder_Scroll_Bar();
	}
}