package com.demiashkevich.entity;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Store {

    private final static int COUNT_SHIP = 5;
    private Semaphore semaphore = new Semaphore(COUNT_SHIP, true);
    private final boolean[] PIER = new boolean[COUNT_SHIP];
    private final int MAX_CAPACITY;
    private int capacity;
    private static Lock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();
    private static Store store;

    private Store() {
        MAX_CAPACITY = 100;
        capacity = 20;
    }

    public static Store getStore(){
        if(store == null) {
            lock.lock();
                if (store == null) {
                    store = new Store();
                    lock.unlock();
                    return store;
                }
        }
        return store;
    }

    public void addContainer(int count, long numberShip) {
        try {
            lock.lock();
            if (capacity + count < MAX_CAPACITY) {
                capacity += count;
                System.out.println(numberShip + " ship " + count + " container was add in store.");
                condition.signalAll();
            } else {
                condition.await();
                this.addContainer(count, numberShip);
            }
        } catch (InterruptedException exception){
            exception.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }

    public void buyContainer(int count, long numberShip){
        try {
            lock.lock();
            if (capacity - count > 0) {
                capacity -= count;
                System.out.println(numberShip + " ship " + count + " container was buy on store.");
                condition.signalAll();
            } else {
                condition.await();
                this.buyContainer(count, numberShip);
            }
        } catch (InterruptedException exception){
            exception.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }

    public int getMAX_CAPACITY() {
        return MAX_CAPACITY;
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

    public boolean[] getPIER() {
        return PIER;
    }
}
