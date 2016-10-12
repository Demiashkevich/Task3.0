package com.demiashkevich.entity;

import java.util.concurrent.TimeUnit;

public class LoadingShip extends Ship {

    private int capacityLoad;

    public LoadingShip(long shipId, int capacity, Store store){
        super(shipId, store);
        this.capacityLoad = capacity;
        System.out.println("Was created " + shipId + " " + LoadingShip.class.getSimpleName()
                + " - " + capacity + " capacity.");
    }

    @Override
    public void run() {
        try {
            this.getStore().getSemaphore().acquire();
            int numberPier = 0;

                for(int i = 0; i < this.getStore().getPIER().length; i++){
                    if(!getStore().getPIER()[i]){
                        getStore().getPIER()[i] = true;
                        numberPier = i;
                        break;
                    }
                }
                this.getStore().buyContainer(capacityLoad, getShipId());
                TimeUnit.SECONDS.sleep(2);
                this.getStore().getPIER()[numberPier] = false;

        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        finally {
            this.getStore().getSemaphore().release();
        }
    }
}
