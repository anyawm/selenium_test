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
    //WebElement regAddress1 = driver.findElement(By.xpath("//p[@id=currentAddress]")); так не находит
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
    // дальше не работает
    WebElement dynamicClickMessage = driver.findElement(By.id("dynamicClickMessage"));
    dynamicClickMessage.equals("You have done a dynamic click");

    driver.quit();
  }

  @Test
  void SwitchingToTabs() {
    driver.get("https://demoqa.com/");
    WebElement element = driver.findElement(By.xpath("//h5[contains (text(), 'Element')]"));
    act.click(element).perform();
    WebElement textBox = driver.findElement(By.id("item-0"));
    textBox.equals("Text Box");
    WebElement checkBox = driver.findElement(By.id("item-1"));
    textBox.equals("Check Box");
    WebElement radioButton = driver.findElement(By.id("item-2"));
    textBox.equals("Radio Button");
    WebElement webTables = driver.findElement(By.id("item-3"));
    textBox.equals("Web Tables");
    WebElement buttons = driver.findElement(By.id("item-4"));
    textBox.equals("Buttons");
    WebElement links = driver.findElement(By.id("item-4"));
    textBox.equals("Links");
    WebElement brokenLinksImages = driver.findElement(By.id("item-5"));
    textBox.equals("Broken Links - Images");
    WebElement uploadAndDownload = driver.findElement(By.id("item-7"));
    textBox.equals("Upload and Download");
    WebElement DynamicProperties= driver.findElement(By.id("item-8"));
    textBox.equals("Dynamic Properties");

    driver.quit();
  }

}


