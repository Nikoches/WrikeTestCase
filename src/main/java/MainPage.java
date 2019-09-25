import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {
    /*
       Получение элементов по xpath.
    */
    @FindBy(xpath = "/html/body/div[1]/header/div[3]/div[2]/div/div/div[2]/div/form/button")
    private WebElement getFreeButton;
    @FindBy(xpath = "//*[@id=\"modal-pro\"]/form/label[1]/input")
    private WebElement inputField;
    @FindBy(xpath = " //*[@id=\"modal-pro\"]/form/label[2]/button")
    private WebElement createAccountButton;
    private final WebDriver driver;
     /*
        Инциализация страницы.
     */
    public MainPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    /**
        Выполнение сценария на странице, с переданным параметром email.
     */
    public void clickAndSendKeys(String email){
        getFreeButton.click();
        inputField.sendKeys(email);
        createAccountButton.click();
    }
}
