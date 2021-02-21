import java.util.*;
import java.io.*;

public class Font {
	private final int mask = 0b00000011111111111111111111111111;

	private int[] dictionary;

	public Font() {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		dictionary = new int[N];
	 	HashSet<Integer> indices = new HashSet<>();

		sc.nextLine();
		for (int i=0; i<N; i++) {
			String word = sc.nextLine();
			for (char c : word.toCharArray()) {
				dictionary[i] |= (int) Math.pow(2, (int)(c - 'a'));
			}
			indices.add(i);
		}

		int count = checkDictionary(indices, 0);
		System.out.println(count);
	}

	public int checkDictionary(HashSet<Integer> indices, int prevSentence) {
		int count = 0;

		while (indices.size() > 0) {
			int max = -1;
			int maxIndex = 0;
			int word = 0;
			int currentSentence = 0;

			for (int index : indices) {
				int tempWord = dictionary[index];
				int tempSentence = prevSentence | tempWord;

				if (Integer.bitCount(tempSentence) > max) {
					max = Integer.bitCount(tempSentence);
					maxIndex = index;
					word = tempWord;
					currentSentence = tempSentence;
				}
			}

			indices.remove(maxIndex);
			if (currentSentence == mask) {
				count += Math.pow(2, indices.size());
			} else {
				int tempCount = checkDictionary(new HashSet<Integer>(indices), currentSentence);
				if (tempCount == 0) {
					return count;
				}
				count += tempCount;
			}
		}

		return count;
	}

	public static void main(String[] args) {
		new Font();
	}
}