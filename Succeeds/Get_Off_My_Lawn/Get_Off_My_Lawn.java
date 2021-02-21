import java.awt.geom.*;
import java.util.*;
public class Get_Off_My_Lawn {
    public Get_Off_My_Lawn() {
        Scanner sc = new Scanner(System.in);

        double L = sc.nextDouble();
        double minRadius = Math.sqrt(L / Math.PI);

        double x1 = sc.nextDouble();
        double y1 = sc.nextDouble();
        double x2 = sc.nextDouble();
        double y2 = sc.nextDouble();

        Point origin = new Point(0, 0);
        Point point1 = new Point(x1, y1);
        Point point2 = new Point(x2, y2);

        double slope1 = point1.getSlope(point2);
        double slope2 = point1.getInverseSlope(point2);

        double yOffset = y1 - x1*slope1;
        double wallXIntercept = yOffset / (slope2 - slope1);
        double wallYIntercept = slope1 * wallXIntercept + yOffset;
        if (Double.isInfinite(slope1)) {
            wallXIntercept = point1.x;
            wallYIntercept = 0;
        } else if (slope1 == 0.0) {
            wallXIntercept = 0;
            wallYIntercept = point1.y;
        }

        Point wallIntercept = new Point(wallXIntercept, wallYIntercept);

        double distanceToWall = origin.getLength(wallIntercept);
        if (distanceToWall >= minRadius) {
            System.out.println((int) Math.ceil(minRadius));
        } else {
            double chainLength = Math.ceil(minRadius);
            while (true) {
                double Area = Math.PI * Math.pow(chainLength, 2);
                double lengthOfSegment = Math.sqrt(Math.pow(chainLength, 2) - Math.pow(distanceToWall, 2));
                double angleOfWall = 2 * Math.asin(lengthOfSegment / chainLength);
                double segmentArea = Math.pow(chainLength, 2) * (angleOfWall - Math.sin(angleOfWall)) / 2;
                if (L <= (Area - segmentArea)) {
                    System.out.println((int) chainLength);
                    break;
                }
                chainLength++;
            }
        }
    }

    public static void main(String[] args) {
        new Get_Off_My_Lawn();
    }

    private class Point {
        public double x;
        public double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double getSlope(Point other) {
            return (other.y - y) / (other.x - x);
        }

        public double getInverseSlope(Point other) {
            return (x - other.x) / (other.y - y);
        }

        public double getLength(Point other) {
            return Math.sqrt(Math.pow(other.x-x, 2) + Math.pow(other.y-y, 2));
        }   
    }
}

