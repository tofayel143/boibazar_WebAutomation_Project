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

public class HomePage extends BaseDriver {
    WebDriver driver;

    ExtentTest test;

    public HomePage(ExtentTest test) {
        PageFactory.initElements(PageDriver.getCurrentDriver(), this);
        this.test = test;
    }

    @FindBy(xpath="/html/body/div[1]/div/nav/div/div[2]/div/div/div[2]/div/div[2]/form/div/div[1]/div/button")
    WebElement cButton;
    @FindBy(xpath="/html/body/div[1]/div/nav/div/div[2]/div/div/div[2]/div/div[2]/form/div/div[1]/div/ul/li[2]/a/span")
    WebElement writer;
    @FindBy(xpath = "//*[@id=\"searchBox\"]")
    WebElement sBox;
    @FindBy(xpath = "/html/body/div[1]/div/nav/div/div[2]/div/div/div[2]/div/div[2]/form/div/div[3]/div/button")
    WebElement sButton;
    @FindBy(xpath = "/html/body/div[1]/div/div[3]/div/section/div/div/div[2]/div/div[1]/div/div/div/div/div/div")
    WebElement selectAuthor;

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

    public void setWriter() throws InterruptedException, IOException {
        Actions action = new Actions(PageDriver.getCurrentDriver());
        try {
            test.info("Hover on bookInformation");
            if (cButton.isDisplayed()){
                action.moveToElement(cButton).build().perform();
                Thread.sleep(5000);
                action.moveToElement(writer).build().perform();
                writer.click();
                Thread.sleep(3000);
                action.moveToElement(sBox).build().perform();
                sBox.sendKeys("পিরিমকুল কাদিরভ");
                Thread.sleep(3000);
                sButton.click();
                Thread.sleep(3000);
                action.moveToElement(selectAuthor).build().perform();
                Thread.sleep(3000);
                selectAuthor.click();
                Thread.sleep(3000);
                passCaseWithSC("Clicked writer", "writerClick");
            }

        }
        catch (Exception e){
            failCase("Could not click bookInformation", "bookInformationClickFail");
        }

    }

}
