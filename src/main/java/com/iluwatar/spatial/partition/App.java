package com.iluwatar.spatial.partition;

/**
 * Created by kristof.szoke on 2017-05-08.
 */
public class App {

	public static void main(String[] args) {
		Grid grid = new Grid();
		Unit unit = new Unit(grid, 7, 9);
		Unit anotherUnit = new Unit(grid, 8, 9);
		unit.setNextCell(anotherUnit);
		//unit.move(4, 5);
		//grid.add(unit);
		grid.handleMelee();
		unit.move(3, 5);
	}
}
