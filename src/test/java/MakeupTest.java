import com.codeborne.selenide.SelenideElement;
import org.junit.Test;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class MakeupTest {

    @Test
    public void searchFieldTest()
    {
        open("https://makeup.com.ua/ua/");
        $(By.className("search-button")).click();
        searchInMakeup("lamel");
        SelenideElement elem = $(By.className("search-results"));
        elem.$(By.cssSelector("strong")).shouldHave(text("113"));
    }

    @Test
    public void authorizationTest()
    {
        open("https://makeup.com.ua/ua/");
        if (!isAuthorized()) {
            loginInMakeup();
        }

        $(By.className("authorized")).click();
        $(By.className("page-header")).shouldHave(text("Контактна інформація"));
    }

    @Test
    public void addToFavourites()
    {
        open("https://makeup.com.ua/ua/");
        if (!isAuthorized()) {
            loginInMakeup();
        }

        $(By.className("simple-slider-list__name")).click();

        $(By.className("product__to-favourite"))
                .shouldBe(visible)
                .click();

        $(By.className("favourite-popup"))
                .shouldBe(visible);
        $(By.className("favourite-popup"))
                .$(By.className("specify-message-block"))
                .shouldHave(text("Хочете отримувати сповіщення про зниження ціни на товар?"));

        $(By.className("favourite-popup")).$(By.className("button_secondary"))
                .shouldBe(visible)
                .click();
    }

    @Test
    public void changeUserInfo()
    {
        open("https://makeup.com.ua/ua/");
        if (!isAuthorized()) {
            loginInMakeup();
        }
        $(By.className("authorized")).click();;

        $(By.id("name")).should(exist).setValue("Rename");
        $(By.id("surname")).should(exist).setValue("Surname");

        $(By.className("private-office__submit-button")).click();

        SelenideElement popup = $(By.id("popup__window"));

        popup.$(By.className("close-icon")).should(exist).click();
        $(By.id("name")).shouldHave(value("Rename"));
        $(By.id("surname")).shouldHave(value("Surname"));

    }

    @Test
    public void subscribeToNewsletter()
    {
        open("https://makeup.com.ua/ua/");
        if (!isAuthorized()) {
            loginInMakeup();
        }
        $(By.className("authorized")).click();
        $(By.className("footer-subscribe-header")).should(exist);
        $(By.id("footer-subscribe-email"))
                .should(exist)
                .setValue("jocah62329@nimadir.com");
        $(By.className("footer-submit")).click();
        $(By.className("popup-content")).should(exist);
    }

    @Test
    public void addItemToCart () {
        open("https://makeup.com.ua/ua/");
        if (!isAuthorized()) {
            loginInMakeup();
        }

        $(".simple-slider-list__link .info-product-wrapper a.simple-slider-list__name")
                .should(exist)
                .click();

        String itemName = $(".product-item__name").getText();

        $(".button.buy")
                .should(exist)
                .shouldBe(visible)
                .click();

        $("div.product__column .product__header").shouldHave(text(itemName));
    }

    @Test
    public void logOut()
    {
        open("https://makeup.com.ua/ua/");
        if (!isAuthorized()) {
            loginInMakeup();
        }
        $(By.className("authorized")).click();
        $(".private-office__tabs__item a")
                .should(exist)
                .click();

        $(By.className("header-office")).click();
        $(By.id("form-auth"))
                .should(exist)
                .shouldHave(text("Вхід до особистого кабінету"));

    }

    protected void loginInMakeup()
    {
        $(By.className("header-office")).click();
        $(By.id("login")).setValue("jocah62329@nimadir.com");
        $(By.id("pw")).setValue("Test!123").pressEnter();
        $(By.className("authorized")).shouldBe(visible);
    }

    protected boolean isAuthorized()
    {
        return $(By.className("authorized")).exists();
    }

    protected void searchInMakeup(String searchQuery)
    {
        $(By.id("search-input")).setValue(searchQuery).pressEnter();
    }

}
