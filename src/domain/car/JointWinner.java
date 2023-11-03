package domain.car;

import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class JointWinner implements Winner {

    private final List<Car> cars;

    public JointWinner(List<Car> cars) {
        this.cars = cars;
    }

    @Override
    public String toString() {
        return cars.stream().map(Car::name).collect(Collectors.joining(","));
    }
}