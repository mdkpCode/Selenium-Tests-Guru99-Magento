package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

public class LoginPage extends BasePage {

    private static final By loginLocator = By.cssSelector("#email");
    private static final By passwordLocator = By.cssSelector("#pass");
    private static final By loginButtonLocator = By.cssSelector("#send2");
    private static final By errorMessageLocator = By.cssSelector("li.error-msg");
    private static final By dashboardWelcomeTextLocator = By.cssSelector("p.hello");
    private static final By validationAdviceLocator = By.cssSelector("div[class=\"validation-advice\"]");

    public HeaderPage header;

    public LoginPage(WebDriver driver) {
        super(driver);
        header = new HeaderPage(driver);
    }

    public LoginPage inputLoginData(String login, String password) {
        for (By by : Arrays.asList(loginLocator, passwordLocator)) {
            driver.findElement(by).clear();
        }
        driver.findElement(loginLocator).sendKeys(login);
        driver.findElement(passwordLocator).sendKeys(password);
        driver.findElement(loginButtonLocator).click();
        return this;
    }

    public String getErrorMessage() {
        return driver.findElement(errorMessageLocator).getText();
    }

    public String getValidationAdvice() {
        String validationAdvice = null;
        List<WebElement> validationAdviceList = driver.findElements(validationAdviceLocator);
        for (WebElement advice : validationAdviceList) {
            validationAdvice = advice.getText();
        }
        return validationAdvice;
    }

    public String getDashboardMessage() {
        return driver.findElement(dashboardWelcomeTextLocator).getText();
    }
}
