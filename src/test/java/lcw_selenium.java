import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class lcw_selenium {

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\myb_6\\Selenium\\ChromeDriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.lcwaikiki.com/tr-TR/TR");
        driver.manage().window().maximize();
        Thread.sleep(2000);
        driver.findElement(By.linkText("Giriş Yap")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("LoginEmail")).sendKeys("myb_67@hotmail.com");
        Thread.sleep(1000);
        driver.findElement(By.name("Password")).sendKeys("myb_6767");
        Thread.sleep(1000);
        driver.findElement(By.id("loginLink")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("search")).sendKeys("Pantolon");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[text()=\"ARA\"]")).click();
        Thread.sleep(1000);
        //scroll ile sayfa sonu
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(1000);
        //Daha fazla ürün
        WebElement more = driver.findElement(By.xpath("//a[@class='lazy-load-button']"));
        more.click();
        Thread.sleep(1000);
        //Rastgele ürün seçme
        List<WebElement> products = driver.findElement(By.cssSelector(".row.c-items")).findElements(By.className("c-item"));
        Random rnd = new Random();
        int rndInt = rnd.nextInt(products.size());
        products.get(rndInt).click();
        WebElement ilkBoyut = driver.findElements(By.xpath("//div[@id='option-size']//a")).get(0);
        ilkBoyut.click();
        WebElement sepet = driver.findElement(By.id("pd_add_to_cart"));
        sepet.click();
        WebElement sepetDetay = driver.findElement(By.className("header-cart"));
        sepetDetay.click();
        String urunFiyat = driver.findElement(By.className("rd-cart-item-price")).getText();
        String sepetFiyat = driver.findElement(By.xpath("//div[@class='price-info-area']//span[@class='pull-right']")).getText();

        Assert.assertEquals(urunFiyat, sepetFiyat);

        WebElement fiyatAdet = driver.findElement(By.xpath("//div[@class='products-area']//div[@class='row mb-5']//div[@class='col-md-8 col-xs-9 pr-0 text-left']//div[@class='rd-cart-item-quantity-area']//a[@class='oq-up plus']"));
        fiyatAdet.click();
        TimeUnit.SECONDS.sleep(3);
        String urunSayisi = driver.findElement(By.xpath("//div[@class='rd-cart-item-quantity-area']//div//input")).getAttribute("value");
        String sepetBoyut = driver.findElement(By.xpath("//div[@class='col-sm-4 col-xs-4 header-cart-section']//span[@id='spanCart']")).getText();
        Assert.assertEquals(urunSayisi, sepetBoyut);
        WebElement sil = driver.findElement(By.xpath("//div[@class='mobile-square-buttons-container']//i[@class='fa fa-trash-o']"));
        sil.click();
        WebElement urunSil = driver.findElement(By.xpath("//div[@class='col-xs-12']//a[@class='inverted-modal-button sc-delete ins-init-condition-tracking']"));
        TimeUnit.SECONDS.sleep(3);
        urunSil.click();
        TimeUnit.SECONDS.sleep(3);
        WebElement sepetBos = driver.findElement(By.xpath("//div[@class='col-sm-12']//p[@class='cart-empty-title']"));
        boolean sepetDurum = true;
        if (driver.findElements(By.xpath("//div[@class='col-sm-12']//p[@class='cart-empty-title']")).size() == 0) {
            sepetDurum = false;
        }
        Assert.assertTrue(sepetDurum);
    }
}
