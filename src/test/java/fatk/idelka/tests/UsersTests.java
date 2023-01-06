package fatk.idelka.tests;

import fatk.idelka.helpers.RandomUtils;
import fatk.idelka.models.users.User;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static fatk.idelka.helpers.users.UsersFactory.addNewUserToStore;
import static fatk.idelka.helpers.users.UsersFactory.createNewUser;
import static fatk.idelka.specs.users.UsersRequest.userRequestSpec;
import static fatk.idelka.specs.users.UsersResponse.userResponseSpec;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тестирование функций пользователя")
public class UsersTests {

    @DisplayName("Создаем нового пользователя")
    @Test
    void createUserTest() {
        User userProfile = createNewUser();
        Response newUserInStore = addNewUserToStore(userProfile);

        step("Сервер должен прислать в ответе Id созданного пользователя", () -> {
            assert newUserInStore.path("message").equals(userProfile.getId().toString());
        });
    }

    @DisplayName("Получаем информацию о пользователе по username")
    @Test
    void getUserByIdTest() {
        User userProfile = createNewUser();
        addNewUserToStore(userProfile);

        step("Отправляем GET запрос", () -> {
            User user = given()
                    .spec(userRequestSpec)
                .when()
                    .get("/user/" + userProfile.getUsername())
                .then()
                    .spec(userResponseSpec)
                    .statusCode(200)
                    .body(matchesJsonSchemaInClasspath("schemas/users/getUserSchema.json"))
                    .extract().as(User.class);

            assertThat(user.getUsername()).isEqualTo(userProfile.getUsername());
            assertThat(user.getEmail()).isEqualTo(userProfile.getEmail());
        });
    }

    @DisplayName("Изменяем информацию о пользователе")
    @Test
    void updateUserInfoTest() {
        User userProfile = createNewUser();
        addNewUserToStore(userProfile);

        step("Отправляем PUT запрос на изменение данных", () -> {
            userProfile.setFirstName(RandomUtils.getFirstname());
            userProfile.setEmail(RandomUtils.getEmail());

            given()
                    .spec(userRequestSpec)
                .when()
                    .body(userProfile)
                    .put("/user/" + userProfile.getUsername())
                .then()
                    .spec(userResponseSpec)
                    .statusCode(200);

            step("Проверяем информацию о юзере", () -> {
                User user = given()
                        .spec(userRequestSpec)
                    .when()
                        .get("/user/" + userProfile.getUsername())
                    .then()
                        .spec(userResponseSpec)
                        .statusCode(200)
                        .body(matchesJsonSchemaInClasspath("schemas/users/getUserSchema.json"))
                        .extract().as(User.class);

                assertThat(user.getFirstName()).isEqualTo(userProfile.getFirstName());
                assertThat(user.getEmail()).isEqualTo(userProfile.getEmail());
            });
        });
    }
}
