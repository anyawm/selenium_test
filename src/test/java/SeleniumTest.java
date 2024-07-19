import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;


public class SeleniumTest {

  WebDriver driver = new ChromeDriver();
  Actions act = new Actions(driver);


  @Test
  void successfulTextBox() {
    driver.get("https://demoqa.com/text-box");

    WebElement fullName = driver.findElement(By.id("userName"));
    fullName.sendKeys(TestData.userName);

    WebElement email = driver.findElement(By.id("userEmail"));
    email.sendKeys(TestData.userEmail);

    WebElement currentAddress = driver.findElement(By.id("currentAddress"));
    currentAddress.sendKeys("Russia, Moscow, Lenina 105-7");

    WebElement permanentAddress = driver.findElement(By.id("permanentAddress"));
    permanentAddress.sendKeys("Russia, Moscow, Lenina 105-7");

    WebElement submitSutton = driver.findElement(By.id("submit"));
    submitSutton.click();

    WebElement regName = driver.findElement(By.id("name"));
    regName.equals(TestData.userName);
    WebElement regEmail = driver.findElement(By.id("email"));
    regName.equals(TestData.userEmail);
    WebElement regAddress1 = driver.findElement(By.xpath("//*[contains(text(), 'Current Address :')]"));
    regAddress1.equals(currentAddress);

    driver.quit();
  }


  @Test
  void successfulRadioButton() {
    driver.get("https://demoqa.com/buttons");

    WebElement doubleClickBtn = driver.findElement(By.id("doubleClickBtn"));
    act.doubleClick(doubleClickBtn).perform();
    WebElement doubleClickMessage = driver.findElement(By.id("doubleClickMessage"));
    doubleClickMessage.equals("You have done a double click");

    WebElement rightClickBtn = driver.findElement(By.id("rightClickBtn"));
    act.contextClick(rightClickBtn).perform();
    WebElement rightClickMessage = driver.findElement(By.id("rightClickMessage"));
    rightClickMessage.equals("You have done a right click");

    // WebElement clickMe = driver.findElement(By.xpath("//*[contains(text(), 'Click Me') and @type='button']"));
    WebElement clickMe = driver.findElement(By.xpath("//*[contains(text(), 'Click Me')]"));
    act.click(clickMe).perform();
    WebElement dynamicClickMessage = driver.findElement(By.id("dynamicClickMessage"));
    dynamicClickMessage.equals("You have done a dynamic click");

    driver.quit();
  }
}

