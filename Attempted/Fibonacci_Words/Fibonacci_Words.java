import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class Fibonacci_Words {
	public String[] fibWords = new String[27];

	public Fibonacci_Words() {
		fibWords[0] = "0";
		fibWords[1] = "1";
		genFibWords(2);
		printFibWords();
	}

	public void genFibWords(int i) {
		System.out.println(i);
		if (i < 27) {
			fibWords[i] = fibWords[i-1] + fibWords[i-2];
			genFibWords(i+1);
		}
	}

	public void printFibWords() {
		for (int i=0; i<15; i++) {
			System.out.println(fibWords[i]);
		}
	}
	
	public static void main(String[] args) {
		new Fibonacci_Words();
	}
}