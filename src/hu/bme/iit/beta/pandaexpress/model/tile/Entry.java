package hu.bme.iit.beta.pandaexpress.model.tile;

import hu.bme.iit.beta.pandaexpress.debug.Logger;
import hu.bme.iit.beta.pandaexpress.model.Stage;

public class Entry extends Tile {
    public Entry(){
        Stage.getInstance().setEntry(this);
    }
}
