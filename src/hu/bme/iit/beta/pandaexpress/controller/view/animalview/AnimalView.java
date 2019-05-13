package hu.bme.iit.beta.pandaexpress.controller.view.animalview;

import hu.bme.iit.beta.pandaexpress.controller.view.View;
import hu.bme.iit.beta.pandaexpress.model.animal.Animal;

import java.awt.*;

/**
 * Animal view class
 * Responsible for showing a circle that represents the animal.
 * Base class for the orangutan and the various panda view classes.
 */
public abstract class AnimalView extends View {
    /**
     * Reference to the animal that the view is responsible for showing
     */
    protected Animal ref;
    /**
     * Radius of the circle that the represents the animal on the canvas
     */
    protected int r = 16;
    /**
     * The length that the animal's circle is shifted from the center of the tile the animal is on
     */
    protected int offset = 32;

    /**
     * Constructor
     * @param a The animal that the view is referencing
     */
    public AnimalView(Animal a) {
        ref = a;
    }

    /**
     * The method that is called upon refreshing the view
     * Calls the onDrawConnection method
     * @param graphics The graphical context
     * @param screenWidth The width of the graphical context
     * @param screenHeight The height of the graphical context
     */
    @Override
    public void onDraw(Graphics2D graphics, int screenWidth, int screenHeight) {
        if(ref.isDead()) return;
        onDrawConnection(graphics, screenWidth, screenHeight);
    }

    /**
     * The method that draws the connection between the animal and the other animal that it is following (if there is any)
     * @param graphics The graphical context
     * @param screenWidth The width of the graphical context
     * @param screenHeight The height of the graphical context
     */
    private void onDrawConnection(Graphics2D graphics, int screenWidth, int screenHeight) {
        Animal following;
        if ((following = ref.getFollowing()) != null) {
            int tileX = ref.getTile().getPositionX();
            int tileY = ref.getTile().getPositionY();
            int followingTileX = following.getTile().getPositionX();
            int followingTileY = following.getTile().getPositionY();
            drawConnection(graphics, tileX - offset, tileY - offset, r, followingTileX - offset, followingTileY - offset, r);
        }
    }
}
