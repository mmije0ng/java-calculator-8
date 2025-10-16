package calculator.util;

import java.util.List;
import java.util.regex.Pattern;

/**
 * 입력 문자열을 커스텀 또는 기본 구분자 기준으로 분리하는 유틸리티 클래스.
 */
public final class StringSplitter {

    private StringSplitter() {}

    /**
     * 입력 문자열을 구분자 기준으로 분리한다.
     * 구분자 뒤 값이 없거나 연속된 구분자가 존재하면 예외 발생.
     */
    public static String[] split(String input, List<String> delimiters) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("입력이 비어 있습니다.");
        }

        // 1. 커스텀 구분자 선언부 제거
        if (input.startsWith("//")) {
            int newlineIndex = input.indexOf("\n");
            input = input.substring(newlineIndex + 1);
        }

        // 2. 여러 구분자를 OR(|)로 결합
        String regex = delimiters.stream()
                .map(Pattern::quote)
                .reduce((a, b) -> a + "|" + b)
                .orElse(",");

        // 3. 마지막 구분자 뒤의 빈 문자열까지 포함하기 위해 limit=-1 설정
        String[] tokens = input.split(regex, -1);

        // 4. 구분자 뒤 값이 없거나 연속된 구분자 예외 처리
        for (String token : tokens) {
            if (token.isEmpty()) {
                throw new IllegalArgumentException("구분자 뒤에 값이 없습니다. (예: 1,2, 또는 1::2)");
            }
        }

        return tokens;
    }
}
