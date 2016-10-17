package com.demiashkevich.thread.state;

import com.demiashkevich.thread.action.StoreAction;
import com.demiashkevich.thread.entity.Store;

public class LoadUnloadStateShip implements IStateShip {

    @Override
    public void work(long shipId, Store store, int countLoad, int countUnload) {
        StoreAction action = Store.getAction();
        action.loadContainer(store, countLoad, shipId);
        action.unloadContainer(store, countUnload, shipId);
    }
}
