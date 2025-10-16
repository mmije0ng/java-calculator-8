package calculator.util;

import java.util.Arrays;

/**
 * 문자열 배열을 정수로 변환하고 합계를 계산하는 클래스.
 */
public final class Calculator {

    private Calculator() {}

    /**
     * 주어진 문자열 배열을 정수로 변환 후 합계를 계산한다.
     *
     * @param tokens 분리된 문자열 배열
     * @return 합계
     * @throws IllegalArgumentException 숫자가 아니거나 음수 값이 포함된 경우
     */
    public static int calculate(String[] tokens) {
        if (tokens == null || tokens.length == 0) {
            return 0;
        }

        return Arrays.stream(tokens)
                .mapToInt(token -> {
                    try {
                        int num = Integer.parseInt(token);
                        if (num < 0) {
                            throw new IllegalArgumentException("음수는 입력할 수 없습니다: " + num);
                        }
                        return num;
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("숫자가 아닌 값이 포함되어 있습니다: " + token);
                    }
                })
                .sum();
    }
}
