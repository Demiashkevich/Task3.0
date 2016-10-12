package com.demiashkevich.runner;

import com.demiashkevich.creator.Creator;
import com.demiashkevich.entity.LoadingShip;
import com.demiashkevich.entity.Ship;
import com.demiashkevich.entity.Store;
import com.demiashkevich.entity.UnloadingShip;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args){
        final int SHIP_COUNT = 5;
        Random randomType = new Random();
        Random randomCapacity = new Random();
        Store store = Store.getStore();
        Class[] typeShip = {LoadingShip.class, UnloadingShip.class};
        for(int current = 0; current < SHIP_COUNT; current++){
            Ship ship = Creator.createShip(typeShip[randomType.nextInt(typeShip.length)], randomCapacity.nextInt(10) + 1, store);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
            ship.start();
        }
    }


}
