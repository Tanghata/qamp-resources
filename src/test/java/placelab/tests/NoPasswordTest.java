package placelab.tests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import placelab.utilities.WebDriverSetup;

public class NoPasswordTest {
    public WebDriver driver;
    private String host = System.getProperty("host");
    private String username = System.getProperty("username");

    @BeforeSuite
    public void initDriver() {
        driver = WebDriverSetup.getWebDriver("chrome");
    }

    @Test
    public void testNoPassword() {
        driver.navigate().to(host);
        Assert.assertEquals(driver.getCurrentUrl(), host);
        Assert.assertEquals(driver.getTitle(), "PlaceLab");

        //Enter the username, but do not enter the password
        WebElement enterUsername = driver.findElement(By.name("email"));
        enterUsername.sendKeys(username);

        //Click on login button
        WebElement submit = driver.findElement(By.xpath("//input[@value='Log in']"));
        submit.click();

        //Validate that user cannot log in without providing the password
        Assert.assertEquals(driver.getCurrentUrl(), host);

        //Validate that an error message is displayed on the login page
        WebElement errorMessage = driver.findElement(By.className("error-area"));
        Boolean errorMessagePresent = errorMessage.isDisplayed();
        Assert.assertTrue(errorMessagePresent);
    }

        @AfterSuite
        public void quitDriver () {
            driver.close();
    }
}
