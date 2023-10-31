package domain.car;

import java.util.List;
import java.util.StringJoiner;

public class JointWinner implements Winner {

    private final List<Car> cars;

    public JointWinner(List<Car> cars) {
        this.cars = cars;
    }

    @Override
    public String toString() {
        StringJoiner s = new StringJoiner(", ");
        for (Car car : cars) {
            s.add(car.toString());
        }
        return s.toString();
    }
}