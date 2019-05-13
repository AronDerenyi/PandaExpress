package hu.bme.iit.beta.pandaexpress.controller.view.animalview.pandaview;

import hu.bme.iit.beta.pandaexpress.model.animal.Animal;

import java.awt.*;

public class TiredPandaView extends PandaView {
    public TiredPandaView(Animal a) {
        super(a);
    }

    @Override
    public void onDraw(Graphics2D graphics, int screenWidth, int screenHeight) {
        super.onDraw(graphics, screenWidth, screenHeight);
        int tileX = ref.getTile().getPositionX();
        int tileY = ref.getTile().getPositionY();
        int offset = 16;
        drawCircle(graphics, tileX - offset, tileY - offset, 16, Color.RED, "F");
    }
}
