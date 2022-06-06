package net.progress.POMs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPagePom extends LoadableComponent {
    private WebDriver driver;

    @FindBy(how = How.ID, using = "user-name")
    private WebElement loginField;

    @FindBy(how = How.ID, using = "password")
    private WebElement passwordField;

    @FindBy(how = How.ID, using = "login-button")
    private WebElement loginButton;

    @FindBy(how = How.CSS, using = ".error-message-container > h3:nth-child(1)")
    private WebElement errorMessage;
    //*[@id="login_button_container"]/div/form/div[3]/h3/text()

    public LoginPagePom(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getErrorMsgValue(){
        return ".error-message-container > h3:nth-child(1)";
    }

    public void fillCredentials(String username, String password){
        loginField.sendKeys(username);
        passwordField.sendKeys(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public String getErrorMessage(){
        return this.errorMessage.getText();
    }

    @Override
    public void load() {
        driver.get("https://www.saucedemo.com/");
    }

    @Override
    public void isLoaded() throws Error {
        Wait wait= new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
    }
}
