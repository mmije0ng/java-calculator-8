package calculator.view;

/**
 * 계산 결과를 출력하는 뷰 클래스.
 */
public final class OutputView {
    private static final String RESULT_FORMAT = "결과 : %d";

    private OutputView() {
    }

    /**
     * 계산 결과를 출력한다.
     *
     * @param result 계산된 결과값
     */
    public static void printResult(int result) {
        System.out.println(String.format(RESULT_FORMAT, result));
    }
}
