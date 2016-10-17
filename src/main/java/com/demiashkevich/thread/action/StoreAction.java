package com.demiashkevich.thread.action;

import com.demiashkevich.thread.entity.Store;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class StoreAction {

    private static Lock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();

    public void unloadContainer(Store store, int count, long numberShip) {
        try {
            lock.lock();
            while (store.getCapacity() + count > Store.MAX_CAPACITY)
            {
                condition.await();
            }
            int capacity = store.getCapacity();
            store.setCapacity(capacity + count);
            System.out.println(numberShip + " ship " + count + " container was unload. Store capacity = " + store.getCapacity());
            condition.signalAll();

        } catch (InterruptedException exception){
            exception.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }

    public void loadContainer(Store store, int count, long numberShip){
        try {
            lock.lock();
            while(store.getCapacity() - count < 0){
                condition.await();
            }
            int capacity = store.getCapacity();
            store.setCapacity(capacity - count);
            System.out.println(numberShip + " ship " + count + " container was load. Store capacity = " + store.getCapacity());
            condition.signalAll();
        } catch (InterruptedException exception){
            exception.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }
}
