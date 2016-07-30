package ru.stqa.selenium;

import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;

public class Addmovie extends TestNgTestBase{

  @Test
  
  public void testAddmovie() throws Exception {

  int count_start=0, count_finish=0;  // counters of movies before and after adding

// creating report file  
    PrintWriter repfile = null;
         try
         {
            repfile = new PrintWriter(new FileOutputStream("Admovie_rep.txt"));
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

// Select lists to show all movies on one page     
    menu1=driver.findElement(By.id("category"));
    menu2=driver.findElement(By.id("n"));

    new Select(menu1).selectByVisibleText("All categories");
    new Select(menu2).selectByVisibleText("All results per page");

    // count quantity of movies after adding
    count_finish = driver.findElements(By.xpath("//a[contains(@href,'go=movie')]")).size();
    
    //Check counts and print result into report file
    if ((count_finish-count_start)==1) {
    	repfile.println("Correct adding movie Test" + namepart+"\n"); 
    	repfile.close();
    	}
    else { 
    	repfile.println("Incorrect adding movie Test" + namepart+"\n");
    	repfile.close();
    	}
  }


}
