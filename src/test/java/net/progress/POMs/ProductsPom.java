package net.progress.POMs;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

import java.util.List;

public class ProductsPom extends LoadableComponent {
    private WebDriver driver;

    @FindBy(how = How.ID, using = "react-burger-menu-btn")
    private WebElement menuButton;

    @FindBy(how = How.XPATH, using = "//*[@id=\"header_container\"]/div[2]/span")
    private WebElement productsTitle;

    @FindBy(how = How.CLASS_NAME, using = "inventory_item")
    private List<WebElement> products;

    @FindBy(how = How.CLASS_NAME, using = "shopping_cart_link")
    private WebElement cartButton;

    @FindBy(how = How.CLASS_NAME, using = "product_sort_container")
    private WebElement sortingButton;

    public ProductsPom(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getTitle(){
        Wait wait = new WebDriverWait(driver, 10);
        return this.productsTitle.getText();
    }

    public String getTitleValue(){
        return "//*[@id=\"header_container\"]/div[2]/span";
    }

    public void selectProduct(String productName){
        WebElement cartButton = null;
        for (WebElement element : products) {
            //WebElement singleName = element.findElement(By.className("inventory_item_name"));
            WebElement singleName = element.findElement(By.cssSelector(".inventory_item_label >a >div"));
            if (singleName.getText().equalsIgnoreCase(productName)) {
                cartButton = element.findElement(By.xpath("//*[contains(@id, 'add-to-cart')]"));
            }
        }

        Assert.assertNotNull(String.format("There is no product with the name %s", productName),cartButton);

        cartButton.click();
    }

    public void selectEachProduct(String productName){
        SingleProductPom singleProductPom = new SingleProductPom(driver);
        for (WebElement element : products) {
            WebElement singleProduct = element.findElement(By.cssSelector(".inventory_item_label >a >div"));
            if (singleProduct.getText().equalsIgnoreCase(productName)) {
                singleProduct.click();
                singleProductPom.clickAddToCart();
                singleProductPom.clickBackButton();
                break;
            }
        }
    }

    public void navigateToCart(){
        cartButton.click();
    }

    public String getFirstProduct(){
        WebElement firstElement = products.get(0);
        WebElement name = firstElement.findElement(By.cssSelector(".inventory_item_label >a >div"));
        String nameElement = name.getText();
        System.out.println(nameElement);

        return nameElement;
    }

    public void selectSorting(String sortingType){
        Select select = new Select(sortingButton);
        select.selectByVisibleText(sortingType);
    }

    @Override
    public void load() {

    }

    @Override
    public void isLoaded() throws Error {
        Wait wait= new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(menuButton));
    }
}
