package hu.bme.iit.beta.pandaexpress.controller.view.tileview;

import hu.bme.iit.beta.pandaexpress.model.tile.Tile;

import java.awt.*;

public class SlotMachineView extends TileView {

	/**
	 * @param tile - reference for an SlotMachine
	 * 
	 * Constructor - sets the SlotMachine specific properties
	 */
	public SlotMachineView(Tile tile) {
		super(tile, new Color(255, 170, 50), "J");
	}
}
