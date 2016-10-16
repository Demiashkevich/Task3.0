package com.demiashkevich.thread.generate;

import com.demiashkevich.thread.state.IStateShip;
import com.demiashkevich.thread.state.LoadStateShip;
import com.demiashkevich.thread.state.LoadUnloadStateShip;
import com.demiashkevich.thread.state.UnloadStateShip;

import java.util.Random;

public class RandomGenerate {

    private static Random random = new Random();

    public static int generateCapacityLoad(){
        return random.nextInt(10) + 1;
    }

    public static int generateCapacityUnload(){
        return random.nextInt(10) + 1;
    }

    public static int generateCapacityStore(){
        return random.nextInt(20) + 10;
    }

    public static IStateShip generateState(){
        Class[] states = new Class[]{LoadStateShip.class, UnloadStateShip.class, LoadUnloadStateShip.class};
        int index = random.nextInt(states.length);
        IStateShip state = null;
        try {
            state = (IStateShip)states[index].newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return state;
    }

}
