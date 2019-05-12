package hu.bme.iit.beta.pandaexpress.window;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public final class Window {

	private static Window instance;

	public static Window getInstance(){
		if(instance == null) instance = new Window();
		return instance;
	}

	private Frame frame;
	private Canvas canvas;

	private Screen screen;

	private Window() {
		frame = new Frame("Panda Express");
		canvas = new Canvas();

		frame.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent event) {
				try {
					frame.dispose();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		frame.setSize(800, 600);
		frame.setResizable(false);
		frame.add(canvas);
		frame.setVisible(true);
		try {
			Thread.sleep(400);
		} catch (InterruptedException e) { }
		canvas.requestFocus();
	}

	public Screen getScreen() {
		return screen;
	}

	public void setScreen(Screen screen) {
		if (this.screen != null) this.screen.detach();
		this.screen = screen;
		if (this.screen != null) this.screen.attach(canvas);
	}
}
