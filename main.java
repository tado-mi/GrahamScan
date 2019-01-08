// for the use of Random
import java.util.*;

// for the use of File
import java.io.*;

public class main {

	public static void randomPoints(int n) {

		Point[] P = new Point[n];

		Random random = new Random();

		for (int i = 0; i < P.length; i = i + 1) {
			
			int x = random.nextInt();
			int y = random.nextInt();
			
			P[i] = new Point(x, y);
			
		}

		GrahamScan grahamScan = new GrahamScan(P);
		// uncomment if n > 50
		// grahamScan.setRadius(4);


	}

	public static void fromFile(String filename) throws FileNotFoundException {

		Scanner scanner = new Scanner(new File(filename));

		Point[] P = new Point[scanner.nextInt()];
		
		for (int i = 0; i < P.length; i = i + 1) {
			
			int x = scanner.nextInt();
			int y = scanner.nextInt();
			
			P[i] = new Point(x, y);
			
		}

		scanner.close();
		
		GrahamScan grahamScan = new GrahamScan(P);

	}

	public static void main(String[] args) throws FileNotFoundException {

		if (args[0].equals("random")) {

			randomPoints(Integer.parseInt(args[1]));

		} else {

			fromFile(args[0]);

		}

	}

}
