package org.pagedriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.basedriver.BaseDriver;
import org.basedriver.PageDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.utilities.GetScreenshot;

import java.io.IOException;

public class AuthorBook extends BaseDriver {
    ExtentTest test;

    public AuthorBook(ExtentTest test){
        PageFactory.initElements(PageDriver.getCurrentDriver(), this);
        this.test = test;
    }

    @FindBy(xpath = "//*[@id=\"categoryList\"]")
    WebElement subject;

    @FindBy(xpath = "//*[@id=\"category1\"]")
    WebElement checkButton;
    @FindBy(xpath = "/html/body/div[1]/div/div[3]/div/div[2]/div/section[3]/div[2]/div/div[1]/div/div[4]/div/div/div/div/h4[1]/p")
    WebElement founded;
    @FindBy(xpath = "//*[@id=\"addbtn756\"]")
    WebElement addToCard;
    @FindBy(xpath = "/html/body/div[1]/div/div[1]/div/a/div[2]")
    WebElement cardIcon;

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

    public void writerList() throws InterruptedException, IOException {
        Actions action = new Actions(PageDriver.getCurrentDriver());
        JavascriptExecutor js = (JavascriptExecutor) PageDriver.getCurrentDriver();
        try {
            test.info("Hover on Subject");
            if (subject.isDisplayed()){
                action.moveToElement(subject).build().perform();
                Thread.sleep(3000);
                passCaseWithSC("Clicked Subject", "SubjectClick");
                try{
                    test.info("Hover on checkButton");
                    if(checkButton.isDisplayed()){
                        action.moveToElement(checkButton).build().perform();
                        Thread.sleep(5000);
                        checkButton.click();
                        Thread.sleep(5000);
                        passCaseWithSC("Clicked checkButton", "checkButtonClick");
                        try{
                            test.info("Hover on founded");
                            if(founded.isDisplayed()){
                                js.executeScript("arguments[0].scrollIntoView(true)", founded);
                                Thread.sleep(3000);
                                passCaseWithSC("Clicked founded", "foundedClick");
                                try{
                                    test.info("Click on addToCard");
                                    if(addToCard.isDisplayed()){
                                        addToCard.click();
                                        Thread.sleep(3000);
                                        passCaseWithSC("Clicked addToCard", "addToCardClick");
                                        try{
                                            test.info("Click on cardIcon");
                                            if(cardIcon.isDisplayed()){
                                                js.executeScript("arguments[0].scrollIntoView(true)", cardIcon);
                                                Thread.sleep(3000);
                                                cardIcon.click();
                                                Thread.sleep(3000);
                                                passCaseWithSC("Clicked cardIcon", "cardIconClick");
                                            }
                                        }
                                        catch (Exception e){
                                            failCase("Could not click cardIcon", "cardIconClickFail");
                                        }
                                    }
                                }
                                catch (Exception e){
                                    failCase("Could not click addToCard", "addToCardClickFail");
                                }
                            }
                        }
                        catch (Exception e){
                            failCase("Could not click founded", "foundedClickFail");
                        }
                    }
                }
                catch (Exception e){
                    failCase("Could not click checkButton", "checkButtonClickFail");
                }
            }
        }
        catch (Exception e){
            failCase("Could not click Subject", "SubjectClickFail");
        }

    }
}
