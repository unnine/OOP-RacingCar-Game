package domain.game;

import domain.car.Car;
import domain.car.Winner;
import domain.judge.Judge;
import util.Logger;

public class RacingGame implements Game {

    private final Car[] cars;
    private final int moveCount;

    private final RacingRule rule;
    private final Judge judge;

    public RacingGame(Car[] cars, int moveCount, RacingRule rule, Judge judge) {
        this.cars = cars;
        this.moveCount = moveCount;
        this.rule = rule;
        this.judge = judge;
    }

    @Override
    public void start() {
        race();
        Winner winner = judge.whoIsWinner(cars);
        Logger.log( "우승 자동차는 [" + winner + "] 입니다.");
    }

    private void race() {
        int count = moveCount;
        while (count > 0) {
            for (Car car : cars) {
                if (rule.shouldGo()) {
                    car.go();
                }
            }
            count--;
        }
    }

}