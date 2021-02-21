import java.util.*;
import java.math.*;
public class Suspension_Bridges {
    public Suspension_Bridges() {
        Scanner sc = new Scanner(System.in);

        double d = sc.nextDouble();
        double s = sc.nextDouble();

        double a = 0;
        double maxA = Integer.MAX_VALUE;
        double minA = 0;
        while (maxA - minA > 1E-10) {
            a = (maxA + minA) / 2;
            if (a + s > a * Math.cosh(d/(2.0 * a))) {
                maxA = a;
            } else {
                minA = a;
            }
        }

        double l = 2.0*a*Math.sinh(d/(2.0*a));
        System.out.println(l);
    }

    public static void main(String[] args) {
        new Suspension_Bridges();
    }
}