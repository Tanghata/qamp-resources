package placelab.tests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import placelab.utilities.WebDriverSetup;

public class WrongUsernameTest {
    public WebDriver driver;
    private String host = System.getProperty("host");
    private String incorrectUsername = "notTheRealUsername";
    private String password = System.getProperty("password");
    private String credentialsErrorMessage = "Invalid credentials!";

    @BeforeSuite
    public void initDriver() {
        driver = WebDriverSetup.getWebDriver("chrome");
    }

    @Test
    public void testWrongUsername() {
        driver.navigate().to(host);
        Assert.assertEquals(driver.getCurrentUrl(), host);
        Assert.assertEquals(driver.getTitle(), "PlaceLab");

        //Enter incorrect username and correct password
        WebElement enterUsername = driver.findElement(By.name("email"));
        enterUsername.sendKeys(incorrectUsername);

        WebElement enterPassword = driver.findElement(By.name("password"));
        enterPassword.sendKeys(password);

        //Click on login button
        WebElement submit = driver.findElement(By.xpath("//input[@value='Log in']"));
        submit.click();

        //Validate that user cannot log after providing incorrect username
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
