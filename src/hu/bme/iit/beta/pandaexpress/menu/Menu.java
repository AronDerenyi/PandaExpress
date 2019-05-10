package hu.bme.iit.beta.pandaexpress.menu;

import hu.bme.iit.beta.pandaexpress.window.Screen;
import hu.bme.iit.beta.pandaexpress.window.Window;

import java.awt.*;

public class Menu extends Screen {

	private static final int BUTTON_CORNER_RADIUS = 24;
	private static final int BUTTON_WIDTH = 256;
	private static final int BUTTON_HEIGHT = 64;
	private static final Paint BUTTON_BACKGROUND_PAINT = new Color(80, 160, 0);
	private static final Paint BUTTON_TEXT_PAINT = Color.WHITE;
	private static final Font BUTTON_TEXT_FONT = new Font("Arial", Font.BOLD, 32);

	@Override
	public void onAttach() {
		drawButton("PLAY", buttonX(), playButtonY());
		drawButton("EXIT", buttonX(), exitButtonY());
		flush();
	}

	@Override
	public void onDetach() {

	}

	@Override
	public void onClick(int x, int y) {
		if (isButtonClicked(x, y, buttonX(), playButtonY())) {
			getGraphics().setPaint(Color.BLUE);
			getGraphics().fillRect(0, 0, getWidth(), getHeight());
			flush();
//			Window.getInstance();
		}
		if (isButtonClicked(x, y, buttonX(), exitButtonY())) {
			System.exit(0);
		}
	}

	private int buttonX() {
		return getWidth() / 2;
	}

	private int playButtonY() {
		return getHeight() / 2 - 64;
	}

	private int exitButtonY() {
		return getHeight() / 2 + 64;
	}

	private void drawButton(String text, int x, int y) {
		Graphics2D g = getGraphics();

		FontMetrics fontMetrics = g.getFontMetrics(BUTTON_TEXT_FONT);
		int textWidth = fontMetrics.stringWidth(text);
		int textHeight = fontMetrics.getHeight();
		int textAscent = fontMetrics.getAscent();

		g.setPaint(BUTTON_BACKGROUND_PAINT);
		g.fillRoundRect(
				x - BUTTON_WIDTH / 2, y - BUTTON_HEIGHT / 2,
				BUTTON_WIDTH, BUTTON_HEIGHT,
				BUTTON_CORNER_RADIUS, BUTTON_CORNER_RADIUS
		);

		g.setPaint(BUTTON_TEXT_PAINT);
		g.setFont(BUTTON_TEXT_FONT);
		g.drawString(text, x - textWidth / 2, y - textHeight / 2 + textAscent);
	}

	private boolean isButtonClicked(int mouseX, int mouseY, int x, int y) {
		return mouseX > x - BUTTON_WIDTH / 2 && mouseX < x + BUTTON_WIDTH / 2 &&
				mouseY > y - BUTTON_HEIGHT / 2 && mouseY < y + BUTTON_HEIGHT / 2;
	}
}
