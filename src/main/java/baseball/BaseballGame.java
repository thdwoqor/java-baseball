package baseball;

import camp.nextstep.edu.missionutils.Console;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import static baseball.Validate.*;

public class BaseballGame extends NumberGame {
    @Override
    public void start() {
    }

    @Override
    public boolean play() {
        return false;
    }

    @Override
    public boolean end() {
        System.out.print("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");

        String number = Console.readLine();
        validateInput(number, 1, 1, 2);

        if (number.equals("1")) {
            return true;
        }
        return false;
    }

    protected void validateInput(String number, int count, int startInclusive, int endInclusive) {
        if (!validateSameNumber(number)) {
            throw new IllegalArgumentException("Duplicate number exists.");
        }
        if (!validateCountNumber(number, count)) {
            throw new IllegalArgumentException("The number must be 3 digits.");
        }
        if (!validateRangeNumber(number, startInclusive, endInclusive)) {
            throw new IllegalArgumentException("The number must be between 1 and 9.");
        }
    }

    public void result(Point point) {
        int ball = point.getBall();
        int strike = point.getStrike();
        if (ball == 0 && strike == 0) {
            System.out.print("낫싱");
        }
        if (ball > 0) {
            System.out.print(String.format("%d볼 ", ball));
        }
        if (strike > 0) {
            System.out.print(String.format("%d스트라이크 ", strike));
        }
        System.out.println();
    }

    public Point hint(String playerInput) {
        int strike;
        int ball;
        int temp;

        List<String> numberList = Arrays.asList(playerInput.split(""));
        List<String> randomNumberList = Arrays.asList(computer.split(""));

        temp = (int) randomNumberList.stream().filter(e -> numberList.stream().anyMatch(Predicate.isEqual(e))).count();
        strike = (int) IntStream.range(0, 3).filter(e -> numberList.get(e).equals(randomNumberList.get(e))).count();
        ball = temp - strike;
        Point point = new Point();
        point.setBall(ball).setStrike(strike);
        return point;
    }
}
