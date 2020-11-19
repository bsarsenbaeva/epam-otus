package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

import static java.util.concurrent.TimeUnit.SECONDS;

public class BaseHooks {
    protected static String baseUrl;
    protected WebDriver driver;

    @BeforeAll
    public static void loadConfig() throws Throwable {
        SuiteConfiguration config = new SuiteConfiguration();
        baseUrl = config.getProperty("site.url");
    }

    @SneakyThrows
    @BeforeEach
    public void initDriver() {
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(5, SECONDS);

        String selenoidURL = "http://localhost:4444/wd/hub";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        capabilities.setVersion("85.0");
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("screenResolution", "1280x1024");
        capabilities.setCapability("enableVideo", true);
        capabilities.setCapability("enableLog", true);
//        capabilities.setCapability("videoName", "video.mp4");
//        capabilities.setCapability("logName", "log.log");
//        capabilities.setCapability("screenResolution", "1280x1024");

        driver = new RemoteWebDriver(new URL(selenoidURL), capabilities);
    }

    @AfterEach
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
