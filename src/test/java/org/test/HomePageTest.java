package org.test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.basedriver.BaseDriver;
import org.basedriver.PageDriver;
import org.pagedriver.HomePage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.utilities.ExtentFactory;

import java.io.IOException;

public class HomePageTest extends BaseDriver {


    ExtentReports report;
    ExtentTest parentTest;
    ExtentTest childTest;

    @BeforeClass
    public void start() throws InterruptedException {
        PageDriver.getCurrentDriver().get(url);
        Thread.sleep(5000);
        report = ExtentFactory.getInstance();
        parentTest = report.createTest("<p style=\"color:#FF6000; font-size:20px\"><b>HomePage</b></p>").assignAuthor("QA").assignDevice("windows");
    }
    @Test
    public void homeTest() throws InterruptedException, IOException {
        childTest = parentTest.createNode("<p style=\"color:#3E96E7; font-size:20px\"><b>HOMEPAGE TEST</b></p>");
        HomePage homePage = new HomePage(childTest);
        homePage.setWriter();

    }
    @AfterClass
    public void report() {
        report.flush();
    }

}
