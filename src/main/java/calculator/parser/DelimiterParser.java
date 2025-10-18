package calculator.parser;

import java.util.List;

/**
 * 입력 문자열에서 커스텀 구분자를 추출하는 파서 클래스.
 * 1. "//"와 "\n" 사이의 문자열을 커스텀 구분자로 인식한다.
 * 2. 커스텀 구분자가 없으면 기본 구분자(, :)를 사용한다.
 * 3. 형식이 잘못된 경우 IllegalArgumentException을 발생시킨다.
 */
public class DelimiterParser {

    private static final String CUSTOM_PREFIX = "//";
    private static final String CUSTOM_SUFFIX = "\n";
    private static final List<String> DEFAULT_DELIMITERS = List.of(",", ":");

    /**
     * 입력 문자열에서 커스텀 구분자를 추출한다.
     *
     * @param input 사용자 입력 문자열
     * @return 커스텀 구분자 1개 또는 기본 구분자 리스트
     * @throws IllegalArgumentException 잘못된 형식(형식 오류, 누락 등)의 입력일 경우
     */
    public List<String> extractDelimiters(String input) {
        // 1. 입력이 null 또는 공백("")인 경우
        if (input == null || input.isBlank()) {
            return DEFAULT_DELIMITERS;
        }

        // 2. "\\n" 문자열을 실제 줄바꿈 문자('\n')로 변환
        input = input.replace("\\n", "\n");

        // 3. 커스텀 구분자 선언 형식 검사
        if (input.startsWith("/") && !input.startsWith(CUSTOM_PREFIX)) {
            throw new IllegalArgumentException(
                    "커스텀 구분자 선언은 '//'로 시작해야 합니다. (예: //;\\n1;2;3)");
        }

        // 4. "//"로 시작하지 않으면 커스텀 구분자 선언이 없다고 판단
        if (!input.startsWith(CUSTOM_PREFIX)) {
            return DEFAULT_DELIMITERS;
        }

        // 5. 커스텀 구분자 선언 후 줄바꿈(\n) 존재 여부 검사
        int newlineIndex = input.indexOf(CUSTOM_SUFFIX);
        if (newlineIndex == -1) {
            throw new IllegalArgumentException(
                    "커스텀 구분자 형식이 올바르지 않습니다. (예: //;\\n1;2;3)");
        }

        // 6. //"와 "\n" 사이의 문자열을 추출하여 구분자로 사용
        String delimiter = input.substring(CUSTOM_PREFIX.length(), newlineIndex);

        // 7. 추출된 구분자가 비어 있는 경우 → 형식상 잘못된 입력
        if (delimiter.isEmpty()) {
            throw new IllegalArgumentException("커스텀 구분자가 비어 있습니다.");
        }

        // 8. 정상적인 구분자 반환
        return List.of(delimiter);
    }

    /**
     * 기본 구분자 목록을 반환
     *
     * @return 기본 구분자 리스트 [",", ":"]
     */
    public List<String> getDefaultDelimiters() {
        return DEFAULT_DELIMITERS;
    }
}
