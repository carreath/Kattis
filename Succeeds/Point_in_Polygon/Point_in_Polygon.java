import java.util.*;
import java.io.*;

public class Point_in_Polygon {
	public int N;

	public Point_in_Polygon() {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		while (N != 0) {
			Point[] shape = new Point[N];
			for (int i=0; i<N; i++) {
				shape[i] = new Point(sc.nextInt(), sc.nextInt());
			}

			int M = sc.nextInt();
			for (int i=0; i<M; i++) {
				Point m = new Point(sc.nextInt(), sc.nextInt());
				int out = checkPolygon(shape, m);

				if (out == 1) {
					System.out.println("in");
				} else if (out == 0) {
					System.out.println("out");
				} else {
					System.out.println("on");
				}
			}
			N = sc.nextInt();
		}
	}

	public int checkPolygon(Point[] shape, Point m) {
		int intersections = 0;

		for (int i=0; i<N; i++) {
			Point a = shape[i];
			Point b = shape[(i+1)%N];

			if (m.equals(a, b) || //CHANGE TO CHECK ONLY LOWER POINT
				(Point_in_Polygon.orientation(a, b, m) == 0 && 
					Point_in_Polygon.onSegment(a, b, m))) {
				return -1;
			}

			if (Point_in_Polygon.intersects(a, b, m, new Point(10500, m.y))) {
				intersections++;
			}
		}

		return intersections % 2;
	}

	public static int crossProduct() {
		return 0;
	}

	public static boolean onSegment(Point a, Point b, Point m) { 
	    if (m.x <= Math.max(a.x, b.x) && 
	    	m.x >= Math.min(a.x, b.x) && 
	        m.y <= Math.max(a.y, b.y) && 
	        m.y >= Math.min(a.y, b.y)) 
	    return true; 
	  
	    return false; 
	} 

	public static int orientation(Point a, Point b, Point m) { 
	    int orientation = (b.y - a.y) * (m.x - b.x) - (b.x - a.x) * (m.y - b.y); 
	  
	    if (orientation == 0) return 0; // colinear 
	  
	    return (orientation > 0)? 1: 2; // CW, CCW 
	} 
	  
	public static boolean intersects(Point a, Point b, Point m, Point inf) { 
		if (1 == 1) {
			if (checkIntersection(a.x, a.y, b.x, b.y, m.x, m.y, inf.x, inf.y)) {
				if (a.y > b.y) {
					return (orientation(a, m, inf) != 0);
				}
				return (orientation(b, m, inf) != 0);
			}
		} else {
			int o1 = orientation(a, b, m); 
			int o2 = orientation(a, b, inf); 
			int o3 = orientation(m, inf, a); 
			int o4 = orientation(m, inf, b); 

			if (o1 != o2 && o3 != o4) {
				return true; 	
			}

			if ((o1 == 0 && onSegment(a, m, b)) ||
				(o2 == 0 && onSegment(a, inf, b)) ||
				(o3 == 0 && onSegment(m, a, inf)) ||
				(o4 == 0 && onSegment(m, b, inf))) {
				return true; 
			}
		}
		return false;
	} 

	public static boolean checkIntersection(int ax, int ay, int bx, int by, int cx, int cy, int dx, int dy) {
	    int det = (bx - ax)*(dy - cy) - (by - ay)*(dx - cx);
	    if (det != 0) {
	        /*
	         * Lines intersect. Check if intersection point is on both segments:
	         */
	        int detu = (cx - ax)*(dy - cy) - (cy - ay)*(dx - cx);
	        int detv = (cx - ax)*(by - ay) - (cy - ay)*(bx - ax);
	        if (det < 0) {
	            // Normalise to det>0 to simplify the following check.
	            det = -det;
	            detu = -detu;
	            detv = -detv;
	        }
	        if (detu >= 0 && detu <= det && detv >= 0 && detv <= det) {
	            return true;
	        } else {
	            return false;
	        }
	    } else {
	        /*
	         * Lines are parallel (or identical):
	         */
	        return false;
	    }
	}

	private class Point {
		public int x;
		public int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public boolean equals(Point a, Point b) {
			if (x == a.x && y == a.y) {
				return true;
			}
			if (x == b.x && y == b.y) {
				return true;
			}
			return false;
		}
	}

	public static void main(String[] args) {
		new Point_in_Polygon();
	}
}