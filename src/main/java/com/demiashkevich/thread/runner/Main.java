package com.demiashkevich.thread.runner;

import com.demiashkevich.thread.entity.Store;
import com.demiashkevich.thread.generate.GenerateShips;

import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Store store = Store.getStore();
        GenerateShips ships = new GenerateShips();
        ships.generateShips(store);

        TimeUnit.SECONDS.sleep(10);

        System.out.println(store.getCapacity());

    }


}
