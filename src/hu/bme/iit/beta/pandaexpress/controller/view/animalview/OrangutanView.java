package hu.bme.iit.beta.pandaexpress.controller.view.animalview;

import hu.bme.iit.beta.pandaexpress.controller.Controller;
import hu.bme.iit.beta.pandaexpress.model.animal.Animal;
import hu.bme.iit.beta.pandaexpress.model.animal.Orangutan;

import java.awt.*;

/**
 * Orangutan view class
 * Responsible for showing a circle that represents an orangutan.
 */
public class OrangutanView extends AnimalView {

    private static int count = 0;
    private final int index = count++;

    /**
     * Constructor
     * @param a The animal that the view is referencing
     */
    public OrangutanView(Animal a) {
        super(a);
    }

    /**
     * The method that is called upon refreshing the view
     * Draws a green circle representing the orangutan
     * @param graphics The graphical context
     * @param screenWidth The width of the graphical context
     * @param screenHeight The height of the graphical context
     */
    @Override
    public void onDraw(Graphics2D graphics, int screenWidth, int screenHeight) {
        if(ref.isDead()) return;
        super.onDraw(graphics, screenWidth, screenHeight);
        int tileX = ref.getTile().getPositionX();
        int tileY = ref.getTile().getPositionY();
        drawCircle(graphics, tileX - offset, tileY - offset, r,
                Controller.getInstance().getSelectedOrangutan() == ref ? Color.CYAN : Color.GREEN,
                Integer.toString(index)
        );
    }

    /**
     * The method that is called when the canvas is clicked
     * If the mouse hits the orangutan's circle when the user clicked, the orangutan will be selected
     * @param mouseX
     * @param mouseY
     * @return
     */
    @Override
    public boolean onClick(int mouseX, int mouseY) {
        if(ref.isDead()) return false;
        int tileX = ref.getTile().getPositionX();
        int tileY = ref.getTile().getPositionY();
        if(!isCircleClicked(mouseX, mouseY, tileX - offset, tileY - offset, r))
            return false;
        Controller.getInstance().setSelectedOrangutan((Orangutan) ref);
        return true;
    }
}
