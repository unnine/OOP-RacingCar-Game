package domain.judge;

import domain.car.Car;
import domain.car.JointWinner;
import domain.car.Winner;

import java.util.ArrayList;
import java.util.List;

public class JointWinnableStrategy implements WinnableStrategy {

    @Override
    public Winner determineWinner(Car[] cars) {
        List<Car> winners = new ArrayList<>(cars.length);
        int maxScore = getMaxScore(cars);

        for (Car car : cars) {
            if (maxScore == car.distanceDriven()) {
                winners.add(car);
            }
        }
        return new JointWinner(winners);
    }

    private int getMaxScore(Car[] cars) {
        int max = 0;
        for (Car car : cars) {
            max = Math.max(max, car.distanceDriven());
        }
        return max;
    }
}