package widgets;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class HeaderButtons {
    protected final SelenideElement buttonNewTemplate = $x("//button[@data-testid=\"page-buttons-new-report\"]");
}
