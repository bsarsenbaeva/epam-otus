package cases;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import pages.MainPage;
import pages.VideoPage;
import utils.BaseHooks;

public class VideoTest extends BaseHooks {

    private MainPage mainPage;
    private VideoPage videoPage;

    @Test
    @Epic("Видео")
    @Feature("Поиск видео")
    @Story("Фильтрация докладов по категориям")
    @Description("Проверка фильтров при поиске видео")
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
    @Epic("Видео")
    @Feature("Поиск видео")
    @Story("Поиск докладов по ключевому слову")
    @Description("Поиск видео по тексту")
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
