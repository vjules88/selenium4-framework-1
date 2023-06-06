package utils.screenshots;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import scripts.BaseSeleniumTest;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtility {


    public static void takeScreenshot(ITestResult iTestResult) {
        WebDriver driver = null;

        if (iTestResult.getInstance() instanceof BaseSeleniumTest) {
            driver = ((BaseSeleniumTest) iTestResult.getInstance()).getOriginalDriver();
        }

        if (driver instanceof TakesScreenshot) {
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            try {
                FileUtils.copyFile(screenshotFile, new File(generateFilename(iTestResult)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }

    private static String generateFilename(ITestResult iTestResult) {
        long endMillis = iTestResult.getStartMillis();
        Date endDate = new Date(endMillis);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS");

        String methodName = iTestResult.getMethod().getMethodName();
        String formattedDate = simpleDateFormat.format(endDate);

        return String.format("screenshots/%s/%s.png", methodName, formattedDate);
    }

}
