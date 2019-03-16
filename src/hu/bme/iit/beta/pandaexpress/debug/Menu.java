package hu.bme.iit.beta.pandaexpress.debug;

import hu.bme.iit.beta.pandaexpress.model.animal.panda.Panda;
import hu.bme.iit.beta.pandaexpress.model.tile.Tile;

import java.io.IOException;
import java.util.Scanner;

public class Menu {

    public void chooseMenuItems(){
        while(true){
            System.out.println("Choose a menu item form below: ");
            System.out.println("\t0: Quit");

            System.out.println("\t1: Panda steps");

            Scanner reader = new Scanner(System.in);
            int menuItem = reader.nextInt();
            if (menuItem == 0) return;
            runChosenItem(menuItem);
            System.out.print("Hit enter to continue... ");
            try { System.in.read();}
            catch (IOException e) {}
            System.out.println();
        }

    }

    private void runChosenItem(int item){
        switch (item){
            case 1:
                pandaSteps();
                break;
            default:
                System.out.println("The selected number isn't a menu item");
                return;
        }
    }

    private void pandaSteps(){
        System.out.println("Panda steps:");

        //init
	    Logger.disable();
        Tile tileUnderPanda = Logger.addAlias(new Tile(), "tileUnderPanda");
        Tile tileWherePandaSteps = Logger.addAlias(new Tile(), "tileWherePandaSteps");
	    Panda panda = Logger.addAlias(new Panda(tileUnderPanda), "Panda");
	    Logger.enable();

        //run
	    panda.move(tileWherePandaSteps);
    }
}
