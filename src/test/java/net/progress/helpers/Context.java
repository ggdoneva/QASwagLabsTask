package net.progress.helpers;

import net.progress.POMs.*;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Map;

public class Context {
    public WebDriver driver;
    public LoginPagePom loginPagePom;
    public ProductsPom productsPom;
    public ElementHelper elementHelper;
    public Map<String, String> products;
    public CartPom cartPom;
    public SingleProductPom singleProductPom;
    public CheckoutFormPom checkoutFormPom;

}
