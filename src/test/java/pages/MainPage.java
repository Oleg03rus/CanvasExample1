package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import widgets.HeaderButtons;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class MainPage extends HeaderButtons {
    private final SelenideElement buttonTextLeftBar = $x("//button[@data-testid='designer-left-bar-staticText']");
    private final SelenideElement canvas = $x("//div[@class=\"konvajs-content\"]");

    //Перетаскивание элемента на канвас
    public MainPage dragAndDropElementTextToCanvas(int xOffset, int yOffset) throws InterruptedException {
        Actions action = new Actions(getWebDriver());
        Thread.sleep(3000);
        action.dragAndDropBy(buttonTextLeftBar, xOffset, yOffset)
                .build()
                .perform();
        Thread.sleep(3000);
        return this;
    }

    public Map<String, Double> getCoordinatesElementOnCanvas() {
        String jscript = "return arguments[0].getBoundingClientRect();";
        Map<String, Object> rect = Selenide.executeJavaScript(jscript, buttonTextLeftBar);

        double x = ((Number) rect.get("x")).doubleValue();
        double y = ((Number) rect.get("y")).doubleValue();

        System.out.println("Coordinates: X = " + x + ", Y = " + y);

        Map<String, Double> coordinates = new HashMap<>();
        coordinates.put("x", x);
        coordinates.put("y", y);

        return coordinates;
    }

    public MainPage clickElementOnCanvas(double x, double y) {
        String clickScript = "var canvas = arguments[0];" +
                "var ctx = canvas.getContext('2d');" +
                "ctx.canvas.dispatchEvent(new MouseEvent('click', {" +
                "    clientX: arguments[1]," +
                "    clientY: arguments[2]," +
                "    bubbles: true" +
                "}));";
        Selenide.executeJavaScript(clickScript, buttonTextLeftBar, x, y);
        return this;
    }

    public NewReportTemplatePage clickButtonNewReportTemplate() {
        buttonNewTemplate.click();
        return new NewReportTemplatePage();
    }

}
