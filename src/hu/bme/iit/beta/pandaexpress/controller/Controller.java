package hu.bme.iit.beta.pandaexpress.controller;

import hu.bme.iit.beta.pandaexpress.controller.view.View;
import hu.bme.iit.beta.pandaexpress.controller.view.animalview.OrangutanView;
import hu.bme.iit.beta.pandaexpress.controller.view.animalview.pandaview.BeepingPandaView;
import hu.bme.iit.beta.pandaexpress.controller.view.animalview.pandaview.RingingPandaView;
import hu.bme.iit.beta.pandaexpress.controller.view.animalview.pandaview.TiredPandaView;
import hu.bme.iit.beta.pandaexpress.controller.view.tileview.*;
import hu.bme.iit.beta.pandaexpress.debug.interpreter.Environment;
import hu.bme.iit.beta.pandaexpress.debug.interpreter.Interpreter;
import hu.bme.iit.beta.pandaexpress.model.Stage;
import hu.bme.iit.beta.pandaexpress.model.Steppable;
import hu.bme.iit.beta.pandaexpress.model.animal.Animal;
import hu.bme.iit.beta.pandaexpress.model.animal.Orangutan;
import hu.bme.iit.beta.pandaexpress.model.animal.panda.BeepingPanda;
import hu.bme.iit.beta.pandaexpress.model.animal.panda.RingingPanda;
import hu.bme.iit.beta.pandaexpress.model.animal.panda.TiredPanda;
import hu.bme.iit.beta.pandaexpress.model.tile.*;
import hu.bme.iit.beta.pandaexpress.model.tile.machine.ChocolateMachine;
import hu.bme.iit.beta.pandaexpress.model.tile.machine.SlotMachine;
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

	private static String initFile = "src/hu/bme/iit/beta/pandaexpress/controller/init.txt";

	private Orangutan selectedOrangutan;
	private List<View> views;
	private Timer timer;

	private Controller(){
		timer = new Timer();
		timer.addSteppable(this);
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
		timer.start();
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
			registerIfSteppable(tile);
		}
		for(Tile tile : tiles){
			Animal animal;
			if((animal = tile.getAnimal()) != null)
				views.add(createAnimalView(animal));
			registerIfSteppable(animal);
		}
	}

	private View createTileView(Tile tile){
		Class tileType = tile.getClass();
		if(tileType == Chair.class)
			return new ChairView(tile);
		if(tileType == ChocolateMachine.class)
			return new ChocolateMachineView(tile);
		if(tileType == Entry.class)
			return new EntryView(tile);
		if(tileType == Exit.class)
			return new ExitView(tile);
		if(tileType == SlotMachine.class)
			return new SlotMachineView(tile);
		if(tileType == Tile.class)
			return new TileView(tile);
		if(tileType == Wardrobe.class)
			return new WardrobeView(tile);
		if(tileType == WeakTile.class)
			return new WeakTileView(tile);
		return null;
	}

	private View createAnimalView(Animal animal){
		Class animalType = animal.getClass();
		if(animalType == Orangutan.class)
			return new OrangutanView(animal);
		if(animalType == BeepingPanda.class)
			return new BeepingPandaView(animal);
		if(animalType == RingingPanda.class)
			return new RingingPandaView(animal);
		if(animalType == TiredPanda.class)
			return new TiredPandaView(animal);
		return null;
	}

	private void registerIfSteppable(Object o){
		if(o instanceof Steppable)
			timer.addSteppable((Steppable)o);
	}

	@Override
	public void onAttach() {
		Graphics2D g = getGraphics();
		g.setPaint(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());

		step();

		flush();
	}

	public void setSelectedOrangutan(Orangutan selected){
		selectedOrangutan = selected;
		System.out.println("orangutan selected");
	}

	public void tileClicked(Tile tile){
		if(selectedOrangutan == null) return;
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
		for(View v: views) {
			v.onDraw(g, getWidth(), getHeight());
		}
		flush();
	}
}
