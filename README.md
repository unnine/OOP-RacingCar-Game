# 객체지향적 자동차 경주 게임

## Usecase
- 주어진 횟수 동안 n대의 자동차는 전진 또는 멈출 수 있다.
- 각 자동차에 이름을 부여할 수 있다. 전진하는 자동차를 출력할 때 자동차 이름을 같이 출력한다.
- 자동차 이름은 쉼표(,)를 기준으로 구분하며 이름은 5자 이하만 가능하다.
- 사용자는 몇 번의 이동을 할 것인지를 입력할 수 있어야 한다.
- 전진하는 조건은 0에서 9 사이에서 random 값을 구한 후 random 값이 4 이상일 경우 전진하고, 3 이하의 값이면 멈춘다.
- 자동차 경주 게임을 완료한 후 가장 많이 전진한 자동차가 우승. 우승자는 한 명 이상일 수 있다.

<br/>

## Main Point
- SRP (객체가 하나의 책임을 갖도록)
- OCP (게임 규칙, 우승자 선정 방법이 변경되었을 때 기존 코드의 수정없이 확장만으로 해결할 수 있도록)
- ~~LSP (상속이 필요하지 않음)~~
- ISP (객체가 자신의 역할에 따른 행동만 갖을 수 있도록 분리)
- DIP (보다 추상적인 계층에 의존하도록)

<br/>

## Model
![image](https://github.com/unnine/OOP-RacingCar-Game/assets/134513528/205d6838-7b23-4214-af66-c0719c0a2c6c)

<br/>

## 확장 사례

### 사례 1. 경주 규칙 변경

#### 기존
> 0에서 9 사이에서 random 값을 구한 후 random 값이 4 이상일 경우 전진하고, 3 이하의 값이면 멈춘다

#### 변경
> 1에서 100 사이에서 random 값을 구한 후 random 값이 50이상일 경우 전진하고, 3이하의 값이면 멈춘다.

<br/>

#### 해결 방식 1. RacingByRandomRule 클래스 내 값을 변경

**변경 전**
```java
public class RacingByRandomRule implements RacingRule {

    @Override
    public boolean shouldGo() {
        return RandomNumber.betweenRange(0, 9) >= 4;
    }
}
```

**변경 후**
```java
public class RacingByRandomRule implements RacingRule {

    @Override
    public boolean shouldGo() {
        return RandomNumber.betweenRange(1, 100) >= 50;
    }
}
```

#### 해결 방식 2. 새로운 규칙 클래스를 추가
**1. 신규 규칙 클래스 생성**
```java
public class RacingByRandomBetweenOneAndHundredRule implements RacingRule {

    @Override
    public boolean shouldGo() {
        return RandomNumber.betweenRange(1, 100) >= 50;
    }
}
```


**2. Context 클래스에서 구현체 교체**

```java
public class Main {

    public static void main(String[] args) {
        ...

        Game game = new RacingGame(
                cars,
                moveCount,
//              new RacingByRandomRule(),
                new RacingByRandomBetweenOneAndHundredRule(),
                new GeneralJudge(new JointWinnableStrategy())
        );
        game.start();
    }
    
    ...
}
```

<br/>

### 사례 2. 우승자 선정 방식 변경

#### 기존
> 가장 많이 전진한 자동차가 우승. 우승자는 한 명 이상일 수 있다.

#### 변경
> 가장 많이 전진한 자동차가 우승. 우승자는 한 명 이상일 수 있다. 우승자가 여러 명이라면 맨 앞 번호의 자동차가 우승.

<br/>

### 해결 방식. 새로운 우승자 선정 전략과 우승자 구현체 생성
**1. 단독 우승자 클래스 생성**
```java
public class SoloWinner implements Winner {

    private final Car car;

    public JointWinner(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return car.toString();
    }
}
```

**2. 단독 우승자 선정 전략 클래스 생성**
```java
public class SoloWinnableStrategy {
    
    @Override
    public Winner determineWinner(Car[] cars, int maxScore) {
        Car winner = null;
        for (Car car : cars) {
            if (maxScore == car.distanceDriven()) {
                winner = car;
                break;
            }
        }
        return new SoloWinner(winner);
    }
}
```

**3. Context 클래스에서 구현체 교체**
```java
public class Main {

    public static void main(String[] args) {
        ...

        Game game = new RacingGame(
                cars,
                moveCount,
                new RacingByRandomRule(),
//              new GeneralJudge(new JointWinnableStrategy())
                new GeneralJudge(new SoloWinnableStrategy())
        );
        game.start();
    }
    
    ...
}
```

<br/>

### 결론
단순히 값 몇 개를 수정하는 작업이라면 기존 클래스를 수정하는 방법도 괜찮겠지만, 실무에서의 구현체들은 복잡한 알고리즘 및 다양한 도메인이 얽혀있다. 따라서 변경보다는 확장하는 방향으로 고려하는 것이 안전하다.
