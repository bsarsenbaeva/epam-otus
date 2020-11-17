package cases;

import org.junit.jupiter.api.Test;
import pages.EventCardPage;
import pages.EventsPage;
import pages.MainPage;
import utils.BaseHooks;


public class EventsTest extends BaseHooks {

    private MainPage mainPage;
    private EventsPage eventsPage;
    private EventCardPage eventCardPage;

    @Test
    public void checkUpcomingEventsCounter(){
        mainPage = new MainPage(driver);
        mainPage.open(baseUrl);
        mainPage.openEvents();
        eventsPage = new EventsPage(driver);
        eventsPage.openUpcomingEventsTab();
        eventsPage.assertUpcomingEventsCounter();
    }

    @Test
    public void checkUpcomingEventCardData(){
        mainPage = new MainPage(driver);
        mainPage.open(baseUrl);
        mainPage.openEvents();
        eventsPage = new EventsPage(driver);
        eventsPage.openUpcomingEventsTab();
        eventsPage.assertUpcomingEventCardData();
    }

    @Test
    public void checkUpcomingEventDate(){
        mainPage = new MainPage(driver);
        mainPage.open(baseUrl);
        mainPage.openEvents();
        eventsPage = new EventsPage(driver);
        eventsPage.openUpcomingEventsTab();
        eventsPage.assertUpcomingEventCardDate();
    }

    @Test
    public void checkPastEventsInCanada(){
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
