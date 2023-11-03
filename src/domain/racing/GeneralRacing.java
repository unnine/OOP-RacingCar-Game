package domain.racing;

import domain.car.Car;

public class GeneralRacing implements Racing {

    private final RacingRule rule;

    public GeneralRacing(RacingRule rule) {
        this.rule = rule;
    }

    @Override
    public void race(Car[] cars, int raceTime) {
        int count = raceTime;
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
