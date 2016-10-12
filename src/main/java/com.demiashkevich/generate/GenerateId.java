package com.demiashkevich.generate;

public class GenerateId {

    private static int shipId = 0;

    public static int incrementShipId(){
        return shipId++;
    }
}
