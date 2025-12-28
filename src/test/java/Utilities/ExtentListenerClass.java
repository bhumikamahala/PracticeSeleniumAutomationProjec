package Utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ExtentListenerClass implements ITestListener{

    ExtentSparkReporter  htmlReporter;
    ExtentReports reports;
    ExtentTest test;

    public void configureReport()
    {
        ReadConfig readConfig = new ReadConfig();
        String timestamp = new SimpleDateFormat("yyyy.mm.dd.hh.mm.ss").format(new Date());
        String reportName = "MyStoreTestReport-" + timestamp + ".html";
        htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "//Reports//" + reportName);  // report get generated with this name in project
        reports = new ExtentReports();
        reports.attachReporter(htmlReporter); // attch the reporter to the main reports object

        //add system information/environment info to reports
        reports.setSystemInfo("Machine:", "testpc1");
        reports.setSystemInfo("OS", "windows 11");
        reports.setSystemInfo("browser:", readConfig.getBrowser());
        reports.setSystemInfo("user name:", "Test User");

        //configuration to change look and feel of report
        htmlReporter.config().setDocumentTitle("Extent Listener Report Demo");
        htmlReporter.config().setReportName("This is First Report Created");
        htmlReporter.config().setTheme(Theme.DARK);


    }
    // implement all methods(abstract methods by default) of ITestListener interface implements by ExtentListenerClass
    //OnStart method is called when any Test starts.
    public void onStart(ITestContext Result)
    {
        configureReport();
        System.out.println("On Start method invoked....");
    }

    //onFinish method is called after all Tests are executed
    public void onFinish(ITestContext Result)
    {
        System.out.println("On Finished method invoked....");
        reports.flush();//it is mandatory to call flush method to ensure information is written to the started reporter.

    }



    // When Test case get failed, this method is called.
    // ITestResult Result --> it contains information about the result of a test.
    public void onTestFailure(ITestResult Result)
    {
        System.out.println("Name of test method failed:" + Result.getName() ); // Result.getName() is test case name which get failed
        test = reports.createTest(Result.getName());//create entry in html report
        test.log(Status.FAIL, MarkupHelper.createLabel("Name of the failed test case is: " + Result.getName() ,ExtentColor.RED));

        String screenShotPath = System.getProperty("user.dir") + "\\ScreenShots\\" + Result.getName() + ".png";

        File screenShotFile = new File(screenShotPath);

        if(screenShotFile.exists())
        {
            test.fail("Captured Screenshot is below:" + test.addScreenCaptureFromPath(screenShotPath)); // attach screenshot in report

        }
             // addScreenCaptureFromPath --> it is used to attach screenshot in the report it is present in ExtentTest class
        //	test.addScreenCaptureFromPath(null)

    }

    // When Test case get Skipped, this method is called.

    public void onTestSkipped(ITestResult Result)
    {
        System.out.println("Name of test method skipped:" + Result.getName() );

        test = reports.createTest(Result.getName());
        test.log(Status.SKIP, MarkupHelper.createLabel("Name of the skip test case is: " + Result.getName() ,ExtentColor.YELLOW));
    }

    // When Test case get Started, this method is called.

    public void onTestStart(ITestResult Result)
    {
        System.out.println("Name of test method started:" + Result.getName() );

    }

    // When Test case get passed, this method is called.

    public void onTestSuccess(ITestResult Result)
    {
        System.out.println("Name of test method sucessfully executed:" + Result.getName() );

        test = reports.createTest(Result.getName());
        test.log(Status.PASS, MarkupHelper.createLabel("Name of the passed test case is: " + Result.getName() ,ExtentColor.GREEN));
    }


    public void onTestFailedButWithinSuccessPercentage(ITestResult Result)
    {

    }



}