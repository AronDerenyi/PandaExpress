package hu.bme.iit.beta.pandaexpress.controller.view.animalview.pandaview;

import hu.bme.iit.beta.pandaexpress.model.animal.Animal;

import java.awt.*;

public class BeepingPandaView extends PandaView {
    public BeepingPandaView(Animal a) {
        super(a);
    }

    @Override
    public void onDraw(Graphics2D graphics, int screenWidth, int screenHeight) {
        super.onDraw(graphics, screenWidth, screenHeight);
        int tileX = ref.getTile().getPositionX();
        int tileY = ref.getTile().getPositionY();
        drawCircle(graphics, tileX - offset, tileY - offset, r, Color.RED, "S");
    }
}
