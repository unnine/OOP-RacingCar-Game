package domain;

public class RacingCar implements Car{

    private final String name;

    public RacingCar(String name) {
        this.name = name;
    }

    @Override
    public void go() {
        System.out.println(name + " 전진합니다.");
    }

    @Override
    public String name() {
        return name;
    }
}