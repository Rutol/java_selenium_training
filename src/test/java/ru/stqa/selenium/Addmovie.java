package ru.stqa.selenium;

import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class Addmovie extends TestNgTestBase{

	//Setting menu into required value
public void setMenu(String elementId, String visibleText) {
	WebElement menu=driver.findElement(By.id(elementId));
	new Select(menu).selectByVisibleText(visibleText);
}

  @Test
  
  public void testAddmovie() throws Exception {

  int count_start=0, count_finish=0;  // counters of movies before and after adding

    
// Login block    
    driver.get(baseUrl + "/php4dvd/");
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("admin");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("admin");
    driver.findElement(By.name("submit")).click();    
    
    driver.findElement(By.linkText("Home")).click();  /* Go to home page */

    setMenu("category","All categories"); //select show all categories movies 
    setMenu("n","All results per page"); //select show all movies on page 
    
    // count quantity of movies before adding
    count_start = driver.findElements(By.xpath("//a[contains(@href,'go=movie')]")).size();
    String namepart=Integer.toString(count_start);

//Add movie block    
    driver.findElement(By.cssSelector("img[alt=\"Add movie\"]")).click();
    driver.findElement(By.name("imdbid")).clear();
    driver.findElement(By.name("imdbid")).sendKeys("6");
    driver.findElement(By.name("name")).clear();
    driver.findElement(By.name("name")).sendKeys("Test "+namepart);
    driver.findElement(By.name("year")).clear();
    driver.findElement(By.name("year")).sendKeys("1971");
    driver.findElement(By.id("text_languages_0")).clear();
    driver.findElement(By.id("text_languages_0")).sendKeys("Russian");
    driver.findElement(By.name("country")).clear();
    driver.findElement(By.name("country")).sendKeys("USSR");
    driver.findElement(By.id("submit")).click();
    
    driver.findElement(By.linkText("Home")).click(); /* Go to home page */

    setMenu("category","All categories"); //select show all categories movies 
    setMenu("n","All results per page"); //select show all movies on page 

    // count quantity of movies after adding
    count_finish = driver.findElements(By.xpath("//a[contains(@href,'go=movie')]")).size();
   
    
    Assert.assertTrue((count_finish-count_start)==1); 

  }


}
