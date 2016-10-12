package com.demiashkevich.entity;

import java.util.concurrent.TimeUnit;

public class UnloadingShip extends Ship{

    private int capacityUnload;

    public UnloadingShip(long shipId, int capacityUnload, Store store){
        super(shipId, store);
        this.capacityUnload = capacityUnload;
        System.out.println("Was created " + shipId + " " + UnloadingShip.class.getSimpleName()
                + " - " + capacityUnload + " capacity.");
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
                this.getStore().addContainer(capacityUnload, getShipId());
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
