package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.net.HttpCookie;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.Integer.parseInt;
import static ru.netology.DataGenerator.*;





public class DeliveryCardTest {
    String local = "ru";
    //public String generateDate(int days) {
        //return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    //}

    @Test
    public void testValidDeliveryCard() {
        String planningDate = generateDate(randomPeriod(4, 365), "dd.MM.yyyy");
        open("http://localhost:9999/");
        String city = generateCity(local);
        String name = generateName(local);
        String phone = generatePhone(local);

        $("[data-test-id=city] input").setValue(city);
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id=name] input").setValue(name);
        $("[data-test-id=phone] input").setValue(phone);
        $("[data-test-id=agreement]").click();
        $(byText("Запланировать")).click();

        $(withText("Успешно")).shouldBe(appear, Duration.ofSeconds(15));
        $("[data-test-id=success-notification]")
                .shouldHave(Condition.text("Встреча успешно запланирована на " + planningDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
   }

   @Test
   public void testReplanningValidDeliveryCard() {
       String planningDate = generateDate(randomPeriod(4, 365), "dd.MM.yyyy");
       open("http://localhost:9999/");
       String city = generateCity(local);
       String name = generateName(local);
       String phone = generatePhone(local);

       $("[data-test-id=city] input").setValue(city);
       $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
       $("[data-test-id='date'] input").setValue(planningDate);
       $("[data-test-id=name] input").setValue(name);
       $("[data-test-id=phone] input").setValue(phone);
       $("[data-test-id=agreement]").click();
       $(byText("Запланировать")).click();

       $(withText("Успешно")).shouldBe(appear, Duration.ofSeconds(15));
       $("[data-test-id=success-notification]")
               .shouldHave(Condition.text("Встреча успешно запланирована на " + planningDate), Duration.ofSeconds(15))
               .shouldBe(Condition.visible);

       String newPlanningDate = generateDate(randomPeriod(4, 365),"dd.MM.yyyy");
       $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
       $("[data-test-id='date'] input").setValue(newPlanningDate);

       $(byText("Запланировать")).click();

       $(withText("Необходимо подтверждение")).shouldBe(appear, Duration.ofSeconds(15));
       $("[data-test-id=replan-notification]")
               .shouldHave(Condition.text("У вас уже запланирована встреча на другую дату. Перепланировать?"), Duration.ofSeconds(15))
               .shouldBe(Condition.visible);


       $(byText("Перепланировать")).click();
       //$x("//div[@data-test-id='replan-notification']//button").click();

       $(withText("Успешно")).shouldBe(appear, Duration.ofSeconds(15));
       $("[data-test-id=success-notification]")
               .shouldHave(Condition.text("Встреча успешно запланирована на " + newPlanningDate), Duration.ofSeconds(15))
               .shouldBe(Condition.visible);
   }
}
