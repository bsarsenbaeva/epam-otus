package pages;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.io.ByteArrayInputStream;

public class MainPage extends AbstractPage {

    @FindBy(css = "a.nav-link[href='/events']")
    public WebElement buttonEvents;
    @FindBy(css = "a.nav-link[href='/video?f%5B0%5D%5Bmedia%5D%5B%5D=Video']")
    public WebElement buttonVideo;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открыта страница {baseUrl}")
    public void open(String baseUrl) {
        driver.get(baseUrl);
        Allure.addAttachment("Главная страница Events",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    @Step("Открыта страница Events")
    public void openEvents() {
        buttonEvents.click();
        Allure.addAttachment("Вкладка Events",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    @Step("Открыта страница Video")
    public void openVideo() {
        buttonVideo.click();
        Allure.addAttachment("Вкладка Video",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }
}
