package com.demiashkevich.thread.state;

import com.demiashkevich.thread.entity.Store;

public class LoadUnloadStateShip implements IStateShip {

    @Override
    public void work(long shipId, Store store, int countLoad, int countUnload) {
        store.buyContainer(countLoad, shipId);
        store.addContainer(countUnload, shipId);
    }
}