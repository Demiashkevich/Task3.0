package com.demiashkevich.thread.state;

import com.demiashkevich.thread.entity.Store;

public interface IStateShip {
    public void work(long shipId, Store store, int countLoad, int countUnload);
}
