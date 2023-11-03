package domain.game;

import domain.car.Car;
import domain.car.Winner;
import domain.judge.Judge;
import domain.racing.Racing;
import domain.player.Player;
import util.Logger;

public class RacingGame implements Game {

    private final Player player;
    private final Racing racing;
    private final Judge judge;

    public RacingGame(Player player, Racing racing, Judge judge) {
        this.player = player;
        this.racing = racing;
        this.judge = judge;
    }

    @Override
    public void play() {
        Car[] cars = ready();
        race(cars);
        Winner winner = whoIsWinner(cars);
        Logger.log( "우승 자동차는 [" + winner + "] 입니다.");
    }

    private Car[] ready() {
        return player.generateCars();
    }

    private void race(Car[] cars) {
        racing.race(cars, decideRaceTime());
    }

    private int decideRaceTime() {
        return player.decidePlayTime();
    }

    private Winner whoIsWinner(Car[] cars) {
        return judge.whoIsWinner(cars);
    }

}