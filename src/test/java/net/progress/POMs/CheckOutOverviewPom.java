package net.progress.POMs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class CheckOutOverviewPom {
    private WebDriver driver;

    @FindBy(how = How.ID, using = "finish")
    private WebElement finishButton;

    public CheckOutOverviewPom(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickFinishButton(){
        finishButton.click();
    }
}
