package life;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 * Main panel, containing grid panel and control panel.
 * 
 * @author waugh
 *
 */
public class LifePanel extends JPanel {
	private LifeGridPanel gridPanel;
	private LifeControlPanel controlPanel;
	
	public LifePanel(int xSize, int ySize, int xCells, int yCells) {
		super();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.gridPanel = new LifeGridPanel(xSize, ySize, xCells, yCells);
		this.addMouseListener(this.gridPanel);
		this.controlPanel = new LifeControlPanel(gridPanel);
		this.add(gridPanel);
		this.add(controlPanel);
	}

	public void updateCells() {
		gridPanel.updateCells();
	}

	
}
