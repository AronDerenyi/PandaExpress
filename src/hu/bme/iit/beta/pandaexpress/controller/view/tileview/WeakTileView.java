package hu.bme.iit.beta.pandaexpress.controller.view.tileview;

import hu.bme.iit.beta.pandaexpress.model.tile.Tile;
import hu.bme.iit.beta.pandaexpress.model.tile.WeakTile;

import java.awt.*;

public class WeakTileView extends TileView {

	public WeakTileView(Tile tile) {
		super(tile, new Color(230, 230, 230), "T");
	}

	@Override
	public void onDraw(Graphics2D graphics, int screenWidth, int screenHeight) {
		if (tile instanceof WeakTile && ((WeakTile) tile).getLives() == 0) {
			color = Color.BLACK;
		}
		super.onDraw(graphics, screenWidth, screenHeight);
	}
}
