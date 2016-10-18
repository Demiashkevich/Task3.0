package com.demiashkevich.thread.state.car;

import com.demiashkevich.thread.entity.Store;

public interface IStateCar {
    public void work(long carId, Store store, int capacity);

}
