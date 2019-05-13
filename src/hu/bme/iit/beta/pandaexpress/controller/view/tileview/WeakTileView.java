package hu.bme.iit.beta.pandaexpress.controller.view.tileview;

import hu.bme.iit.beta.pandaexpress.model.tile.Tile;
import hu.bme.iit.beta.pandaexpress.model.tile.WeakTile;

import java.awt.*;

public class WeakTileView extends TileView {

	/**
	 * @param tile - reference for an WeakTile
	 * 
	 * Constructor - sets the WeakTile specific properties
	 */
	public WeakTileView(Tile tile) {
		super(tile, new Color(230, 230, 230), "T");
	}

	
	/** 
	 *  Overrides TileView/onDraw - paints broken tile black 
	 */
	@Override
	public void onDraw(Graphics2D graphics, int screenWidth, int screenHeight) {
		if (tile instanceof WeakTile && ((WeakTile) tile).getLives() == 0) {
			color = Color.BLACK;
		}
		super.onDraw(graphics, screenWidth, screenHeight);
	}
}
