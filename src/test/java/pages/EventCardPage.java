package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class EventCardPage extends AbstractPage {

    @FindBy(css = ".evnt-content-text")
    public WebElement eventName;
    @FindBy(css = ".evnt-content-cell button")
    public WebElement buttonRegister;
    @FindBy(css = ".evnt-icon-point:nth-child(1) .evnt-icon-info h4")
    public WebElement textDateAndTimeOfEvent;
    @FindBy(css = ".evnt-card-table")
    public List<WebElement> tableEventPrograms;

    public EventCardPage(WebDriver driver) {
        super(driver);
    }

    @Step("Проверка данных карточки")
    public void assertEventCardData() {
        waitForElement(eventName);
        waitForElement(buttonRegister);
        getTableEventPrograms();
        String dateAndTime = textDateAndTimeOfEvent.getText();

        assertThat(dateAndTime, equalTo("26 ноября, с 18.30 (самарское время), онлайн"));
    }

    private List<WebElement> getTableEventPrograms() {
        return waitForElements(tableEventPrograms);
    }
}
