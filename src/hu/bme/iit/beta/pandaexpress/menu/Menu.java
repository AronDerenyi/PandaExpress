package hu.bme.iit.beta.pandaexpress.menu;

import hu.bme.iit.beta.pandaexpress.window.Screen;

public class Menu extends Screen {

	@Override
	public void onAttach() {
		getGraphics().drawRect(0, 0, getWidth() / 2, getHeight() / 2);
		flush();
	}

	@Override
	public void onDetach() {

	}

	@Override
	public void onClick(int x, int y) {
		getGraphics().drawRect(x - 2, y - 2, 4, 4);
		flush();
	}
}
