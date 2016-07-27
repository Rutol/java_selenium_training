package ru.stqa.selenium;

import org.testng.annotations.*;
import org.openqa.selenium.*;


public class Addmovie extends TestNgTestBase{

	//LoginTest lt = new LoginTest();
  @Test
  
  public void testAddmovie() throws Exception {
	//lt.testLogin();
	  
    //driver.get(baseUrl + "/php4dvd/#!/search/test/sort/name%20asc/");
    int count_start=0, count_finish=0;
    
    driver.get(baseUrl + "/php4dvd/");
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("admin");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("admin");
    driver.findElement(By.name("submit")).click();    
    
    count_start = driver.findElements(By.xpath("//a[contains(@href,'go=movie')]")).size();
    String namepart=Integer.toString(count_start);
    
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
    
    count_finish = driver.findElements(By.xpath("//a[contains(@href,'go=movie')]")).size();
    if ((count_finish-count_start)==1) { System.out.println("Ok"); }
    else { System.out.println("Error"); }
  }


}
