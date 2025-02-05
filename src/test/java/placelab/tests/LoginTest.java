package placelab.tests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import placelab.utilities.WebDriverSetup;

public class LoginTest {
    public WebDriver driver;
    private String host = System.getProperty("host");
    private String homePageUrl = "https://demo.placelab.com/dashboard/traffic";
    private String user = "Damir Memic";
    private String username = System.getProperty("username");
    private String password = System.getProperty("password");

    //Specify the driver and browser that will be used for this scenario
    @BeforeSuite
    public void initDriver() {
        driver = WebDriverSetup.getWebDriver("chrome");
    }

    //Actual test case implementation
    @Test
    public void testLoginPage() {
        driver.navigate().to(host); //Go to PlaceLab demo app
        Assert.assertEquals(driver.getCurrentUrl(), host); //Validate that user is redirected to the right page
        Assert.assertEquals(driver.getTitle(), "PlaceLab");

        WebElement logo = driver.findElement(By.xpath("//img[@src='/assets/logo" +
                "-526ea19604d26801aca90fe441f7df4775a24a5d74ae273dbc4af85f42241259.png']"));
        boolean logoPresent = logo.isDisplayed();
        Assert.assertTrue(logoPresent);
//      System.out.println(logo.getLocation());

        //Fill out login parameters
        WebElement enterUsername = driver.findElement (By.name("email"));
        enterUsername.sendKeys(username);
//      driver.findElement(By.name("email")).sendKeys(username);

        WebElement enterPassword = driver.findElement(By.name("password"));
        enterPassword.sendKeys(password);

        //Click on login button
        WebElement submit = driver.findElement(By.xpath("//input[@value='Log in']"));
        submit.click();

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

    //Clean up - close the browser
    @AfterSuite
    public void quitDriver() {
        driver.close();
    }
}