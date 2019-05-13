package hu.bme.iit.beta.pandaexpress.controller.view.animalview.pandaview;

import hu.bme.iit.beta.pandaexpress.model.animal.Animal;

import java.awt.*;

/**
 * Tired panda view class
 * Responsible for showing a circle that represents a tired panda.
 */
public class TiredPandaView extends PandaView {
    /**
     * Constructor
     * @param a The animal that the view is referencing
     */
    public TiredPandaView(Animal a) {
        super(a);
    }

    /**
     * The method that is called upon refreshing the view
     * Draws a red circle with the text "F" in it
     * @param graphics The graphical context
     * @param screenWidth The width of the graphical context
     * @param screenHeight The height of the graphical context
     */
    @Override
    public void onDraw(Graphics2D graphics, int screenWidth, int screenHeight) {
        super.onDraw(graphics, screenWidth, screenHeight);
        int tileX = ref.getTile().getPositionX();
        int tileY = ref.getTile().getPositionY();
        drawCircle(graphics, tileX - offset, tileY - offset, r, Color.RED, "F");
    }
}
