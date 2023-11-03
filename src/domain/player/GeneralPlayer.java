package domain.player;

import domain.car.Car;
import domain.car.RacingCar;
import util.Logger;

import java.util.Arrays;
import java.util.Scanner;

public class GeneralPlayer implements Player {

    private final Scanner scanner = new Scanner(System.in);

    private final int nameLengthLimit = 5;
    private final String nameSeparateCharacter = ",";

    @Override
    public Car[] generateCars() {
        String[] carNames = inputCarNames();
        return generateCarsFromNames(carNames);
    }

    private String[] inputCarNames() {
        printInputCarNameMessage();

        String concatenatedNameByComma = scanner.next();
        assertExistsCarName(concatenatedNameByComma);

        String[] names = concatenatedNameByComma.split(nameSeparateCharacter);
        assertAllValidLength(names);

        return names;
    }

    private Car[] generateCarsFromNames(String[] names) {
        return Arrays.stream(names).map(RacingCar::new).toArray(Car[]::new);
    }

    private void printInputCarNameMessage() {
        String message = String.format(
                "경주할 자동차 이름을 입력해주세요. 자동차 이름은 %d 글자를 넘길 수 없습니다. 자동차가 여러 개인 경우, '%s'로 구분하여 입력해주세요.",
                nameLengthLimit,
                nameSeparateCharacter);
        Logger.log(message);
    }

    private void assertExistsCarName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("자동차 이름이 없습니다.");
        }
    }

    private void assertAllValidLength(String[] names) {
        boolean existsOverLength = Arrays.stream(names).anyMatch(s -> s.length() > nameLengthLimit);
        if (existsOverLength) {
            throw new IllegalArgumentException(String.format("자동차 이름의 길이는 %d 자리까지만 가능합니다.", nameLengthLimit));
        }
    }

    @Override
    public int decidePlayTime() {
        Logger.log("경주 시간을 숫자로 입력해주세요.");
        return scanner.nextInt();
    }

}