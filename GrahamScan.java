// for the use of Stack, Arrays
import java.util.*;

// for implementing the ActionListener
import java.awt.*;
import java.awt.event.*;

// for extending JFrame and using Timer
import javax.swing.*;

public class GrahamScan extends JFrame implements ActionListener{

	// set of the Points and the Hull
	Point[] P, Hull;

	// GUI parameters
	int margin, width, height, radius;
	GrahamScanGUI GUI;

	// board on which the Hull is displayed
	int boardWidth, boardHeight;
	// control Panel
	int cPanelWidth, cPanelHeight;

	// live demo
	int step;			// steps of the algorithm
	int index;			// communicates information between steps >2
	Stack<Point> hull;	// var shared between all the steps
	Point onHull;		// var shared between steps 1, 2 and 3

	// timer
	// note: java.util also has a Timer
	javax.swing.Timer timer;

	public GrahamScan(Point[] P) {

		this.P = P;
		
		// GUI
		// set parameters
        margin	= 50;
        radius  = 8;	// default

        boardWidth	= 700;
        boardHeight	= 700;

        cPanelWidth  = 200;
        cPanelHeight = 700;

        width  = boardWidth  + cPanelWidth + 4 * margin;
        height = boardHeight + 4 * margin;

		setSize (width, height);
        setTitle("Graham Scan");
        
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // since Java Graphics treats screen as a positive coordinate system,
		// we need to translate Points st all the coordinates are positive
		translate();
		// scale according to the size of the screen and the rectangle
		scale();

		GUI = new GrahamScanGUI(this);
		add(GUI);

		// compute the Hull with Live Demo!
		step  = 0;
		timer = new javax.swing.Timer(1500, this);
		timer.start();

		// computeHull();

	}

	public void setRadius(int r) {

		radius = r;
		GUI.repaint();

	}

	// performs the Graham Scan
	public void computeHull() {

		if (step == 0) { // finished

			// stop the timer
			timer.stop();

			// save the findings
			Hull = new Point[hull.size()];
			int i = 0;
			while (!hull.empty()) {

				Point temp = hull.pop();
				Hull[i] = temp;
				i = i + 1;

			}

			// display
			GUI.grahamScan = this;
			GUI.repaint();

			// or print
			System.out.println("Graham Scan is over. Here are the results: ");
			printHull();

		}

		if (step == 1) { // step 1: identify an extremal Point

			System.out.println("Identifying an extremal Point.");

			hull = new Stack<Point>();

			// sort P wrt to y coordinate (break tie with x)
			Arrays.sort(P);

			// an extremal Point is bound to be on the hull
			onHull = P[0];
			hull.push(onHull);

			// !! show onHull
			GUI.grahamScan = this;
			System.out.println("Point " + onHull + " has been identified.");

		}
		
		if (step == 2) { // step 2: sort & find a Point non-identical to Point onHull
		
			System.out.println("Sorting wrt " + onHull + ".");

			// sort P wrt to the angle as seen from the Point onHull		
			Arrays.sort(P, 1, P.length, onHull.angleComparator());
			
			// find the first Point different from the Point onHull
			int i = 0;
			for (i = 1; i < P.length; i = i + 1) {

				if (!onHull.isEqual(P[i])) {

					break;
					// there are at least two distinct points

				}

			}

			index = i;
			// !! show P[i]
			System.out.println("Point " + P[i] + " has been identified as a distinct Point.");

		}

		if (step == 3) { // step 3: find 3rd non-colinear Point

			System.out.println("Looking for a non-colinear Point.");
		
			// find the first Point non-colinear with the Point onHull and the Point at index i
			int j = 0;
			for (j = index + 1; j < P.length; j = j + 1) {
				
				if (!onHull.isOn(P[index], P[j])) {

					break;
					// there are at least 3 non-colinear points

				}

			}
			
			// assume the Point at index (j - 1) will be on the hull
			hull.push(P[j - 1]);

			// !! show P[j]
			// !! show P[j] supposedly being on the Hull
			System.out.println("Point " + P[j] + " has been identified as a non-colinear Point and assumed to be on the Hull.");

			index = j;

		}

		if (step > 3) { // step n: process all the remaining Points

			// non-live demo code
			/*
				for (int i = index; i < P.length; i = i + 1) {
					
					Point top = hull.pop();

					while (!(hull.peek().isLeft(top, P[i]))) {

						top = hull.pop();

					}

					hull.push(top);
					hull.push(P[i]);
					
				}
			*/

			Point top = hull.pop();

			while (!(hull.peek().isLeft(top, P[index]))) {

				top = hull.pop();

			}

			hull.push(top);
			hull.push(P[index]);

			// !! show top
			// !! show P[index]
			// !! show the lines
			System.out.println("Points " + top + " and " + P[index] + " are assumed to be on the Hull.");


			// note: P[index] has been processed at this step
			index = index + 1;

			if (index == P.length) {

				// algorithm is finished
				step = -1;

			}

		}

	}

	public void translate() {

		// sort P wrt to y coordinate (break tie with x)
		Arrays.sort(P);

		if (P[0].y < margin) {

			// translate all the Points
			// note: iterating backwards so that P[0].y will change the last
			for (int i = P.length - 1; i > -1; i = i - 1) {

				P[i].y = P[i].y + Math.abs(P[0].y) + margin;

			}

		}

		// find the smallest x coordinate
		double min = Double.MAX_VALUE;
		for (int i = 0; i < P.length; i = i + 1) {

			if (P[i].x < min) min = P[i].x;			

		}

		if (min < margin) {

			// translate all the Points
			for (int i = 0; i < P.length; i = i + 1) {

				P[i].x = P[i].x + Math.abs(min) + margin;

			}

		}
		
	}

	public void translate(int x, int y) {

		for (int i = 0; i < P.length; i = i + 1) {

			P[i].x = P[i].x + x;
			P[i].y = P[i].y + y;

		}


	}

	public void scale() {

		// 'compute' the rectangle within which the hull could live
		double[] min = {Double.MAX_VALUE, Double.MAX_VALUE};
		double[] max = {Double.MIN_VALUE, Double.MIN_VALUE};

		
		if (Hull != null) {

			for (int i = 0; i < Hull.length; i = i + 1) {

				Point focus = Hull[i];

				if (focus.x < min[0]) min[0] = focus.x;
				if (focus.y < min[1]) min[1] = focus.y;

				if (focus.x > max[0]) max[0] = focus.x;
				if (focus.y > max[1]) max[1] = focus.y;

			}

		} else {

			for (int i = 0; i < P.length; i = i + 1) {

				Point focus = P[i];

				if (focus.x < min[0]) min[0] = focus.x;
				if (focus.y < min[1]) min[1] = focus.y;

				if (focus.x > max[0]) max[0] = focus.x;
				if (focus.y > max[1]) max[1] = focus.y;

			}

		}

		// !! draw the background

		double w = max[0] - min[0];
		double wFactor = boardWidth / w;

		double h = max[1] - min[1];
		double hFactor = boardHeight / h;

		// scale
		for (int i = 0; i < P.length; i = i + 1) {

			P[i].x = (int) (P[i].x * wFactor);
			P[i].y = (int) (P[i].y * hFactor);


		}

		translate(2 * margin, 2 * margin);

	}

	// for debugging purposes
	public void printHull() {

		for (int i = 0; i < Hull.length - 1; i = i + 1) {

			System.out.print(Hull[i] + ", ");

		}

		System.out.println(Hull[Hull.length - 1]);

	}

	public void actionPerformed(ActionEvent e) {
    
    	step = step + 1;
    	System.out.print("Step " + step + ": ");
    	computeHull();
        
    }

}