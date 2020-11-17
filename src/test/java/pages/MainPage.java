package pages;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.io.ByteArrayInputStream;

import static java.util.concurrent.TimeUnit.SECONDS;

public class MainPage extends AbstractPage {

    @FindBy(css = "a.nav-link[href='/events']")
    public WebElement buttonEvents;
    @FindBy(css = "a.nav-link[href='/video?f%5B0%5D%5Bmedia%5D%5B%5D=Video']")
    public WebElement buttonVideo;
    @FindBy(css = ".evnt-filtered-link[href='/all-events']")
    public WebElement buttonUpcomingEvents;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открыта страница {baseUrl}")
    public void open(String baseUrl) {
        driver.get(baseUrl);
        Allure.addAttachment("Главная страница GitHub",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    @Step("Открыта страница Events")
    public void openEvents() {
        buttonEvents.click();
    }

    @Step("Открыта страница Video")
    public void openVideo() {
        buttonVideo.click();
    }
}
