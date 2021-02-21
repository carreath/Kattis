import java.util.*;
import java.io.*;
import java.math.*;

public class Supercomputer {
	private long[] bits;
	private int[] bitCount;

	public Supercomputer() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] in = br.readLine().split(" ");
		int N = Integer.parseInt(in[0]);
		int K = Integer.parseInt(in[1]);

		bits = new long[(N/63)+2];

		StringBuilder out = new StringBuilder();
		bitCount = new int[N + 1];

		for (int i=0; i<K; i++) {
        	in = br.readLine().split(" ");
			String command = in[0];
			if (command.equals("F")) {
				int bit = Integer.parseInt(in[1]);
				int index = (bit-1)/63;
				bits[index] = bits[index] ^ (long) Math.pow(2, (bit-1)%63);
			} else {
				int start = Integer.parseInt(in[1]);
				int startIndex = (start-1)/63;
				int end = Integer.parseInt(in[2]);
				int endIndex = (end-1)/63;

				int count = 0;
				if (endIndex != startIndex) {
					count = Long.bitCount(bits[startIndex] >> ((start-1)%63));
					count += Long.bitCount(bits[endIndex] << (64 - (end%63)));
					for (int j=startIndex+1; j<=endIndex-1; j++) {
						count += Long.bitCount(bits[j]);
					}
				} else {
					count = Long.bitCount(isolateBits(start, end));
				}

				out.append(count + "\n");
			}
		}

		System.out.print(out);
	}

	public long isolateBits(int start, int end) {
		int startIndex = (start-1)/63;
		long temp = (bits[startIndex] >> ((start-1)%63));
		temp = (temp << ((start-1)%63));
		return (temp << (64 - (end%63)));
	}

	public static void main(String[] args) throws IOException {
		new Supercomputer();
	}
}