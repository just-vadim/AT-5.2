package ru.netology.ibank;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.util.User;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static ru.netology.util.DataGenerator.*;

public class AuthTest {

    private void inputInLoginForm(String login, String pass) {
        $("[name='login']").setValue(login);
        $("[name='password']").setValue(pass);
        $(".button").click();
    }

    @BeforeEach
    void setUp() {
        open("http://localhost:9999/");
    }

    @Test
    void shouldLoginByExistentActiveUser() {
        User user = generateValidUser(true);
        inputInLoginForm(user.getName(), user.getPassword());
        $(byText("Личный кабинет")).waitUntil(Condition.visible, 15000);
    }

    @Test
    void shouldNotLoginByNonexistentUser() {
        User user = generateUserInfo(true);
        inputInLoginForm(user.getName(), user.getPassword());
        $(withText("Неверно указан логин или пароль")).waitUntil(Condition.visible, 15000);
    }

    @Test
    void shouldNotLoginByBlockedUser() {
        User user = generateValidUser(false);
        inputInLoginForm(user.getName(), user.getPassword());
        $(withText("Пользователь заблокирован")).waitUntil(Condition.visible, 15000);
    }

    @Test
    void shouldNotLoginByExistentActiveUserIfLoginInvalid() {
        User user = generateUserWithInvalidLogin(true);
        inputInLoginForm(user.getName(), user.getPassword());
        $(withText("Неверно указан логин или пароль")).waitUntil(Condition.visible, 15000);
    }

    @Test
    void shouldNotLoginByExistentActiveUserIfPassInvalid() {
        User user = generateUserWithInvalidPass(true);
        inputInLoginForm(user.getName(), user.getPassword());
        $(withText("Неверно указан логин или пароль")).waitUntil(Condition.visible, 15000);
    }
}
