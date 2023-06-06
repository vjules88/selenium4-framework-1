package utils.listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.screenshots.ScreenshotUtility;

public class ScreenshotListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
        ScreenshotUtility.takeScreenshot(result);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);
        ScreenshotUtility.takeScreenshot(result);
    }
}
