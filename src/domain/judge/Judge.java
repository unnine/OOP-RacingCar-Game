package domain.judge;

import domain.car.Car;
import domain.car.Winner;

public interface Judge {

    Winner whoIsWinner(Car[] cars);

}