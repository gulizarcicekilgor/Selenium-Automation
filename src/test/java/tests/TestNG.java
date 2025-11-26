package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*; // 🔴 TestNG

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class TestNG {

    WebDriver driver; // 🔴 instance variable

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown() {
       // if(driver != null) driver.quit();
    }

    @Test(priority = 1)
    public void textBoxTest() {
        driver.get("https://demoqa.com/text-box");
        driver.findElement(By.id("userName")).sendKeys("Gülizar");
        driver.findElement(By.id("userEmail")).sendKeys("gulizarciek@gmail.com");
        driver.findElement(By.id("currentAddress")).sendKeys("İzmir Buca");
        driver.findElement(By.id("permanentAddress")).sendKeys("Yozgat Merkez");
        driver.findElement(By.id("submit")).click();
        System.out.println(driver.findElement(By.id("name")).getText());
    }

    @Test(priority = 2)
    public void checkboxTest() {
        driver.get("https://demoqa.com/checkbox");
        WebElement homeCheckbox = driver.findElement(By.cssSelector("label[for='tree-node-home'] span.rct-checkbox svg"));
        homeCheckbox.click();
        String homeCheckboxClassName = homeCheckbox.getAttribute("class");
        System.out.println(homeCheckboxClassName.equals("rct-icon rct-icon-check") ? "Checked" : "Not checked");
    }

    @Test(priority = 3)
    public void alertTests() {
        driver.get("https://demoqa.com/alerts");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.findElement(By.id("alertButton")).click();
        wait.until(ExpectedConditions.alertIsPresent()).accept();

        driver.findElement(By.id("timerAlertButton")).click();
        wait.until(ExpectedConditions.alertIsPresent()).accept();

        driver.findElement(By.id("confirmButton")).click();
        wait.until(ExpectedConditions.alertIsPresent()).dismiss();

        driver.findElement(By.id("promtButton")).click();
        Alert prompt = wait.until(ExpectedConditions.alertIsPresent());
        prompt.sendKeys("TestNG Test");
        prompt.accept();
    }

    @Test(priority = 4)
    public void tabTests() throws InterruptedException {
        driver.get("https://demoqa.com/browser-windows");
        driver.findElement(By.id("tabButton")).click();
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        System.out.println(driver.getCurrentUrl());
        driver.close();
        driver.switchTo().window(tabs.get(0));
    }

    @Test()
    public void Frames() {
        driver.get("https://demoqa.com/frames");
        driver.switchTo().frame("frame1");

        String pragraph = driver.findElement(By.id("sampleHeading")).getText();
        System.out.println(pragraph);


    }
}
