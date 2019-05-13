package hu.bme.iit.beta.pandaexpress.controller.view.tileview;

import java.awt.*;

import hu.bme.iit.beta.pandaexpress.controller.Controller;
import hu.bme.iit.beta.pandaexpress.controller.view.View;
import hu.bme.iit.beta.pandaexpress.model.tile.Tile;

public class TileView extends View {

	/**
	* reference for the tile
	*/
	public final Tile tile;

	/**
	* radius of the circle
	*/
	protected int radius = 20;
	/**
	* color of the circle
	*/
	protected Paint color;
	/**
	* text in the circle: shows the type of the tile, empty if it's a regular tile
	*/
	protected String text;

	/**
	 * @param tile - sets tile property
	 * @param color - sets color property
	 * @param text - sets text property
	 * 
	 * Protected constructor sets the properties of the class
	 */
	protected TileView(Tile tile, Paint color, String text) {
		this.tile = tile;

		this.color = color;
		this.text = text;
	}

	/**
	 * @param tile - sets tile property
	 * 
	 * Public Constructor - it gives the default value for the properties used by normal tiles
	 */
	public TileView(Tile tile) {
		this(tile, Color.WHITE, null);
	}

	/** 
	 * Draws a tile and the connections between the neighbors
	 * 
	 *  A tile is represented by a circle 
	 *  The tile properties positionX and positionY values determines the position of the circle on the screen
	 *  The text inside the circle show the type of the tile
	 *  Two tiles connected by a straight line
	 */
	@Override
	public void onDraw(Graphics2D graphics, int screenWidth, int screenHeight) {
		drawCircle(graphics, tile.getPositionX(), tile.getPositionY(), radius, color, text);

		for (Tile neighbor : tile.getNeighbors()) {
			drawConnection(graphics,
					tile.getPositionX(), tile.getPositionY(), radius,
					neighbor.getPositionX(), neighbor.getPositionY(), radius
			);
		}
	}

	/** 
	 * This method is called when a tile clicked
	 */
	@Override
	public boolean onClick(int mouseX, int mouseY) {
		if (Controller.getInstance().isOrangutanSelected() &&
				isCircleClicked(mouseX, mouseY, tile.getPositionX(), tile.getPositionY(), radius)) {

			Controller.getInstance().tileClicked(this.tile);
			return true;
		}
		return false;
	}
}
