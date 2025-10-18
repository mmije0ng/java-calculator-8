package calculator.service;

import calculator.parser.DelimiterParser;
import calculator.parser.StringParser;
import calculator.validator.NumberValidator;
import java.util.Arrays;
import java.util.List;

/**
 * 계산기 서비스 클래스.
 * 문자열 파싱부터 계산까지의 전체 프로세스를 담당한다.
 */
public class CalculatorService {
    private final DelimiterParser delimiterParser;
    private final StringParser stringParser;
    private final NumberValidator numberValidator;

    public CalculatorService() {
        this.delimiterParser = new DelimiterParser();
        this.stringParser = new StringParser();
        this.numberValidator = new NumberValidator();
    }

    /**
     * 입력 문자열을 받아 계산 결과를 반환한다.
     *
     * @param input 사용자 입력 문자열
     * @return 계산 결과
     * @throws IllegalArgumentException 잘못된 입력 형식인 경우
     */
    public int calculate(String input) {
        // 1. 구분자 추출
        List<String> delimiters = delimiterParser.extractDelimiters(input);
        
        // 2. 문자열 분리
        String[] tokens = stringParser.split(input, delimiters);
        
        // 3. 계산 수행
        if (tokens == null || tokens.length == 0) {
            return 0;
        }

        return Arrays.stream(tokens)
                .mapToInt(numberValidator::parseAndValidate)
                .sum();
    }
}
