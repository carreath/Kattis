import java.util.*;
import java.io.*;

public class Rimski {
	public Rimski() throws Exception {
		Scanner sc = new Scanner(System.in);

		String out = "";
		String number = sc.nextLine();
		int length = number.length();

		if (length > 1 && number.substring(0, 2).equals("LX") && (number.equals("LXXI") || (length < 3 || !number.substring(0, 3).equals("LXX")))) {
			number = "XL" + number.substring(2);
		}

		if (length == 0 || (length >= 2 && number.substring(length-2).equals("II")) || (length >= 3 && (number.substring(length - 3).equals("III") && length > 2))) {
			out = number;
		} else if (number.charAt(length-1) == 'I' && (length >= 2 && number.charAt(length-2) != 'L')) {
			out = number.substring(0, length-2) + "I" + number.charAt(length-2);
		} else {
			out = number;
		}
		System.out.println(out);
	}

	public static void main(String[] args) throws Exception  {
		new Rimski();
	}
}