package hu.bme.iit.beta.pandaexpress.controller.view.tileview;

import java.awt.Color;
import java.awt.Graphics2D;

import hu.bme.iit.beta.pandaexpress.model.tile.Tile;

public class EntryView extends TileView {

	public EntryView(Tile t) {
		super(t);
	}
	
	@Override
	public void onDraw(Graphics2D graphics, int screenWidth, int screenHeight) {
		drawCircle(graphics, tile.getPositionX(), tile.getPositionY(), r, Color.WHITE, "Be");
	    onDrawConnection(graphics, screenWidth, screenHeight);
	}

}
