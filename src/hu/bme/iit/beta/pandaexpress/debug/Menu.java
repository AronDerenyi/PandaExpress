package hu.bme.iit.beta.pandaexpress.debug;

import hu.bme.iit.beta.pandaexpress.model.Stage;
import hu.bme.iit.beta.pandaexpress.model.animal.Orangutan;
import hu.bme.iit.beta.pandaexpress.model.animal.panda.BeepingPanda;
import hu.bme.iit.beta.pandaexpress.model.animal.panda.Panda;
import hu.bme.iit.beta.pandaexpress.model.animal.panda.RingingPanda;
import hu.bme.iit.beta.pandaexpress.model.animal.panda.TiredPanda;
import hu.bme.iit.beta.pandaexpress.model.tile.*;
import hu.bme.iit.beta.pandaexpress.model.tile.machine.SlotMachine;
import hu.bme.iit.beta.pandaexpress.model.tile.machine.ChocolateMachine;

import java.io.IOException;
import java.util.Scanner;

public class Menu {
    /**
     * menü elemek kiírása és input bekérése
     */
    public void chooseMenuItems(){
        while(true){
            System.out.println("Choose a menu item from below: ");

            System.out.println("\t0:  Quit");
            System.out.println("\t1:  Panda steps");
            System.out.println("\t2:  Orangutan catches a panda");
            System.out.println("\t3:  Panda follows orangutan");
            System.out.println("\t4:  Panda follows orangutan who catches another panda");
            System.out.println("\t5:  Slot machine rings");
            System.out.println("\t6:  Chocolate machine beeps next to a panda");
            System.out.println("\t7:  Panda steps on weak tile");
            System.out.println("\t8:  Tired panda steps");
            System.out.println("\t9:  Tired panda sits on chair");
            System.out.println("\t10: Orangutan leaves");
            System.out.println("\t11: Orangutan escorts a panda out");
            System.out.println("\t12: Orangutan goes through a wardrobe");
            System.out.println("\t13: Orangutan walks panda through a wardrobe");
            System.out.println("\t14: Panda tries to step on a machine");
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
    //menüelem kiválasztása
    private void runChosenItem(int item){
        switch (item){
            case 1:
                pandaSteps();
                break;
            case 2:
                orangutanCatchesPanda();
                break;
            case 3:
                pandaFollowsOrangutan();
                break;
            case 4:
                pandaFollowsOrangutanCatching();
                break;
            case 5:
                slotMachineRings();
                break;
            case 6:
                chocolateMachineBeeps();
                break;
            case 7:
                pandaStepsOnWeakTile();
                break;
            case 8:
                tiredPandaSteps();
                break;
            case 9:
                tiredPandaSitsOnChair();
                break;
            case 10:
                orangutanLeaves();
                break;
            case 11:
                orangutanEscortsPandaOut();
                break;
            case 12:
                orangutanGoesThroughWardrobe();
                break;
            case 13:
                orangutanWalksPandaThroughWardrobe();
                break;
            case 14:
                pandaTriesToStepOnMachine();
                break;
            case 15:
                pandaTriesToStepOnOrangutan();
                break;
            default:
                System.out.println("The selected number isn't a menu item");
                return;
        }
    }

    //menüpont 1.
    private void pandaSteps(){
        System.out.println("Panda steps:");

        //init
	    Logger.disable();

        Tile tileUnderPanda = Logger.addAlias(new Tile(), "tileUnderPanda");
        Tile tileWherePandaSteps = Logger.addAlias(new Tile(), "tileWherePandaSteps");

	    Panda panda = new BeepingPanda();
        panda.move(tileUnderPanda);
	    Logger.addAlias(panda, "Panda");

	    Logger.enable();

	    tileUnderPanda.connectNeighbor(tileWherePandaSteps);

        //run
	    panda.move(tileWherePandaSteps);
    }

    //menüpont 2.
    //az orángután egy olyan csempére lép ahol egy panda van, ezért megfogja azt
    private void orangutanCatchesPanda(){
        //title
        System.out.println("Orangutan catches Panda:");

        //initialization
        Logger.disable();

        //tiles
        Tile tileUnderPanda = Logger.addAlias(new Tile(), "tileUnderPanda");
        Tile tileUnderOrangutan = Logger.addAlias(new Tile(), "tileUnderOrangutan");

        //animals
        Panda panda = new BeepingPanda();
        panda.move(tileUnderPanda);
        Logger.addAlias(panda, "Panda");

        Orangutan orangutan = new Orangutan();
        orangutan.move(tileUnderOrangutan);
        Logger.addAlias(orangutan, "Orangutan");

        Logger.enable();
        //connecting
        tileUnderPanda.connectNeighbor(tileUnderOrangutan);

        //run sequence
        orangutan.move(tileUnderPanda);
    }

    //meenüpont 3.
    private void pandaFollowsOrangutan(){
        System.out.println("Panda follows orangutan:");

        //init
        Logger.disable();

        Tile tileUnderOrangutan = Logger.addAlias(new Tile(), "tileUnderOrangutan");
        Tile tileUnderPanda = Logger.addAlias(new Tile(), "tileUnderPanda");
        Tile tileWhereOrangutanSteps = Logger.addAlias(new Tile(), "tileWhereOrangutanSteps");

        Panda panda = new BeepingPanda();
        panda.move(tileUnderPanda);
        Logger.addAlias(panda, "Panda");

        Orangutan orangutan = new Orangutan();
        orangutan.move(tileUnderOrangutan);
        Logger.addAlias(orangutan, "Orangutan");

        Logger.enable();
        tileUnderOrangutan.connectNeighbor(tileUnderPanda);
        tileUnderOrangutan.connectNeighbor(tileWhereOrangutanSteps);

        panda.follow(orangutan);

        //run
        orangutan.move(tileWhereOrangutanSteps);
    }

    //menüpont 4.
    //azt szimulálja, hogy egy olyan orángután kap el egy pandát, akit már követ egy másik
    private void pandaFollowsOrangutanCatching(){
        //title
        System.out.println("Panda follows orangutan who catches another panda:");

        //initialization
        Logger.disable();

        //tiles
        Tile tileUnderFollowingPanda = Logger.addAlias(new Tile(), "tileUnderFollowingPanda");
        Tile tileUnderNewPanda = Logger.addAlias(new Tile(), "tileUnderNewPanda");
        Tile tileUnderOrangutan = Logger.addAlias(new Tile(), "tileUnderOrangutan");

        //animals
        Panda followingPanda = new BeepingPanda();
        followingPanda.move(tileUnderFollowingPanda);
        Logger.addAlias(followingPanda, "followingPanda");

        Panda newPanda = new BeepingPanda();
        newPanda.move(tileUnderNewPanda);
        Logger.addAlias(tileUnderNewPanda, "newPanda");

        Orangutan orangutan = new Orangutan();
        orangutan.move(tileUnderOrangutan);
        Logger.addAlias(orangutan, "Orangutan");

        Logger.enable();

        //connections
        tileUnderFollowingPanda.connectNeighbor(tileUnderOrangutan);
        tileUnderOrangutan.connectNeighbor(tileUnderNewPanda);

        followingPanda.follow(orangutan);

        //run sequence
        orangutan.move(tileUnderNewPanda);

    }

    //menüpont 5.
    private void slotMachineRings(){
        System.out.println("Slot machine rings:");

        //init
        Logger.disable();

        Tile tileUnderPanda1 = Logger.addAlias(new Tile(), "tileUnderPanda1");
        Tile tileUnderPanda2 = Logger.addAlias(new Tile(), "tileUnderPanda2");

        int[] slotMachineConfig = {0};
        SlotMachine slotMachine = new SlotMachine();
        slotMachine.setWhenToMakeNoise(slotMachineConfig);
        Logger.addAlias(slotMachine, "slotMachine");

        Panda panda1 = new RingingPanda();
        panda1.move(tileUnderPanda1);
        Logger.addAlias(panda1, "RingingPanda");

        Panda panda2 = new BeepingPanda();
        panda2.move(tileUnderPanda2);
        Logger.addAlias(panda2, "FollowingPanda");

        Logger.enable();

        tileUnderPanda1.connectNeighbor(tileUnderPanda2);
        tileUnderPanda1.connectNeighbor(slotMachine);

        panda2.follow(panda1);

        //run
        slotMachine.step();
    }

    //menüpont 6.
    //a csokoládéautomata sípolását és a panda reakcióját teszteli: ettől ugrik egyet és az alatta levő gyenge csempe élettartama csökken
    private void chocolateMachineBeeps(){
        //title
        System.out.println("Chocolate machine beeps next to a panda:");

        //initialization
        Logger.disable();
        //chocolate machine
        int[] chocolateMachineConfig = {0};
        ChocolateMachine chocolateMachine = new ChocolateMachine();
        chocolateMachine.setWhenToMakeNoise(chocolateMachineConfig);
        Logger.addAlias(chocolateMachine, "chocolateMachine");
        //tile
        WeakTile tileUnderPanda = Logger.addAlias(new WeakTile(), "tileUnderPanda");
        //panda
        Panda panda = new BeepingPanda();
        panda.move(tileUnderPanda);
        Logger.addAlias(panda, "BeepingPanda");

        Logger.enable();
        //connecting
        tileUnderPanda.connectNeighbor(chocolateMachine);

        //run sequence
        chocolateMachine.step();
    }

    //menüpont 7.
    private void pandaStepsOnWeakTile(){
        System.out.println("Panda steps on weak tile:");

        //init
        Logger.disable();

        Tile tileUnderPanda = Logger.addAlias(new Tile(), "tileUnderPanda");
        WeakTile tileWherePandaSteps = Logger.addAlias(new WeakTile(), "weakTile");

        Panda panda = new BeepingPanda();
        panda.move(tileUnderPanda);
        Logger.addAlias(panda, "Panda");

        Logger.enable();

        tileUnderPanda.connectNeighbor(tileWherePandaSteps);
        System.out.println("How many lives should the weak tile have? (0-20)");
        Scanner reader = new Scanner(System.in);
        int lives = reader.nextInt();
        tileWherePandaSteps.setLives(lives);

        //run
        panda.move(tileWherePandaSteps);
    }

    //menüpont 8.
    //a fáradt panda egy lépését mutatja be
    private void tiredPandaSteps(){
        //title
        System.out.println("Tired panda steps:");

        //initialization
        Logger.disable();
        //tiles
        Tile tileUnderPanda = Logger.addAlias(new Tile(), "tileUnderPanda");
        Tile tileWherePandaSteps = Logger.addAlias(new Tile(), "tileWherePandaSteps");
        Tile tile3 = Logger.addAlias(new Tile(), "nearbyTile");
        //panda
        Panda panda = new TiredPanda();
        panda.move(tileUnderPanda);
        Logger.addAlias(panda, "tiredPanda");
        Logger.enable();
        //connections
        tileUnderPanda.connectNeighbor(tileWherePandaSteps);
        tileWherePandaSteps.connectNeighbor(tile3);

        //run sequence
        panda.move(tileWherePandaSteps);
    }

    //menüpont 9.
    private void tiredPandaSitsOnChair(){
        System.out.println("Tired panda sits on chair:");

        //init
        Logger.disable();

        Tile tileUnderPanda = Logger.addAlias(new Tile(), "tileUnderPanda");
        Tile tileWherePandaSteps = Logger.addAlias(new Tile(), "tileWherePandaSteps");
        Chair chair = Logger.addAlias(new Chair(), "chair");

        Panda panda = new TiredPanda();
        panda.move(tileUnderPanda);
        Logger.addAlias(panda, "tiredPanda");

        Logger.enable();

        tileUnderPanda.connectNeighbor(tileWherePandaSteps);
        tileWherePandaSteps.connectNeighbor(chair);

        //run
        panda.move(tileWherePandaSteps);
    }

    //menüpont 10.
    //az orángután kijáratra lépését mutatja be
    private void orangutanLeaves(){
        //title
        System.out.println("Orangutan leaves:");

        //initialization
        Logger.disable();
        //entry
        Entry entry = Logger.addAlias(new Entry(), "entry");
        //tiles
        Tile tileUnderOrangutan  = Logger.addAlias(new Tile(), "tileUnderOrangutan");
        Tile exit = Logger.addAlias(new Exit(), "exit");
        //orangutan
        Orangutan orangutan = new Orangutan();
        orangutan.move(tileUnderOrangutan);
        Logger.addAlias(orangutan, "orangutan");

        //connecting entry
        Stage.getInstance().setEntry(entry);

        Logger.enable();
        //connecting tiles
        tileUnderOrangutan.connectNeighbor(exit);

        //run sequence
        orangutan.move(exit);
        orangutan.step();
    }

    //menüpont 11.
    private void orangutanEscortsPandaOut(){
        System.out.println("Orangutan escorts panda out:");

        //init
        Logger.disable();

        Entry entry = Logger.addAlias(new Entry(), "entry");
        Tile tileUnderOrangutan = Logger.addAlias(new Tile(), "tileUnderOrangutan");
        Tile tileUnderPanda = Logger.addAlias(new Tile(), "tileUnderPanda");
        Tile exit = Logger.addAlias(new Exit(), "exit");

        Orangutan orangutan = new Orangutan();
        orangutan.move(tileUnderOrangutan);
        Logger.addAlias(orangutan, "orangutan");

        Panda panda = new BeepingPanda();
        panda.move(tileUnderPanda);
        Logger.addAlias(panda, "panda");

        Stage.getInstance().setEntry(entry);

        Logger.enable();

        tileUnderOrangutan.connectNeighbor(tileUnderPanda);
        tileUnderOrangutan.connectNeighbor(exit);

        panda.follow(orangutan);

        //run
        orangutan.move(exit);
        orangutan.step();
        panda.step();
    }

    //menüpont 12.
    //az orángután szekrénybe lépését szimulálja
    private void orangutanGoesThroughWardrobe(){
        //title
        System.out.println("Orangutan goes through a wardrobe:");

        //initialization
        Logger.disable();
        //entry tiles and wardrobes
        Tile tileUnderOrangutan = Logger.addAlias(new Tile(), "tileUnderOrangutan");
        Wardrobe wardrobeIn = Logger.addAlias(new Wardrobe(), "wardrobeIn");
        Tile wardrobeOutEntry = Logger.addAlias(new Tile(), "wardrobeOutEntry");
        Wardrobe wardrobeOut = Logger.addAlias(new Wardrobe(), "wardrobeOut");
        //orangutan
        Orangutan orangutan = new Orangutan();
        orangutan.move(tileUnderOrangutan);
        Logger.addAlias(orangutan, "orangutan");

        Logger.enable();
        //connecting
        wardrobeIn.setEntry(tileUnderOrangutan);
        wardrobeOut.setEntry(wardrobeOutEntry);
        wardrobeIn.connect(wardrobeOut);

        //run sequence
        orangutan.move(wardrobeIn);

    }

    //menüpont 13.
    private void orangutanWalksPandaThroughWardrobe(){
        System.out.println("Orangutan walks panda through wardrobe:");

        //init
        Logger.disable();

        Tile tileUnderOrangutan = Logger.addAlias(new Tile(), "tileUnderOrangutan");
        Tile tileUnderPanda = Logger.addAlias(new Tile(), "tileUnderPanda");
        Wardrobe wardrobe = Logger.addAlias(new Wardrobe(), "wardrobe");
        Tile otherWardrobeEntry = Logger.addAlias(new Tile(), "otherWardrobeEntry");
        Wardrobe otherWardrobe = Logger.addAlias(new Wardrobe(), "otherWardrobe");
        Tile afterWardrobeEntry = Logger.addAlias(new Tile(), "afterWardrobeEntry");


        Orangutan orangutan = new Orangutan();
        orangutan.move(tileUnderOrangutan);
        Logger.addAlias(orangutan, "orangutan");

        Panda panda = new BeepingPanda();
        panda.move(tileUnderPanda);
        Logger.addAlias(panda, "panda");

        Logger.enable();
        wardrobe.setEntry(tileUnderOrangutan);
        otherWardrobe.setEntry(otherWardrobeEntry);

        tileUnderOrangutan.connectNeighbor(tileUnderPanda);
        otherWardrobeEntry.connectNeighbor(afterWardrobeEntry);

        wardrobe.connect(otherWardrobe);

        panda.follow(orangutan);

        //run
        orangutan.move(wardrobe);
        orangutan.move(afterWardrobeEntry);
    }

    //menüpont 14.
    //a panda megpróbál gépre lépni sikertelenül
    private void pandaTriesToStepOnMachine(){
        //title
        System.out.println("Panda tries to step on a machine:");

        //initialization
        Logger.disable();
        //tile
        Tile tileUnderPanda = Logger.addAlias(new Tile(), "tileUnderPanda");
        //slotmachine
        int[] slotMachineConfig = {0};
        SlotMachine slotMachine = new SlotMachine();
        slotMachine.setWhenToMakeNoise(slotMachineConfig);
        Logger.addAlias(slotMachine, "slotMachine");
        //panda
        Panda panda = new BeepingPanda();
        panda.move(tileUnderPanda);
        Logger.addAlias(panda, "panda");

        Logger.enable();

        //connecting
        tileUnderPanda.connectNeighbor(slotMachine);

        //run sequence
        panda.move(slotMachine);
    }

    //menüpont 15.
    private void pandaTriesToStepOnOrangutan(){
        System.out.println("Panda tries to step on orangutan:");

        //init
        Logger.disable();

        Tile tileUnderOrangutan = Logger.addAlias(new Tile(), "tileUnderOrangutan");
        Tile tileUnderPanda = Logger.addAlias(new Tile(), "tileUnderPanda");

        Panda panda = new BeepingPanda();
        panda.move(tileUnderPanda);
        Logger.addAlias(panda, "Panda");

        Orangutan orangutan = new Orangutan();
        orangutan.move(tileUnderOrangutan);
        Logger.addAlias(orangutan, "Orangutan");

        Logger.enable();

        tileUnderOrangutan.connectNeighbor(tileUnderPanda);

        //run
        panda.move(tileUnderOrangutan);
    }

}
