package TestCases;

import Utilities.ReadConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

public class BaseClass {
    // all reusable methods we will create here
    ReadConfig readConfig = new ReadConfig();
    // keys ko read kar k , unki values ko store karna h
    String url = readConfig.getBaseUrl();
    String browser = readConfig.getBrowser();
    String os = readConfig.getOS();

    public static WebDriver driver ;
    public static Logger logger ;
    // launch the browser
    @BeforeClass
    public void setUp() throws MalformedURLException {

        String executionType = readConfig.getProperty("execution_env");
        // Default to local if executionType is null or empty
        if(executionType == null || executionType.trim().isEmpty()) {
            executionType = "local";
        }
        
        if(executionType.equalsIgnoreCase("local")){
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
                    throw new RuntimeException("Unsupported browser: " + browser + ". Supported browsers: chrome, firefox");
            }
            if(driver != null) {
                if(os != null && os.equalsIgnoreCase("windows")){
                    driver.manage().window().maximize();
                } else if (os != null && os.equalsIgnoreCase("linux")) {
                    driver.manage().window().fullscreen();
                }
                // wait chahiya apn ko sare test cases k liya , implicit wait applicable to all webElements
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            }
        } else if (executionType.equalsIgnoreCase("remote")) {
            DesiredCapabilities cap = new DesiredCapabilities();
            switch (browser.toLowerCase()){
                case "chrome":
                    cap.setBrowserName("chrome");
                    break;
                case "firefox":
                    cap.setBrowserName("firefox");
                    break;
                default:
                    throw new RuntimeException("Unsupported browser for remote execution: " + browser + ". Supported browsers: chrome, firefox");
            }
            if(os != null && os.equalsIgnoreCase("windows")){
                cap.setPlatform(Platform.WIN10);
            } else if (os != null && os.equalsIgnoreCase("linux")) {
                cap.setPlatform(Platform.LINUX);
            }
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
            if(driver != null) {
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            }
        } else {
            throw new RuntimeException("Invalid execution type: " + executionType + ". Valid values: local, remote");
        }

        // logging
        logger = LogManager.getLogger("AutomationProject");
        
        if(driver == null) {
            throw new RuntimeException("WebDriver initialization failed. Check your configuration.");
        }
    }

    // close browser / quiet
    @AfterClass
    public void tearDown(){
        if(driver != null) {
            driver.close();
            driver.quit();
        }
    }

    public void captureScreenShot(WebDriver driver, String testName) throws IOException {
        // code to capture screenshot
        TakesScreenshot ts= (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE); // call getScreenshotAs method to create image file
        File target = new File(System.getProperty("user.dir") + "/Screenshots/" + testName + ".png");
        FileUtils.copyFile(source,target);
    }
}
