package tests;

import models.lombok.UserData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static specs.BaseSpec.*;

@Tag("API-TEST")
@DisplayName("Тестирование GET")
public class UserApiTests extends TestBase {

    @Test
    @DisplayName("Проверка получения существующего пользователя")
    void getSingleUserTest() {
        UserData user = step("Отправляем GET-запрос для пользователя с ID 2", () ->
                given(requestSpec)
                        .when()
                        .get("/users/2")
                        .then()
                        .spec(responseSpec200)
                        .log().body()
                        .extract().jsonPath()
                        .getObject("data", UserData.class)
        );

        step("Проверяем данные пользователя", () -> {
            assertThat(user).isNotNull();
            assertThat(user.getId()).isEqualTo(2);
            assertThat(user.getEmail()).isNotEmpty();
            assertThat(user.getFirst_name()).isNotEmpty();
            assertThat(user.getLast_name()).isNotEmpty();
        });
    }

    @Test
    @DisplayName("Проверка ошибки для несуществующего пользователя")
    void userNotFoundTest() {
        step("Отправляем GET-запрос для несуществующего пользователя с ID 23", () ->
                given(requestSpec)
                        .when()
                        .get("/users/23")
                        .then()
                        .spec(responseSpec404)
                        .log().body()
        );
    }
}


