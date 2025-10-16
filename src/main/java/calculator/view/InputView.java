package calculator.view;

import java.util.Scanner;

public final class InputView {
    private static final String INPUT_PROMPT = "덧셈할 문자열을 입력해 주세요.";

    private InputView() {
    }

    public static String readInput() {
        System.out.println(INPUT_PROMPT);
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input;
    }
}
