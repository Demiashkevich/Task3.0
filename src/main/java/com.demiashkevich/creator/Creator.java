package com.demiashkevich.creator;

import com.demiashkevich.entity.LoadingShip;
import com.demiashkevich.entity.Ship;
import com.demiashkevich.entity.Store;
import com.demiashkevich.entity.UnloadingShip;
import com.demiashkevich.generate.GenerateId;

public class Creator {

    public static Ship createShip(Class typeShip, int capacityShip, Store store){
        String name = typeShip.getSimpleName();
        switch(name){
            case "LoadingShip": return new LoadingShip(GenerateId.incrementShipId(), capacityShip, store);
            case "UnloadingShip": return new UnloadingShip(GenerateId.incrementShipId(), capacityShip, store);
            default: return null;
        }
    }

}
