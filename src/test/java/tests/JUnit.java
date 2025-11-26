package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*; // 🔴 JUnit
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JUnit {

    static WebDriver driver; // 🔴 static, @BeforeAll ve @AfterAll için

    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterAll
    public static void tearDown() {
        if(driver != null) driver.quit();
    }

    @Test
    @Order(1)
    public void textBoxTest() {
        driver.get("https://demoqa.com/text-box");
        driver.findElement(By.id("userName")).sendKeys("Gülizar");
        driver.findElement(By.id("userEmail")).sendKeys("gulizarciek@gmail.com");
        driver.findElement(By.id("currentAddress")).sendKeys("İzmir Buca");
        driver.findElement(By.id("permanentAddress")).sendKeys("Yozgat Merkez");
        driver.findElement(By.id("submit")).click();
        System.out.println(driver.findElement(By.id("name")).getText());
    }

    @Test
    @Order(2)
    public void checkboxTest() {
        driver.get("https://demoqa.com/checkbox");
        WebElement homeCheckbox = driver.findElement(By.cssSelector("label[for='tree-node-home'] span.rct-checkbox svg"));
        homeCheckbox.click();
        String homeCheckboxClassName = homeCheckbox.getAttribute("class");
        System.out.println(homeCheckboxClassName.equals("rct-icon rct-icon-check") ? "Checked" : "Not checked");
    }
    //isEnabled
    @Test
    @Order(3)
    public void formCheckboxTest() {
        driver.get("https://demoqa.com/automation-practice-form");
        //isAEnabled kontrolü
        WebElement sportCheckbox = driver.findElement(By.id("hobbies-checkbox-1"));
        boolean isEnabledCheck = sportCheckbox.isEnabled(); // true ya da false döner. boolen değerdir. checkboz'ın tıklanır olup oladığını kontrol eder.

        WebElement SportsCheckboxLabel = driver.findElement(By.xpath("//label[@for='hobbies-checkbox-1']"));

        if(isEnabledCheck) {
            try {
                sportCheckbox.click();
            }catch (Exception e) {
                SportsCheckboxLabel.click();
                System.out.println("Entered catch block");
            }
        }
        boolean isSelectedCheck = sportCheckbox.isSelected();
        System.out.println("isSelectted");
    }
    //RadioButton
    @Test
    public void RadioButtonTest() {
        driver.get("https://demoqa.com/radio-button");

        //enabled -disabled
        // şeçili seçili değil mi
        WebElement yesRadioButton = driver.findElement(By.xpath("//label[@for='yesRadio']"));
        //tıklanır mı değil mi buton
        boolean isEnabled = yesRadioButton.isEnabled();
        if(isEnabled){
            yesRadioButton.click();
            System.out.println("Radio button is enabled");
        }
        else{
            System.out.println("Radio button is not enabled");
        }
        //getText() method
        WebElement yesRadioText= driver.findElement(By.xpath("//p[@class='mt-3']"));
        System.out.println(yesRadioText.getText());


        // tıklanır olmayan radio butonun kontrolü
        WebElement noRadioBtn = driver.findElement(By.xpath("//input[@id='noRadio']"));

        if(noRadioBtn.isEnabled()){
            noRadioBtn.click();
            System.out.println("Radio button is enabled and clicked");
        } else {
            System.out.println("Radio button is not enabled");
        }

        //branch açma / maini stabil tutma
        System.out.println("main update");

        //label[@for='noRadio']


    }
    @Test
    public void ClickTests(){
        driver.get("https://demoqa.com/buttons");
        WebElement doubleBtn = driver.findElement(By.xpath("//button[@id='doubleClickBtn']"));
        // doubleclick için action class'ından yararlanmak gerekiyor
        Actions actions = new Actions(driver);
        actions.doubleClick(doubleBtn).perform();

        WebElement getMessageDouble= driver.findElement(By.xpath("//p[@id='doubleClickMessage']"));
        String messageDouble = getMessageDouble.getText();
        System.out.println(messageDouble);

        WebElement rightBtn = driver.findElement(By.xpath("//button[@id='rightClickBtn']"));
        // contextClick(right click için action class'ından yararlanmak gerekiyor
        actions.contextClick(rightBtn).perform();

        WebElement getMessageRight= driver.findElement(By.xpath("//p[@id='rightClickMessage']"));
        String messageRight = getMessageRight.getText();
        System.out.println(messageRight);

        WebElement dymnmicBtn= driver.findElement(By.xpath("//button[text()='Click Me']"));
        dymnmicBtn.click();
    }
    @Test
    public void DynamicPropertiesTests()
    {
        // bu fonksiyonda yapılan buton kontrolleri için en başında yapılan wait işlemi dikkate alınmalı
        driver.get("https://demoqa.com/dynamic-properties");
        //dynamic id olduğundan Xpath ile elemet locate ediyoruz
        WebElement dynamicID = driver.findElement(By.xpath("//div/p"));
        String textdynamicID= dynamicID.getText();
        System.out.println("textdynamicIDText: "+textdynamicID);


        //5 saniye sonra enable olan buton için (buton hep visible)
        WebElement enableAfterButton = driver.findElement(By.xpath("//button[@id='enableAfter']"));
        // Explicit Wait: max 7 saniye bekle, selenium her saniye kontrol eder. bu sadece üst sınır
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        // Butonun tıklanır olduğu an işlemi yap
        wait.until(ExpectedConditions.elementToBeClickable(enableAfterButton));
        enableAfterButton.click();


        //5 saniye sonra class name değişiyor
        WebElement colorChangeButton = driver.findElement(By.id("colorChange"));
        String colorClassName = colorChangeButton.getAttribute("class");
        System.out.println("BeforecolorClass: "+ colorClassName);    // BEfore className i yukardaki wait işlemlerinin üstüne taşıyıp çalıştır.
        wait.until(ExpectedConditions.attributeToBe(colorChangeButton, "class", "mt-4 text-danger btn btn-primary"));
        colorClassName = colorChangeButton.getAttribute("class");
        System.out.println("AftercolorClass: "+ colorClassName);

        // 5 sn sonra görünür olan buton için
        WebDriverWait wait3 =new WebDriverWait(driver, Duration.ofSeconds(7));
        wait3.until(ExpectedConditions.visibilityOfElementLocated(By.id("visibleAfter")));
        driver.findElement(By.id("visibleAfter")).click();



    }
    @Test
    public void BrokenLinkTest() {
        driver.get("https://demoqa.com/broken");
        for(WebElement linkElement : driver.findElements(By.tagName("a")))
        {
            String href = linkElement.getAttribute("href");
            System.out.println("konrol edilen: "+href);
            if(href==null || href.isEmpty()){
                System.out.println(" → Geçersiz link (href yok)");
                continue;
            }
            try {
                HttpURLConnection urlConnection = (HttpURLConnection) new URL(href).openConnection();
                urlConnection.connect();
                int statusCode = urlConnection.getResponseCode();
                if(statusCode>=400){
                    System.out.println("Broken link: "+statusCode);

                }
                else {
                    System.out.println("Valid link: "+statusCode);

                }

            } catch (Exception e) {
                System.out.println("Hata" + e.getMessage());
            }
        }
    }
    @Test
    public void BrokenLinkTest2() {
        driver.get("https://demoqa.com/broken");
        try {
            // VALID link
            String validUrl = driver.findElement(By.linkText("Click Here for Valid Link")).getAttribute("href");
            HttpURLConnection c1 = (HttpURLConnection) new URL(validUrl).openConnection();
            c1.connect();
            System.out.println(" valid link → " + validUrl + " → Kod: " + c1.getResponseCode());


        } catch (Exception e) {
            e.getMessage();
        }
        try {
            String brokenUrl =driver.findElement(By.linkText("Click Here for Broken Link")).getAttribute("href");
            HttpURLConnection c2 = (HttpURLConnection) new URL(brokenUrl).openConnection();
            c2.connect();
            System.out.println(" broken link → " + brokenUrl + " → Kod: " + c2.getResponseCode());
        }catch (Exception e){
            e.getMessage();}



    }
    @Test
    public void BrokenLinkImage() {

        driver.get("https://demoqa.com/broken");
        // image elementini buluyorum
        WebElement image = driver.findElement(By.xpath("//img[@src='/images/Toolsqa_1.jpg']"));
        //Driver'ı JavaStript çalıştırabilir hale getirdim
        JavascriptExecutor js = (JavascriptExecutor)  driver;

        // ardından JavaScript ile resmin width height değlerlerine bakıyorum.(0 a eşitlerse resim broken

        Boolean imageDisplayed = (Boolean) js.executeScript("return arguments[0].naturalWidth>0 && arguments[0].naturalHeight>0;", image);
        if (imageDisplayed){
            System.out.println("imageDisplayed is not broken");}
        else {
            System.out.println("imageDisplayed is broken");}



    }
    @Test
    public void DownloadFileTest() throws InterruptedException {
        driver.get("https://demoqa.com/upload-download");
        WebElement downloadButton=driver.findElement(By.id("downloadButton"));
        downloadButton.click();

        String folderpath = "C:/Users/guliz/Downloads/";
        String fileName= "sampleFile.jpeg";

        Thread.sleep(10000);

        File file = new File(folderpath);
        File[] files = file.listFiles();

        boolean foundFile = false;
        for (int j = 0; j < files.length; j++) {
            if (files[j].getName().equals(fileName)) {
                files[j].delete(); // normalde file.delete yapmıştın ve dosyayı sildirememiştin. önemli nokta!!!
                System.out.println("file found");
                foundFile = true;

                break;

            }
        }
        if(!foundFile)
        {
            System.out.println("file does not exist");

        }


    }
    @Test
    public void UploadFileTest()
    {
        driver.get("https://demoqa.com/upload-download");
        WebElement uploadButton=driver.findElement(By.id("uploadFile"));
        uploadButton.sendKeys("C:/Users/guliz/OneDrive/Masaüstü/test.png");

        // Yükleme yapıldığını doğrulamak için çıktı mesajını al
        WebElement uploadFilePath = driver.findElement(By.id("uploadedFilePath"));
        System.out.println(uploadFilePath.getText());
    }
    @Test
    public void TabTests() throws InterruptedException {
        driver.get("https://demoqa.com/browser-windows");
        WebElement newTabBtn = driver.findElement(By.xpath("//button[@id='tabButton']"));
        //new window ve new tab tamamen aynı şekilde işliyor.
        // WebElement newWindowBtn = driver.findElement(By.xpath("//button[@id='windowButton']"))
        newTabBtn.click();
        //Açılan tabları bir listeye attık. getWindowHandles() Set döner. onu aray liste çevirdik.
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        System.out.println(tabs.size());
        //switchTo() başka taba geçmemize yarar
        driver.switchTo().window(tabs.get(1));
        System.out.println(driver.getCurrentUrl());
        Thread.sleep(2000);
        driver.close();

        //  https://demoqa.com/sample
    }
    @Test
    public void tabTests() throws InterruptedException {
        driver.get("https://demoqa.com/browser-windows");
        driver.findElement(By.id("tabButton")).click();
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        System.out.println(driver.getCurrentUrl());
        driver.close();
        driver.switchTo().window(tabs.get(0));
    }


    @Test
    @Order(3)
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
        prompt.sendKeys("JUnit Test");
        prompt.accept();
    }
    @Test
    public void FrameTest() throws InterruptedException {
        driver.get("https://demoqa.com/frames");

    }


}

