package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test1 {

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();


        // Text Box testini çalıştır
        //textBoxTest(driver);

        // Checkbox testini çalıştır
        checkboxTest(driver);
    }

    // 🧩 1. TextBox Testi
    public static void textBoxTest(WebDriver driver) {
        driver.get("https://demoqa.com/text-box");
        WebElement username = driver.findElement(By.id("userName"));
        username.sendKeys("Gülizar");

        WebElement userEmail = driver.findElement(By.cssSelector("input#userEmail"));
        userEmail.sendKeys("gulizarciek@gmail.com");

        WebElement currentAddress = driver.findElement(By.cssSelector("textarea#currentAddress"));
        currentAddress.sendKeys("İzmir Buca");

        WebElement permanentAddress = driver.findElement(By.id("permanentAddress"));
        permanentAddress.sendKeys("Yozgat Merkez");

        WebElement submitBtn = driver.findElement(By.xpath("//button[@id='submit']"));
        submitBtn.click();

        System.out.println(driver.findElement(By.id("name")).getText());
        System.out.println(driver.findElement(By.id("email")).getText());
    }

    // 🧩 2. Checkbox Testi
    public static void checkboxTest(WebDriver driver) {
        driver.get("https://demoqa.com/checkbox");

        // Checkbox sayfasındaki ana checkbox'ı bul ve tıkla

        String homeCheckBoxCssValue = "label[for='tree-node-home'] span.rct-checkbox svg";
        WebElement homeCheckbox = driver.findElement(new By.ByCssSelector(homeCheckBoxCssValue));
        homeCheckbox.click();

        homeCheckbox = driver.findElement(new By.ByCssSelector(homeCheckBoxCssValue));

        //Checkbox’ın işaretli olup olmadığını HTML class değerine bakarak anlamak.
        String homeCheckboxClassName = homeCheckbox.getAttribute("class");

        if (homeCheckboxClassName.equals("rct-icon rct-icon-check"))
        {
            System.out.println("Checkbox is checked");
        }
        else{
            System.out.println("Checkbox is not checked");
        }

    }
}