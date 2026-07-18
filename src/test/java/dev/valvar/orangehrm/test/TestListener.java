package dev.valvar.orangehrm.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * Слушатель тестов.
 */
public class TestListener implements ITestListener {

    private static final Logger logger = LoggerFactory.getLogger(TestListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        logger.debug("=== СТАРТ ТЕСТА: " + getTestName(result) + " ===");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.debug("=== ТЕСТ УСПЕШНО ПРОЙДЕН: " + getTestName(result) + " ===");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.debug("=== ТЕСТ ПРОВАЛЕН: " + getTestName(result) + " ===");
    }

    /**
     * Получить "человеческое" наименование теста.
     *
     * @param result экземпляр {@link ITestResult}.
     * @return наименование.
     */
    private String getTestName(ITestResult result) {
        String description = result.getMethod().getDescription();

        if (description != null && !description.trim().isEmpty()) {
            return description;
        } else {
            return result.getMethod().getMethodName();
        }
    }

}
