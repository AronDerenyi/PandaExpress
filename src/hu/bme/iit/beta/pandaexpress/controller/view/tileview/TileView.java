package hu.bme.iit.beta.pandaexpress.controller.view.tileview;

import java.awt.Color;
import java.awt.Graphics2D;

import hu.bme.iit.beta.pandaexpress.controller.Controller;
import hu.bme.iit.beta.pandaexpress.controller.view.View;
import hu.bme.iit.beta.pandaexpress.model.tile.Tile;

public class TileView extends View {
	protected Tile tile;
	protected int r= 50;
	
	public TileView(Tile t) {
		tile= t;
	}
	
	@Override
	public void onDraw(Graphics2D graphics, int screenWidth, int screenHeight) {
		drawCircle(graphics, tile.getPositionX(), tile.getPositionY(), r, Color.WHITE);
	    onDrawConnection(graphics, screenWidth, screenHeight);
	}
	 
	protected void onDrawConnection(Graphics2D graphics, int screenWidth, int screenHeight) {
		for(int i=0; i < tile.getNeighbors().length; i++) {
			Tile neighbor= tile.getNeighbors()[i];
			drawConnection(graphics, tile.getPositionX(), tile.getPositionY(), r, neighbor.getPositionX(), neighbor.getPositionY(), r);
		}
	}

	@Override
	public boolean onClick(int mouseX, int mouseY) {
		if(!isCircleClicked(mouseX, mouseY, tile.getPositionX(), tile.getPositionY(), r))
			return false;
		Controller.getInstance().tileClicked(this.tile);
		return true;
	}
}
