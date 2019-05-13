package hu.bme.iit.beta.pandaexpress.controller.view.tileview;

import hu.bme.iit.beta.pandaexpress.model.tile.Tile;

import java.awt.*;

public class ChocolateMachineView extends TileView {

	/**
	 * @param tile - reference for a ChocolateMachine
	 * 
	 * Constructor - sets the ChocolateMachine specific properties
	 */
	public ChocolateMachineView(Tile tile) {
		super(tile, new Color(170, 100, 50), "Cs");
	}
}
