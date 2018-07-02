package tatocUsinfXpath;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;


public class Tatoc 
{
	public static void main(String args[])
	{
		WebDriver driver = new ChromeDriver();
		
		//System.setProperty("webdriver.chrome.driver", "D:\\Tools/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\karan\\Desktop\\chromedriver.exe");
		
		// LOAD WEBSITE
		driver.get("http://10.0.1.86/tatoc");
		
		// CLICK ON BASIC COURSE
		driver.findElement(By.linkText("Basic Course")).click();
		
		// CLICK ON GREENBOX
		driver.findElement(By.className("greenbox")).click();

		// FRAME 
		 driver.switchTo().frame("main");
		 String box1 = driver.findElement(By.id("answer")).getAttribute("class");
		 
		 while(true)
		 {
			 driver.findElement(By.xpath("/html/body/center/a[1]")).click();
			 driver.switchTo().frame("child");
			 String box2 = driver.findElement(By.id("answer")).getAttribute("class");
			 
			 driver.switchTo().parentFrame();
			 if(box1.equals(box2))
			 {
				 
				 driver.findElement(By.xpath("/html/body/center/a[2]")).click();
				 break;
			 }
		 }
		
		 // DRAG AROUND
		 
		   		
     WebElement From = driver.findElement(By.xpath("//*[@id=\"dragbox\"]"));		//Element which needs to drag. 
     WebElement To = driver.findElement(By.xpath("//*[@id=\"dropbox\"]"));		 //Element on which need to drop.			
     Actions act=new Actions(driver);			 //Using Action class for drag and drop.				
     act.dragAndDrop(From, To).build().perform();		//Dragged and dropped.
     driver.findElement(By.xpath("/html/body/div/div[2]/a")).click();
      
     // POPUP WINDOW
     driver.findElement(By.xpath("/html/body/div/div[2]/a[1]")).click();
     String parentWindowHandler = driver.getWindowHandle(); // Store your parent window
     String subWindowHandler = null;

     Set<String> handles = driver.getWindowHandles(); // get all window handles
     Iterator<String> iterator = handles.iterator();
     while (iterator.hasNext()){
         subWindowHandler = iterator.next();
     }
     driver.switchTo().window(subWindowHandler);
     
     WebElement name = driver.findElement(By.id("name"));
     name.sendKeys("Akash");
     driver.findElement(By.id("submit")).click();
     driver.switchTo().window(parentWindowHandler); 
     driver.findElement(By.xpath("/html/body/div/div[2]/a[2]")).click();
      
     // COOKIE HANDLING
     
     driver.findElement(By.xpath("/html/body/div/div[2]/a[1]")).click();
     String token = driver.findElement(By.xpath("//*[@id=\"token\"]")).getText().split("Token: ")[1];
     //System.out.println(token);
     driver.manage().addCookie( new Cookie("Token", token, "/")); 
     
     System.out.println(driver.manage().getCookies());
     
     
     driver.findElement(By.xpath("/html/body/div/div[2]/a[2]")).click();
	    
    		 
     
      
		
		
		
		
		
		
		
		
		//driver.close();
		
		
	}

}