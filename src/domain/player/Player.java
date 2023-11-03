package domain.player;

import domain.car.Car;

public interface Player {

    Car[] generateCars();

    int decidePlayTime();

}
