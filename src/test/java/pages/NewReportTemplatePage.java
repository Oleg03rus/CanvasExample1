package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import widgets.HeaderButtons;

import static com.codeborne.selenide.Selenide.$x;

public class NewReportTemplatePage extends HeaderButtons {
    private final SelenideElement emptyA4 = $x("//div[@data-testid='page-new-report-blank']");
    private final SelenideElement buttonCreate = $x("//button[@data-testid='page-new-report-create']");

    public NewReportTemplatePage clickEmptyA4() {
        emptyA4.click();
        return this;
    }

    public MainPage clickButtonCreate() {
        buttonCreate.click();
        return new MainPage();
    }


}
