package BaseSteps;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public class DesiredCapability {

    public static ChromeOptions setChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("download.default_directory", System.getProperty("user.dir") + "//Exported Files");
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("disable-infobars");
        options.addArguments("disable-save-password-bubble");
        options.setExperimentalOption("useAutomationExtension", false);
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        return options;
    }

    public static EdgeOptions setEdgeOptions() {
        EdgeOptions options = new EdgeOptions();
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_settings.popups", 0);
        prefs.put("download.prompt_for_download", false);
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        Map<String, Object> edgeOptions = new HashMap<String, Object>();
        edgeOptions.put("prefs", prefs);
        edgeOptions.put("excludeSwitches", Collections.singletonList("enable-automation"));
        options.setCapability("ms:edgeChrominum", true);
        options.setCapability("ms:edgeOptions", edgeOptions);
        return options;
    }

    public static FirefoxOptions setFirefoxOptions() {
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("network.proxy.no_proxies_on", "localhost");
        profile.setPreference("javascript.enabled", true);
        FirefoxOptions options = new FirefoxOptions();
        options.setLogLevel(FirefoxDriverLogLevel.fromLevel(Level.FINEST));
        options.addPreference("browser.link.open_newwindow", 3);
        options.addPreference("browser.link.open_newwindow.restriction", 0);
        return options;
    }
}