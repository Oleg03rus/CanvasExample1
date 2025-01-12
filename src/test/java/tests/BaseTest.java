package tests;

import com.codeborne.selenide.Configuration;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import utils.ProjectConfigs;

import static com.codeborne.selenide.Selenide.*;

public class BaseTest {
    private ProjectConfigs config;

    @BeforeEach
    public void setUp() {
        config = ConfigFactory.create(ProjectConfigs.class, System.getProperties());
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");

        Configuration.baseUrl = config.url();
        Configuration.browser = config.browser();
        Configuration.browserSize = config.browserSize();
        Configuration.timeout = config.timeout();
        open("/");
    }

    @AfterEach
    void tearDown() {
        closeWebDriver();
    }
}
