// for the use of Stack, Arrays
import java.util.*;

// for implementing the ActionListener
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.Line2D;

// for the use of File
import java.io.*;
import java.util.*;

// for extending JFrame and using Timer
import javax.swing.*;

public class GrahamScan extends JPanel {

	// set of the Points and the Hull
	ArrayList<Point> P;
	Stack<Point> hull;

	public GrahamScan(String filename) {

		P = new ArrayList<>();

		setPreferredSize(new Dimension(750, 700));

		try {

			Scanner scanner = new Scanner(new File(filename));

			while (scanner.hasNext()) {

				int x = scanner.nextInt();
				int y = scanner.nextInt();
				P.add(new Point(x, y));

			}

			scanner.close();

		} catch (FileNotFoundException e) {

			System.out.println("File " + filename + " not found exception: " + e);

		}

		computeHull();
		scale(750, 700);

		repaint();

	}

	public void log(String s) {
		// temporary function will be used to log steps into an external .txt file
		System.out.println(s);
	}

	public void computeHull() {

		hull = new Stack<>();

		log("Identifying an extremal Point.");
		// sort P wrt to y coordinate (break tie with x)
		Collections.sort(P);

		// an extremal Point is bound to be on the hull
		Point onHull = P.get(0);
		log("Point " + onHull + " has been identified.");

		hull.push(onHull);

		log("Sorting wrt " + onHull + ".");
		// sort P wrt to the angle as seen from the Point onHull
		Collections.sort(P, onHull.angleComparator());

		int index = 0;
		for (int i = 1; i < P.size(); i = i + 1) {

			if (!onHull.isEqual(P.get(i))) {

				index = i;
				break;
				// there are at least two distinct points

			}

		}

		log("Point " + P.get(index) + " has been identified as a distinct Point.");
		log("Looking for a non-colinear Point.");

		for (int i = index + 1; i < P.size(); i = i + 1) {

			if (!onHull.isOn(P.get(index), P.get(i))) {

				index = i - 1;
				break;
				// there are at least 3 non-colinear points

			}

		}

		log("Point " + P.get(index) + " has been identified as a non-colinear Point and assumed to be on the Hull.");

		hull.push(P.get(index));
		index = index + 1;

		for (int i = index; i < P.size(); i = i + 1) {

			Point top = hull.pop();

			while (!(hull.peek().isLeft(top, P.get(i)))) {
				top = hull.pop();
			}

			hull.push(top);
			hull.push(P.get(i));

		}

		log("Graham Scan is over. Here are the results: ");
		repaint();
		printHull();

	}

	public void scale(int w, int h) {

		// compute the rectangle within which the points fit
		Point min = new Point(Double.MAX_VALUE, Double.MAX_VALUE);
		Point max = new Point(Double.MIN_VALUE, Double.MIN_VALUE);

		for (Point p: P) {

			if (p.x < min.x) min.x = p.x;
			if (p.y < min.y) min.y = p.y;

			if (p.x > max.x) max.x = p.x;
			if (p.y > max.y) max.y = p.y;

		}

		int margin = 200;

		double x = max.x - min.x;
		w = w - margin;
		double xScale = w / x;

		double y = max.y - min.y;
		h = h - margin;
		double yScale = h / y;

		// stretch the points
		for (Point p: P) {
			p.x = p.x * xScale;
			p.y = p.y * yScale;
		}

	}

	// for debugging purposes
	public void printHull() {

		for (Point p: hull) {

			System.out.print(p + ", ");

		}

	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		// background
		g.setColor(new Color(155, 255, 255));
		g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);

		g.setColor(new Color(0, 10, 0));
		for (Point p: P) {
			p.draw(g, 10);
		}

		if (hull != null) {
			g.setColor(new Color(0, 10, 200));
			int len = hull.size();

			for (int i = 0; i < len; i = i + 1) {

				Point u = hull.get(i);
				Point v = hull.get((i + 1) % len);

				Graphics2D g2 = (Graphics2D) g;
				g2.setStroke(new BasicStroke(5));
				g2.draw(new Line2D.Float((int)u.x, (int)u.y, (int)v.x, (int)v.y));

			}

		}

		// frame
		g.setColor(Color.black);
		g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);

	}

}
