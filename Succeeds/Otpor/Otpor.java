import java.util.*;
import java.io.*;

public class Otpor {
	private double[] resistors;

	public Otpor() {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		resistors = new double[N];
		for (int i=0; i<N; i++) {
			resistors[i] = sc.nextDouble();
		}

		sc.nextLine();
		String circuit = sc.nextLine().substring(1);
		System.out.println(String.format("%.5f", newLayer(new Scanner(circuit).useDelimiter(""))));
	}

	public double newLayer(Scanner circuit) {
		char pointer = ' ';
		double resistance = 0;
		while (circuit.hasNext() && pointer != ')') {
			pointer = circuit.next().charAt(0);
			if (pointer == '(') {
				resistance = newLayer(circuit);
			} else if (pointer == 'R') {
				resistance = resistors[circuit.nextInt()-1];
			} else if (pointer == '-') {
				pointer = circuit.next().charAt(0);
				if (pointer == '(') {
					resistance += newLayer(circuit);
				} else {
					resistance += resistors[circuit.nextInt()-1];
				}
			} else if (pointer == '|') {
				double inverse = 1.0/resistance;
				while (circuit.hasNext() && (pointer=circuit.next().charAt(0)) != ')') {
					if (pointer == '(') {
						inverse += 1.0/newLayer(circuit);
					} else if (pointer == 'R') {
						inverse += 1.0/resistors[circuit.nextInt()-1];
					}
				}
				resistance = 1.0/inverse;
			}
		}
		return resistance;
	}

	public static void main(String[] args) {
		new Otpor();
	}
}