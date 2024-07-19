import com.github.javafaker.Faker;
import java.security.SecureRandom;

public class TestData {
  static Faker faker = new Faker();
  static String userName = faker.name().fullName();

  static String randomString(int len) {
    String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    SecureRandom rnd = new SecureRandom();
    StringBuilder sb = new StringBuilder(len);
    for (int i = 0; i < len; i++)
      sb.append(AB.charAt(rnd.nextInt(AB.length())));
    return sb.toString();
  }

  public static String randomEmail(int len) {
    return randomString(len) + "@host.at";
  }

  static String userEmail = randomEmail(10);

}
