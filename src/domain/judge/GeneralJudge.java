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
        return winnableStrategy.determineWinner(cars);
    }
}