package life;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

/**
 * Panel containing the actual Life grid.
 * 
 * @author waugh
 *
 */
public class LifeGridPanel extends JPanel implements MouseListener, MouseMotionListener {
	private static final long serialVersionUID = 1L;
	private LifeGrid grid;

	public LifeGridPanel(int xSize, int ySize, int xCells, int yCells) {
		this.setPreferredSize(new Dimension(xSize, ySize));
		this.grid = new LifeGrid(xCells, yCells);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int width  = this.getWidth();
		int height = this.getHeight();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.WHITE);
		double xCellSize = ((double) width)  / grid.xCells;
		double yCellSize = ((double) height) / grid.yCells;
		for (int i=0; i<grid.xCells; ++i) {
			for (int j=0; j<grid.yCells; ++j) {
				if (!grid.cellAlive(i,j)) continue;
				int x0 = (int)(i * xCellSize + 0.5);
				int y0 = (int)(j * yCellSize + 0.5);
				g.fillRect(x0, y0, (int)xCellSize, (int)yCellSize);
			}
		}
	}

	public void updateCells() {
		this.grid = grid.evolve();
		this.repaint();
	}

	public void clearCells() {
		this.grid.clear();
		this.repaint();
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		activateCell(event);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}

	@Override
	public void mouseDragged(MouseEvent event) {
		activateCell(event);		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {}
	
	private void activateCell(MouseEvent event) {
		int ix = event.getX();
		int iy = event.getY();
		int width  = this.getWidth();
		int height = this.getHeight();
		double xCellSize = ((double) width)  / grid.xCells;
		double yCellSize = ((double) height) / grid.yCells;
		int ixCell = (int)(ix/xCellSize);
		int iyCell = (int)(iy/yCellSize);
		grid.activateCell(ixCell,iyCell);
		this.repaint();
	}
}
