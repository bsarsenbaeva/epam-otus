package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public abstract class AbstractPage {
    protected WebDriver driver;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected WebElement waitForElement(WebElement element) {
        return new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOf(element));
    }

    protected Boolean waitForElementDisappear(WebElement element) {
        return new WebDriverWait(driver, 10)
                .until(ExpectedConditions.invisibilityOf(element));
    }

    protected List<WebElement> waitForElements(List<WebElement> elements) {
        return new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfAllElements(elements));
    }
}
