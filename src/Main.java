import domain.car.Car;
import domain.car.RacingCar;
import domain.game.Game;
import domain.game.RacingGame;
import domain.judge.GeneralJudge;
import domain.judge.JointWinnableStrategy;
import domain.game.RacingByRandomRule;
import domain.user.Player;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Player player = new Player();
        String[] carNames = player.enterCarNames();
        Car[] cars = createCarFromName(carNames);
        int moveCount = player.enterMoveCount();

        Game game = new RacingGame(
                cars,
                moveCount,
                new RacingByRandomRule(),
                new GeneralJudge(new JointWinnableStrategy())
        );
        game.start();
    }

    private static Car[] createCarFromName(String[] names) {
        return Arrays.stream(names).map(RacingCar::new).toArray(Car[]::new);
    }

}