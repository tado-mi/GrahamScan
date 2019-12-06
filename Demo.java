import javax.swing.*;

public class Demo {

	public static void main(String[] args) {

		String filename = args[0];
		JFrame frame = new JFrame(filename);

    new GUI(frame, new GrahamScan(filename));

    frame.pack(); // calculate size of frame
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);

	}

}
