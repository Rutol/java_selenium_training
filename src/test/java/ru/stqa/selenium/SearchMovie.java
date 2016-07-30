package ru.stqa.selenium;

import org.testng.annotations.*;
import org.openqa.selenium.*;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class SearchMovie extends TestNgTestBase{


  @Test
  public void testSearchMovie() throws Exception {

	  int count_exist=0, count_notexist=0;
// creating report file  
    PrintWriter repfile = null;
         try
         {
            repfile = new PrintWriter(new FileOutputStream("Search_rep.txt"));
         }
         catch(FileNotFoundException e)
         {
             System.out.println("Ошибка открытия файла Search_rep.txt");
             System.exit(0);
         }    

	  
	// Login block    
	driver.get(baseUrl + "/php4dvd/");
	driver.findElement(By.id("username")).clear();
	driver.findElement(By.id("username")).sendKeys("admin");
	driver.findElement(By.name("password")).clear();
	driver.findElement(By.name("password")).sendKeys("admin");
	driver.findElement(By.name("submit")).click();    
	    
	driver.findElement(By.linkText("Home")).click();  /* Go to home page */
	
	//Search existing movie
	driver.findElement(By.id("q")).clear();
    driver.findElement(By.id("q")).sendKeys("best"); //
    driver.findElement(By.id("q")).sendKeys(Keys.RETURN);

    // Waiting results
    WebDriverWait wait = new WebDriverWait(driver, 2);
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("results")));    
    Assert.assertTrue(driver.findElement(By.id("results")).findElements(By.tagName("a")).size() > 0);
    
    count_exist = driver.findElements(By.xpath("//a[contains(@href,'go=movie')]")).size();

	//Search not existing movie
	driver.findElement(By.id("q")).clear();
    driver.findElement(By.id("q")).sendKeys("ddddd");
    driver.findElement(By.id("q")).sendKeys(Keys.RETURN);

    // Waiting results
    wait = new WebDriverWait(driver, 2);
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("results")));    
    Assert.assertTrue(driver.findElement(By.id("results")).findElements(By.tagName("a")).size() == 0);
    
    count_notexist = driver.findElements(By.xpath("//a[contains(@href,'go=movie')]")).size();

    //Check counts and print result into report file
    if ((count_exist>0)&&(count_notexist==0)) {
    	repfile.println("Correct search movie Test\n");
    	repfile.println(count_exist+"\n");
    	repfile.println(count_notexist+"\n");
    	repfile.close();
    	}
    else { 
    	repfile.println("Incorrect search movie \n");
    	repfile.println(count_exist+"\n");
    	repfile.println(count_notexist+"\n");
    	repfile.close();
    	}

  }

}
