package ru.stqa.selenium;

import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class SearchMovie extends TestNgTestBase{


  @Test
  public void testSearchMovie() throws Exception {

	//  int count_exist=0, count_notexist=0;

	  
	// Login block    
	driver.get(baseUrl + "/php4dvd/");
	driver.findElement(By.id("username")).clear();
	driver.findElement(By.id("username")).sendKeys("admin");
	driver.findElement(By.name("password")).clear();
	driver.findElement(By.name("password")).sendKeys("admin");
	driver.findElement(By.name("submit")).click();    
	    
	driver.findElement(By.linkText("Home")).click();  /* Go to home page */
	
	//Add movie block    
    driver.findElement(By.cssSelector("img[alt=\"Add movie\"]")).click();
    driver.findElement(By.name("imdbid")).clear();
    driver.findElement(By.name("imdbid")).sendKeys("6");
    driver.findElement(By.name("name")).clear();
    driver.findElement(By.name("name")).sendKeys("Abra-Kadabra");
    driver.findElement(By.name("year")).clear();
    driver.findElement(By.name("year")).sendKeys("1971");
    driver.findElement(By.id("text_languages_0")).clear();
    driver.findElement(By.id("text_languages_0")).sendKeys("Russian");
    driver.findElement(By.name("country")).clear();
    driver.findElement(By.name("country")).sendKeys("USSR");
    driver.findElement(By.id("submit")).click();
	
    driver.findElement(By.linkText("Home")).click();  /* Go to home page */

 // Select lists to show all movies on one page      
    WebElement menu1=driver.findElement(By.id("category"));
    WebElement menu2=driver.findElement(By.id("n"));

    new Select(menu1).selectByVisibleText("All categories");
    new Select(menu2).selectByVisibleText("All results per page");

	//Search existing movie
	driver.findElement(By.id("q")).clear();
    driver.findElement(By.id("q")).sendKeys("Abra"); 
    driver.findElement(By.id("q")).sendKeys(Keys.RETURN);

    // Waiting results
    WebDriverWait wait = new WebDriverWait(driver, 2);
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("results")));    
    Assert.assertTrue(driver.findElement(By.id("results")).findElements(By.tagName("a")).size() > 0);
    
    //Search not existing movie
	driver.findElement(By.id("q")).clear();
    driver.findElement(By.id("q")).sendKeys("ddddd");
    driver.findElement(By.id("q")).sendKeys(Keys.RETURN);

    // Waiting results
    wait = new WebDriverWait(driver, 2);
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("results")));    
    Assert.assertTrue(driver.findElement(By.id("results")).findElements(By.tagName("a")).size() == 0);
    
  }

}
