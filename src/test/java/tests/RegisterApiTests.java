package tests;

import models.lombok.LoginRequestModel;
import models.lombok.LoginResponseModel;
import models.lombok.ErrorResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static specs.BaseSpec.*;

@Tag("API-TEST")
@DisplayName("Тестирование POST")
public class RegisterApiTests extends TestBase {

    @Test
    @DisplayName("Проверка успешной регистрации пользователя")
    void successfulRegisterTest() {
        LoginRequestModel requestModel = new LoginRequestModel();
        requestModel.setEmail("eve.holt@reqres.in");
        requestModel.setPassword("pistol");

        LoginResponseModel responseModel = step("Отправляем запрос на регистрацию", () ->
                given(requestSpec)
                        .body(requestModel)
                        .when()
                        .post("/register")
                        .then()
                        .spec(responseSpec200)
                        .extract().as(LoginResponseModel.class)
        );

        step("Проверяем, что токен не пустой", () ->
                assertThat(responseModel.getToken()).isNotNull());
    }

    @Test
    @DisplayName("Проверка ошибки при отсутствии пароля")
    void missingPasswordTest() {
        LoginRequestModel requestModel = new LoginRequestModel();
        requestModel.setEmail("eve.holt@reqres.in");

        ErrorResponseModel errorResponse = step("Отправляем запрос без пароля", () ->
                given(requestSpec)
                        .body(requestModel)
                        .when()
                        .post("/register")
                        .then()
                        .spec(responseSpec400)
                        .extract().as(ErrorResponseModel.class)
        );

        step("Проверяем сообщение об ошибке", () ->
                assertThat(errorResponse.getError()).isEqualTo("Missing password"));
    }
}