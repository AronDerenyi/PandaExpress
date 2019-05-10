package hu.bme.iit.beta.pandaexpress.controller;

import hu.bme.iit.beta.pandaexpress.controller.view.View;
import hu.bme.iit.beta.pandaexpress.window.Screen;

import java.awt.*;

public class Controller extends Screen {

	private static Controller instance;

	public static Controller getInstance(){
		if(instance == null) instance = new Controller();
		return instance;
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
}
