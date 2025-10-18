package calculator.validator;

/**
 * 숫자 형변환 및 유효성 검사를 담당하는 클래스.
 */
public class NumberValidator {

    /**
     * 문자열을 정수로 변환하고 유효성을 검사한다.
     *
     * @param token 변환할 문자열
     * @return 변환된 정수
     * @throws IllegalArgumentException 숫자가 아니거나 음수인 경우
     */
    public int parseAndValidate(String token) {
        try {
            int num = Integer.parseInt(token);
            if (num < 0) {
                throw new IllegalArgumentException("음수는 입력할 수 없습니다: " + num);
            }
            return num;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자가 아닌 값이 포함되어 있습니다: " + token);
        }
    }
}
