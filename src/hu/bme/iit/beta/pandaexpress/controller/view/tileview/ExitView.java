package hu.bme.iit.beta.pandaexpress.controller.view.tileview;

import hu.bme.iit.beta.pandaexpress.model.tile.Tile;

import java.awt.*;

public class ExitView extends TileView {

	/**
	 * @param tile - reference for an Exit
	 * 
	 * Constructor - sets the Exit specific properties
	 */
	public ExitView(Tile tile) {
		super(tile, new Color(255, 220, 220), "Ki");
	}
}
