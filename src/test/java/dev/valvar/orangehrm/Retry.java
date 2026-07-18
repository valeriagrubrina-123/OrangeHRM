package dev.valvar.orangehrm;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * Повторитель упавших тестов.
 */
public class Retry implements IRetryAnalyzer {

    /**
     * Текущее количество повторных попыток.
     */
    private int count = 0;

    @Override
    public boolean retry(ITestResult result) {
        int testMaxRetry = Integer.parseInt(ConfigReader.getProperty("testMaxRetry"));

        if (!result.isSuccess() && count < testMaxRetry) {
            count++;

            return true;
        } else {
            return false;
        }
    }

}
