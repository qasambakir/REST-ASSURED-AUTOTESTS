package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static specs.BaseSpec.requestSpec;

@Tag("API-TEST")
@DisplayName("Тестирование GET")
public class UserApiTests extends TestBase {

    @Test
    @DisplayName("Проверка получения существующего пользователя")
    void getSingleUserTest() {
        step("Отправляем GET-запрос для пользователя с ID 2", () ->
                given(requestSpec)
                        .when()
                        .get("/users/2")
                        .then()
                        .statusCode(200)
                        .log().body()
        );
    }

    @Test
    @DisplayName("Проверка ошибки для несуществующего пользователя")
    void userNotFoundTest() {
        step("Отправляем GET-запрос для несуществующего пользователя с ID 23", () ->
                given(requestSpec)
                        .when()
                        .get("/users/23")
                        .then()
                        .statusCode(404)
                        .log().body()
        );
    }
}