package net.progress.POMs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class SingleProductPom {
    private WebDriver driver;

    @FindBy(how = How.ID, using = "back-to-products")
    private WebElement backButton;

    @FindBy(how = How.XPATH, using = "//*[contains(@id, 'add-to-cart')]")
    private WebElement addToCartButton;

    public SingleProductPom(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickBackButton() {
        backButton.click();
    }

    public void clickAddToCart() {
        addToCartButton.click();
    }
}
