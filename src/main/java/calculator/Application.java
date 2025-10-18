package calculator;

import calculator.service.CalculatorService;
import calculator.view.InputView;
import calculator.view.OutputView;

/**
 * 계산기 애플리케이션의 메인 클래스.
 * 사용자 입력을 받아 계산을 수행한다.
 */
public class Application {
    private final CalculatorService calculatorService;

    public Application() {
        this.calculatorService = new CalculatorService();
    }

    /**
     * 계산기 애플리케이션을 실행한다.
     */
    public void run() {
        String input = InputView.readInput();
        int result = calculatorService.calculate(input);
        OutputView.printResult(result);
    }

    public static void main(String[] args) {
        Application application = new Application();
        application.run();
    }
}