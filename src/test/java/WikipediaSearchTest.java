import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import scripts.BaseSeleniumTest;

import java.io.IOException;
import java.util.Locale;

public class WikipediaSearchTest extends BaseSeleniumTest {


    @Test
    public void searchKeywordInWiki() throws InterruptedException, IOException {
        driver.get("https://www.wikipedia.org");

        WebElement searchBox = fluentWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("searchInput")));
        searchBox.sendKeys("juliusz verne");

        WebElement searchButton = fluentWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#search-form > fieldset > button")));
        searchButton.click();

        String pageSource = driver.getPageSource().toLowerCase(Locale.ROOT);

        if (pageSource.contains("verne")) {
            System.out.println("Wyniki wyszukiwania zawierają słowo kluczowe 'juliusz verne'.");
        } else {
            System.out.println("Wyniki wyszukiwania nie zawierają słowa kluczowego 'juliusz verne'.");
        }
    }


}