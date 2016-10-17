package com.demiashkevich.thread.runner;

import com.demiashkevich.thread.entity.Store;
import com.demiashkevich.thread.generate.GenerateShips;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Store store = Store.getStore();
        GenerateShips ships = new GenerateShips();
        ships.generateShips(store);
    }


}
