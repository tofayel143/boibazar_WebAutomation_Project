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

public class RegistrationPage extends BaseDriver {
    ExtentTest test;

    public RegistrationPage(ExtentTest test){
        PageFactory.initElements(PageDriver.getCurrentDriver(), this);
        this.test = test;
    }

    @FindBy(xpath = "//*[@id=\"ca-name-input\"]")
    WebElement fullName;
    @FindBy(xpath = "//*[@id=\"ca-email-input\"]")
    WebElement emailAdd;
    @FindBy(xpath = "//*[@id=\"password-input-signup\"]")
    WebElement passWord;
    @FindBy(xpath = "//*[@id=\"password-input-signup\"]")
    WebElement selectDistrict;
    @FindBy(xpath = "/html/body/div[1]/div/div[3]/div/section/div[2]/div/div/div[2]/div[2]/select[1]/option[51]")
    WebElement district;
    @FindBy(xpath = "//*[@id=\"thana\"]")
    WebElement policeStation;
    @FindBy(xpath = "/html/body/div[1]/div/div[3]/div/section/div[2]/div/div/div[2]/div[2]/select[2]/option[3]")
    WebElement thana;
    @FindBy(xpath = "//*[@id=\"receiver-address-input\"]")
    WebElement address;
    @FindBy(xpath = "//*[@id=\"next-btn-shipping\"]")
    WebElement nextB;

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

    public void setRegistrationPage() throws InterruptedException, IOException {
        Actions action = new Actions(PageDriver.getCurrentDriver());
        JavascriptExecutor js = (JavascriptExecutor) PageDriver.getCurrentDriver();
        try {
            test.info("Hover on fullName");
            if (fullName.isDisplayed()){
                action.moveToElement(fullName).build().perform();
                Thread.sleep(3000);
                fullName.sendKeys("Tofayel Ahmed Sajib");
                passCaseWithSC("Clicked fullName", "fullNameClick");
                try{
                    test.info("Hover on emailAdd");
                    if(emailAdd.isDisplayed()){
                        action.moveToElement(emailAdd).build().perform();
                        Thread.sleep(5000);
                        emailAdd.sendKeys("tofayelsajib.aff@gmail.com");
                        passCaseWithSC("Clicked emailAdd", "emailAddClick");
                        try{
                            test.info("Hover on passWord");
                            if(passWord.isDisplayed()){
                                action.moveToElement(passWord).build().perform();
                                Thread.sleep(3000);
                                passWord.sendKeys("Sajib#123456");
                                passCaseWithSC("Clicked passWord", "passWord");
                                try{
                                    test.info("Click on selectDistrict");
                                    if(selectDistrict.isDisplayed()){
                                        action.moveToElement(selectDistrict).build().perform();
                                        Thread.sleep(3000);
                                        selectDistrict.click();
                                        Thread.sleep(3000);
                                        passCaseWithSC("Clicked selectDistrict", "selectDistrictClick");
                                        try{
                                            test.info("Click on district");
                                            if(district.isDisplayed()){
                                                js.executeScript("arguments[0].scrollIntoView(true)", district);
                                                Thread.sleep(3000);
                                                district.click();
                                                Thread.sleep(3000);
                                                passCaseWithSC("Clicked district", "districtClick");
                                                try {
                                                    test.info("Click on policeStation");
                                                    if(policeStation.isDisplayed()) {
                                                        action.moveToElement(policeStation).build().perform();
                                                        Thread.sleep(3000);
                                                        policeStation.click();
                                                        Thread.sleep(3000);
                                                        passCaseWithSC("Clicked policeStation", "policeStationClick");
                                                        try{
                                                            test.info("Click on thana");
                                                            if (thana.isDisplayed()){
                                                                js.executeScript("arguments[0].scrollIntoView(true)", thana);
                                                                Thread.sleep(3000);
                                                                thana.click();
                                                                Thread.sleep(3000);
                                                                passCaseWithSC("Click thana", "thanaClick");
                                                                try{
                                                                    test.info("Click on address");
                                                                    if (address.isDisplayed()){
                                                                        action.moveToElement(address).build().perform();
                                                                        Thread.sleep(3000);
                                                                        address.sendKeys("House# 26, Road No. 3/B, Mohammad Housing Limited, Mohammadpur, Dhaka - 1207.");
                                                                        Thread.sleep(3000);
                                                                        passCaseWithSC("Click address", "addressClick");
                                                                        try{
                                                                            test.info("Click on nextB");
                                                                            if (nextB.isDisplayed()){
                                                                                js.executeScript("arguments[0].scrollIntoView(true)", nextB);
                                                                                Thread.sleep(3000);
                                                                                nextB.click();
                                                                                Thread.sleep(3000);
                                                                                passCaseWithSC("Click nextB", "nextBClick");
                                                                            }
                                                                        }
                                                                        catch (Exception e){
                                                                            failCase("Could not click nextB", "nextBClickFail");
                                                                        }
                                                                    }
                                                                }
                                                                catch (Exception e){
                                                                    failCase("Could not click address", "addressClickFail");
                                                                }
                                                            }
                                                        }
                                                        catch (Exception e){
                                                            failCase("Could not click thana", "thanaClickFail");
                                                        }
                                                    }
                                                }
                                                catch (Exception e) {
                                                    failCase("Cloud not click policeStation", "policeStationClickFail");
                                                }
                                            }
                                        }
                                        catch (Exception e){
                                            failCase("Could not click district", "districtClickFail");
                                        }
                                    }
                                }
                                catch (Exception e){
                                    failCase("Could not click selectDistrict", "selectDistrictClickFail");
                                }
                            }
                        }
                        catch (Exception e){
                            failCase("Could not click passWord", "passWordClickFail");
                        }
                    }
                }
                catch (Exception e){
                    failCase("Could not click emailAdd", "emailAddClickFail");
                }
            }
        }
        catch (Exception e){
            failCase("Could not click fullName", "fullNameClickFail");
        }

    }
}
