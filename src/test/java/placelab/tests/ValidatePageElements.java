package placelab.tests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import placelab.utilities.WebDriverSetup;

public class ValidatePageElements {
    public WebDriver driver;
    private String host = System.getProperty("host");

    @BeforeSuite
    public void initDriver() {
        driver = WebDriverSetup.getWebDriver("chrome");
    }

    @Test
    public void testPageElements() {
        driver.navigate().to(host);
        Assert.assertEquals(driver.getCurrentUrl(), host);
        Assert.assertEquals(driver.getTitle(), "PlaceLab");

        //Validate unclickable elements on the page
        WebElement pageSubtitle = driver.findElement(By.xpath("//p[@class='bold headline']"));
        Assert.assertEquals(pageSubtitle.getText(), "Turn your data into information, information into insight "+
            "and insight into business decisions.");

        WebElement rightHeadline1 = driver.findElement(By.xpath("(//div[@class='span5 pull-right']/div[@class"+
            "='row info']/div[@class='span3'])[1]"));
        Assert.assertEquals(rightHeadline1.getText(), "Turn raw location data into actionable insights");

        WebElement rightHeadline2 = driver.findElement(By.xpath("//p[@style='padding-top:8px;']"));
        Assert.assertEquals(rightHeadline2.getText(), "Quantify user experience");

        WebElement rightHeadline3 = driver.findElement(By.xpath("(//div[@class='span5 pull-right']/div[@class"+
            "='row info']/div[@class='span3'])[3]"));
        Assert.assertEquals(rightHeadline3.getText(), "Independent attestation of quality");

        WebElement footer = driver.findElement(By.className("copyright"));
        Assert.assertEquals (footer.getText(), "© Copyright 2012 - 2022 Atlantbh d.o.o. ® All Rights Reserved.");
    }

    @AfterSuite
    public void quitDriver() {
        driver.close();
    }
}
