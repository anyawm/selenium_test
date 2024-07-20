package tests;

import static io.qameta.allure.Allure.step;

import java.io.File;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;


public class SeleniumTest {

  WebDriver driver = new ChromeDriver();
  Actions act = new Actions(driver);


  @Test
  @DisplayName("Elements")
  void switchingToTabs() {
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
    WebElement DynamicProperties = driver.findElement(By.id("item-8"));
    textBox.equals("Dynamic Properties");
    driver.quit();
  }

  @Test
  @DisplayName("Text-box")
  void successfulTextBox() {
    driver.get("https://demoqa.com/text-box");

    WebElement fullName = driver.findElement(By.id("userName"));
    fullName.sendKeys(TestData.userFullName);

    WebElement email = driver.findElement(By.id("userEmail"));
    email.sendKeys(TestData.userEmail);

    WebElement currentAddress = driver.findElement(By.id("currentAddress"));
    currentAddress.sendKeys("Russia, Moscow, Lenina 105-7");

    WebElement permanentAddress = driver.findElement(By.id("permanentAddress"));
    permanentAddress.sendKeys("Russia, Moscow, Lenina 105-7");

    WebElement submitSutton = driver.findElement(By.id("submit"));
    submitSutton.click();

    WebElement regName = driver.findElement(By.id("name"));
    regName.equals(TestData.userFullName);
    WebElement regEmail = driver.findElement(By.id("email"));
    regName.equals(TestData.userEmail);
    WebElement regAddress1 = driver.findElement(
        By.xpath("//*[contains(text(), 'Current Address :')]"));
    //WebElement regAddress1 = driver.findElement(By.xpath("//p[@id=currentAddress]")); так не находит
    regAddress1.equals(currentAddress);

    driver.quit();
  }


  @Test
  @DisplayName("Buttons")
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
  @DisplayName("Student Registration Form")
  void registrationPage() {
    step("Открыть форму регистрации", () -> {
      driver.get("https://demoqa.com/automation-practice-form");
    });
    step("заполнить ФИО, почту, пол", () -> {
      WebElement userFirstName = driver.findElement(By.id("firstName"));
      userFirstName.sendKeys(TestData.userName);
      WebElement userLastName = driver.findElement(By.id("lastName"));
      userLastName.sendKeys(TestData.userSurname);
      WebElement email = driver.findElement(By.id("userEmail"));
      email.sendKeys(TestData.userEmail);
      WebElement userGender = driver.findElement(By.id("gender-radio-3"));
      act.click(userGender).perform();
    });
    step("заполнить номер телефона, дату рождения и предмет", () -> {
      WebElement userNumber = driver.findElement(By.id("userNumber"));
      userNumber.sendKeys(TestData.userPhone);
      //не смогла изменить дату ни через ввод текста, ни через календарь, поэтому поменяла как смогла :)
      WebElement userBirthDay = driver.findElement(By.id("dateOfBirthInput"));
      act.doubleClick(userBirthDay).sendKeys("2000").sendKeys(Keys.ENTER).perform();
      //userBirthDay.sendKeys("01.01.2000");
      WebElement userSubject = driver.findElement(By.id("subjectsInput"));
      userSubject.sendKeys(TestData.userSubject);
    });
    step("заполнить [хобби, адрес и штат]", () -> {
      //тест не падает, но хобби не заполняется, если выбрать act.click(), но на форме не нужен даблклик
      //пробовала разные локаторы
      //WebElement userHobby = driver.findElement(By.id("hobbies-checkbox-2"));
      //WebElement userHobby = driver.findElement(By.xpath("//@label[contains(text(), 'Reading')]"));
      WebElement userHobby = driver.findElement(By.id("hobbiesWrapper")).
          findElement(By.xpath("//*[contains(text(), 'Reading')]"));
      act.doubleClick(userHobby).perform();
      WebElement userAddress = driver.findElement(By.id("currentAddress"));
      userAddress.sendKeys(TestData.userAddress);
      // штат и город тоже не смогла заполнить
      // тест не падает, но не заполняется ни текстом ни выбором (выбором падает)
      // выбором пробовала так
      //WebElement userState = driver.findElement(By.id("state"));
      //act.click(userState);
      //WebElement chooseState = driver.findElement(By.id("react-select-3-option-2")); //нашла при фризе
      // act.click(chooseState).perform();
      WebElement userState = driver.findElement(
          By.xpath("//div[contains(text(), 'Select State')]"));
      act.doubleClick(userState).sendKeys("NCR").sendKeys(Keys.ENTER).perform();
    });
    step("отправка фото и формы", () -> {
      WebElement photo = driver.findElement(By.id("uploadPicture"));
      photo.sendKeys(
          "C:\\Users\\anna_\\IdeaProjects\\SeleniumProject\\src\\test\\resources\\devushka-koshka melk.png");
      // меня смущает абсолютный путь, но я не поняла как сделать по-другому
      WebElement submit = driver.findElement(By.id("submit"));
      submit.submit();
    });
    step("Проверка зполнения формы", () -> {
      WebElement modalWindow = driver.findElement(By.className("modal-header"));
      modalWindow.isDisplayed();
      WebElement modalTitle = driver.findElement(By.id("example-modal-sizes-title-lg"));
      modalTitle.equals("Thanks for submitting the form");
      WebElement table = driver.findElement(By.className("table-responsive"));
      table.equals(TestData.userName);
      table.equals(TestData.userSurname);
      table.equals(TestData.userEmail);
      table.equals(TestData.userAddress);
      table.equals(TestData.userPhone);
    });
  }
}


