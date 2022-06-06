package net.progress.POMs;

import net.progress.helpers.SelectorTypes;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static net.progress.helpers.ElementHelper.doesElementExist;

public class CartPom extends LoadableComponent {
    private WebDriver driver;

    @FindBy(how = How.XPATH, using = "//*[@id=\"header_container\"]/div[2]/span")
    private WebElement cartTitle;

    @FindBy(how=How.CLASS_NAME, using = "cart_item")
    private List<WebElement> cartItems;

    @FindBy(how=How.ID, using = "checkout")
    private WebElement checkoutButton;

    public CartPom(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getCartTitle(){
        Wait wait = new WebDriverWait(driver, 10);
        return this.cartTitle.getText();
    }

    public String getCartTitleValue(){
        return "//*[@id=\"header_container\"]/div[2]/span";
    }

    public boolean doesProductExist(String productName){
        boolean result=false;
        for(WebElement element: cartItems){
            WebElement singleName = element.findElement(By.cssSelector(".cart_item_label >a >div"));
            //System.out.println(singleName.getText());
            if(singleName.getText().equalsIgnoreCase(productName)){
                result=true;
                break;
            }
        }
        return result;
    }

    public void navigateToCheckout(){
        checkoutButton.click();
    }


    @Override
    protected void load() {

    }

    @Override
    public void isLoaded() throws Error {
        Wait wait= new WebDriverWait(driver, 10);
        Assert.assertTrue("Expected title does not exist. The user has not reached the Cart page.",
                doesElementExist(driver, SelectorTypes.XPath, getCartTitleValue()));
    }
}
