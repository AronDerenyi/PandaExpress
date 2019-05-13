package hu.bme.iit.beta.pandaexpress.controller.view.tileview;

import hu.bme.iit.beta.pandaexpress.model.tile.Tile;

import java.awt.*;

public class EntryView extends TileView {

	/**
	 * @param tile - reference for an Entry
	 * 
	 * Constructor - sets the Entry specific properties
	 */
	public EntryView(Tile tile) {
		super(tile, new Color(220, 255, 220), "Be");
	}
}
