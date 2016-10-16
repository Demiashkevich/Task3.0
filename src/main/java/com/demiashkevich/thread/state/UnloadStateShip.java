package com.demiashkevich.thread.state;

import com.demiashkevich.thread.entity.Store;

public class UnloadStateShip implements IStateShip {

    @Override
    public void work(long shipId, Store store, int countLoad, int countUnload) {
        store.addContainer(countUnload, shipId);
    }
}
