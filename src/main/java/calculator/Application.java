package calculator;

import calculator.util.Calculator;
import calculator.util.DelimiterExtractor;
import calculator.util.StringSplitter;
import calculator.view.InputView;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        try {
            // (1) 사용자 입력 받기
            String input = InputView.readInput();

            // (2) 구분자 추출
            List<String> delimiters = DelimiterExtractor.extractDelimiters(input);

            // (3) 입력값을 구분자를 기준으로 분리
            String[] tokens = StringSplitter.split(input, delimiters);

            // (4) 정수 변환 및 합계 계산
            int result = Calculator.sum(tokens);

            // (5) 결과 출력
            System.out.printf("결과 : %d%n", result);

        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }
}
