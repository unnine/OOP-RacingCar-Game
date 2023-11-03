import domain.game.Game;
import domain.game.RacingGame;
import domain.judge.GeneralJudge;
import domain.judge.JointWinnableStrategy;
import domain.racing.RacingByRandomRule;
import domain.racing.GeneralRacing;
import domain.player.GeneralPlayer;

public class Main {

    public static void main(String[] args) {
        Game game = new RacingGame(
                new GeneralPlayer(),
                new GeneralRacing(new RacingByRandomRule()),
                new GeneralJudge(new JointWinnableStrategy())
        );
        game.play();
    }
}