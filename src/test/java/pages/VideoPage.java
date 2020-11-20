package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class VideoPage extends AbstractPage {

    @FindBy(css = ".evnt-toggle-filters-button")
    public WebElement buttonMoreFilters;
    @FindBy(css = ".evnt-more-filters")
    public WebElement tabMoreFilters;
    @FindBy(css = ".evnt-search-filter input")
    public WebElement inputSearchFilter;
    @FindBy(css = ".evnt-filter-button[id=filter_category]")
    public WebElement buttonCategoryFilter;
    @FindBy(css = ".evnt-filter-menu[aria-labelledby='filter_category'] .evnt-text-fields")
    public WebElement inputSearchCategoryFilter;
    @FindBy(css = ".evnt-filter-menu[aria-labelledby='filter_category'] .evnt-checkbox .form-check-label[data-value='E2E Testing']")
    public WebElement checkBoxE2EInSearchCategoryFilter;
    @FindBy(css = ".evnt-filter-button[id=filter_location]")
    public WebElement buttonLocationFilter;
    @FindBy(css = ".evnt-filter-menu[aria-labelledby='filter_location'] .evnt-text-fields")
    public WebElement inputSearchLocationFilter;
    @FindBy(css = ".evnt-filter-menu[aria-labelledby='filter_location'] .evnt-checkbox:nth-child(2)")
    public WebElement checkBoxInSearchLocationFilter;
    @FindBy(css = ".evnt-filter-button[id=filter_language]")
    public WebElement buttonLanguageFilter;
    @FindBy(css = ".evnt-filter-menu[aria-labelledby='filter_language'] .evnt-checkbox .form-check-label[data-value='ENGLISH']")
    public WebElement checkBoxEnglishLanguage;
    @FindBy(css = ".evnt-card-table .language")
    public WebElement videoCardLanguage;
    @FindBy(css = ".evnt-card-table .evnt-talk-name span")
    public WebElement videoCardName;
    @FindBy(css = ".evnt-card-table .evnt-talk-name span")
    public List<WebElement> videoCardNames;

    public VideoPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открыть все фильтры")
    public void openMoreFilters() {
        waitForElement(buttonMoreFilters).click();
        waitForElement(tabMoreFilters);
    }

    @Step("Фильтровать карточки по категории")
    public void filterCardsByCategory(String categoryName) {
        waitForElement(buttonCategoryFilter).click();
        waitForElement(inputSearchCategoryFilter).sendKeys(categoryName);
        waitForElement(checkBoxE2EInSearchCategoryFilter).click();
    }

    @Step("Фильтровать карточки по локации")
    public void filterCardsByLocation(String locationName) {
        waitForElement(buttonLocationFilter).click();
        waitForElement(inputSearchLocationFilter).sendKeys(locationName);
        waitForElement(checkBoxInSearchLocationFilter).click();
    }

    @Step("Фильтровать карточки по языку")
    public void filterCardsByLanguage() {
        waitForElement(buttonLanguageFilter).click();
        waitForElement(checkBoxEnglishLanguage).click();
    }

    @Step("Поиск видео по тексту")
    public void searchVideoByText(String name) {
        waitForElement(inputSearchFilter).sendKeys(name);
    }

    private List<WebElement> getVideoCardNames() {
        return waitForElements(videoCardNames);
    }

    @Step("Проверка данных найденных видеео")
    public void assertFoundCardData() {
        String foundCardName = waitForElement(videoCardName).getText();
        String foundCardLanguage = waitForElement(videoCardLanguage).getText();

        assertThat(foundCardName, equalTo("Testing takeover - success story"));
        assertThat(foundCardLanguage, equalTo("En"));
    }

    @Step("Проверка что найденные видео содержат текст")
    public void assertFoundCardNameContains(String name) {
        boolean anyMatch = getVideoCardNames().stream()
                .map(WebElement::getText)
                .anyMatch(text -> "inside".equals(name));
    }
}
