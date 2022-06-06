package net.progress.helpers;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ElementHelper {
    public static boolean doesElementExist(WebDriver driver, SelectorTypes findBy, String value) {
        List<WebElement> elements = new ArrayList<>();
        switch (findBy) {
            case ID:
                elements = driver.findElements(By.id(value));
                break;
            case Name:
                elements = driver.findElements(By.name(value));
                break;
            case XPath:
                elements = driver.findElements(By.xpath(value));
                break;
            case CssSelector:
                elements = driver.findElements(By.cssSelector(value));
                break;
            case ClassName:
                elements = driver.findElements(By.className(value));
                break;
            default:
                Assert.fail("There is no definition for selector of type " + findBy);
                break;
        }
        return elements.size() > 0;
    }
}
