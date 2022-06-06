package net.progress.stepDefinitions;

import cucumber.api.java.en.And;
import io.cucumber.datatable.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.progress.POMs.*;
import net.progress.helpers.Context;
import net.progress.helpers.DriverHelper;
import net.progress.helpers.ElementHelper;
import net.progress.helpers.SelectorTypes;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Map;

import static net.progress.helpers.ElementHelper.doesElementExist;

public class LoginAndShopSteps {

    private WebDriver driver;
    private Context context;

    public LoginAndShopSteps() {
        driver = DriverHelper.getDriver();
        this.context = new Context();
    }

    @Given("a user lands on the SwagLabs login page")
    public void aUserLandsOnTheSwagLabsLoginPage() {
        LoginPagePom loginPagePom = new LoginPagePom(driver);
        loginPagePom.load();
        loginPagePom.isLoaded();
        context.loginPagePom = loginPagePom;
    }

    @When("the user logs in with username {string} and password {string}")
    public void theUserLogsInWithUsernameAndPassword(String username, String password) {
        context.loginPagePom.fillCredentials(username, password);
        context.loginPagePom.clickLoginButton();
    }

    @Then("the user lands on the Products page")
    public void theUserLandsOnTheProductsPage() {
        ProductsPom productsPom = new ProductsPom(driver);
        ElementHelper elementHelper = new ElementHelper();

        Assert.assertTrue("Expected element does not exist. The user has not reached the Products page.",
                doesElementExist(driver, SelectorTypes.XPath, productsPom.getTitleValue()));
        Assert.assertEquals("The user is not on the Products page",
                String.format("PRODUCTS"), productsPom.getTitle());

        context.elementHelper = elementHelper;
        context.productsPom = productsPom;
    }

    @Given("the user adds items to the cart:")
    public void theUserAddsTheBelowItemsToTheCart(DataTable table) {
        Map<String, String> products = table.asMaps().get(0);
        for (String key : products.keySet()) {
            String productName = products.get(key);
            if (!productName.trim().equals("")) {
                context.productsPom.selectProduct(productName);
            }
        }
        context.products = products;
    }

    @Given("the user adds items to the cart, one by one:")
    public void theUserAddsItemsToTheCartOneByOne(DataTable table) {
        Map<String, String> products = table.asMaps().get(0);
        for (String key : products.keySet()) {
            String productName = products.get(key);
            if (!productName.trim().equals("")) {
                context.productsPom.selectEachProduct(productName);
            }
        }
        context.products = products;
    }

    @When("the user opens the cart")
    public void theUserOpensTheCart() {
        context.productsPom.navigateToCart();
        CartPom cartPom = new CartPom(driver);
        cartPom.isLoaded();
        context.cartPom = cartPom;
    }

    @Then("the cart is updated with the respective products")
    public void theCartIsUpdatedWithTheRespectiveProducts() {
        Map<String, String> expectedProducts = context.products;
        for (String key : expectedProducts.keySet()){
            String productName = expectedProducts.get(key);
            if (!productName.equals("")) {
                Assert.assertTrue(String.format("The following product is not added to the cart:", productName),
                        context.cartPom.doesProductExist(productName));
            }
        }
    }

    @When("the users selects the type of {string} sorting")
    public void theUsersSelectsTheTypeOfSorting(String sorting) throws InterruptedException {
        context.productsPom.selectSorting(sorting);
        Thread.sleep(2000);
    }

    @Then("the {string} first product will be as follows")
    public void theFirstProductWillBeAsFollows(String first) {
        //System.out.println(first);
        Assert.assertEquals("The first product is not the expected one.",
                first,context.productsPom.getFirstProduct());
    }

    @Given("the user adds {string} item to the cart")
    public void theUserAddsItemToTheCart(String product) {
        context.productsPom.selectProduct(product);
    }

    @When("the user navigates to Checkout page")
    public void theUserNavigatesToCheckoutPage() {
        CartPom cartPom = new CartPom(driver);
        context.productsPom.navigateToCart();
        cartPom.navigateToCheckout();

        context.cartPom = cartPom;
    }

    @And("the user fills the following details:")
    public void theUserFillsTheFollowingDetails(DataTable dataTable) {
        CheckoutFormPom checkoutFormPom = new CheckoutFormPom(driver);
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);

        for(Map<String, String> map:data) {
            checkoutFormPom.fillFirstName(map.get("FirstName"));
            checkoutFormPom.fillLastName(map.get("LastName"));
            checkoutFormPom.fillZipCode(map.get("ZipCode"));
        }
        checkoutFormPom.clickContinue();
        context.checkoutFormPom = checkoutFormPom;
    }

    @And("proceeds with checkout")
    public void proceedsWithCheckout() {
        CheckOutOverviewPom checkOutOverviewPom = new CheckOutOverviewPom(driver);
        checkOutOverviewPom.clickFinishButton();
    }

    @Then("the user successfully places the order")
    public void theUserSuccessfullyPlacesTheOrder() {
        CheckoutCompletePom checkoutCompletePom = new CheckoutCompletePom(driver);

        Assert.assertTrue("Expected element does not exist. The user has not reached the Checkout Complete page.",
                doesElementExist(driver, SelectorTypes.CssSelector, checkoutCompletePom.getMessageValue()));
        Assert.assertEquals("The user has not placed the order",
                String.format("THANK YOU FOR YOUR ORDER"), checkoutCompletePom.getCompleteMessage());

    }

}
