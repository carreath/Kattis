import java.util.*;
import java.io.*;

public class Intersecting_Rectangles {
	public Rectangle[] rect;
	public int N;

	public Intersecting_Rectangles() {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		rect = new Rectangle[N];

		for (int i=0; i<N; i++) {
			rect[i] = new Rectangle(sc.nextLong(), sc.nextLong(), sc.nextLong(), sc.nextLong());
		}

		boolean intersects = false;
		for (int i=0; i<N; i++) {
			for (int j=i+1; j<N; j++) {
				intersects = checkIntersection(rect[i].x1, rect[i].y1, rect[i].x1, rect[i].y2, rect[j].x1, rect[j].y1, rect[j].x1, rect[j].y2);
				if (intersects) break;
				intersects = checkIntersection(rect[i].x1, rect[i].y1, rect[i].x1, rect[i].y2, rect[j].x1, rect[j].y1, rect[j].x2, rect[j].y1);
				if (intersects) break;
				intersects = checkIntersection(rect[i].x1, rect[i].y1, rect[i].x1, rect[i].y2, rect[j].x2, rect[j].y2, rect[j].x1, rect[j].y2);
				if (intersects) break;
				intersects = checkIntersection(rect[i].x1, rect[i].y1, rect[i].x1, rect[i].y2, rect[j].x2, rect[j].y2, rect[j].x2, rect[j].y1);
				if (intersects) break;
				
				intersects = checkIntersection(rect[i].x1, rect[i].y1, rect[i].x2, rect[i].y1, rect[j].x1, rect[j].y1, rect[j].x1, rect[j].y2);
				if (intersects) break;
				intersects = checkIntersection(rect[i].x1, rect[i].y1, rect[i].x2, rect[i].y1, rect[j].x1, rect[j].y1, rect[j].x2, rect[j].y1);
				if (intersects) break;
				intersects = checkIntersection(rect[i].x1, rect[i].y1, rect[i].x2, rect[i].y1, rect[j].x2, rect[j].y2, rect[j].x1, rect[j].y2);
				if (intersects) break;
				intersects = checkIntersection(rect[i].x1, rect[i].y1, rect[i].x2, rect[i].y1, rect[j].x2, rect[j].y2, rect[j].x2, rect[j].y1);
				if (intersects) break;

				intersects = checkIntersection(rect[i].x2, rect[i].y2, rect[i].x1, rect[i].y2, rect[j].x1, rect[j].y1, rect[j].x1, rect[j].y2);
				if (intersects) break;
				intersects = checkIntersection(rect[i].x2, rect[i].y2, rect[i].x1, rect[i].y2, rect[j].x1, rect[j].y1, rect[j].x2, rect[j].y1);
				if (intersects) break;
				intersects = checkIntersection(rect[i].x2, rect[i].y2, rect[i].x1, rect[i].y2, rect[j].x2, rect[j].y2, rect[j].x1, rect[j].y2);
				if (intersects) break;
				intersects = checkIntersection(rect[i].x2, rect[i].y2, rect[i].x1, rect[i].y2, rect[j].x2, rect[j].y2, rect[j].x2, rect[j].y1);
				if (intersects) break;
				
				intersects = checkIntersection(rect[i].x2, rect[i].y2, rect[i].x2, rect[i].y1, rect[j].x1, rect[j].y1, rect[j].x1, rect[j].y2);
				if (intersects) break;
				intersects = checkIntersection(rect[i].x2, rect[i].y2, rect[i].x2, rect[i].y1, rect[j].x1, rect[j].y1, rect[j].x2, rect[j].y1);
				if (intersects) break;
				intersects = checkIntersection(rect[i].x2, rect[i].y2, rect[i].x2, rect[i].y1, rect[j].x2, rect[j].y2, rect[j].x1, rect[j].y2);
				if (intersects) break;
				intersects = checkIntersection(rect[i].x2, rect[i].y2, rect[i].x2, rect[i].y1, rect[j].x2, rect[j].y2, rect[j].x2, rect[j].y1);
				if (intersects) break;
				
			}
		}
		if (intersects) {
			System.out.println(1);
		} else {
			System.out.println(0);
		}
	}

	public static boolean checkIntersection(long ax, long ay, long bx, long by, long cx, long cy, long dx, long dy) {
	    long det = (bx - ax)*(dy - cy) - (by - ay)*(dx - cx);
	    if (det != 0) {
	        /*
	         * Lines intersect. Check if intersection point is on both segments:
	         */
	        long detu = (cx - ax)*(dy - cy) - (cy - ay)*(dx - cx);
	        long detv = (cx - ax)*(by - ay) - (cy - ay)*(bx - ax);
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

	public static void main(String[] args) {
		new Intersecting_Rectangles();
	}

	private class Rectangle {
		public long x1;
		public long y1;
		public long x2;
		public long y2;

		public Rectangle(long x1, long y1, long x2, long y2) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}
	}
}