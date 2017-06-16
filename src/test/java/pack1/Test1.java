package pack1;

import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import pack2.BaseClass;

/**
 * Created by E002465 on 14-06-2017.
 */
public class Test1 extends BaseClass{

    @Test
    public void test1(){
        System.out.println("hai");
        logger=extent.startTest("Test1");
        driver.get("https://www.google.co.in");
        logger.log(LogStatus.INFO,"opened browser");
    }

    @Test
    public void test2(){
        logger=extent.startTest("a");
        if(driver.getTitle().equalsIgnoreCase("Google")){
            logger.log(LogStatus.PASS,"hurresh");
        }else {
            Assert.assertFalse(true);
            logger.log(LogStatus.FAIL,"oops");
        }
    }

    @Test(enabled = false)
    public void tes1(){
        driver.get("http://automationpractice.com/index.php");
        WebElement ele=driver.findElement(By.xpath("//select[@id='days']"));
        Select opt=new Select(ele);
        opt.selectByIndex(5);
    }
}
