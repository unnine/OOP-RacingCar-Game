package domain.judge;

import domain.car.Car;
import domain.car.Winner;

public class GeneralJudge implements Judge {

    private final WinnableStrategy winnableStrategy;

    public GeneralJudge(WinnableStrategy winnableStrategy) {
        this.winnableStrategy = winnableStrategy;
    }

    @Override
    public Winner whoIsWinner(Car[] cars) {
        int maxScore = getMaxScore(cars);
        return winnableStrategy.decideWinner(cars, maxScore);
    }

    private int getMaxScore(Car[] cars) {
        int max = 0;
        for (Car car : cars) {
            max = Math.max(max, car.distanceDriven());
        }
        return max;
    }
}