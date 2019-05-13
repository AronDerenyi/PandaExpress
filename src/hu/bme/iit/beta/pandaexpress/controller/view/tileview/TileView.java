package hu.bme.iit.beta.pandaexpress.controller.view.tileview;

import java.awt.*;

import hu.bme.iit.beta.pandaexpress.controller.Controller;
import hu.bme.iit.beta.pandaexpress.controller.view.View;
import hu.bme.iit.beta.pandaexpress.model.tile.Tile;

public class TileView extends View {

	public final Tile tile;

	private final int radius = 50;
	private final Paint color;
	private final String text;

	protected TileView(Tile tile, Paint color, String text) {
		this.tile = tile;

		this.color = color;
		this.text = text;
	}

	public TileView(Tile tile) {
		this(tile, Color.WHITE, null);
	}

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
