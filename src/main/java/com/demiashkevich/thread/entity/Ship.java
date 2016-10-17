package com.demiashkevich.thread.entity;

import com.demiashkevich.thread.state.IStateShip;

import java.util.concurrent.TimeUnit;

public class Ship extends Thread {

    private long shipId;
    private Store store;
    private int capacityLoad;
    private int capacityUnload;
    private IStateShip state;

    public Ship(long shipId, int capacityLoad, int capacityUnload, Store store, IStateShip state) {
        this.shipId = shipId;
        this.store = store;
        this.capacityLoad = capacityLoad;
        this.capacityUnload = capacityUnload;
        this.state = state;
        System.out.println(shipId + " ship was created to " + state.getClass().getSimpleName() + ": capacityLoad = " +
                capacityLoad + ", capacityUnload = " + capacityUnload);
    }

    @Override
    public void run() {
        try {
            store.getSemaphore().acquire();
            int numberPier = 0;

            for(int i = 0; i < this.getStore().getPier().length; i++){
                if(!getStore().getPier()[i]){
                    getStore().getPier()[i] = true;
                    numberPier = i;
                    break;
                }
            }
            TimeUnit.SECONDS.sleep(2);
            state.work(shipId, store, capacityLoad, capacityUnload);
            this.getStore().getPier()[numberPier] = false;

        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        finally {
            store.getSemaphore().release();
        }
    }

    public int getCapacityLoad() {
        return capacityLoad;
    }

    public int getCapacityUnload() {
        return capacityUnload;
    }

    public long getShipId() {
        return shipId;
    }

    public Store getStore() {
        return store;
    }
}
