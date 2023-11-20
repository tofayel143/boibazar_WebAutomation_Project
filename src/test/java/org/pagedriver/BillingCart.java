package org.pagedriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.basedriver.BaseDriver;
import org.basedriver.PageDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.utilities.GetScreenshot;

import java.io.IOException;

public class BillingCart extends BaseDriver {
    WebDriver driver;

    ExtentTest test;

    public BillingCart(ExtentTest test) {
        PageFactory.initElements(PageDriver.getCurrentDriver(), this);
        this.test = test;
    }

    @FindBy(xpath="//*[@id=\"checkout-btn\"]")
    WebElement nextBtn;


    public void failCase(String msg, String scName) throws IOException {
        test.fail("<p style=\"color:#FF5353; font-size:13px\"><b>"+msg+"</b></p>");
        Throwable t = new InterruptedException("Exception");
        test.fail(t);
        String screenShotPath = GetScreenshot.capture(PageDriver.getCurrentDriver(),""+scName+"");
        String dest = System.getProperty("user.dir") + "\\screenshots\\" + ""+scName+".png";
        test.fail(MediaEntityBuilder.createScreenCaptureFromPath(dest).build());
        PageDriver.getCurrentDriver().quit();
    }
    public void passCase(String msg) {
        test.pass("<p style=\"color:#85BC63; font-size:13px\"><b>"+msg+"</b></p>");
    }

    public void passCaseWithSC(String msg, String scName) throws IOException {
        test.pass("<p style=\"color:#85BC63; font-size:13px\"><b>"+msg+"</b></p>");
        String screenShotPath = GetScreenshot.capture(PageDriver.getCurrentDriver(), ""+scName+"");
        String dest = System.getProperty("user.dir") + "\\screenshots\\" + ""+scName+".png";
        test.pass(MediaEntityBuilder.createScreenCaptureFromPath(dest).build());
    }

    public void billingCartBtn() throws InterruptedException, IOException {
        Actions action = new Actions(PageDriver.getCurrentDriver());
        try {
            test.info("Hover on bookInformation");
            if (nextBtn.isDisplayed()){
                action.moveToElement(nextBtn).build().perform();
                Thread.sleep(3000);
                nextBtn.click();
                Thread.sleep(3000);
                passCaseWithSC("Clicked nextBtn", "nextBtnClick");
            }

        }
        catch (Exception e){
            failCase("Could not click nextBtn", "nextBtnClickFail");
        }

    }

}
