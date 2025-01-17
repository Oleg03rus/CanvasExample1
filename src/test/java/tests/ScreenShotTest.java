package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Allure;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.junit.jupiter.api.*;
import org.openqa.selenium.JavascriptExecutor;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.webdriver;

public class ScreenShotTest extends BaseTest{
    private String testName;
    private static File outputDir;

    @BeforeAll
    public static void initFolder() {
        outputDir = new File("build/screenshots");
        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }
    }

    @BeforeEach
    public void initTestName(TestInfo info) {
        testName = info.getTestMethod().get().getName();
    }

    @Test
    public void screenShot() throws IOException {
        $(".konvajs-content").shouldBe(Condition.visible);

        JavascriptExecutor js = (JavascriptExecutor) webdriver().object();
        js.executeScript(
                "var canvas = document.querySelector('canvas');" +
                        "var ctx = canvas.getContext('2d');" +
                        "ctx.fillStyle = 'red';" +
                        "ctx.fillRect(300, 150, 100, 100);"

        );

        Screenshot screenshot = new AShot()
                .shootingStrategy(ShootingStrategies.viewportPasting(3000))
                .takeScreenshot(WebDriverRunner.getWebDriver());
        File actualScreen = new File(outputDir.getAbsolutePath() + "/" + testName + ".png");
        ImageIO.write(screenshot.getImage(), "png", actualScreen);

        File expectedScreen = new File(String.format("src/test/resources/references/%s.png", testName));
        if(!expectedScreen.exists()){
            throw new RuntimeException("No reference image");
        }
        assertImage(actualScreen, expectedScreen);

    }

    private void assertImage(File actual, File expected) throws IOException {
        ImageDiff differ = new ImageDiffer()
                .makeDiff(ImageIO.read(actual), ImageIO.read(expected))
                .withDiffSizeTrigger(10);
        if(differ.hasDiff()){
            BufferedImage diffImage = differ.getMarkedImage();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(diffImage, "png", bos);
            byte[] image = bos.toByteArray();
            Allure.getLifecycle().addAttachment("diff", "image/png", "png", image);
        }
        Assertions.assertFalse(differ.hasDiff());
    }
}
