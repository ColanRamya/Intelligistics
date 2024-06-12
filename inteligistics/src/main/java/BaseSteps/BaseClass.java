package BaseSteps;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class BaseClass {

    public static Duration timeout = Duration.ofSeconds(10);
    public static WebDriver driver;

    public static String getDataFromPropertyFile(String filePath, String parameterName) {

        Properties prop = new Properties();
        try {
            FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\PropertyFile\\PropertyClassPaths.properties");
            prop.load(file);
            String parameter = prop.getProperty(filePath);
            FileInputStream parametrName = new FileInputStream(System.getProperty("user.dir") + parameter);
            prop.load(parametrName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prop.getProperty(parameterName);
    }

    public static WebDriver browserLaunch() {

        //String browserSelect = System.getenv("${browser}");
         String browserSelect = BaseClass.getDataFromPropertyFile("file", "browser");

        if (browserSelect.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(DesiredCapability.setChromeOptions());
        } else if (browserSelect.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver(DesiredCapability.setFirefoxOptions());
        } else if (browserSelect.equals("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver(DesiredCapability.setEdgeOptions());
        }
        driver.manage().window().maximize();
        return driver;

    }

    public static void urlLaunch(String filename, String url) {

        driver.get(BaseClass.getDataFromPropertyFile(filename, url));

    }

    public static void browserQuit() {
        //loading wait
        driver.quit();
    }

    public static WebElement findElementByXpath(String filename, String xpath) {

        WebElement webelement = driver.findElement(By.xpath(getDataFromPropertyFile(filename, xpath)));
        return webelement;

    }

    public static void setText(String filename, String xpath, String text) {

        waitTillTheElementIsDisplayed(filename, xpath);
        WebElement element = findElementByXpath(filename, xpath);
        for (int i = 0; i <= text.length() - 1; i++) {
            waitForMilliseconds(300);
            element.sendKeys("" + text.charAt(i));
        }
    }

    public static void clickControl(String filename, String xpath) {

        waitTillTheElementIsClickable(filename, xpath);
        WebElement element = findElementByXpath(filename, xpath);
        for (int i = 0; i <= 3; i++) {
            try {
                element.click();
                break;
            } catch (ElementClickInterceptedException ex) {
                return;
            } catch (StaleElementReferenceException ex) {
                return;
            }
        }
    }

    public static boolean isControlDispalyed(String filename, String xpath) {

        waitTillTheElementIsDisplayed(filename, xpath);
        try {
            return findElementByXpath(filename, xpath).isDisplayed();
        } catch (Exception ex) {
            return false;
        }
    }

    public static void waitTillTheElementIsDisplayed(String filename, String xpath) {

        for (int i = 0; i <= 3; i++) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, timeout);
                wait.until(ExpectedConditions.visibilityOf(findElementByXpath(filename, xpath)));
                break;
            } catch (Throwable e) {
                e.printStackTrace();
                return;
            }
        }
    }

    public static void waitTillTheElementIsClickable(String filename, String xpath) {

        waitTillTheElementIsDisplayed(filename, xpath);
        for (int i = 0; i <= 3; i++) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, timeout);
                wait.until(ExpectedConditions.elementToBeClickable(findElementByXpath(filename, xpath)));
                break;
            } catch (Throwable e) {
                e.printStackTrace();
                return;
            }
        }
    }

    public static void waitTillTheElementIsNotDisplayed(String filename, String xpath) {

        for (int i = 0; i <= 3; i++) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, timeout);
                wait.until(ExpectedConditions.invisibilityOf(findElementByXpath(filename, xpath)));
                break;
            } catch (Throwable e) {
                e.printStackTrace();
                return;
            }
        }
    }

    public static void waitForMilliseconds(int milliseconds) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(milliseconds));

    }

    public static void waitForSeconds(int seconds) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));

    }

    public static void moveToElement(String filename, String xpath) {

        Actions action = new Actions(driver);
        WebElement mousehover = findElementByXpath(filename, xpath);
        action.moveToElement(mousehover).click().perform();
    }

    public static void dropDownSelectElementByVisibleText(WebElement droplistpath, String option) {

        Select dropdownselect = new Select(droplistpath);
        dropdownselect.selectByVisibleText(option);

    }

    public static void scrollToBottom() {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");

    }

    public static List<WebElement> findElementsByXpath(String page, String pathOfElement) {

        List<WebElement> listOfElements = driver.findElements(By.xpath(getDataFromPropertyFile(page, pathOfElement)));
        return listOfElements;

    }

    public static void scrollIntoViewOf(WebElement element) {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public static void clickUsingJS(String filename, String xpath) {

        waitTillTheElementIsClickable(filename, xpath);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", findElementByXpath(filename, xpath));
    }
}