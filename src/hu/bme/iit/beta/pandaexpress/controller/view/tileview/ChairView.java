package hu.bme.iit.beta.pandaexpress.controller.view.tileview;

import hu.bme.iit.beta.pandaexpress.model.tile.Tile;

import java.awt.*;

public class ChairView extends TileView {

	/**
	 * @param tile - reference for a Chair
	 * 
	 * Constructor - sets the ChairView specific properties
	 */
	public ChairView(Tile tile) {
		super(tile, new Color(160, 120, 100), "F");
	}
}
