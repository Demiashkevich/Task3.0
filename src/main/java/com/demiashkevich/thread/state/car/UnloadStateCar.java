package com.demiashkevich.thread.state.car;

import com.demiashkevich.thread.action.StoreAction;
import com.demiashkevich.thread.entity.Store;

public class UnloadStateCar implements IStateCar {
    @Override
    public void work(long carId, Store store, int capacity) {
        StoreAction action = Store.getAction();
        action.carUnload(carId, store, capacity);
    }
}
