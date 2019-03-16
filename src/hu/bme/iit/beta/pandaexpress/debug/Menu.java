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
        Panda panda = new Panda();
        Tile tileUnderPanda = new Tile();
        Tile tileWherePandaSteps = new Tile();

        // save objects in logger
        Logger.addAlias(panda, "Panda");
        Logger.addAlias(tileUnderPanda, "tileUnderPanda");
        Logger.addAlias(tileWherePandaSteps, "tileWherePandaSteps");

        //run
        // test data
        Tile t = new Tile();
        Logger.addAlias(t, "tile1");
        Logger.startFunction("func", 1, "param", true);
        Logger.startFunction("func2", t, null);
        Logger.endFunction();
        Logger.startFunction("func3");
        Logger.endFunction();
        Logger.endFunction("retVal");
    }
}
