package domain;

import java.util.Arrays;
import java.util.Scanner;

public class Player {

    private final Scanner scanner = new Scanner(System.in);

    public int enterMoveCount() {
        System.out.println("경주 시간을 숫자로 입력해주세요.");
        return scanner.nextInt();
    }

    public String[] enterCarNames() {
        System.out.println("경주할 자동차 이름을 입력해주세요. 자동차 이름은 5글자를 넘길 수 없습니다. 자동차가 여러 개인 경우, 콤마로 구분하여 입력해주세요.");

        String concatName = scanner.next();
        assertExistsCarName(concatName);
        String[] names = concatName.split(",");

        boolean existsOverLength = Arrays.stream(names).anyMatch(s -> s.length() > 5);
        if (existsOverLength) {
            throw new IllegalArgumentException("자동차 이름의 길이는 5자리까지만 가능합니다.");
        }
        return names;
    }

    private void assertExistsCarName(String name) {
        boolean empty = name == null || "".equals(name);
        if (empty) {
            throw new IllegalArgumentException("자동차 이름이 없습니다.");
        }
    }

}