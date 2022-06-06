package net.progress.POMs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class CheckoutCompletePom {
    private WebDriver driver;

    @FindBy(how = How.CSS, using = "#checkout_complete_container > h2")
    private WebElement completeMessage;

    public CheckoutCompletePom(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getCompleteMessage(){
        return completeMessage.getText();
    }

    public String getMessageValue(){
        return "#checkout_complete_container > h2";
    }
}
