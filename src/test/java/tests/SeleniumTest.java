package tests;

import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static tests.TestData.userEmail;
import static tests.TestData.userFullName;
import static tests.TestData.userName;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;


public class SeleniumTest {

  WebDriver driver = new ChromeDriver();
  Actions act = new Actions(driver);

  @BeforeEach
  void beforeAll() {
    driver.manage().window().setSize(new Dimension(1920, 1080));
  }

  @AfterEach
  void setup() {
    driver.quit();
  }

  @Test
  @DisplayName("Elements")
  void switchingToTabs() {
    driver.get("https://demoqa.com/");
    driver.findElement(By.xpath("//h5[contains (text(), 'Element')]")).click();
    assertEquals("Text Box", driver.findElement(By.id("item-0")).getText());
    assertEquals("Check Box", driver.findElement(By.id("item-1")).getText());
    assertEquals("Radio Button", driver.findElement(By.id("item-2")).getText());
    assertEquals("Web Tables", driver.findElement(By.id("item-3")).getText());
    assertEquals("Buttons", driver.findElement(By.id("item-4")).getText());
    assertEquals("Links", driver.findElement(By.id("item-5")).getText());
    assertEquals("Broken Links - Images", driver.findElement(By.id("item-6")).getText());
    assertEquals("Upload and Download", driver.findElement(By.id("item-7")).getText());
    assertEquals("Dynamic Properties", driver.findElement(By.id("item-8")).getText());
  }

  @Test
  @DisplayName("Text-box")
  void successfulTextBox() {
    driver.get("https://demoqa.com/text-box");
    driver.findElement(By.id("userName")).sendKeys(userFullName);
    driver.findElement(By.id("userEmail")).sendKeys(userEmail);
    driver.findElement(By.id("currentAddress")).sendKeys("Russia, Moscow, Lenina 105-7");
    driver.findElement(By.id("permanentAddress")).sendKeys("Russia, Moscow, Lenina 105-7");
    driver.findElement(By.id("submit")).click();
    assertEquals("Name:" + userFullName, driver.findElement(By.id("name")).getText());
    assertEquals("Email:" + userEmail, driver.findElement(By.id("email")).getText());
    assertEquals("Current Address :Russia, Moscow, Lenina 105-7", driver.findElement(By.xpath("//*[contains(text(), 'Current Address :')]")).
        getText());
    assertEquals("Permananet Address :Russia, Moscow, Lenina 105-7", driver.findElement(By.xpath("//*[contains(text(), 'Permananet Address :')]")).
        getText());
  }


  @Test
  @DisplayName("Buttons")
  void successfulRadioButton() {
    driver.get("https://demoqa.com/buttons");

    act.doubleClick(driver.findElement(By.id("doubleClickBtn"))).perform();
    assertEquals("You have done a double click", driver.findElement(By.id("doubleClickMessage")).
        getText());
    act.contextClick(driver.findElement(By.id("rightClickBtn"))).perform();
    assertEquals("You have done a right click", driver.findElement(By.id("rightClickMessage")).
        getText());
    driver.findElement(By.xpath("//button[text()='Click Me']")).click();
    assertEquals("You have done a dynamic click", driver.findElement(By.id("dynamicClickMessage")).
        getText());
  }

  @Test
  @DisplayName("Student Registration Form")
  void registrationPage() {
    step("Открыть форму регистрации", () -> {
      driver.get("https://demoqa.com/automation-practice-form");
    });
    step("заполнить ФИО, почту, пол", () -> {
      driver.findElement(By.id("firstName")).sendKeys(userName);
      driver.findElement(By.id("lastName")).sendKeys(TestData.userSurname);
      driver.findElement(By.id("userEmail")).sendKeys(userEmail);
      driver.findElement(By.xpath("//label[@for = 'gender-radio-3']")).click();
    });
    step("заполнить номер телефона, дату рождения и предмет", () -> {
      driver.findElement(By.id("userNumber")).sendKeys(TestData.userPhone);
      driver.findElement(By.id("dateOfBirthInput")).click();
      driver.findElement(By.xpath("//*[@aria-label = 'Choose Monday, July 22nd, 2024']")).click();
      driver.findElement(By.id("subjectsInput")).sendKeys(TestData.userSubject);
    });
    step("заполнить [хобби, адрес и штат]", () -> {
      //driver.findElement(By.xpath("//label[@for='hobbies-checkbox-2']")).click();
      driver.findElement(By.id("currentAddress")).sendKeys(TestData.userAddress);
      });
    step("отправка фото и формы", () -> {
      WebElement photo = driver.findElement(By.id("uploadPicture"));
      photo.sendKeys(
          "C:\\Users\\anna_\\IdeaProjects\\SeleniumProject\\src\\test\\resources\\devushka-koshka melk.png");
      // меня смущает абсолютный путь, но я не поняла как сделать по-другому
      driver.findElement(By.id("submit")).submit();
    });
    step("Проверка зполнения формы", () -> {
      driver.findElement(By.className("modal-header")).isDisplayed();
      assertEquals("Thanks for submitting the form", driver.findElement(By.id("example-modal-sizes-title-lg")).getText());
    });
  }
}


