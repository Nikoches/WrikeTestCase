import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

public class ResendPage {

    private final WebDriver driver;
    @FindBy(xpath = "/html/body/div[1]/main/div/div/div[2]/div/div[2]/div/form/button")
    private WebElement submitButton;
    private WebElement[] arrayButtons;
    @FindBy(xpath = "/html/body/div[1]/div/div[3]/div/div[1]/div/ul/li[1]/a")
    private WebElement twitterElement;
    /*
        Инциализация страницы.
     */
    public ResendPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.arrayButtons = generateAnswers();
    }
    /*
        Генерация случайных ответов.
     */
    private WebElement[] generateAnswers() {
        WebElement[] arrayButtons1 = new WebElement[3];
        int i = 1;
        for (int z = 0; z < 3; z++) {
            String key = String.format("/html/body/div[1]/main/div/div/div[2]/div/div[2]/div/form/div[%s]/label[%s]/button", i++, 1 + new Random(System.currentTimeMillis()).nextInt(2 - 1 + 1));
            arrayButtons1[z] = driver.findElement(By.xpath(key));
        }
        return arrayButtons1;

    }
    /*
        Заполнение ответов.
     */
    public void putAnswers() {
        for (WebElement x : arrayButtons) {
            x.click();
        }
    }
   /*
        Submit.
    */
    public void submitClick() {
        submitButton.click();
    }
    public WebElement getSubmitButton(){
        WebElement surveySucces = driver.findElement(By.xpath(" /html/body/div[1]/main/div/div/div[2]/div/div[2]/div/div"));
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(surveySucces));
        return surveySucces;

    }
    public WebElement getTwitterElement() {
        return twitterElement;
    }
}
