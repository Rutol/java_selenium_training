package ru.stqa.selenium;

import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;

public class DelMovie extends TestNgTestBase{
	
  @Test
  public void testDelMovie() throws Exception {

    int count_start=0, count_finish=0; // counters of movies before and after adding

 // creating report file  
    PrintWriter repfile = null;
         try
         {
            repfile = new PrintWriter(new FileOutputStream("Delmovie_rep.txt"));
         }
         catch(FileNotFoundException e)
         {
             System.out.println("Ошибка открытия файла Admovie_rep.txt");
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

 // Select lists to show all movies on one page     
    WebElement menu1=driver.findElement(By.id("category"));
    WebElement menu2=driver.findElement(By.id("n"));

    new Select(menu1).selectByVisibleText("All categories");
    new Select(menu2).selectByVisibleText("All results per page");
    
    // count quantity of movies before adding
    count_start = driver.findElements(By.xpath("//a[contains(@href,'go=movie')]")).size();
    
    // Select first movie on home page
    WebElement anymovie = driver.findElement(By.xpath("//a[contains(@href,'go=movie')]//div"));

    anymovie.click(); /*Open movie page*/
    
    //Click Remove
    driver.findElement(By.cssSelector("img[alt=\"Remove\"]")).click();
    
    //Click OK
    driver.switchTo().alert().accept();

    driver.findElement(By.linkText("Home")).click(); /* Go to home page */

// Select lists to show all movies on one page     
    menu1=driver.findElement(By.id("category"));
    menu2=driver.findElement(By.id("n"));

    new Select(menu1).selectByVisibleText("All categories");
    new Select(menu2).selectByVisibleText("All results per page");

    // count quantity of movies after adding
    count_finish = driver.findElements(By.xpath("//a[contains(@href,'go=movie')]")).size();

    //Check counts and print result into report file
    if ((count_start-count_finish)==1) {
    	repfile.println("Correct deleting movie\n"); 
    	repfile.close();
    	}
    else { 
    	repfile.println("Incorrect deleting movie\n");
    	repfile.close();
    	}
    
 }

}
