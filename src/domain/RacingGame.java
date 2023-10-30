package domain;

import util.RandomNumber;

import java.util.*;

public class RacingGame implements Game {

    private final int[][] scoreByCar;
    private final Car[] cars;
    private final int moveCount;

    public RacingGame(String[] carNames, int moveCount) {
        this.cars = createCarFromName(carNames);
        this.scoreByCar = new int[carNames.length][1];
        this.moveCount = moveCount;
    }

    private Car[] createCarFromName(String[] names) {
        return Arrays.stream(names)
                .map(RacingCar::new)
                .toArray(Car[]::new);
    }

    @Override
    public void start() {
        race();
        announceWinner();
    }

    private void race() {
        int count = moveCount;
        while (count > 0) {
            for (int i = 0; i < cars.length; i++) {
                if (isGo()) {
                    scoreByCar[i][0]++;
                    cars[i].go();
                }
            }
            count--;
        }
    }

    private void announceWinner() {
        int maxDistance = getMaxDistance();

        StringJoiner str = new StringJoiner(", ");
        Car car;
        int score;
        for (int i = 0; i < scoreByCar.length; i++) {
            car = cars[i];
            score = scoreByCar[i][0];

            if (maxDistance == score) {
                str.add(car.name());
            }
        }
        System.out.println("우승 자동차는 [" + str + "] 입니다!");
    }

    private boolean isGo() {
        return RandomNumber.betweenZeroAndNine() >= 4;
    }

    private int getMaxDistance() {
        int max = 0;
        for (int[] v : scoreByCar) {
            max = Math.max(max, v[0]);
        }
        return max;
    }

}