package com.demiashkevich.thread.entity;

import com.demiashkevich.thread.state.car.IStateCar;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Car extends Thread {

    private static Lock lock = new ReentrantLock();

    private long carId;
    private int capacity;
    private Store store;
    private IStateCar state;

    public Car(long carId, Store store, int capacity, IStateCar state) {
        this.carId = carId;
        this.store = store;
        this.capacity = capacity;
        this.state = state;
        System.out.println(carId + " car was created to " + state.getClass().getSimpleName() + ": capacity = " + capacity);
    }

    @Override
    public void run() {
        try {
            lock.lock();
            state.work(carId, store, capacity);
        }finally {
            lock.unlock();
        }
    }

}
