package hu.bme.iit.beta.pandaexpress.controller.view.animalview.pandaview;

import hu.bme.iit.beta.pandaexpress.controller.view.animalview.AnimalView;
import hu.bme.iit.beta.pandaexpress.model.animal.Animal;

/**
 * Panda view class
 * Responsible for showing a circle that represents a panda.
 * Base class for the various panda view classes.
 */
public abstract class PandaView extends AnimalView {

    /**
     * Constructor
     * @param a The animal that the view is referencing
     */
    public PandaView(Animal a) {
        super(a);
    }
}
