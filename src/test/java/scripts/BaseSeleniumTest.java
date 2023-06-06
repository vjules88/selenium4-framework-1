package scripts;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.*;
import utils.listeners.ScreenshotListener;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

@Listeners(ScreenshotListener.class)
public class BaseSeleniumTest {

    protected WebDriver driver;
    protected FluentWait<WebDriver> fluentWait;

    public WebDriver getOriginalDriver(){
        return driver;
    }



    @BeforeClass
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser) throws MalformedURLException {
        MutableCapabilities capabilities;

        switch (browser){
            case "chrome":
                capabilities = new ChromeOptions();
                break;
            case "firefox":
                capabilities = new FirefoxOptions();
                break;
            case "edge":
                capabilities = new EdgeOptions();
                break;
            default:
                throw new RuntimeException("Wrong browser parameter. Try chrome, firefox, edge.");
        }

        driver = new RemoteWebDriver(new URL("http://localhost:4444"), capabilities);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

        fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
