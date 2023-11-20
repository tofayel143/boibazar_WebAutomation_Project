package org.test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.basedriver.BaseDriver;
//import org.basedriver.PageDriver;
import org.pagedriver.BillingCart;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.utilities.ExtentFactory;

import java.io.IOException;

public class BillingCartTest extends BaseDriver {


    ExtentReports report;
    ExtentTest parentTest;
    ExtentTest childTest;

    @BeforeClass
    public void start() throws InterruptedException {
//        PageDriver.getCurrentDriver().get(url);
//        Thread.sleep(5000);
        report = ExtentFactory.getInstance();
        parentTest = report.createTest("<p style=\"color:#FF6000; font-size:20px\"><b>BillingCart</b></p>").assignAuthor("QA").assignDevice("windows");
    }
    @Test
    public void billingCartTest() throws InterruptedException, IOException {
        childTest = parentTest.createNode("<p style=\"color:#3E96E7; font-size:20px\"><b>BILLING CART TEST</b></p>");
        BillingCart billingCart = new BillingCart(childTest);
        billingCart.billingCartBtn();

    }
    @AfterClass
    public void report() {
        report.flush();
    }

}
