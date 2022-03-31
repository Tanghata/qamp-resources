package placelab.tests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import placelab.utilities.WebDriverSetup;

public class NoUsernameNoPasswordTest {
    public WebDriver driver;
    private String host = System.getProperty("host");
    private String credentialsErrorMessage = "Invalid credentials!";

    @BeforeSuite
    public void initDriver() {
        driver = WebDriverSetup.getWebDriver("chrome");
    }

    @Test
    public void testNoUsernameNoPassword() {
        driver.navigate().to(host);
        Assert.assertEquals(driver.getCurrentUrl(), host);
        Assert.assertEquals(driver.getTitle(), "PlaceLab");

        //Click on login button without providing username or password
        WebElement submit = driver.findElement(By.xpath("//input[@value='Log in']"));
        submit.click();

        //Validate that user cannot log in with providing neither the username nor password
        Assert.assertEquals(driver.getCurrentUrl(), host);

        //Validate that specific error message is displayed on the login page
        WebElement errorMessage = driver.findElement(By.className("error-area"));
        Assert.assertEquals(errorMessage.getText(), credentialsErrorMessage);
    }

    @AfterSuite
    public void quitDriver() {
        driver.close();
    }
}
