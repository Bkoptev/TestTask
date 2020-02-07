package com.qa.task;

public class HomePage extends AbstractPage {

    /**
     * Constructor
     *
     * @param testClass the instance of home page
     */
    public HomePage(BaseTest testClass) {
        super(testClass);
        testClass.waitTillElementIsVisible(pageDiv);
    }
}
