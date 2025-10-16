package calculator.util;

/**
 * 분리된 문자열 배열을 정수로 변환하고 합계를 계산하는 유틸리티 클래스.
 */
public final class Calculator {

    private Calculator() {
    }

    /**
     * 문자열 배열을 정수로 변환하고 합계를 반환한다.
     *
     * @param tokens 분리된 문자열 배열 (예: ["1", "2", "3"])
     * @return 정수 합계
     * @throws IllegalArgumentException 숫자가 아닌 값이 포함된 경우
     */
    public static int sum(String[] tokens) {
        if (tokens == null || tokens.length == 0) {
            return 0; // 빈 입력일 경우 0 반환
        }

        int sum = 0;

        for (String token : tokens) {
            try {
                sum += Integer.parseInt(token);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("숫자가 아닌 값이 포함되어 있습니다: " + token);
            }
        }

        return sum;
    }
}
