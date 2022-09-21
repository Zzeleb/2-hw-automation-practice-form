package com.demoqa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class AutomationPracticeFormTests {

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;
    }

    @Test
    void fillFormTest() {
        String name = "Artem";
        String surname = "Zeleb";
        String Email = "zeleb@inbox.ru";
        String Number = "9042545658";
        String picture = "123.jpg";
        String subjects = "Maths";
        String hobby = "Reading";
        String address = "Nevskiy 5, 15";

        open("/automation-practice-form");
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");
        $("#firstName").setValue(name);
        $("#lastName").setValue(surname);
        $("#userEmail").setValue(Email);
        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").setValue(Number);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption("1985");
        $(".react-datepicker__month-select").selectOption("October");
        $(".react-datepicker__day--016").click();
        $("#subjectsInput").setValue(subjects).pressEnter();
        $$(".custom-control-label").findBy(text(hobby)).click();
        $("input#uploadPicture").uploadFromClasspath(picture);
        $("#currentAddress").setValue(address);
        $("#state").click();
        $("#stateCity-wrapper").$(byText("Haryana")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Karnal")).click();
        $("#submit").click();

        $(".table-responsive").shouldHave(text(name));
        $(".table-responsive").shouldHave(text(surname));
        $(".table-responsive").shouldHave(text(Email));
        $(".table-responsive").shouldHave(text("Male"));
        $(".table-responsive").shouldHave(text(Number));
        $(".table-responsive").shouldHave(text("16 October,1985"));
        $(".table-responsive").shouldHave(text(subjects));
        $(".table-responsive").shouldHave(text(hobby));
        $(".table-responsive").shouldHave(text(picture));
        $(".table-responsive").shouldHave(text(address));
        $(".table-responsive").shouldHave(text("Haryana Karnal"));
    }
}
