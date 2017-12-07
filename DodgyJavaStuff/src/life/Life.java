package life;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * Main program for Conway's Game of Life.
 * @author waugh
 *
 */
public class Life {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
		        createAndDisplayGui();
			}
		});
	}

	protected static void createAndDisplayGui() {
		JFrame frame = new JFrame("Life");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		LifePanel panel = new LifePanel(1000,1000,100,100);
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}

}
