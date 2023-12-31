package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;


public class Contact_Us_Steps {
    private WebDriver driver;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/java/drivers/chromedriver.exe");
        // ou System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "c:/selenium/drivers/chromedriver.exe");

/*
        não funcionou no meu teste.
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);*/

        ChromeOptions chromeOptions = new ChromeOptions();
        //chromeOptions.addArguments("--start-maximized"); - aqui não funcionou no meu teste

        chromeOptions.addArguments("--remote-allow-origins=*");
        chromeOptions.addArguments("--disable-extensions");

        //chromeOptions.addArguments("test-type");
        //chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--window-size=1024,768");
        chromeOptions.addArguments("--enable-precise-memory-info");
        chromeOptions.addArguments("--disable-popup-blocking");
        chromeOptions.addArguments("--disable-default-apps");
        chromeOptions.addArguments("--test-type=browser");

        driver = new ChromeDriver(chromeOptions);
        //driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Given("I access the webdriver university contact us page")
    public void i_access_the_webdriver_university_contact_us_page() {
        driver.get("http://www.webdriveruniversity.com/Contact-Us/contactus.html");
    }
    @When("I enter a first name")
    public void i_enter_a_first_name()  {
        driver.findElement(By.cssSelector("[name=\"first_name\"]")).sendKeys("Joe");
        //Thread.sleep(3000);
    }
    @And("I enter a last name")
    public void i_enter_a_last_name() {
        driver.findElement(By.cssSelector("[name=\"last_name\"]")).sendKeys("Blogs");
    }
    @And("I enter an email address")
    public void i_enter_an_email_address(){
        driver.findElement(By.name("email")).sendKeys("joe_blogs123@gmail.com");
    }
    @And("I enter a comment")
    public void i_enter_a_comment() {
        driver.findElement(By.name("message")).sendKeys("Hello how are you?");
    }
    @And("I click on the submit button")
    public void i_click_on_the_submit_button() {
        driver.findElement(By.cssSelector("[value=\"SUBMIT\"]")).click();
    }
    @Then("I should be presented with a successful contact us submission message")
    public void i_should_be_presented_with_a_successful_contact_us_submission_message() {
        final String MESSAGE_SUBMISSION = "//*[@id='contact_reply']";
        WebElement messageSubmission = driver.findElement(By.xpath(MESSAGE_SUBMISSION.toString()));
        Assert.assertEquals(messageSubmission.getText(),"Thank You for your Message!");
    }
}
