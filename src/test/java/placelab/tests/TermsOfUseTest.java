package placelab.tests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import placelab.utilities.WebDriverSetup;

public class TermsOfUseTest {
    public WebDriver driver;
    private String host = System.getProperty("host");
    private String termsOfUseUrl = "https://demo.placelab.com/terms_of_service";

    @BeforeSuite
    public void initDriver() {
        driver = WebDriverSetup.getWebDriver("chrome");
    }

    @Test
    public void testTermsOfUse() {
        driver.navigate().to(host);
        Assert.assertEquals(driver.getCurrentUrl(), host);
        Assert.assertEquals(driver.getTitle(), "PlaceLab");

        //Validate that 'Terms of Use' message is displayed on the login page
        WebElement termsOfUseMessage = driver.findElement(By.xpath("//span/a[@class='link-btn']"));
        Boolean termsOfUseMessagePresent = termsOfUseMessage.isDisplayed();
        Assert.assertTrue(termsOfUseMessagePresent);

        //Store the ID of the original window
        String originalWindow = driver.getWindowHandle();

        //Click on 'Terms of Use' link which opens in a new tab or window
        WebElement submit = driver.findElement(By.xpath("//span/a[@class='link-btn']"));
        submit.click();

        //Loop through to find a new tab or window handle
        for (String windowHandle : driver.getWindowHandles()) {
            if(!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        //Validate that you've navigated to the right page
        Assert.assertEquals(driver.getCurrentUrl(), termsOfUseUrl);

        WebElement termsOfUsePageTitle = driver.findElement(By.className("terms-header"));
        Assert.assertEquals(termsOfUsePageTitle.getText(), "Terms and conditions of use");

        //Close the newly-opened tab or window, and then switch back to the old one
        driver.close();
        driver.switchTo().window(originalWindow);
    }

    @AfterSuite
        public void quitDriver() {
        driver.close();
    }
}
