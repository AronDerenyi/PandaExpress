package hu.bme.iit.beta.pandaexpress.controller.view.animalview;

import hu.bme.iit.beta.pandaexpress.controller.view.View;
import hu.bme.iit.beta.pandaexpress.model.animal.Animal;

import java.awt.*;

public abstract class AnimalView extends View {
    protected Animal ref;
    public AnimalView(Animal a) {
        ref = a;
    }

    @Override
    public void onDraw(Graphics2D graphics, int screenWidth, int screenHeight) {
        onDrawConnection(graphics, screenWidth, screenHeight);
    }
    private void onDrawConnection(Graphics2D graphics, int screenWidth, int screenHeight) {
        Animal following;
        if ((following = ref.getFollowing()) != null) {
            int tileX = ref.getTile().getPositionX();
            int tileY = ref.getTile().getPositionY();
            int followingTileX = following.getTile().getPositionX();
            int followingTileY = following.getTile().getPositionY();
            int offset = 16;
            drawConnection(graphics, tileX - offset, tileY - offset, 16, followingTileX - offset, followingTileY - offset, 16);
        }
    }
}
