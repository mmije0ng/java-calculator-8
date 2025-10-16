package calculator;

import calculator.util.DelimiterExtractor;
import calculator.util.StringSplitter;
import calculator.view.InputView;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        String input = InputView.readInput();

        try {
            // 1. 입력 문자열에서 구분자 추출
            List<String> delimiters = DelimiterExtractor.extractDelimiters(input);
            System.out.println("사용 중인 구분자: " + delimiters); // 디버깅용

            // 2. 구분자를 이용해 문자열 분리
            String[] tokens = StringSplitter.split(input, delimiters);

            // 3. 분리 결과 확인 (향후 Calculator로 전달 예정)
            System.out.println("분리된 입력값: " + String.join(", ", tokens)); // 디버깅용

            // TODO: 분리된 숫자들을 더하기, 숫자가 아닌 경우 예외처리

        } catch (IllegalArgumentException e) {
            // 4. 모든 예외는 [ERROR] 형식으로 출력 후 종료
            System.out.println("[ERROR] " + e.getMessage());
        }
    }
}
