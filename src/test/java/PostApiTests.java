import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class PostApiTests extends TestBase {

    @DisplayName("Проверка создания пользователя")
    @Test
    void postCreateUserTest() {

        String user = "{ \"name\": \"stiven\", \"job\": \"QA\" }";
        given()
                .log().uri()
                .contentType(JSON)
                .body(user)
        .when()
                .post("users/2")
        .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("stiven"))
                .body("job", is("QA"))
                .body("id", notNullValue())
                .body("createdAt", notNullValue());
    }

    @DisplayName("Проверка успешной регистрации")
    @Test
    void checkSuccsefullRegisterTest() {

        String user = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\" }";
        given()
                .log().uri()
                .contentType(JSON)
                .body(user)
        .when()
                .post("register")
        .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("id", notNullValue())
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @DisplayName("Проверка успешной ошибки при отсутствии пароля")
    @Test
    void checkSuccsefullMissingPasswordTest() {

        String user = "{ \"email\": \"eve.holt@reqres.in\"}";
        given()
                .log().uri()
                .contentType(JSON)
                .body(user)
        .when()
                .post("register")
        .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing password"));
    }
}
