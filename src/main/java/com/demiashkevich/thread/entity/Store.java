package com.demiashkevich.thread.entity;

import com.demiashkevich.thread.action.StoreAction;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Store {

    public static final int COUNT_SHIP = 5;
    public static final int MAX_CAPACITY = 100;

    private Semaphore semaphore = new Semaphore(COUNT_SHIP, true);
    private final boolean[] pier = new boolean[COUNT_SHIP];
    private static AtomicBoolean instance = new AtomicBoolean(false);

    private int capacity;
    private static Lock lock = new ReentrantLock();
    private static Store store;
    private static final StoreAction action =  new StoreAction();

    private Store() {
        capacity = 0;
        System.out.println("Store capacity " + capacity);
    }

    public static Store getStore(){
        if(!instance.get()) {
            try {
                lock.lock();
                if (store == null) {
                    instance.getAndSet(true);
                    store = new Store();
                }
            }finally {
                lock.unlock();
            }
        }
        return store;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }

    public boolean[] getPier() {
        return pier;
    }

    public static StoreAction getAction() {
        return action;
    }
}
