package hu.bme.iit.beta.pandaexpress.debug;

import hu.bme.iit.beta.pandaexpress.model.animal.Orangutan;
import hu.bme.iit.beta.pandaexpress.model.animal.panda.BeepingPanda;
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
            System.out.println("\t2: ");
            System.out.println("\t3: Panda follows orangutan");

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
            case 3:
                pandaFollowsOrangutan();
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
	    Panda panda = Logger.addAlias(new BeepingPanda(tileUnderPanda), "Panda");
	    Logger.enable();

	    tileUnderPanda.connectNeighbor(tileWherePandaSteps);

        //run
	    panda.move(tileWherePandaSteps);
    }

    private void pandaFollowsOrangutan(){
        System.out.println("Panda follows orangutan:");

        //init
        Logger.disable();
        Tile tileUnderOrangutan = Logger.addAlias(new Tile(), "tileUnderOrangutan");
        Tile tileUnderPanda = Logger.addAlias(new Tile(), "tileUnderPanda");
        Tile tileWhereOrangutanSteps = Logger.addAlias(new Tile(), "tileWhereOrangutanSteps");
        Panda panda = Logger.addAlias(new BeepingPanda(tileUnderPanda), "Panda");
        Orangutan orangutan = Logger.addAlias(new Orangutan(tileUnderOrangutan), "Orangutan");
        Logger.enable();

        tileUnderOrangutan.connectNeighbor(tileUnderPanda);
        tileUnderOrangutan.connectNeighbor(tileWhereOrangutanSteps);

        panda.follow(orangutan);

        //run
        orangutan.move(tileWhereOrangutanSteps);
    }
}
