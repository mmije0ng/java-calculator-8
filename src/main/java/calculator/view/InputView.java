package calculator.view;

import camp.nextstep.edu.missionutils.Console;

public final class InputView {
    private static final String INPUT_PROMPT = "덧셈할 문자열을 입력해 주세요.";

    private InputView() {
    }

    /**
     * 사용자 입력을 한 줄로 받되, 문자열 내에 '\n'이 포함되어 있으면 실제 개행으로 변환한다.
     * 예: "//;\n1;2;3" → "//;" + 줄바꿈 + "1;2;3"
     */
    public static String readInput() {
        System.out.println(INPUT_PROMPT);
        String input = Console.readLine();

        // 문자열 안에 '\n'이 문자 그대로 포함된 경우 실제 줄바꿈으로 변환
        if (input.contains("\\n")) {
            input = input.replace("\\n", "\n");
        }

        return input;
    }
}
