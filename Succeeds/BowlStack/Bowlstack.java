import java.util.*;
import java.io.*;

public class Bowlstack {
	public int B;

	public Bowl[] bowls;
	public Bowl[] bowlStack;
	public int head = -1;
	public int count = 0;
	public double minHeight = Double.MAX_VALUE;
	public boolean[] stacked;
	public double currentMin = Double.MAX_VALUE;

	public Bowlstack() {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int t=0; t<T; t++) {
			minHeight = Double.MAX_VALUE;
			head = 0;
			B = sc.nextInt();
			bowls = new Bowl[B];

			for (int i=0; i<B; i++) {
				double[] bowl = new double[3];
				bowl[0] = sc.nextDouble();
				bowl[1] = sc.nextDouble();
				bowl[2] = sc.nextDouble();
				bowls[i] = new Bowl(bowl, 0);
			}

			bowlStack = new Bowl[B];
			stacked = new boolean[B];

			currentMin = Double.MAX_VALUE;
			stack(0);
			System.out.println((int) minHeight);

			//bowlStack = new Bowl[B];
			//stacked = new boolean[B];
			//double min2 = stack2(0);

			//System.out.println(((int) min2));
		}
	}

	public void stack(int count) {
		if (count == B) {
           // System.out.println("============================================================");
            double tempMin = 0;
            for (int i=0; i<B; i++) {
               // System.out.println(bowlStack[i]);
                double tempMin2 = bowlStack[i].y + bowlStack[i].bowl[0];
                if (tempMin2 > tempMin) {
                    tempMin = tempMin2;
                }
            }
           // System.out.println(tempMin);
           // System.out.println("============================================================");
            minHeight = tempMin;
        }

		double min = Double.MAX_VALUE;
		double maxY, tempY = 0;
		for (int i=0; i<B; i++) {
			if (!stacked[i]) {
				maxY = 0;
				for (int j=0; j<count; j++) {
					tempY = bowlStack[j].y + bowls[i].getY(bowlStack[j]);
					if (maxY<tempY) {
						maxY = tempY;
					}
					//System.out.println(bowlStack[j] + " " + bowlStack[count]);
				}
				if (maxY+bowls[i].bowl[0] > minHeight) {
					continue;
				}

				bowlStack[count] = bowls[i];
				stacked[i] = true;
				bowls[i].y = maxY;
				stack(count + 1);
				bowls[i].y = 0;
				stacked[i] = false;
			}
		}
	}

	public double stack2(int count) {
		double minHeight = 0;
		for (int i=0; i<count; i++) {
			//System.out.println("Counting " + i + " " + bowlStack[i]);
			double tempMin = bowlStack[i].y + bowlStack[i].bowl[0];
			if (tempMin > minHeight) {
				minHeight = tempMin;
			}
		}

		if (count == B) {
			//System.out.println(minHeight);
			currentMin = Math.min(currentMin, minHeight);
			return currentMin;
		} else if (currentMin != Double.MAX_VALUE && minHeight >= currentMin) {
			//System.out.println("exit: " + currentMin);
			return currentMin;
		}

		for (int i=0; i<B; i++) {
			if (!stacked[i]) {
				bowlStack[++head] = bowls[i];
				bowls[i].y = 0;
				stacked[i] = true;
				
				if (head > 0) {
					for (double inc=10.0; inc>0.01; inc-=inc/10) {
						boolean done = false;
						while (!done && head != 0) {
							for (int j=0; j<head; j++) {
								if (bowls[i].isColliding(bowlStack[j])) {
									//bowls[i].y += bowlStack[j].getY(bowls[i]);
									done = false;
									break;
								}
								done = true;
							}
							if (!done) {
								bowls[i].y += inc;
							}
						}
						if (inc > 0.1) {
							bowls[i].y -= inc;
						}
					}
				}

				double tempMin = stack2(count + 1);
				//System.out.println("stack2: " + bowls[i]);

				stacked[i] = false;
				head--; 
			}
		}
		return currentMin;
	}

	private class Bowl {
		public double slope = 0;
		public double y;
		public double[] bowl;

		public Bowl(double[] bowl, double y) {
			this.bowl = bowl;
			this.y = y;
		}

		public double getY(Bowl bottom) {
			//System.out.println(y + " " + this + " " + bottom);
			double tempY = 0;
			if (bottom.getSlope() > getSlope()) {
				tempY = bottom.bowl[0]*(bowl[1]-bottom.bowl[1])/(bottom.bowl[2]-bottom.bowl[1]);	
			} else if (bowl[2]<bottom.bowl[2]) {
				tempY = bottom.bowl[0]*(bowl[2]-bottom.bowl[1])/(bottom.bowl[2]-bottom.bowl[1]) - bowl[0];
			} else {
				tempY = bottom.bowl[0] - bowl[0]*(bottom.bowl[2]-bowl[1])/(bowl[2]-bowl[1]);
			}
			if (tempY < 0) {
				return 0;
			}
			if (tempY > bottom.bowl[0]) {
				return bottom.bowl[0];
			}
			return tempY;
			//System.out.println(y + " " + this + " " + bottom);
		}

		/*
		public void getY(Bowl bottom) {
			//System.out.println(y + " " + this + " " + bottom);
			double tempY = 0;
			if (bowl[1] <= bottom.bowl[1]) {
				if (getSlope() >= bottom.getSlope()) {
					System.out.println("1");
					tempY = tempY;
				} else {
					if (bowl[0] >= bottom.bowl[0]) {
					System.out.println("2");
						tempY = bottom.getLipIntersect(this);
					} else {
						if (bowl[2] >= bottom.bowl[2]) {
					System.out.println("3");
							tempY = bottom.getLipIntersect(this);
						} else {
					System.out.println("4");
							tempY = getLipIntersect(bottom) + bowl[0];
						}
					}
				}
			} else {
				if (getSlope() >= bottom.getSlope()) {
					System.out.println("5");
					tempY = getBaseIntersect(bottom);
				} else {
					System.out.println("6");
					tempY = bottom.getLipIntersect(this);
				}
			}
			
			tempY += bottom.y;
			if (tempY > y) {
				y = tempY;
			}

			//System.out.println(y + " " + this + " " + bottom);
		}
		*/

		public double getLipIntersect(Bowl top) {
			return bowl[0] - top.F2(bowl[2] - top.bowl[1]);
		}

		public double getBaseIntersect(Bowl bottom) {
			return bottom.F(bowl[1]);
		}

		public double F(double x) {
			return getSlope() * x - (getSlope()*bowl[1]);
		} 

		public double F2(double x) {
			return getSlope() * x;
		} 

		public double getSlope() {
			if (slope == 0) {
				slope = (bowl[2] - bowl[1])/bowl[0];			} 
			return slope;
		}

		public boolean isColliding(Bowl other) {
			if (getLeftSide().intersecting(other.getLeftSide())) {
				return true;
			} else if (getBottomSide().intersecting(other.getBottomSide())) {
				return true;
			}
			return false;
		}

		public Line getLeftSide() {
			return new Line(-bowl[1], y, -bowl[2], y + bowl[0]);
		}
		public Line getBottomSide() {
			return new Line(-bowl[1], y, bowl[1], y);
		}
		public Line getRightSide() {
			return new Line(bowl[1], y, bowl[2], y + bowl[0]);
		}

		public String toString() {
			return "s: " + slope + " y: " + y + " " + Arrays.toString(bowl);
		}
	}

	private class Line {
		public double x1;
		public double y1;
		public double x2;
		public double y2;

		public Line(double x1, double y1, double x2, double y2) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}

		public boolean intersecting(Line other) {
			int dir1 = direction(x1,y1,x2,y2,other.x1,other.y1);
			int dir2 = direction(x1,y1,x2,y2,other.x2,other.y2);
			int dir3 = direction(other.x1,other.y1,other.x2,other.y2,x1,y1);
			int dir4 = direction(other.x1,other.y1,other.x2,other.y2,x2,y2);
		
			if (dir1 != dir2 && dir3 != dir4) {
				return true;
			} else if (dir1 == 0 && onLine(this, other.x1, other.y1)) {
				return true;
			} else if (dir2 == 0 && onLine(this, other.x2, other.y2)) {
				return true;
			} else if (dir3 == 0 && onLine(other, x1, y1)) {
				return true;
			} else if (dir4 == 0 && onLine(other, x2, y2)) {
				return true;
			}

			return false;
		}

		public boolean onLine(Line line, double x, double y) {
			if (x <= Math.max(line.x1, line.x2) && x <= Math.min(line.x1, line.x2) && 
				y <= Math.max(line.y1, line.y2) && y <= Math.min(line.y1, line.y2)) {
				return true;
			}
			return false;
		}

		// -1 ccw
		// 0 colinear
		// 1 cw
		public int direction(double x1, double y1, double x2, double y2, double x3, double y3) {
			int val = (int) ((y2-y1)*(x3-x2)-(x2-x1)*(y3-y2));

			if (val == 0) {
				return 0;
			} else if (val < 0) {
				return -1;
			} else {
				return 1;
			}
		}
	}

	public static void main(String[] args) {
		new Bowlstack();
	}
}