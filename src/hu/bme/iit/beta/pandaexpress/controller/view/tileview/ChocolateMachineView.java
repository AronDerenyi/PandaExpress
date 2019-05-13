package hu.bme.iit.beta.pandaexpress.controller.view.tileview;

import java.awt.Color;
import java.awt.Graphics2D;

import hu.bme.iit.beta.pandaexpress.model.tile.Tile;

public class ChocolateMachineView extends TileView {

	public ChocolateMachineView(Tile t) {
		super(t);
	}
	
	@Override
	public void onDraw(Graphics2D graphics, int screenWidth, int screenHeight) {
		drawCircle(graphics, tile.getPositionX(), tile.getPositionY(), r, Color.WHITE, "Cs");
	    onDrawConnection(graphics, screenWidth, screenHeight);
	}

}
