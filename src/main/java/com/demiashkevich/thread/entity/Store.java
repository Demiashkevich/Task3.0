package com.demiashkevich.thread.entity;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.demiashkevich.thread.generate.RandomGenerate.generateCapacityStore;

public class Store {

    public static final int COUNT_SHIP = 5;
    public static final int MAX_CAPACITY = 100;

    private Semaphore semaphore = new Semaphore(COUNT_SHIP, true);
    private final boolean[] pier = new boolean[COUNT_SHIP];
    private static AtomicBoolean instance = new AtomicBoolean(false);

    private int capacity;
    private static Lock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();
    private static Store store;

    private Store() {
        capacity = generateCapacityStore();
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

    public void addContainer(int count, long numberShip) {
        try {
            lock.lock();
            while (capacity + count > MAX_CAPACITY)
            {
                condition.await();
            }
            capacity += count;
            System.out.println(numberShip + " ship " + count + " container was add in store.");
            condition.signalAll();

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
            while(capacity - count < 0){
                condition.await();
            }
            capacity -= count;
            System.out.println(numberShip + " ship " + count + " container was buy on store.");
            condition.signalAll();

        } catch (InterruptedException exception){
            exception.printStackTrace();
        }
        finally {
            lock.unlock();
        }
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
}
