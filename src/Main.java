import domain.Game;
import domain.Player;
import domain.RacingGame;

public class Main {

    public static void main(String[] args) {
        Player player = new Player();

        String[] carNames = player.enterCarNames();
        int moveCount = player.enterMoveCount();

        Game game = new RacingGame(carNames, moveCount);
        game.start();
    }

}