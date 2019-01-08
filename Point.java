import java.awt.*;
import java.util.*;

public class Point implements Comparable<Point> {
	
	double x, y;
	
	// basic methods: constructor
	public Point(double x, double y) {
		
		this.x = x;
		this.y = y;
		
	}
	
	// basic methods: toString
	public String toString() {
		
		return "(" + x + ", " + y + ")";
		
	}
	
	// basic methods: isEqual
	public boolean isEqual(Point P) {
		
		if (P == null) return false;
		
		return (P.x == x) && (P.y == y);
		
	}
	
	// orientation wrt a line AB
	public int cross(Point A, Point B) {
		
		double val = (A.x - x) * (B.y - y) - (A.y - y) * (B.x - x);
		
		if (val < 0) return -1;
		
		if (val > 0) return  1;
		
		return 0;
		
	}
	
	public boolean isOn(Point A, Point B) {
		
		return cross(A, B) == 0;
		
	}
	
	public boolean isRight(Point A, Point B) {
		
		return cross(A, B) < 0;
		
	}
	
	public boolean isLeft(Point A, Point B) {
		
		return cross(A, B) > 0;
	}

	// compateTo for basic sortinc
	public int compareTo(Point P) {
		
		if (y < P.y) return -1;
		if (y > P.y) return  1;
		if (x < P.x) return -1;
		if (x > P.x) return  1;
		
		return 0;
		
	}
	
	// compares Points {P1, P2} with respect to the given Point
	// with intnetion to sort points left to right (therefore the switch of the sign)
	// treating colinear Points as 'equal'
	protected class angleComparator implements Comparator<Point> {
		
		public int compare(Point P1, Point P2) {
			
			int cross = cross(P1, P2);
			
			// switch around
			if (cross > 0) return -1;
			
			if (cross < 0) return  1;
			
			return 0;
			
		}
		
	}

	public Comparator<Point> angleComparator() {
		
		return new angleComparator();
		
	}

	// GUI methods
	public void draw(Graphics g, int r) {

		g.fillOval((int) (x - r / 2), (int) (y - r / 2), r, r);
		
	}
			
	public void draw(Graphics g, Point P) {

		g.drawLine((int) x, (int) y, (int) P.x, (int) P.y);

	}

}
