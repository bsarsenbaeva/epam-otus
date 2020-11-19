package cases;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import pages.EventCardPage;
import pages.EventsPage;
import pages.MainPage;
import utils.BaseHooks;

@Execution(ExecutionMode.CONCURRENT)
public class EventsTest extends BaseHooks {

    private MainPage mainPage;
    private EventsPage eventsPage;
    private EventCardPage eventCardPage;

    @Test
    @Epic("События")
    @Feature("Ближайшие события")
    @Story("Просмотр предстоящих мероприятий")
    @Description("Количество событий равно ожидаемому значению")
    public void checkUpcomingEventsCounter() {
        mainPage = new MainPage(driver);
        mainPage.open(baseUrl);
        mainPage.openEvents();
        eventsPage = new EventsPage(driver);
        eventsPage.openUpcomingEventsTab();
        eventsPage.assertUpcomingEventsCounter();
    }

    @Test
    @Epic("События")
    @Feature("Ближайшие события")
    @Story("Просмотр данных карточек мероприятий")
    @Description("Данные в найденном событие корректные")
    public void checkUpcomingEventCardData() {
        mainPage = new MainPage(driver);
        mainPage.open(baseUrl);
        mainPage.openEvents();
        eventsPage = new EventsPage(driver);
        eventsPage.openUpcomingEventsTab();
        eventsPage.assertUpcomingEventCardData();
    }

    @Test
    @Epic("События")
    @Feature("Ближайшие события")
    @Story("Валидация дат предстоящих мероприятий")
    @Description("Дата в ближайшем событие корректная")
    public void checkUpcomingEventDate() {
        mainPage = new MainPage(driver);
        mainPage.open(baseUrl);
        mainPage.openEvents();
        eventsPage = new EventsPage(driver);
        eventsPage.openUpcomingEventsTab();
        eventsPage.assertUpcomingEventCardDate();
    }

    @Test
    @Epic("События")
    @Feature("Прошедшие события")
    @Story("Просмотр прошедших мероприятий в Канаде")
    @Description("Проверка данных прошедших событии в Канаде")
    public void checkPastEventsInCanada() {
        mainPage = new MainPage(driver);
        mainPage.open(baseUrl);
        mainPage.openEvents();
        eventsPage = new EventsPage(driver);
        eventsPage.openPastEventsTab();
        eventsPage.filterCardsByLocation("Canada");
        eventsPage.assertPastEventsCounter();
        eventsPage.assertPastEventCardDate();
    }

    @Test
    @Epic("События")
    @Feature("Карточка события")
    @Story("Просмотр детальной информации о мероприятии")
    @Description("Данные в карточке события корректные")
    public void checkEventCardData() {
        mainPage = new MainPage(driver);
        mainPage.open(baseUrl);
        mainPage.openEvents();
        eventsPage = new EventsPage(driver);
        eventsPage.openUpcomingEventsTab();
        eventsPage.openFirstEventCard();
        eventCardPage = new EventCardPage(driver);
        eventCardPage.assertEventCardData();
    }
}
