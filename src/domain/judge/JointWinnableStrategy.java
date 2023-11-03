package domain.judge;

import domain.car.Car;
import domain.car.JointWinner;
import domain.car.Winner;

import java.util.ArrayList;
import java.util.List;

public class JointWinnableStrategy implements WinnableStrategy {

    @Override
    public Winner decideWinner(Car[] cars, int maxScore) {
        List<Car> winners = new ArrayList<>(cars.length);

        for (Car car : cars) {
            if (maxScore == car.distanceDriven()) {
                winners.add(car);
            }
        }
        return new JointWinner(winners);
    }
}