package domain.judge;

import domain.car.Car;
import domain.car.Winner;

public interface WinnableStrategy {

    Winner determineWinner(Car[] cars, int maxScore);

}