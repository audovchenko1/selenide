import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class GoogleTest {

    @Test
    public void googleWeatherTest()
    {
        open("https://google.com.ua");
        $(By.id("APjFqb"))
                .setValue("The weather in Odessa")
                .pressEnter();
        $(By.id("wob_wc")).should(exist);
    }

}
