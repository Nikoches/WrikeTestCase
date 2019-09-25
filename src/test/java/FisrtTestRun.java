import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
@RunWith(Theories.class)
public class FisrtTestRun {
    public static Properties properties;
    private static WebDriver driver;
    private static String content;
    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String NUMBER = "0123456789";

    private static final String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER + NUMBER;
    /*
        Закрываем соединение и браузер.
     */
    @AfterClass
    public static void closeDriver() {
        driver.close();
    }
    /*
        Тут надо дописать броузеросы
         используя @Theory
    */
    @DataPoints
    public static Object[][] isEmptyData = new Object[][] {
            { "webdriver.chrome.driver", "webdriver.chrome.driver" },
            { " ", false },
            { "some string", false },
    };
    /*
        SetUp:
        1.делаем окно наибольшим, для отображения нужной кнопки.
        2.Устанавливаем задержку в 10 сек.
        3.Переходим по URL_MAIN.
     */
    @Before
    public void setUp() {
        try (InputStream input = FisrtTestRun.class.getClassLoader().getResourceAsStream("app.properties")) {
            properties = new Properties();
            if (input == null) {
                throw new IOException("Not Found app.properties");
            }
            properties.load(input);
            content = Files.readString(Paths.get("src/main/resources/ss.txt"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.setProperty("webdriver.chrome.driver", properties.getProperty("webdriver.chrome.driver"));
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(properties.getProperty("main_url"));
    }

    @Test
    public void checkPages() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickAndSendKeys(generateEmail());
        ResendPage resendPage = new ResendPage(driver);
        resendPage.putAnswers();
        resendPage.submitClick();
        assertEquals(properties.getProperty("match_url"), driver.getCurrentUrl());
        assertEquals(properties.getProperty("twitter_url"), resendPage.getTwitterElement().getAttribute("href"));
        assertEquals(content.strip(), resendPage.getTwitterElement().getAttribute("innerHTML").strip());
        assertTrue(resendPage.getSubmitButton().isDisplayed());

    }

    private String generateEmail() {
        StringBuilder sb = new StringBuilder(5);
        for (int i = 0; i < 5; i++) {
            int rndCharAt = new Random().nextInt(DATA_FOR_RANDOM_STRING.length());
            char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);
            sb.append(rndChar);
        }
        return sb.toString()+"wpt@wriketask.qaa";
    }

}