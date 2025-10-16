package calculator;

import calculator.util.Calculator;
import calculator.util.DelimiterExtractor;
import calculator.util.StringSplitter;
import calculator.view.InputView;
import java.util.List;

/**
 * 1. 사용자 입력을 받아 구분자 추출 및 계산 수행
 * 2. 잘못된 입력 시 IllegalArgumentException이 발생하여 프로그램 종료
 */
public class Application {
    public static void main(String[] args) {
        String input = InputView.readInput();

        // 입력 문자열에서 구분자 목록을 추출
        List<String> delimiters = DelimiterExtractor.extractDelimiters(input);

        // 입력 문자열을 추출된 구분자를 기준으로 분리
        String[] tokens = StringSplitter.split(input, delimiters);

        // 분리된 문자열 배열을 정수로 변환하고 합계를 계산
        int result = Calculator.calculate(tokens);
        System.out.println("결과 : " + result);
    }
}