package placelab.tests;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import placelab.utilities.WebDriverSetup;

public class LoginWithEnterKey {
    public WebDriver driver;
    private String host = System.getProperty("host");
    private String homePageUrl = "https://demo.placelab.com/dashboard/traffic";
    private String user = "Damir Memic";
    private String username = System.getProperty("username");
    private String password = System.getProperty("password");

    @BeforeSuite
    public void initDriver() {
        driver = WebDriverSetup.getWebDriver("chrome");
    }

    @Test
    public void testLoginWithEnter() {
        driver.navigate().to(host);
        Assert.assertEquals(driver.getCurrentUrl(), host);
        Assert.assertEquals(driver.getTitle(), "PlaceLab");

        //Fill out login parameters and then press 'enter' on your keyboard
        WebElement enterUsername = driver.findElement (By.name("email"));
        enterUsername.sendKeys(username);

        WebElement enterPassword = driver.findElement(By.name("password"));
        enterPassword.sendKeys(password);

        WebElement submit = driver.findElement(By.xpath("//input[@value='Log in']"));
        submit.sendKeys(Keys.ENTER);

        //Validate that user is successfully logged in
        Assert.assertEquals(driver.getCurrentUrl(), homePageUrl);
        try {
            WebElement userName = driver.findElement(By.id("user-name"));
            assert (userName.getText().contains(user));
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException("Expected user is not logged in!");
        }
        WebElement userRole = driver.findElement(By.id("user-role"));
        Assert.assertEquals(userRole.getText(), "Group Admin");
    }

    @AfterSuite
    public void quitDriver() {
        driver.close();
    }
}
