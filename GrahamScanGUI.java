// for the use of Color, Graphics
import java.awt.*;

// for extending JComponent
import javax.swing.*;

public class GrahamScanGUI extends JComponent{

	GrahamScan grahamScan;
	Graphics   graphics;

	public GrahamScanGUI(GrahamScan grahamScan) {

		this.grahamScan = grahamScan;
		repaint();
	
	}

	public void paintComponent(Graphics g) {

		graphics = g;

		drawBackground();
		drawHull();
		drawPoints();

		drawPanel();

	}

	public void drawBackground() {

		Color col = new Color(0, 190, 200);
		graphics.setColor(col);

		int m = 20;

		int x = 2 * grahamScan.margin - m;
		int y = 2 * grahamScan.margin - m;

		int w = grahamScan.boardWidth  + 2 * m;
		int h = grahamScan.boardHeight + 2 * m;

		graphics.fillRect(x, y, w, h);

	}

	public void drawPanel() {

		Color col = new Color(0, 200, 190);
		graphics.setColor(col);

		int m = 20;

		int x = 3 * grahamScan.margin + grahamScan.boardHeight;
		int y = 2 * grahamScan.margin - m;
		int w = grahamScan.cPanelWidth  + 2 * m;
		int h = grahamScan.cPanelHeight + 2 * m;

		graphics.fillRect(x, y, w, h);


	}

	public void drawPoints() {

		Color col = new Color(0, 10, 0);
		graphics.setColor(col);

		for (int i = 0; i < grahamScan.P.length; i = i + 1) {

			Point focus = grahamScan.P[i];
			focus.draw(graphics, grahamScan.radius);

		}

	}

	public void drawHull() {

		if (grahamScan.Hull == null) return;

		Color col = new Color(60, 0, 30);
		graphics.setColor(col);

		int len = grahamScan.Hull.length;
		
		for (int i = 0; i < len; i = i + 1) {

			Point focus1 = grahamScan.Hull[i];
			Point focus2 = grahamScan.Hull[(i + 1) % len];

			// draw a line
			focus1.draw(graphics, focus2);


		}

	}


}
