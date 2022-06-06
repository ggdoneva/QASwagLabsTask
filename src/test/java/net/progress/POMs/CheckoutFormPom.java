package net.progress.POMs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class CheckoutFormPom {
    private WebDriver driver;

    @FindBy(how = How.ID, using = "first-name")
    private WebElement firstNameField;

    @FindBy(how = How.ID, using = "last-name")
    private WebElement lastNameField;

    @FindBy(how = How.ID, using = "postal-code")
    private WebElement zipCodeField;

    @FindBy(how = How.ID, using = "continue")
    private WebElement continueButton;

    public CheckoutFormPom(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickContinue(){
        continueButton.click();
    }

    public void fillFirstName(String firstName){
        firstNameField.sendKeys(firstName);
    }

    public void fillLastName(String lastName){
        lastNameField.sendKeys(lastName);
    }

    public void fillZipCode(String zipCode){
        zipCodeField.sendKeys(zipCode);
    }


}
