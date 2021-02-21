import java.util.*;
import java.io.*;

public class Grade_Curving {
    public Grade_Curving() {
        Scanner sc = new Scanner(System.in);

		double x = sc.nextDouble();
        double initialX = x;
        double yLow = sc.nextDouble();
        double yHigh = sc.nextDouble();
    	
    	String out = "";
    	int countLow = 0;
        while (Math.ceil(x) < yLow) {
        	countLow++;
            x = 10.0 * Math.sqrt(x);
        }
        if (Math.ceil(initialX) < yLow && Math.ceil(x) > yHigh) {
        	out = "impossible";
        } else {
        	int countHigh = countLow;
	        while (Math.ceil(10.0 * Math.sqrt(x)) <= yHigh && x != 100.0) {
        		countHigh++;
	            x = 10.0 * Math.sqrt(x);
	        }
	        out = countLow + " ";
	        if (yHigh == 100) {
	        	out += "inf";
	        } else {
	        	out += countHigh;
	        }
        }
        System.out.println(out);
    }

    public static void main(String[] args) {
        new Grade_Curving();
    }
}