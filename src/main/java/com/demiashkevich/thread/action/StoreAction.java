package com.demiashkevich.thread.action;

import com.demiashkevich.thread.entity.Car;
import com.demiashkevich.thread.entity.Store;
import com.demiashkevich.thread.state.car.LoadStateCar;
import com.demiashkevich.thread.state.car.UnloadStateCar;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.demiashkevich.thread.generate.IncrementId.incrementCar;
import static com.demiashkevich.thread.generate.RandomGenerate.generateCapacityLoad;

public class StoreAction {

    private static Lock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();

    public void unloadContainer(Store store, int count, long number) {
        try {
            lock.lock();
            while (store.getCapacity() + count > Store.MAX_CAPACITY){
                Car car = new Car(incrementCar(), store, generateCapacityLoad(), new LoadStateCar());
                car.start();
                System.out.println(number + " ship wait unload");
                condition.await();
                System.out.println(number + " ship get up unload");
            }
            int capacity = store.getCapacity();
            store.setCapacity(capacity + count);
            System.out.println(number + " ship " + count + " container was unload. Store capacity = " + store.getCapacity());
            condition.signalAll();

        } catch (InterruptedException exception){
            exception.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }

    public void loadContainer(Store store, int count, long number){
        try {
            lock.lock();
            while(store.getCapacity() - count < 0){
                int extendCount = Math.abs(store.getCapacity() - count);
                Car car = new Car(incrementCar(), store, extendCount, new UnloadStateCar());
                car.start();
                System.out.println(number + " ship wait load");
                condition.await();
                System.out.println(number + " ship get up load");
            }
            int capacity = store.getCapacity();
            store.setCapacity(capacity - count);
            System.out.println(number + " ship " + count + " container was load. Store capacity = " + store.getCapacity());
            condition.signalAll();
        } catch (InterruptedException exception){
            exception.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }

    public void carLoad(long carId, Store store, int capacity){
        try {
            lock.lock();
            int currentCapacity = store.getCapacity();
            store.setCapacity(currentCapacity - capacity);
            System.out.println(carId + " car unload to store: capacity = " + capacity + ". Store capacity = " + store.getCapacity());
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }
    public void carUnload(long carId, Store store, int capacity){
        try {
            lock.lock();
            int currentCapacity = store.getCapacity();
            store.setCapacity(currentCapacity + capacity);
            System.out.println(carId + " car load to store: capacity = " + capacity + ". Store capacity = " + store.getCapacity());
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }
}
