package hu.bme.iit.beta.pandaexpress.controller.view.tileview;

import hu.bme.iit.beta.pandaexpress.model.tile.Tile;

import java.awt.*;

public class WardrobeView extends TileView {

	/**
	 * @param tile - reference for an Wardrobe
	 * 
	 * Constructor - sets the Wardrobe specific properties
	 */
	public WardrobeView(Tile tile) {
		super(tile, new Color(220, 220, 255), "Sz");
	}
}
