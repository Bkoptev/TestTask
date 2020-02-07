package com.qa.task;

import org.junit.Assert;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Set;

public abstract class AbstractPage {

    protected BaseTest testClass;

    public String actualWindow;

    //
    // Web elements with @FindBy annotation
    //
    @FindBy(xpath = "//a[@class='login']")
    private WebElement loginLink;

    @FindBy(xpath = "//div[@id='page']")
    public WebElement pageDiv;

    /** Constructor */
    public AbstractPage(BaseTest testClass) {
        this.testClass = testClass;
        PageFactory.initElements(testClass.getDriver(), this); // Initialize WebElements
        testClass.waitTillElementIsVisible(pageDiv);
    }


    /**
     * @param webElement element which needs to to be opened in new tab
     * @return instance of page
     */
    public PrintedDress openInNewTabAndSwitch(WebElement webElement) {
        actualWindow = testClass.getDriver().getWindowHandle();
        webElement.sendKeys(Keys.chord(Keys.CONTROL, Keys.RETURN));

        Set<String> windows = testClass.getDriver().getWindowHandles();
        String newWindow = null;
        for (String window : windows) {
            if (!window.equals(actualWindow)) {
                newWindow = window;
            }
        }
        testClass.getDriver().switchTo().window(newWindow);
        return new PrintedDress(testClass);
    }

    /** close current tab */
    public void closeTab() {
        testClass.getDriver().close();
    }

    /** switches to active tab */
    public void switchToActiveTab() {
        testClass.getDriver().switchTo().window(actualWindow);
    }

    /** prints name of Cookies */
    public void printCookiesName() {
        Set<Cookie> cookies = testClass.getDriver().manage().getCookies();
        for (Cookie s : cookies) {
            System.out.println(s.getName());
        }
    }
}
