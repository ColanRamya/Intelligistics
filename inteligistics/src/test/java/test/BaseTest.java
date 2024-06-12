package test;

import org.testng.Assert;
import BaseSteps.BaseClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void urlLaunch() {
        BaseClass.browserLaunch();
        BaseClass.urlLaunch("file", "applicationurl");
    }

    @AfterMethod(alwaysRun = true)
    public void teardown() {

        BaseClass.browserQuit();
    }
}
