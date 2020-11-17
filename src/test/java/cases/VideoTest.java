package cases;

import org.junit.jupiter.api.Test;
import pages.MainPage;
import pages.VideoPage;
import utils.BaseHooks;

public class VideoTest extends BaseHooks {

    private MainPage mainPage;
    private VideoPage videoPage;

    @Test
    public void checkVideoSearchFilters(){
        mainPage = new MainPage(driver);
        mainPage.open(baseUrl);
        mainPage.openVideo();
        videoPage = new VideoPage(driver);
        videoPage.filterCardsByCategory("Testing");
        videoPage.filterCardsByLocation("Belarus");
        videoPage.filterCardsByLanguage();
        videoPage.assertFoundCardData();
    }

    @Test
    public void searchVideoByText(){
        mainPage = new MainPage(driver);
        mainPage.open(baseUrl);
        mainPage.openVideo();
        videoPage = new VideoPage(driver);
        String textForSearch = "QA";
        videoPage.searchVideoByText(textForSearch);
        videoPage.assertFoundCardNameContains(textForSearch);
    }
}
