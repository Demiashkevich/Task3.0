package com.demiashkevich.thread.generate;

public class IncrementId {

    private static int shipId = 0;

    public static int incrementShipId(){
        return shipId++;
    }
}
