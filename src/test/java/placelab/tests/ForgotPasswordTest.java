package placelab.tests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import placelab.utilities.WebDriverSetup;

public class ForgotPasswordTest {
    public WebDriver driver;
    private String host = System.getProperty("host");
    private String forgotPasswordUrl = "https://demo.placelab.com/password/forgot";

    @BeforeSuite
    public void initDriver() {
        driver = WebDriverSetup.getWebDriver("chrome");
    }

    @Test
    public void testForgotPassword() {
        driver.navigate().to(host);
        Assert.assertEquals(driver.getCurrentUrl(), host);
        Assert.assertEquals(driver.getTitle(), "PlaceLab");

        //Validate that 'Forgot your password?' message is displayed on the login page, and then click on it
        WebElement forgotPasswordMessage = driver.findElement(By.xpath("//div/a[@class='link-btn']"));
        Boolean forgotPasswordMessagePresent = forgotPasswordMessage.isDisplayed();
        Assert.assertTrue(forgotPasswordMessagePresent);

        WebElement submit = driver.findElement(By.xpath("//div/a[@class='link-btn']"));
        submit.click();

        //Validate that you've navigated to the right page
        Assert.assertEquals(driver.getCurrentUrl(), forgotPasswordUrl);

        WebElement forgotPasswordPageSubtitle1 = driver.findElement(By.className("small-headline"));
        Assert.assertEquals(forgotPasswordPageSubtitle1.getText(), "Let's find your account");
    }

    @AfterSuite
    public void quitDriver() {
        driver.close();
    }
}
