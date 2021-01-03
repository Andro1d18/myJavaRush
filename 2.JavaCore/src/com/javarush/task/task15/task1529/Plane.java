package com.javarush.task.task15.task1529;

public class Plane implements CanFly {
    public int countPass;

    @Override
    public void fly() {

    }

    Plane(int countPass){
        this.countPass = countPass;
    }


}
