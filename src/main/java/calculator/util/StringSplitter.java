package calculator.util;

import java.util.List;
import java.util.regex.Pattern;
import java.util.Arrays;

/**
 * 입력 문자열을 커스텀 또는 기본 구분자 기준으로 분리하는 유틸리티 클래스.
 *
 * 역할:
 * 1. DelimiterExtractor에서 추출한 구분자 목록을 기반으로 입력 문자열을 분리한다.
 * 2. 커스텀 구분자 선언부("//...\\n")가 있을 경우 본문(숫자 부분)만 분리 대상으로 사용한다.
 * 3. 구분자 뒤 값이 없거나 연속된 구분자로 인해 빈 문자열이 생기면 예외를 발생시킨다.
 */
public final class StringSplitter {

    private StringSplitter() {
    }

    /**
     * 입력 문자열을 주어진 구분자(delimiters) 기준으로 분리한다.
     *
     * @param input 입력 문자열 (예: "1,2:3" 또는 "//;\\n1;2;3")
     * @param delimiters 구분자 목록 (기본 구분자 또는 커스텀 구분자)
     * @return 분리된 문자열 배열
     * @throws IllegalArgumentException 입력이 비었거나, 구분자 뒤에 값이 없는 경우
     */
    public static String[] split(String input, List<String> delimiters) {
        // (입력이 비어 있거나 null인 경우 예외 발생
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("입력이 비어 있습니다.");
        }

        // 커스텀 구분자 선언부("//" ~ "\n") 제거 후 본문(숫자 부분)만 추출
        if (input.startsWith("//")) {
            int newlineIndex = input.indexOf("\n");
            input = input.substring(newlineIndex + 1);
        }

        // 여러 구분자를 정규식 OR(|)로 결합
        String regex = delimiters.stream()
                .map(Pattern::quote)
                .reduce((a, b) -> a + "|" + b)
                .orElse(",");

        // 문자열을 구분자 기준으로 분리
        String[] tokens = input.split(regex, -1);

        // 구분자 뒤에 값이 없거나 연속된 구분자로 인해 빈 문자열("") 발생 시 예외 처리
        if (Arrays.stream(tokens).anyMatch(String::isEmpty)) {
            throw new IllegalArgumentException("구분자 뒤에 값이 없습니다. (예: 1,2, 또는 1::2)");
        }

        // 정상적으로 분리된 문자열 배열 반환
        return tokens;
    }
}
