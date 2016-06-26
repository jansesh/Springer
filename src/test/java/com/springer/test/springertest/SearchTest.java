package com.springer.test.springertest;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import junit.framework.Assert;

public class SearchTest extends Env {
	
public WebDriver driver;
 

    @Before
    public void openBrowser(){
        driver = (WebDriver) new FirefoxDriver();
    
        driver.get(ENV_URL);
        driver.manage().window().maximize();

		 /*
          * Below line is called implicit wait
		  *
		  * this is used to wait for element for 30 sec before it throws any exceptions
		  * Eg:
		  * if any element is located with in 2 sec time, it will skip and continues execution
		  * in the above example we can save 28sec for one locator
		  */
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @After
    public void closeBrowser(){
        driver.quit();
    }
//Basic Search functionality with filters and the results for the discipline science.
    @Test
    public void searchTest(){
    	
    	String expectedResult="30 Result(s) for 'science'";
        driver.findElement(By.xpath("//input[@id='query']")).sendKeys("science");
        driver.findElement(By.xpath("//input[@id='search']")).click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.xpath("html/body/div[5]/div[2]/div[3]/div[2]/div[2]/ol/li[7]/a/span[2]")).click();
        driver.findElement(By.xpath("html/body/div[5]/div[2]/div[3]/div[2]/div[3]/ol/li[1]/a/span[2]")).click();
        driver.findElement(By.xpath("html/body/div[5]/div[2]/div[3]/div[2]/div[4]/ol/li[1]/a/span[2]")).click();
        driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
        driver.findElement(By.xpath("html/body/div[5]/div[2]/div[3]/div[2]/div[5]/ol/li[1]/a/span[2]")).click();
        String actualResult = driver.findElement(By.xpath("html/body/div[5]/div[2]/div[3]/div[1]/div[1]/h1")).getText();
        System.out.println("Actual result :" +actualResult);
        System.out.println("ExpectedResult: " +expectedResult);
        Assert.assertTrue(actualResult.contains(expectedResult));
        driver.manage() .timeouts().implicitlyWait(40,TimeUnit.SECONDS);
    }
}
