package TestCases;

import Utilities.ReadConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.*;

public class BaseClass {
    // all reusable methods we will create here
    ReadConfig readConfig = new ReadConfig();
    // keys ko read kar k , unki values ko store karna h
    String url = readConfig.getBaseUrl();
    String browser = readConfig.getBrowser();

    public static WebDriver driver ;
    public static Logger logger ;
    // launch the browser
    @BeforeClass
    public void setUp(){
        switch (browser.toLowerCase()){
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            default:
                driver = null;
                break;
        }
        // wait chahiya apn ko sare test cases k liya , implicit wait applicable to all webElements
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // logging
      logger = LogManager.getLogger("AutomationProject");
    }

    // close browser / quiet
    @AfterClass
    public void tearDown(){
        if(driver != null) {
            driver.close();
            driver.quit();
        }
    }
}
