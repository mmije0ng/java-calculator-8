package calculator.util;

import java.util.List;

/**
 * 입력 문자열에서 커스텀 구분자를 추출하는 유틸리티 클래스.
 * 커스텀 구분자가 없다면 기본 구분자 사용
 */
public final class DelimiterExtractor {

    private static final String CUSTOM_PREFIX = "//";
    private static final String CUSTOM_SUFFIX = "\n";
    private static final List<String> DEFAULT_DELIMITERS = List.of(",", ":");

    private DelimiterExtractor() {
    }

    /**
     * 입력 문자열에서 커스텀 구분자를 추출한다.
     *
     * @param input 사용자 입력 문자열
     * @return 커스텀 구분자 1개 또는 기본 구분자 리스트
     * @throws IllegalArgumentException 잘못된 커스텀 구분자 형식 또는 구분자 부재 시
     */
    public static List<String> extractDelimiters(String input) {
        // 입력이 비어 있거나 null인 경우 기본 구분자 사용
        if (input == null || input.isBlank()) {
            return DEFAULT_DELIMITERS;
        }

        // 잘못된 커스텀 구분자 선언 형식일 경우
        if (input.startsWith("/") && !input.startsWith(CUSTOM_PREFIX)) {
            throw new IllegalArgumentException("커스텀 구분자 선언은 '//'로 시작해야 합니다. (예: //;\\n1;2;3)");
        }

        // "//"로 시작하지 않는 경우 → 기본 구분자(, :) 사용
        if (!input.startsWith(CUSTOM_PREFIX)) {
            // ✔ 기본 구분자 존재 여부 확인
            boolean hasDefaultDelimiter = DEFAULT_DELIMITERS.stream()
                    .anyMatch(input::contains);

            if (!hasDefaultDelimiter) {
                throw new IllegalArgumentException("입력 문자열에 구분자가 존재하지 않아 분리할 수 없습니다. (예: 1,2:3)");
            }

            return DEFAULT_DELIMITERS;
        }

        // 커스텀 구분자 선언 후 줄바꿈(\n)이 없으면 형식 오류
        int newlineIndex = input.indexOf(CUSTOM_SUFFIX);
        if (newlineIndex == -1) {
            throw new IllegalArgumentException("커스텀 구분자 형식이 올바르지 않습니다. (예: //;\\n1;2;3)");
        }

        // "//"와 "\n" 사이의 문자열을 추출하여 커스텀 구분자로 지정
        String delimiter = input.substring(CUSTOM_PREFIX.length(), newlineIndex);

        // 커스텀 구분자가 비어 있는 경우 예외 처리
        if (delimiter.isEmpty()) {
            throw new IllegalArgumentException("커스텀 구분자가 비어 있습니다.");
        }

        // 커스텀 구분자가 본문에 실제 존재하지 않으면 예외
        String body = input.substring(newlineIndex + 1);
        if (!body.contains(delimiter)) {
            throw new IllegalArgumentException("입력 문자열에 커스텀 구분자가 존재하지 않아 분리할 수 없습니다. (예: //;\\n1;2;3)");
        }

        // 유효한 커스텀 구분자 반환
        return List.of(delimiter);
    }

    /**
     * 기본 구분자 목록을 반환한다.
     *
     * @return 기본 구분자 리스트 [",", ":"]
     */
    public static List<String> getDefaultDelimiters() {
        return DEFAULT_DELIMITERS;
    }
}
