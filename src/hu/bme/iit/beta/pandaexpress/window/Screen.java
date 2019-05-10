package hu.bme.iit.beta.pandaexpress.window;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;

public abstract class Screen {

	private Canvas canvas;
	private BufferStrategy bufferStrategy;
	private Graphics2D graphics;

	private final MouseListener mouseListener = new MouseAdapter() {
		@Override
		public void mousePressed(MouseEvent e) {
			onClick(e.getX(), e.getY());
		}
	};

	final void attach(Canvas attachedCanvas) {
		this.canvas = attachedCanvas;

		canvas.createBufferStrategy(2);
		bufferStrategy = canvas.getBufferStrategy();
		graphics = (Graphics2D) bufferStrategy.getDrawGraphics();
		graphics.setRenderingHints(new RenderingHints(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON)
		);

		canvas.addMouseListener(mouseListener);

		onAttach();
	}

	final void detach() {
		bufferStrategy.dispose();
		graphics.dispose();
		canvas.removeMouseListener(mouseListener);

		onDetach();
	}

	protected void onAttach() {

	}

	protected void onDetach() {

	}

	protected void onClick(int x, int y) {

	}

	protected final int getWidth() {
		return canvas.getWidth();
	}

	protected final int getHeight() {
		return canvas.getHeight();
	}

	protected final Graphics2D getGraphics() {
		return graphics;
	}

	protected final void flush() {
		bufferStrategy.show();
	}
}
