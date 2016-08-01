package ru.stqa.selenium;

import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
//import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;


public class DelMovieNo extends TestNgTestBase{

	//Setting menu into required value
public void setMenu(String elementId, String visibleText) {
	WebElement menu=driver.findElement(By.id(elementId));
	new Select(menu).selectByVisibleText(visibleText);
}

  
  @Test
  public void testDelMovieNo() throws Exception {

    int count_start=0, count_finish=0; // counters of movies before and after adding

   
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
    
    // Select first movie on home page
    WebElement anymovie = driver.findElement(By.xpath("//a[contains(@href,'go=movie')]//div"));

    anymovie.click(); /*Open movie page*/
    
    //Click Remove
    driver.findElement(By.cssSelector("img[alt=\"Remove\"]")).click();
    
    //Click OK
    driver.switchTo().alert().dismiss();

    driver.findElement(By.linkText("Home")).click(); /* Go to home page */

// Select lists to show all movies on one page     
    //menu1=driver.findElement(By.id("category"));
    //menu2=driver.findElement(By.id("n"));

    //new Select(menu1).selectByVisibleText("All categories");
    //new Select(menu2).selectByVisibleText("All results per page");
    
    setMenu("category","All categories"); //select show all categories movies 
    setMenu("n","All results per page"); //select show all movies on page 

    // count quantity of movies after adding
    count_finish = driver.findElements(By.xpath("//a[contains(@href,'go=movie')]")).size();

    Assert.assertTrue(count_start==count_finish);     //Check counts 
    
 }

}
