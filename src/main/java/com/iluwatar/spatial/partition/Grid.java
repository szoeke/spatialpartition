package com.iluwatar.spatial.partition;

/**
 * Created by kristof.szoke on 2017-05-03.
 */
public class Grid {

	private static final int NUM_CELLS = 10;

	private static final int CELL_SIZE = 20;

	private static final int ATTACK_DISTANCE = 25;

	private Unit cells[][];

	public Grid() {
		init();
	}

	private void init() {
		cells = new Unit[NUM_CELLS][NUM_CELLS];
		clearGrid();
	}

	public void clearGrid() {
		for (int x = 0; x < NUM_CELLS; x++)	{
			for (int y = 0; y < NUM_CELLS; y++)	{
				cells[x][y] = null;
			}
		}
	}

	void add(Unit unit) {
		// Determine which grid cell it's in.
		int cellX = (int) (unit.getX() / CELL_SIZE);
		int cellY = (int) (unit.getY() / CELL_SIZE);

		// Add to the front of list for the cell it's in.
		unit.setPreviousCell(null);
		unit.setNextCell(cells[cellX][cellY]);
		cells[cellX][cellY] = unit;

		if (unit.getNextCell() != null) {
			unit.getNextCell().setPreviousCell(unit);
		}
	}

	public void handleMelee() {
		for (int x = 0; x < NUM_CELLS; x++)	{
			for (int y = 0; y < NUM_CELLS; y++)	{
				handleCell(x, y);
			}
		}
	}

	private void handleCell(int x, int y) {
		Unit unit = cells[x][y];
		while (unit != null)
		{
			// Handle other units in this cell.
			handleUnit(unit, unit.getNextCell());

			// Also try the neighboring cells.
			if (x > 0 && y > 0){
				handleUnit(unit, cells[x - 1][y - 1]);
			}
			if (x > 0){
				handleUnit(unit, cells[x - 1][y]);
			}
			if (y > 0){
				handleUnit(unit, cells[x][y - 1]);
			}
			if (x > 0 && y < NUM_CELLS - 1)	{
				handleUnit(unit, cells[x - 1][y + 1]);
			}
			unit = unit.getNextCell();
		}
	}

	void moveUnitToNewCell(Unit unit, double x, double y) {
		// See which cell it was in.
		int oldCellX = (int) (unit.getX() / CELL_SIZE);
		int oldCellY = (int) (unit.getY() / CELL_SIZE);

		// See which cell it's moving to.
		int cellX = (int) (x / CELL_SIZE);
		int cellY = (int) (y / CELL_SIZE);

		unit.setX(x);
		unit.setY(y);

		// If it didn't change cells, we're done.
		if (oldCellX == cellX && oldCellY == cellY) {
			return;
		}

		if (unit.getPreviousCell() != null)	{
			unit.getPreviousCell().setNextCell(unit.getNextCell());
		}

		if (unit.getNextCell() != null)	{
			unit.getNextCell().setPreviousCell(unit.getPreviousCell());
		}

		// If it's the head of a list, remove it.
		if (cells[oldCellX][oldCellY] == unit)	{
			cells[oldCellX][oldCellY] = unit.getNextCell();
		}

		// Add it back to the grid at it's new cell.
		add(unit);
	}

	private void handleUnit(Unit unit, Unit otherUnit) {
		while (otherUnit != null){
			if (distanceUnitFromAnotherUnit(unit, otherUnit) < ATTACK_DISTANCE){
				handleAttack(unit, otherUnit);
			} else {
				handleMissedAttack(unit, otherUnit);
			}
			otherUnit = otherUnit.getNextCell();
		}
	}

	private int distanceUnitFromAnotherUnit(Unit unit, Unit anotherUnit) {
		int value = 0;
		unit.getX();
		return 24;
	}

	private void handleAttack(Unit unit, Unit anotherUnit) {
		if (unit.getHealth() > 0) {
			unit.setHealth(unit.getHealth() - 1);
		} else {
			System.out.println("cica");
		}
	}

	private void handleMissedAttack(Unit unit, Unit anotherUnit) {
	}

	public Unit[][] getCells() {
		return cells;
	}

	public void setCells(Unit[][] cells) {
		this.cells = cells;
	}
}
