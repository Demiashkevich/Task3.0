package com.demiashkevich.action;

import com.demiashkevich.entity.Store;

public class StoreAction {

    private Store store;

    public StoreAction(Store store){
        this.store = store;
    }

    public void addContainer(int count){
        if(store.getCapacity() + count < store.getMAX_CAPACITY()){
            store.setCapacity(count + store.getCapacity());
            System.out.println(count + " container was add in store.");
        }
    }

    public void buyContainer(int count){
        if(store.getCapacity() - count > 0){
            store.setCapacity(store.getCapacity() - count);
            System.out.println(count + " container was buy on store.");
        }
    }

}
