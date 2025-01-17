package tests;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import pages.MainPage;
import pages.NewReportTemplatePage;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class MainPageTest extends BaseTest {
    private MainPage mainPage = new MainPage();
    private NewReportTemplatePage newReportTemplatePage = new NewReportTemplatePage();

    @Test
    void testDragAndDropTextELementOnCanvas() throws InterruptedException {
        mainPage.clickButtonNewReportTemplate();
        newReportTemplatePage.clickEmptyA4()
                .clickButtonCreate();
        mainPage.dragAndDropElementTextToCanvas(600, 500);
        Map<String, Double> coordinates = mainPage.getCoordinatesElementOnCanvas();
        mainPage.clickElementOnCanvas(coordinates.get("x"), coordinates.get("y"));
    }
}
