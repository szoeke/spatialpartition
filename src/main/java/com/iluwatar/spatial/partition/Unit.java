package com.iluwatar.spatial.partition;

/**
 * Created by kristof.szoke on 2017-05-03.
 */
public class Unit {

	private Grid grid;

	private double x;

	private double y;

	private Unit previousCell;

	private Unit nextCell;

	private int health;

	public Unit(Grid grid, double x, double y) {
		init(grid, x, y);
		grid.add(this);
	}

	private void init(Grid grid, double x, double y) {
		this.grid = grid;
		this.x = x;
		this.y = y;
		this.previousCell = null;
		this.nextCell = null;
		this.health = 5;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public Unit getPreviousCell() {
		return previousCell;
	}

	public void setPreviousCell(Unit previousCell) {
		this.previousCell = previousCell;
	}

	public Unit getNextCell() {
		return nextCell;
	}

	public void setNextCell(Unit nextCell) {
		this.nextCell = nextCell;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	void move(double x, double y) {
		grid.moveUnitToNewCell(this, x, y);
	}
}
