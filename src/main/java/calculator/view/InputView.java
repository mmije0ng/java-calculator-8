package calculator.view;

import camp.nextstep.edu.missionutils.Console;

public final class InputView {
    private static final String INPUT_PROMPT = "덧셈할 문자열을 입력해 주세요.";

    private InputView() {
    }

    /**
     * 사용자로부터 문자열을 입력받는다.
     * Console.readLine()을 사용하여 표준 입력을 처리한다.
     *
     * @return 사용자 입력 문자열
     */
    public static String readInput() {
        System.out.println(INPUT_PROMPT);
        return Console.readLine();
    }
}
