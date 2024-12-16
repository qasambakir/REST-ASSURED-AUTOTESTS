import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;



public class GetApiTests {

    @DisplayName("Проверка стаус кода 404")
    @Test
    void checkGetStatusCode404Test() {
        given()
                .log().uri()
        .when()
                .get("https://reqres.in/api/users/23")
        .then()
                .log().status()
                .log().body()
                .statusCode(404);
    }

    @DisplayName("Проверка Юзера")
    @Test
    void checkGetSingleUserTest() {
        given()
                .log().uri()
        .when()
                .get("https://reqres.in/api/users/2")
        .then()
                .log().status()
                .log().body()
                .statusCode(200);
    }
}
