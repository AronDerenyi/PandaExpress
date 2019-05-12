package hu.bme.iit.beta.pandaexpress.controller;

import hu.bme.iit.beta.pandaexpress.controller.view.View;
import hu.bme.iit.beta.pandaexpress.debug.interpreter.Environment;
import hu.bme.iit.beta.pandaexpress.debug.interpreter.Interpreter;
import hu.bme.iit.beta.pandaexpress.model.Stage;
import hu.bme.iit.beta.pandaexpress.model.Steppable;
import hu.bme.iit.beta.pandaexpress.model.animal.Animal;
import hu.bme.iit.beta.pandaexpress.model.animal.Orangutan;
import hu.bme.iit.beta.pandaexpress.model.tile.Tile;
import hu.bme.iit.beta.pandaexpress.model.tile.WeakTile;
import hu.bme.iit.beta.pandaexpress.window.Screen;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.util.*;
import java.util.List;

public class Controller extends Screen implements Steppable {

	private static Controller instance;

	public static Controller getInstance(){
		if(instance == null) instance = new Controller();
		return instance;
	}

	private static String initFile = "src/hu/bme/iit/beta/pandaexpress/controller/initFile.txt";

	private Orangutan selectedOrangutan;
	private List<View> views;

	private Controller(){
		views = new ArrayList<>();
		try {
			Interpreter interpreter = new Interpreter(
					new FileInputStream(new File(initFile)),
					System.out,
					new Environment()
			);
			Set<Tile> tiles = new HashSet<>();
			DFS(Stage.getInstance().getEntry(), tiles);
			createViews(tiles);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static void DFS(Tile root, Set<Tile> foundTiles){
		Tile[] neighbours = root.getNeighbors();
		for(Tile neighbor : neighbours)
			if(foundTiles.add(neighbor))
				DFS(neighbor, foundTiles);
	}

	private void createViews(Collection<Tile> tiles){
		for(Tile tile : tiles){
			views.add(createTileView(tile));
			Animal animal;
			if((animal = tile.getAnimal()) != null)
				views.add(createAnimalView(animal));
		}
	}

	private View createTileView(Tile tile){
//		Class tileType = tile.getClass();
//		if(tileType == WeakTile.class)
//			return new WeakTileView(tile);
		return new View() {
			@Override
			public void onDraw(Graphics2D graphics, int screenWidth, int screenHeight) {
				drawCircle(graphics, 100, 100, 16, Color.CYAN, "T1");
			}
		};
	}

	private View createAnimalView(Animal animal){
//		Class animalType = animal.getClass();
//		if(animalType == Orangutan.class)
//			return new WeakTileView(tile);
		return new View() {
			@Override
			public void onDraw(Graphics2D graphics, int screenWidth, int screenHeight) {
				drawCircle(graphics, 100, 100, 16, Color.CYAN, "T1");
			}
		};
	}
	@Override
	public void onAttach() {
		Graphics2D g = getGraphics();
		g.setPaint(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());

		View v1 = new View() {
			@Override
			public void onDraw(Graphics2D graphics, int screenWidth, int screenHeight) {
				drawCircle(graphics, 100, 100, 16, Color.CYAN, "T1");
			}
		};

		View v2 = new View() {
			@Override
			public void onDraw(Graphics2D graphics, int screenWidth, int screenHeight) {
				drawCircle(graphics, 300, 200, 16, Color.YELLOW, "T2");
				drawConnection(graphics, 300, 200, 16, 100, 100, 16);
			}
		};

		v1.onDraw(g, getWidth(), getHeight());
		v2.onDraw(g, getWidth(), getHeight());

		flush();
	}

	public void setSelectedOrangutan(Orangutan selected){
		selectedOrangutan = selected;
	}

	public void tileClicked(Tile tile){
		for(Tile t: selectedOrangutan.getTile().getNeighbors())
			if(t == tile){
				selectedOrangutan.move(tile);
				break;
			}
		selectedOrangutan = null;
	}

	@Override
	protected void onClick(int x, int y) {
		for(View view : views)
			if(view.onClick(x, y))
				return;
	}

	@Override
	public void step() {
		Graphics2D g = getGraphics();
		g.setPaint(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		for(View v: views) v.onDraw(g, getWidth(), getHeight());
		flush();
	}
}
