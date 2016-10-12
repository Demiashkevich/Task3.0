package com.demiashkevich.entity;

public abstract class Ship extends Thread {

    private long shipId;
    private Store store;

    public Ship(long shipIdId, Store store) {
        this.shipId = shipIdId;
        this.store = store;
    }

    public long getShipId() {
        return shipId;
    }

    public Store getStore() {
        return store;
    }
}
