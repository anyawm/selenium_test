package tests;

import com.github.javafaker.Faker;
import java.security.SecureRandom;

public class TestData {

  static Faker faker = new Faker();
  static String userFullName = faker.name().fullName(),
      userName = faker.name().firstName(),
      userSurname = faker.name().lastName(),
      userAddress = faker.address().fullAddress(),
      userPhone = faker.phoneNumber().subscriberNumber(10);


  static String randomString(int len) {
    String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    SecureRandom rnd = new SecureRandom();
    StringBuilder sb = new StringBuilder(len);
    for (int i = 0; i < len; i++) {
      sb.append(AB.charAt(rnd.nextInt(AB.length())));
    }
    return sb.toString();
  }

  public static String randomEmail(int len) {
    return randomString(len) + "@host.at";
  }

  static String
      userEmail = randomEmail(10),
      userSubject = "English";

}
