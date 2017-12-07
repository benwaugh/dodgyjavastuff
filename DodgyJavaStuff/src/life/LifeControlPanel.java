package life;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Control panel, with buttons to send commands to the grid panel.
 * 
 * @author waugh
 *
 */
public class LifeControlPanel extends JPanel {
	private static final int TIMESTEP = 200;

	private LifeGridPanel gridPanel;
	Timer timer;

	public LifeControlPanel(LifeGridPanel gridPanel) {
		this.gridPanel = gridPanel;
		JButton bStart = new JButton("Start");
		JButton bStop  = new JButton("Stop");
		JButton bStep  = new JButton("Step");
		JButton bClear = new JButton("Clear");
		bStart.addActionListener((ActionEvent x) -> {this.startTimer();});
		bStop.addActionListener((ActionEvent x) -> {this.stopTimer();});
		bStep.addActionListener((ActionEvent x)->{this.gridPanel.updateCells();});
		bClear.addActionListener((ActionEvent x)->{this.gridPanel.clearCells();});

		this.add(bStart);
		this.add(bStop);
		this.add(bStep);
		this.add(bClear);
	}

	private void startTimer() {
		timer = new Timer(TIMESTEP,(ActionEvent x) -> {gridPanel.updateCells();});
		timer.start();
	}

	private void stopTimer() {
		if (timer != null) timer.stop();
		timer = null;
	}
}
