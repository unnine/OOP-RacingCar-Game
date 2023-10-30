package domain;

public record RacingCar(String name) implements Car {

    @Override
    public void go() {
        System.out.println(name + " 전진합니다.");
    }
}