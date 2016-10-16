package com.demiashkevich.thread.generate;

import com.demiashkevich.thread.entity.Ship;
import com.demiashkevich.thread.entity.Store;

import java.util.concurrent.TimeUnit;

import static com.demiashkevich.thread.generate.IncrementId.incrementShipId;
import static com.demiashkevich.thread.generate.RandomGenerate.generateCapacityLoad;
import static com.demiashkevich.thread.generate.RandomGenerate.generateCapacityUnload;
import static com.demiashkevich.thread.generate.RandomGenerate.generateState;

public class GenerateShips {

    private static final int SHIP_COUNT = 3;

    public void generateShips(Store store){
        for(int current = 0; current < SHIP_COUNT; current++){
            Ship ship = new Ship(incrementShipId(), generateCapacityLoad(), generateCapacityUnload(), store, generateState());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
            ship.start();
        }
    }

}
