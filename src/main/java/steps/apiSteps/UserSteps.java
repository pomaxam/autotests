package steps.apiSteps;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.internal.matchers.IsCollectionContaining.hasItem;

public class UserSteps {
    @Step ("Обновить существующего питомца (вызов метода UPDATE) Позитивный тест")
    public void updateExistingPet() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2/pet";

        String requestBody = "{\n" +
                "  \"id\": 3,\n" +
                "  \"category\": {\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"animal\"\n" +
                "  },\n" +
                "  \"name\": \"Rexona\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"https://main-cdn.sbermegamarket.ru/big2/hlr-system/152/462/581/989/233/6/100058809183b0.jpg\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 3,\n" +
                "      \"name\": \"#dog\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}";

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post()
                .then()
                .statusCode(200) // Проверяем код состояния
                .body("id", equalTo(3)) // Проверяем, что id соответствует ожидаемому
                .body("category.id", equalTo(1)) // Проверяем, что id категории соответствует ожидаемому
                .body("category.name", equalTo("animal")) // Проверяем, что название категории соответствует ожидаемому
                .body("name", equalTo("Rexona")) // Проверяем, что имя соответствует ожидаемому
                .body("photoUrls", hasItem("https://main-cdn.sbermegamarket.ru/big2/hlr-system/152/462/581/989/233/6/100058809183b0.jpg")) // Проверяем, что URL фото соответствует ожидаемому
                .body("tags[0].id", equalTo(3)) // Проверяем, что id тега соответствует ожидаемому
                .body("tags[0].name", equalTo("#dog")) // Проверяем, что название тега соответствует ожидаемому
                .body("status", equalTo("available")); // Проверяем, что статус соответствует ожидаемому
    }
    @Step ("Найти питомца по идентификатору (Вызов метода GET) Негативный тест")
    public void testFindPetByIdNegative() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2/pet";

        long petId = 99789;

        given()
                .pathParam("petId", petId)
                .when()
                .get("/{petId}")
                .then()
                .statusCode(404) // Проверяем, что код состояния - 404 (Not Found)
                .body("type", equalTo("error")) // Проверяем, что тип ответа - ошибка
                .body("message", equalTo("Pet not found")); // Проверяем сообщение об ошибке
    }
    @Step ("Найти питомца по статусу (Вызов метода GET) Позитивный тест")
    public void testFindPetByStatusPositive() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2/pet";

        String status = "serega";

        given()
                .queryParam("status", status)
                .when()
                .get("/findByStatus")
                .then()
                .statusCode(200); // Проверяем, что код состояния - 200
    }
    @Step ("Добавление нового питомца в магазин (Вызов метода POST) Позитивный тест")
    public void testAddNewPet() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2/pet";

        String requestBody = "{\n" +
                "  \"id\": 1,\n" +
                "  \"category\": {\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"pidor\"\n" +
                "  },\n" +
                "  \"name\": \"doggie_style\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"https://cdnn1.ukraina.ru/img/07e6/0c/02/1041436899_0:0:2905:2047_1440x900_80_1_1_6fd268ee900f29e6eae4ce34429efb1c.jpg.webp?source-sid=rian_photo\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 1,\n" +
                "      \"name\": \"Bbl6LyaDoK\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}";

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post()
                .then()
                .statusCode(200) // Проверяем, что код состояния - 200 (OK)
                .body("id", equalTo(1))
                .body("category.id", equalTo(1))
                .body("category.name", equalTo("pidor"))
                .body("name", equalTo("doggie_style"))
                .body("photoUrls", hasItem("https://cdnn1.ukraina.ru/img/07e6/0c/02/1041436899_0:0:2905:2047_1440x900_80_1_1_6fd268ee900f29e6eae4ce34429efb1c.jpg.webp?source-sid=rian_photo"))
                .body("tags[0].id", equalTo(1))
                .body("tags[0].name", equalTo("Bbl6LyaDoK"))
                .body("status", equalTo("available"));

    }
    @Step ("Поиск питомца по ID (Вызов метода GET) Позитивный тест")
    public void testSearchPetByIdPositive() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2/pet";

        long petId = 4;

        given()
                .pathParam("petId", petId)
                .when()
                .get("/{petId}")
                .then()
                .statusCode(200) // Проверяем, что код состояния - 200 (OK)
                .body("id", equalTo(4))
                .body("category.id", equalTo(1))
                .body("category.name", equalTo("string"))
                .body("name", equalTo("sultan nuevo nombre"))
                .body("tags[0].id", equalTo(1))
                .body("tags[0].name", equalTo("string"))
                .body("status", equalTo("available"));
    }
}