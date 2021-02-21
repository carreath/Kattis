import java.util.*;

public class generate {
	public static void main(String[] args) {
		System.out.println(10000 + " " + 100000 + " " + 10000 + " " + 100);
		int f=0;
		for (int i=1; i<=100; i++) {
			for (int j=i+1; j<=10000; j++) {
				System.out.println(i + " " + j + " " + (new Random().nextInt(100) + 1));
				f++;
				if (f == 100000) {
					break;
				}
			}
			if (f == 100000) {
				break;
			}
		}
		for (int j=1; j<=10000; j++) {
			System.out.println(j);
		}
		System.out.println("0 0 0 0");
	}
}