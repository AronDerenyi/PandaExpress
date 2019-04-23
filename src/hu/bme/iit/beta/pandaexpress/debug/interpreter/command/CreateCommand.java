package hu.bme.iit.beta.pandaexpress.debug.interpreter.command;

import hu.bme.iit.beta.pandaexpress.debug.interpreter.Environment;
import hu.bme.iit.beta.pandaexpress.model.animal.Orangutan;
import hu.bme.iit.beta.pandaexpress.model.animal.panda.BeepingPanda;
import hu.bme.iit.beta.pandaexpress.model.animal.panda.RingingPanda;
import hu.bme.iit.beta.pandaexpress.model.animal.panda.TiredPanda;
import hu.bme.iit.beta.pandaexpress.model.tile.*;
import hu.bme.iit.beta.pandaexpress.model.tile.machine.ChocolateMachine;
import hu.bme.iit.beta.pandaexpress.model.tile.machine.SlotMachine;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class CreateCommand implements Command {

	private static final Map<String, Supplier<Object>> MODEL_SUPPLIERS = new HashMap<>();

	static {
		MODEL_SUPPLIERS.put("Orangutan", Orangutan::new);

		MODEL_SUPPLIERS.put("BeepingPanda", BeepingPanda::new);
		MODEL_SUPPLIERS.put("RingingPanda", RingingPanda::new);
		MODEL_SUPPLIERS.put("TiredPanda", TiredPanda::new);

		MODEL_SUPPLIERS.put("ChocolateMachine", ChocolateMachine::new);
		MODEL_SUPPLIERS.put("SlotMachine", SlotMachine::new);

		MODEL_SUPPLIERS.put("Chair", Chair::new);
		MODEL_SUPPLIERS.put("Entry", Entry::new);
		MODEL_SUPPLIERS.put("Exit", Exit::new);
		MODEL_SUPPLIERS.put("Tile", Tile::new);
		MODEL_SUPPLIERS.put("Wardrobe", Wardrobe::new);
		MODEL_SUPPLIERS.put("WeakTile", WeakTile::new);
	}

	@Override
	public String getName() {
		return "create";
	}

	@Override
	public void call(String[] arguments, OutputStream output, Environment environment) {
		PrintWriter writer = new PrintWriter(new OutputStreamWriter(output));

		if (arguments.length != 3) {
			writer.println("Create requires 2 arguments");
			writer.flush();
			return;
		}

		try {
			String type = arguments[1];
			String name = arguments[2];

			Object object = MODEL_SUPPLIERS.get(type).get();
			environment.add(name, object);
		} catch (Exception e) {
			writer.println("Failed to create: \"" + arguments[3] + "\"");
			writer.flush();
		}
	}
}
