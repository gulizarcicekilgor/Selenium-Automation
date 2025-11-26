package tests;
import io.github.bonigarcia.wdm.WebDriverManager;
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
import java.util.Arrays;
import java.util.List;

public class Test1 {

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        AlertTests(driver);
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
    //isEnabled
    public static void formCheckboxTest(WebDriver driver) {
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
    public static void RadioButtonTest(WebDriver driver) {
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

    public static void ClickTests(WebDriver driver){
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
    public static void DynamicPropertiesTests(WebDriver driver)
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
    public static void BrokenLinkTest(WebDriver driver) {
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
    public static void BrokenLinkTest2(WebDriver driver) {
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
    public static void BrokenLinkImage(WebDriver driver) {

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
    public static  void DownloadFileTest(WebDriver driver) throws InterruptedException {
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
    public static void UploadFileTest(WebDriver driver)
    {
        driver.get("https://demoqa.com/upload-download");
        WebElement uploadButton=driver.findElement(By.id("uploadFile"));
        uploadButton.sendKeys("C:/Users/guliz/OneDrive/Masaüstü/test.png");

        // Yükleme yapıldığını doğrulamak için çıktı mesajını al
        WebElement uploadFilePath = driver.findElement(By.id("uploadedFilePath"));
        System.out.println(uploadFilePath.getText());
    }
    public  static void TabTests(WebDriver driver) throws InterruptedException {
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

    public static void AlertTests(WebDriver driver) {
        driver.get("https://demoqa.com/alerts");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // 1️⃣ Normal Alert
            System.out.println("=== Normal Alert Test ===");
            driver.findElement(By.id("alertButton")).click();

            Alert alert1 = wait.until(ExpectedConditions.alertIsPresent());
            System.out.println("Alert text: " + alert1.getText());
            alert1.accept();
            Thread.sleep(1000);

            // 2️⃣ 5 saniye geciken alert
            System.out.println("=== Timer Alert Test ===");
            driver.findElement(By.id("timerAlertButton")).click();
            // Bu satır, sayfa butona tıkladıktan sonra alert görünene kadar bekle anlamına gelir. 5 saniye beklemesi için yeniden wait kullanmaya gerek kalmıyor
            Alert alert2 = wait.until(ExpectedConditions.alertIsPresent());
            System.out.println("Timer alert text: " + alert2.getText());
            alert2.accept();
            Thread.sleep(1000);

            // 3️⃣ Confirm (OK / Cancel)
            System.out.println("=== Confirm Alert Test ===");
            driver.findElement(By.id("confirmButton")).click();

            Alert alert3 = wait.until(ExpectedConditions.alertIsPresent());
            System.out.println("Confirm Alert text: " + alert3.getText());
            alert3.dismiss();   // Cancel'a basmak için
            System.out.println("Cancel seçildi");
            Thread.sleep(1000);

            // 4️⃣ Prompt Alert
            System.out.println("=== Prompt Alert Test ===");
            driver.findElement(By.id("promtButton")).click();

            Alert alert4 = wait.until(ExpectedConditions.alertIsPresent());
            System.out.println("Prompt text: " + alert4.getText());
            alert4.sendKeys("Gili Test");
            alert4.accept();
            Thread.sleep(1000);

        } catch (Exception e) {
            System.out.println("Hata oluştu: " + e.getMessage());
        }
    }




}