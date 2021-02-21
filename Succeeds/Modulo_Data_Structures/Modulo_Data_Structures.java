import java.util.*;
import java.io.*;

public class Modulo_Data_Structures {
    public Modulo_Data_Structures() throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    	String[] in = br.readLine().split(" ");
        int N = Integer.parseInt(in[0]);
        int Q = Integer.parseInt(in[1]);

        String[][] commands = new String[Q][];
        for (int i=0; i<Q; i++) {
        	in = br.readLine().split(" ");
        	commands[i] = in;
        }

        StringBuilder out = new StringBuilder();
        int rtN = (int) Math.ceil(Math.sqrt(N));
        int[] ACounts = new int[N+1];
        int[][] BCounts = new int[rtN][rtN];
        for (int i=0; i<Q; i++) {
            if (commands[i][0].equals("1")) {
                int A = Integer.parseInt(commands[i][1]);
                int B = Integer.parseInt(commands[i][2]);
                int C = Integer.parseInt(commands[i][3]);

                if (B >= rtN) {
                    for (int j=A; j<=N; j+=B) {
                        ACounts[j] += C;
                    }
                } else {
                    BCounts[B][A]+=C;
                }
            } else {
                int D = Integer.parseInt(commands[i][1]);
                int count = ACounts[D];
                for (int j=1; j<rtN; j++) {
                    count += BCounts[j][D%j];
                }

                out.append(count + "\n");
            }
        }
        System.out.println(out);
    }

    public static void main(String[] args) throws IOException {
        new Modulo_Data_Structures();
    }
}