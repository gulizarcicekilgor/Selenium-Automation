package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*; // 🔴 TestNG

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class TestNG {

    WebDriver driver; //  instance variable

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

        WebElement heading = driver.findElement(By.id("sampleHeading"));
        String text = heading.getText();
        System.out.println(text);

        driver.switchTo().parentFrame();
        List<WebElement> elementList = driver.findElements(By.cssSelector("div[id='framesWrapper'] div"));
        String paragraph = elementList.get(0).getText();
        System.out.println(paragraph);

        driver.switchTo().frame("frame2");

        WebElement heading2 = driver.findElement(By.id("sampleHeading"));
        String text2 = heading2.getText();
        System.out.println(text2);
    }
    @Test
    public void NestedFrame() throws InterruptedException {
        //for ads
        driver.get("https://demoqa.com/nestedframes");
        Thread.sleep(5000);
        WebElement adFrame = driver.findElement(By.cssSelector("iframe[title='3rd party ad content']"));
        driver.switchTo().frame(adFrame);

        WebElement closeBtn = driver.findElement(By.id("cbb"));
        closeBtn.click();

    }
    @Test
    public void NestedFrame2()  {
        driver.get("https://demoqa.com/nestedframes");
        driver.switchTo().frame("frame1");
        WebElement body = driver.findElement(By.tagName("body"));
        System.out.println(body.getText());
        driver.switchTo().frame(0);
        WebElement p = driver.findElement(By.tagName("p"));
        System.out.println(p.getText());

        driver.switchTo().parentFrame();
        driver.switchTo().parentFrame();
        WebElement parentText = driver.findElement(By.cssSelector("#framesWrapper > div:first-of-type"));
        System.out.println(parentText.getText());

    }
    @Test
    public void Accordians() throws InterruptedException {
        //sayfa ik açıldığında ilk accordian açık mı kontrolü
        driver.get("https://demoqa.com/accordian");
        WebElement accordianfirst = driver.findElement(By.id("section1Content")).findElement(By.xpath("./parent::div"));
        String classValue = accordianfirst.getAttribute("class");
        Assert.assertTrue(classValue.contains("show"),"First accordian should be open by default");
       // System.out.println(classValue);

        /*
        //collapsing anını yaklamak için
        driver.findElement(By.id("section1Heading")).click();
        classValue = accordianfirst.getAttribute("class");
        System.out.println(classValue); */

        // refreh yapınca acordion açık mı kontrolü
        driver.navigate().refresh();
        WebElement afterRefreh = driver.findElement(By.id("section1Content")).findElement(By.xpath("./parent::div"));
        //System.out.println(afterRefreh.getAttribute("class"));
        Assert.assertTrue(classValue.contains("show"),"After refresh, accordian should be open by default");
        //System.out.println(classValue);


        //ikinci accordiana tıklayınca birinci kapanıyor mu
        driver.findElement(By.id("section2Heading")).click();
        Thread.sleep(5000);
        WebElement firstCollapse = driver.findElement(By.id("section1Content")).findElement(By.xpath("./parent::div"));

        WebElement secondCollapse = driver.findElement(By.id("section2Content")).findElement(By.xpath("./parent::div"));

        //1. kapalı olmalı
        Assert.assertFalse(firstCollapse.getAttribute("class").contains("show"), "First accordian should be closed");
        //ikinci açık olmalı
        Assert.assertTrue(secondCollapse.getAttribute("class").contains("show"), "Second accordian should be open");

    }
}
