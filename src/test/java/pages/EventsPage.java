package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.temporal.TemporalAdjusters.nextOrSame;
import static java.time.temporal.TemporalAdjusters.previousOrSame;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EventsPage extends AbstractPage {

    @FindBy(css = "a.active:nth-child(1)")
    public WebElement tabUpcomingEvents;
    @FindBy(css = "li.evnt-tab-item:nth-child(2) > a:nth-child(1)")
    public WebElement tabPastEvents;
    @FindBy(css = "a.active:nth-child(1) .evnt-tab-counter")
    public WebElement counterUpcomingEvents;
    @FindBy(css = "li.evnt-tab-item:nth-child(2) > a:nth-child(1) .evnt-tab-counter")
    public WebElement counterPastEvents;
    @FindBy(css = ".evnt-event-card")
    public List<WebElement> eventCards;
    @FindBy(css = ".evnt-cards-container:nth-child(1) .evnt-event-card .date")
    public WebElement dateOfTheEvent;
    @FindBy(css = ".evnt-cards-container:nth-child(1) .evnt-event-card .language span")
    public WebElement languageOfThisWeekEvent;
    @FindBy(css = ".evnt-cards-container:nth-child(1) .evnt-event-card .online span")
    public WebElement placeOfThisWeekEvent;
    @FindBy(css = ".evnt-cards-container:nth-child(1) .evnt-event-card .evnt-event-name span")
    public WebElement nameOfThisWeekEvent;
    @FindBy(css = ".evnt-cards-container:nth-child(1) .evnt-event-card .status")
    public WebElement statusOfThisWeekEvent;
    @FindBy(css = ".evnt-cards-container:nth-child(1) .evnt-event-card .evnt-speaker")
    public List<WebElement> speakersOfThisWeekEvent;
    @FindBy(css = ".evnt-filter-button[id=filter_location]")
    public WebElement buttonLocationFilter;
    @FindBy(css = ".evnt-filter-menu[aria-labelledby='filter_location'] .evnt-text-fields")
    public WebElement inputSearchLocationFilter;
    @FindBy(css = ".evnt-filter-menu[aria-labelledby='filter_location'] .evnt-checkbox:nth-child(2)")
    public WebElement checkBoxInSearchLocationFilter;
    @FindBy(css = ".evnt-tag:nth-child(1) label")
    public WebElement filterTag;
    @FindBy(css = ".evnt-cards-container .evnt-event-card")
    public WebElement firstEventCard;

    public EventsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открыта вкладка Upcoming Events")
    public void openUpcomingEventsTab() {
        tabUpcomingEvents.click();
    }

    @Step("Открыта вкладка Past Events")
    public void openPastEventsTab() {
        tabPastEvents.click();
    }

    private List<WebElement> getEventCards() {
        return waitForElements(eventCards);
    }

    @Step("Проверка количества карточек в Ближайших событиях")
    public void assertUpcomingEventsCounter() {
        int eventCardCount = getEventCards().size();
        String eventCardCountText = Integer.toString(eventCardCount);
        String upcomingEventsCounter = counterUpcomingEvents.getText();
        assertThat(eventCardCountText, equalTo(upcomingEventsCounter));
    }

    private List<WebElement> getEventSpeakers() {
        return waitForElements(speakersOfThisWeekEvent);
    }

    @Step("Проверка данных карточки в Ближайших событиях")
    public void assertUpcomingEventCardData() {
        String dateOfThisWeekEventText = dateOfTheEvent.getText();
        String languageOfThisWeekEventText = languageOfThisWeekEvent.getText();
        String nameOfThisWeekEventText = nameOfThisWeekEvent.getText();
        String placeOfThisWeekEventText = placeOfThisWeekEvent.getText();
        String statusOfThisWeekEventText = statusOfThisWeekEvent.getText();
        getEventSpeakers();

        assertThat(dateOfThisWeekEventText, equalTo("26 Nov 2020"));
        assertThat(languageOfThisWeekEventText, equalTo("Рус"));
        assertThat(nameOfThisWeekEventText, equalTo("Online HR Meetup"));
        assertThat(placeOfThisWeekEventText, equalTo("Online only"));
        assertThat(statusOfThisWeekEventText, equalTo("Registration opened"));
    }

    @Step("Проверка даты ближайшего мероприятия")
    public void assertUpcomingEventCardDate() {
        String dateOfThisWeekEventText = dateOfTheEvent.getText();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy", Locale.ENGLISH);
        LocalDate dateOfThisWeekEvent = LocalDate.parse(dateOfThisWeekEventText, formatter);
        LocalDate today = LocalDate.now();
        LocalDate mondayOfThisWeek = today.with(previousOrSame(MONDAY));
        LocalDate sundayOfThisWeek = today.with(nextOrSame(SUNDAY));
        boolean isAfterMondayOfThisWeek = dateOfThisWeekEvent.isAfter(mondayOfThisWeek);
        boolean isBeforeMondayOfThisWeek = dateOfThisWeekEvent.isBefore(sundayOfThisWeek);

        assertTrue(isAfterMondayOfThisWeek);
        assertTrue(isBeforeMondayOfThisWeek);
    }

    @Step("Проверка даты прошедшего мероприятия")
    public void assertPastEventCardDate() {
        String dateOfPastEventText = dateOfTheEvent.getText();
        String splittedDateOfPastEvent = dateOfPastEventText.substring(dateOfPastEventText.length() - 10);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy", Locale.ENGLISH);
        LocalDate dateOfThisWeekEvent = LocalDate.parse(splittedDateOfPastEvent, formatter);
        LocalDate today = LocalDate.now();
        boolean isBeforeToday = dateOfThisWeekEvent.isBefore(today);

        assertTrue(isBeforeToday);
    }

    @Step("Фильтровать карточки по локации")
    public void filterCardsByLocation(String locationName) {
        waitForElement(buttonLocationFilter).click();
        waitForElement(inputSearchLocationFilter).sendKeys(locationName);
        waitForElement(checkBoxInSearchLocationFilter).click();
        String filterTagName = waitForElement(filterTag).getText();
        assertThat(filterTagName, equalTo(locationName));
        waitForElement(buttonLocationFilter).click();
    }

    @Step("Проверка количества карточек в Прошедших событиях")
    public void assertPastEventsCounter() {
        int eventCardCount = getEventCards().size();
        String eventCardCountText = Integer.toString(eventCardCount);
        String pastEventsCounter = waitForElement(counterPastEvents).getText();
        assertThat(eventCardCountText, equalTo(pastEventsCounter));
    }

    @Step("Открыть первую карточку в списке")
    public void openFirstEventCard() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");
        waitForElement(firstEventCard).click();
    }

}
