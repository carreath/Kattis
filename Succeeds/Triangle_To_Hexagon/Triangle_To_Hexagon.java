import java.util.*;
import java.io.*;

public class Triangle_To_Hexagon {
	private final double precision = 0.0000001;
	private double radius;
	private Point circumcenter;
	private Point A;
	private Point B;
	private Point C;
	private Point E;
	private Point F;
	private Point G;
	private Point H;
	private Point I;
	private Point J;
	private Point K;
	private Point M;
	private Point N;
	private Point O;
	private Point P;

	public Triangle_To_Hexagon() {
		Scanner sc = new Scanner(System.in);

		A = new Point(0, 0);
		B = new Point(sc.nextDouble(), 0);
		C = new Point(sc.nextDouble(), sc.nextDouble());

		I = A.calcIncenter();
		circumcenter = A.calcCircumcenter();

		radius = circumcenter.getLength(A);

		M = A.getIntersectionPoint();
		N = B.getIntersectionPoint();
		P = C.getIntersectionPoint();

		E = A.getHexPoint(B, P, N);
		F = A.getHexPoint(C, N, P);
		G = C.getHexPoint(A, N, M);
		H = C.getHexPoint(B, M, N);
		J = B.getHexPoint(C, M, P);
		K = B.getHexPoint(A, P, M);

		double EF = E.getLength(F);
		double FG = F.getLength(G);
		double GH = G.getLength(H);
		double HJ = H.getLength(J);
		double JK = J.getLength(K);
		double KE = K.getLength(E);

		System.out.println(EF + " " + FG + " " + GH + " " + HJ + " " + JK + " " + KE);
	}

	public static void main(String[] args) {
		new Triangle_To_Hexagon();
	}

	private class Point {
		public double x;
		public double y;

		public Point(double x, double y) {
			this.x = x;
			this.y = y;
		}

		public Point() {
		}

		public Point calcIncenter() {
			Point incenter = new Point();
			incenter.x = (B.getLength(C) * A.x + A.getLength(C) * B.x + A.getLength(B) * C.x)/(A.getLength(B) + A.getLength(C) + B.getLength(C));
			incenter.y = (B.getLength(C) * A.y + A.getLength(C) * B.y + A.getLength(B) * C.y)/(A.getLength(B) + A.getLength(C) + B.getLength(C));
			return incenter;
		}

		public Point calcCircumcenter() {
			Point circumcenter = new Point();
			circumcenter.x = A.getMidpoint(B).x;
			circumcenter.y = (Math.pow(C.x,2) + Math.pow(C.y, 2) - 2.0*C.x*circumcenter.x) / (2.0 * C.y);

			return circumcenter;
		}

		public Point getIntersectionPoint() {
			Point intersectPoint = new Point();
			double slope = getSlope(I, false);
			double yOffset = y - (slope * x);

			if (Double.isInfinite(slope)) {
				intersectPoint.x = x;
				intersectPoint.y = 2.0 * circumcenter.y - y;
			} else {
				double a = (Math.pow(slope, 2) + 1.0);
				double b = 2.0 * (slope * yOffset - slope * circumcenter.y - circumcenter.x);
				double c = (Math.pow(circumcenter.y,2) - Math.pow(radius,2) + Math.pow(circumcenter.x,2) - (2.0*yOffset*circumcenter.y) + Math.pow(yOffset,2));
			
				double x1 = (-b + Math.sqrt(Math.pow(b, 2) - (4.0*a*c))) / (2.0*a);
				double x2 = (-b - Math.sqrt(Math.pow(b, 2) - (4.0*a*c))) / (2.0*a);

				double y1 = slope * x1 + yOffset;
				double y2 = slope * x2 + yOffset;

				if ((!(Math.abs(x - x1) <= precision && Math.abs(y - y1) <= precision)) && Math.abs(Math.pow(x1 - circumcenter.x, 2) + Math.pow(y1 - circumcenter.y, 2) - Math.pow(radius, 2)) <= precision) {
					intersectPoint.x = x1;
					intersectPoint.y = y1;
				} else {
					intersectPoint.x = x2;
					intersectPoint.y = y2;
				}
			}

			return intersectPoint;
		}

		public Point getHexPoint(Point target, Point otherMain, Point otherTarget) {
			Point hexPoint = new Point();

			double slope1 = getSlope(target, false);
			double slope2 = otherMain.getSlope(otherTarget, false);

			double offset1 = y - (slope1 * x);
			double offset2 = otherMain.y - (slope2 * otherMain.x);

			if (Math.abs(slope1) == Double.POSITIVE_INFINITY || Math.abs(slope2) == Double.POSITIVE_INFINITY) {
				hexPoint.x = x;
				if (Math.abs(slope1) == Double.POSITIVE_INFINITY) {
					hexPoint.y = slope2 * hexPoint.x + offset2;
				} else {
					hexPoint.y = slope1 * hexPoint.x + offset1;
				}
			} else {
				hexPoint.x = (offset2 - offset1) / (slope1 - slope2);
				hexPoint.y = slope1 * hexPoint.x + offset1;
			}

			return hexPoint;
		}

		public double getSlope(Point other, boolean perpendicular) {
			if (perpendicular) {
				return (x - other.x) / (other.y - y);
			}
			else {
				return (other.y - y) / (other.x - x);
			}
		}

		public double getLength(Point other) {
			return Math.sqrt(Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2));
		}

		public Point getMidpoint(Point other) {
			Point midPoint = new Point((other.x - x) / 2, (other.y - y) / 2);
			return midPoint;
		}
		
		public double getAngle(Point other) {
			double angle = Math.atan(Math.abs((other.y - y)/(other.x - x)));
			if ((other.x - x) >= 0) {
				if ((other.y - y) < 0) {
					angle += 3.0 * Math.PI / 2.0;
				} 
			} else {
				if ((other.y - y) >= 0) {
					angle += Math.PI / 2.0;
				} else {
					angle += Math.PI;
				}
			}
			return angle;
		}

		public String toString() {
			return "(" + x + "," + y + ")";
		}
	}
}