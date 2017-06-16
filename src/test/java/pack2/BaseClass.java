package pack2;

import com.mongodb.MapReduceCommand;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;

/**
 * Created by E002465 on 14-06-2017.
 */
public class BaseClass {
    public ExtentTest logger;
    public ExtentReports extent ;
    public WebDriver driver;

    @BeforeSuite
    public void beforeSuite(){
        System.out.println("BeforeSuite");
        extent = new ExtentReports(System.getProperty("user.dir") +"\\Reports\\Repo.html",true);
        System.setProperty("webdriver.gecko.driver","C:\\Users\\E002465\\Desktop\\New folder\\TestNGPra\\geckodriver.exe");
        driver=new FirefoxDriver();
    }

    @AfterSuite
    public void afterSuite(){

        driver.quit();
        extent.close();
    }

    @AfterMethod
    public void afterMethod(ITestResult result) throws IOException
    {
        if(result.getStatus()==ITestResult.FAILURE){
            logger.log(LogStatus.FAIL,"Test case failed is: "+result.getName());
            String screenShotPath = capture(driver,"screen");
            System.out.println(screenShotPath+"==>");
            logger.log(LogStatus.FAIL,"screent shot is", logger.addScreenCapture(screenShotPath));

        }
        else if(result.getStatus()==ITestResult.SKIP){
            logger.log(LogStatus.FAIL,"Test case skipped is: "+result.getName());
        }
        extent.endTest(logger);
        extent.flush();
    }
    private String capture(WebDriver driver,String screenShotName) throws IOException
    {
        TakesScreenshot ts = (TakesScreenshot)driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String dest = "Reports/sc/"+screenShotName+".png";
        File destination = new File(dest);
        FileUtils.copyFile(source,destination);
        return "./sc/screen.png";
    }
}
