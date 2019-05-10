package hu.bme.iit.beta.pandaexpress.controller.view;

import java.awt.*;

public abstract class View {

	public void onDraw(Graphics2D graphics, int screenWidth, int screenHeight) {

	}

	public boolean onClick(int mouseX, int mouseY) {
		return false;
	}

	public final boolean isCircleClicked(int mouseX, int mouseY, int x, int y, int r) {
		float distX = mouseX - x;
		float distY = mouseY - y;
		float dist = (float) Math.sqrt(distX * distX + distY * distY);
		return dist < r;
	}

	public final void drawCircle(Graphics2D graphics, int x, int y, int r, Paint color, String text) {
		graphics.setPaint(color);
		graphics.fillOval(x - r, y - r, r * 2, r * 2);
		graphics.setPaint(Color.BLACK);
		graphics.drawOval(x - r, y - r, r * 2, r * 2);

		if (text != null && !text.isEmpty()) {
			graphics.setFont(new Font("Arial", Font.BOLD, 12));
			FontMetrics fontMetrics = graphics.getFontMetrics();
			int textWidth = fontMetrics.stringWidth(text);
			int textHeight = fontMetrics.getHeight();
			int textAscent = fontMetrics.getAscent();
			graphics.drawString(text, x - textWidth / 2, y - textHeight / 2 + textAscent);
		}
	}

	public final void drawCircle(Graphics2D graphics, int x, int y, int r, Paint color) {
		drawCircle(graphics, x, y, r, color, null);
	}

	public final void drawConnection(Graphics2D graphics, int x1, int y1, int r1, int x2, int y2, int r2) {
		float dirX = x2 - x1;
		float dirY = y2 - y1;
		float distance = (float) Math.sqrt(dirX * dirX + dirY * dirY);
		dirX /= distance;
		dirY /= distance;

		x1 += dirX * r1;
		y1 += dirY * r1;
		x2 -= dirX * r2;
		y2 -= dirY * r2;

		graphics.setPaint(Color.BLACK);
		graphics.drawLine(x1, y1, x2, y2);
	}
}
