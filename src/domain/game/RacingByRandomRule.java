package domain.game;

import util.RandomNumber;

public class RacingByRandomRule implements RacingRule {

    @Override
    public boolean shouldGo() {
        return RandomNumber.betweenRange(0, 9) >= 4;
    }
}