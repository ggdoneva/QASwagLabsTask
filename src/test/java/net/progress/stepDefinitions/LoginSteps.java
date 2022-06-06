package net.progress.stepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.progress.POMs.LoginPagePom;
import net.progress.POMs.ProductsPom;
import net.progress.helpers.Context;
import net.progress.helpers.DriverHelper;
import net.progress.helpers.ElementHelper;
import net.progress.helpers.SelectorTypes;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import static net.progress.helpers.ElementHelper.doesElementExist;

public class LoginSteps {
    private WebDriver driver;
    private Context context;

    public LoginSteps() {
        driver = DriverHelper.getDriver();
        this.context = new Context();
    }

    @Given("a user lands on the login page")
    public void navigateToSite() {
        LoginPagePom loginPagePom = new LoginPagePom(driver);
        loginPagePom.load();
        loginPagePom.isLoaded();
        context.loginPagePom = loginPagePom;
    }

    @When("the user fills their {string} and {string}")
    public void fillUserCredentials(String username, String password) {
        context.loginPagePom.fillCredentials(username, password);
        context.loginPagePom.clickLoginButton();
    }

    @Then("the user is successfully logged in to the website")
    public void successfullyLogged() {
        ProductsPom productsPom = new ProductsPom(driver);
        ElementHelper elementHelper = new ElementHelper();

        Assert.assertTrue("Expected element does not exist. The user has not reached the Products page.",
                doesElementExist(driver, SelectorTypes.XPath, productsPom.getTitleValue()));

        Assert.assertEquals("The user is not on the Products page",
                String.format("PRODUCTS"), productsPom.getTitle());

        context.elementHelper = elementHelper;
        context.productsPom = productsPom;
        driver.close();
    }

    @Then("the user is shown the account locked message")
    public void theUserIsShownTheAccountLockedMessage() {

        Assert.assertTrue("Expected element does not exist.",
                doesElementExist(driver, SelectorTypes.CssSelector, context.loginPagePom.getErrorMsgValue()));

        Assert.assertEquals("The message is not the expected one for locked user.",
                String.format("Epic sadface: Sorry, this user has been locked out."), context.loginPagePom.getErrorMessage());

        driver.close();
    }

    @Then("the user is shown the username and password mismatch message")
    public void theUserIsShownTheUsernameAndPasswordMismatchMessage() {

        Assert.assertTrue("Expected element does not exist.",
                doesElementExist(driver, SelectorTypes.CssSelector, context.loginPagePom.getErrorMsgValue()));

        Assert.assertEquals("The message is not the expected one for incorrect credentials.",
                "Epic sadface: Username and password do not match any user in this service", context.loginPagePom.getErrorMessage());

        driver.close();
    }
}
