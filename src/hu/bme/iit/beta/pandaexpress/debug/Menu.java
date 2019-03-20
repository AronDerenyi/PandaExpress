package hu.bme.iit.beta.pandaexpress.debug;

import hu.bme.iit.beta.pandaexpress.model.Stage;
import hu.bme.iit.beta.pandaexpress.model.animal.Orangutan;
import hu.bme.iit.beta.pandaexpress.model.animal.panda.BeepingPanda;
import hu.bme.iit.beta.pandaexpress.model.animal.panda.Panda;
import hu.bme.iit.beta.pandaexpress.model.animal.panda.RingingPanda;
import hu.bme.iit.beta.pandaexpress.model.animal.panda.TiredPanda;
import hu.bme.iit.beta.pandaexpress.model.tile.*;
import hu.bme.iit.beta.pandaexpress.model.tile.machine.SlotMachine;

import java.io.IOException;
import java.util.Scanner;

public class Menu {

    public void chooseMenuItems(){
        while(true){
            System.out.println("Choose a menu item form below: ");
            System.out.println("\t0:  Quit");

            System.out.println("\t1:  Panda steps");
            System.out.println("\t2:  ");
            System.out.println("\t3:  Panda follows orangutan");
            System.out.println("\t4:  ");
            System.out.println("\t5:  Slot machine rings");
            System.out.println("\t6:  ");
            System.out.println("\t7:  Panda steps on weak tile");
            System.out.println("\t8:  ");
            System.out.println("\t9:  Tired panda sits on chair");
            System.out.println("\t10: ");
            System.out.println("\t11: Orangutan escorts a panda out");
            System.out.println("\t12: ");
            System.out.println("\t13: Orangutan walks panda through a wardrobe");
            System.out.println("\t14: ");
            System.out.println("\t15: Panda tries to step on orangutan");

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
            case 5:
                slotMachineRings();
                break;
            case 7:
                pandaStepsOnWeakTile();
                break;
            case 9:
                tiredPandaSitsOnChair();
                break;
            case 11:
                orangutanEscortsPandaOut();
                break;
            case 13:
                orangutanWalksPandaThroughWardrobe();
                break;
            case 15:
                pandaTriesToStepOnOrangutan();
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

    private void slotMachineRings(){
        System.out.println("Slot machine rings:");

        //init
        Logger.disable();

        Tile tileUnderPanda1 = Logger.addAlias(new Tile(), "tileUnderPanda1");
        Tile tileUnderPanda2 = Logger.addAlias(new Tile(), "tileUnderPanda2");

        int[] slotMachineConfig = {0};
        SlotMachine slotMachine = Logger.addAlias(new SlotMachine(slotMachineConfig), "slotMachine");

        Panda panda1 = Logger.addAlias(new RingingPanda(tileUnderPanda1), "RingingPanda");
        Panda panda2 = Logger.addAlias(new BeepingPanda(tileUnderPanda2), "FollowingPanda");

        Logger.enable();

        tileUnderPanda1.connectNeighbor(tileUnderPanda2);
        tileUnderPanda1.connectNeighbor(slotMachine);

        panda2.follow(panda1);

        //run
        slotMachine.step();
    }

    private void pandaStepsOnWeakTile(){
        System.out.println("Panda steps on weak tile:");

        //init
        Logger.disable();

        Tile tileUnderPanda = Logger.addAlias(new Tile(), "tileUnderPanda");
        WeakTile tileWherePandaSteps = Logger.addAlias(new WeakTile(), "weakTile");

        Panda panda = Logger.addAlias(new BeepingPanda(tileUnderPanda), "Panda");

        Logger.enable();

        tileUnderPanda.connectNeighbor(tileWherePandaSteps);
        System.out.println("How many lives should the weak tile have? (1-20)");
        Scanner reader = new Scanner(System.in);
        int lives = reader.nextInt();
        tileWherePandaSteps.setLives(lives);

        //run
        panda.move(tileWherePandaSteps);
    }

    private void tiredPandaSitsOnChair(){
        System.out.println("Tired panda sits on chair");

        //init
        Logger.disable();

        Tile tileUnderPanda = Logger.addAlias(new Tile(), "tileUnderPanda");
        Tile tileWherePandaSteps = Logger.addAlias(new Tile(), "tileWherePandaSteps");
        Chair chair = Logger.addAlias(new Chair(), "chair");

        Panda panda = Logger.addAlias(new TiredPanda(tileUnderPanda), "Panda");

        Logger.enable();

        tileUnderPanda.connectNeighbor(tileWherePandaSteps);
        tileWherePandaSteps.connectNeighbor(chair);

        //run
        panda.move(tileWherePandaSteps);
    }

    private void orangutanEscortsPandaOut(){
        System.out.println("Orangutan escorts panda out");

        //init
        Logger.disable();

        Entry entry = Logger.addAlias(new Entry(), "entry");
        Tile tileUnderOrangutan = Logger.addAlias(new Tile(), "tileUnderOrangutan");
        Tile tileUnderPanda = Logger.addAlias(new Tile(), "tileUnderPanda");
        Tile exit = Logger.addAlias(new Exit(), "exit");

        Orangutan orangutan = Logger.addAlias(new Orangutan(tileUnderOrangutan), "orangutan");
        Panda panda = Logger.addAlias(new BeepingPanda(tileUnderPanda), "panda");

        Stage.getInstance().setEntry(entry);

        Logger.enable();

        tileUnderOrangutan.connectNeighbor(tileUnderPanda);
        tileUnderOrangutan.connectNeighbor(exit);

        panda.follow(orangutan);

        //run
        orangutan.move(exit); // TODO: Exit.move is not implemented correctly
    }

    private void orangutanWalksPandaThroughWardrobe(){
        System.out.println("Orangutan walks panda through wWardrobe");

        //init
        Logger.disable();

        Tile tileUnderOrangutan = Logger.addAlias(new Tile(), "tileUnderOrangutan");
        Tile tileUnderPanda = Logger.addAlias(new Tile(), "tileUnderPanda");
        Wardrobe wardrobe = Logger.addAlias(new Wardrobe(tileUnderOrangutan), "wardrobe");
        Tile otherWardrobeEntry = Logger.addAlias(new Tile(), "otherWardrobeEntry");
        Wardrobe otherWardrobe = Logger.addAlias(new Wardrobe(otherWardrobeEntry), "otherWardrobe");
        Tile afterWardrobeEntry = Logger.addAlias(new Tile(), "afterWardrobeEntry");

        Logger.enable();

        Orangutan orangutan = Logger.addAlias(new Orangutan(tileUnderOrangutan), "orangutan");
        Panda panda = Logger.addAlias(new BeepingPanda(tileUnderPanda), "panda");

        tileUnderOrangutan.connectNeighbor(tileUnderPanda);
        otherWardrobeEntry.connectNeighbor(afterWardrobeEntry);

        wardrobe.connect(otherWardrobe);

        panda.follow(orangutan);

        //run
        orangutan.move(wardrobe);
        orangutan.move(afterWardrobeEntry);
    }

    private void pandaTriesToStepOnOrangutan(){
        System.out.println("Panda tries to step on orangutan:");

        //init
        Logger.disable();

        Tile tileUnderOrangutan = Logger.addAlias(new Tile(), "tileUnderOrangutan");
        Tile tileUnderPanda = Logger.addAlias(new Tile(), "tileUnderPanda");

        Panda panda = Logger.addAlias(new BeepingPanda(tileUnderPanda), "Panda");
        Orangutan orangutan = Logger.addAlias(new Orangutan(tileUnderOrangutan), "Orangutan");

        Logger.enable();

        tileUnderOrangutan.connectNeighbor(tileUnderPanda);

        //run
        panda.move(tileUnderOrangutan);
    }

}
