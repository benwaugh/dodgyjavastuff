package life;

import java.util.Random;

/**
 * The grid, containing cells represented by a 2D array of integers, where
 *   0 = dead
 *   1 = alive
 * 
 * @author waugh
 *
 */
public class LifeGrid {
	public final int xCells;
	public final int yCells;
	private int[][] cells;
	private Random rand = new Random();
	
	public LifeGrid(int xCells, int yCells) {
		cells = randomGrid(xCells,yCells);
		this.xCells = xCells;
		this.yCells = yCells;
	}

	private LifeGrid(int[][] newCells) {
		this.cells = newCells;
		this.xCells = newCells[0].length;
		this.yCells = newCells.length;
	}

	private int[][] randomGrid(int xCells, int yCells) {
		int[][] cells = new int[yCells][xCells];
		for (int i=0; i<xCells; ++i) {
			for (int j=0; j<yCells; ++j) {
				cells[j][i] = rand.nextInt(2);
			}
		}
		return cells;
	}

	public boolean cellAlive(int i, int j) {
		return cells[j][i] == 1;
	}

	public LifeGrid evolve() {
		int[][] newCells = new int[yCells][xCells];
		for (int i=0; i<xCells; ++i) {
			for (int j=0; j<yCells; ++j) {
				int iLeft = i-1;
				if (iLeft<0) iLeft += xCells;
				int iRight = i+1;
				if (iRight>=xCells) iRight -= xCells;
				int jUp = j-1;
				if (jUp<0) jUp += yCells;
				int jDown = j+1;
				if (jDown>=yCells) jDown -= yCells;
				int numLiveCells = 0;
				numLiveCells += cells[j][iLeft];
				numLiveCells += cells[jUp][iLeft];
				numLiveCells += cells[jUp][i];
				numLiveCells += cells[jUp][iRight];
				numLiveCells += cells[j][iRight];
				numLiveCells += cells[jDown][iRight];
				numLiveCells += cells[jDown][i];
				numLiveCells += cells[jDown][iLeft];
				newCells[j][i] = newState(cells[j][i],numLiveCells);
			}
		}
		return new LifeGrid(newCells);
	}

	private int newState(int oldState, int numLiveCells) {
		if (oldState==1) {
			if (numLiveCells<2) return 0;
			if (numLiveCells>3) return 0;
			return 1;
		} else {
			if (numLiveCells==3) return 1;
			return 0;
		}
	}

	public void activateCell(int ix, int iy) {
		if (ix<0 || ix>=xCells) return; // out of range
		if (iy<0 || iy>=yCells) return; // out of range
		this.cells[iy][ix] = 1;
	}

	public void clear() {
		cells = new int[yCells][xCells];		
	}

}
