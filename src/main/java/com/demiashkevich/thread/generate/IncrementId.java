package com.demiashkevich.thread.generate;

public class IncrementId {

    private static int shipId = 0;
    private static int carId = 0;

    public static int incrementShipId(){
        return shipId++;
    }

    public static int incrementCar(){
        return carId++;
    }
}
