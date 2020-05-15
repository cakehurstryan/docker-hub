package docker;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class HubTest {

    public static RemoteWebDriver chrome;
    public static RemoteWebDriver firefox;

    @BeforeClass
    public static void setup() throws Exception {
        System.out.print("Running test setup in Docker containers");
        ChromeOptions chromeOptions = new ChromeOptions();
        FirefoxOptions firefoxOptions = new FirefoxOptions();

        chrome = new RemoteWebDriver(new URL("http://test-hub:4444/wd/hub"), chromeOptions);
        firefox = new RemoteWebDriver(new URL("http://test-hub:4444/wd/hub"), firefoxOptions);
    }

    @Test
    public void testWithChrome() {
        chrome.navigate().to("http://test1:80");
        Assert.assertTrue("title should start with Docker Demo Page",
                chrome.getTitle().startsWith("Docker Demo Page"));

        chrome.close();
    }

    @Test
    public void testWithFirefox() {
        firefox.navigate().to("http://test1:80");
        Assert.assertTrue("title should start with Docker Demo Page",
                firefox.getTitle().startsWith("Docker Demo Page"));

        firefox.close();
    }
}