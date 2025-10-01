package tests;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.xml.xpath.XPath;

public class Test1 {
    public static void main(String[] args) {
        //System.setProperty("chromeDriver","/drivers/chromedriver"); pom xml iile ayarladımbuna gerek yok
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(); // driver objesi


        driver.get("https://demoqa.com/text-box");
        driver.manage().window().maximize(); //

        WebElement username = driver.findElement(By.id("userName"));
        username.click();
        username.sendKeys("Gülizar");

        WebElement userEmail= driver.findElement(By.cssSelector("input#userEmail"));
        userEmail.click();
        userEmail.sendKeys("gulizarciek@gmail.com");

        WebElement currentAddress = driver.findElement(By.cssSelector("textarea#currentAddress"));
        currentAddress.click();//
        currentAddress.sendKeys("İzmir Buca");

        WebElement permanentAddress = driver.findElement(By.id("permanentAddress"));
        permanentAddress.click();
        permanentAddress.sendKeys("Yozgat Merkez");

        WebElement submitBtn = driver.findElement(By.xpath("//button[@id='submit']"));
        submitBtn.click();


        WebElement getname = driver.findElement(By.xpath("//div/p[@id='name']"));
        System.out.println(getname.getText());





    }


}
