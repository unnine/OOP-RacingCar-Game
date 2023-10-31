package domain.car;

import util.Logger;

public class RacingCar implements Car {

    private final String name;
    private int distanceDriven = 0;

    public RacingCar(String name) {
        this.name = name;
    }

    @Override
    public void go() {
        distanceDriven++;
        Logger.log(name + " 전진합니다.");
    }

    @Override
    public int distanceDriven() {
        return distanceDriven;
    }

    @Override
    public String toString() {
        return name;
    }
}