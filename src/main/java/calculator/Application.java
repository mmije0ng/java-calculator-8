package calculator;

import calculator.util.DelimiterExtractor;
import calculator.view.InputView;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        String input = InputView.readInput();

        try {
            List<String> delimiters = DelimiterExtractor.extractDelimiters(input);
            System.out.println("사용 중인 구분자: " + delimiters); // 디버깅용

            // TODO: 구분자를 사용해 입력값을 숫자 단위로 나누고 검증
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }
}
