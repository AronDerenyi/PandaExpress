package hu.bme.iit.beta.pandaexpress.controller.view.animalview;

import hu.bme.iit.beta.pandaexpress.controller.Controller;
import hu.bme.iit.beta.pandaexpress.model.animal.Animal;
import hu.bme.iit.beta.pandaexpress.model.animal.Orangutan;

import java.awt.*;

public class OrangutanView extends AnimalView {
    public OrangutanView(Animal a) {
        super(a);
    }

    @Override
    public void onDraw(Graphics2D graphics, int screenWidth, int screenHeight) {
        super.onDraw(graphics, screenWidth, screenHeight);
        int tileX = ref.getTile().getPositionX();
        int tileY = ref.getTile().getPositionY();
        drawCircle(graphics, tileX - offset, tileY - offset, r, Color.GREEN);
    }

    @Override
    public boolean onClick(int mouseX, int mouseY) {
        int tileX = ref.getTile().getPositionX();
        int tileY = ref.getTile().getPositionY();
        if(!isCircleClicked(mouseX, mouseY, tileX - offset, tileY - offset, r))
            return false;
        Controller.getInstance().setSelectedOrangutan((Orangutan) ref);
        return true;
    }
}
