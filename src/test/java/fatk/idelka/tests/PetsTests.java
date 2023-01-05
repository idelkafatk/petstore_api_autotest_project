package fatk.idelka.tests;

import fatk.idelka.helpers.RandomUtils;
import fatk.idelka.models.Pet;
import org.asynchttpclient.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static fatk.idelka.helpers.pets.PetsFactory.addNewPetToStore;
import static fatk.idelka.helpers.pets.PetsFactory.makeNewPet;
import static fatk.idelka.specs.pets.PetsRequest.petsRequestSpec;
import static fatk.idelka.specs.pets.PetsResponse.petsResponseSpec;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тестирование раздела питомцев")
public class PetsTests {

    @DisplayName("Добавляем нового питомца в магазин")
    @Test
    void createPetTest() {
        Pet petProfile = makeNewPet();
        Pet newPet = addNewPetToStore(petProfile);

        step("Проверяем, что полученные данные соответсвуют отправленным", () -> {
            assertThat(newPet.getName()).isEqualTo(petProfile.getName());
            assertThat(newPet.getId()).isEqualTo(petProfile.getId());
        });
    }

    @DisplayName("Получение информации о питомце по ID")
    @Test
    void getPetByIdTest() {
        Pet petProfile = makeNewPet();
        addNewPetToStore(petProfile);

        Pet getPetById = given()
                .spec(petsRequestSpec)
            .when()
                .get("/pet/" + petProfile.getId())
            .then()
                .spec(petsResponseSpec)
                .statusCode(200)
                .extract().as(Pet.class);

        step("Проверяем, что данные питомца соответствует заданным", () -> {
            assertThat(getPetById.getId()).isEqualTo(petProfile.getId());
            assertThat(getPetById.getName()).isEqualTo(petProfile.getName());
            assertThat(getPetById.getPhotoUrls()).isEqualTo(petProfile.getPhotoUrls());
        });
    }

    @DisplayName("Обновление информации о питомце")
    @Test
    void updatePetInformationTest() {
        Pet petProfile = makeNewPet();
        addNewPetToStore(petProfile);

        step("Меняем данные питомца", () -> {
            petProfile.setName("New_Pet_Name");
            petProfile.setPhotoUrls(RandomUtils.getPhotoUrls());
            Pet updatedPetProfile = given()
                    .spec(petsRequestSpec)
                    .when()
                    .body(petProfile)
                    .put("/pet")
                    .then()
                    .spec(petsResponseSpec)
                    .body(matchesJsonSchemaInClasspath("schemas/pets/PetSchema.json"))
                    .statusCode(200)
                    .extract().as(Pet.class);

            step("Проверяем, что данные питомца изменились", () -> {
                assertThat(updatedPetProfile.getName()).isEqualTo(petProfile.getName());
                assertThat(updatedPetProfile.getPhotoUrls()).isEqualTo(petProfile.getPhotoUrls());
            });
        });

    }

    @DisplayName("Удаление информации о питомце по ID")
    @Test
    void delPetByIdTest() {
        Pet petProfile = makeNewPet();
        Pet newPetInStore = addNewPetToStore(petProfile);

        Response response =
                given()
                .spec(requestSpecification)
            .when()
                .body(newPetInStore)
                .delete("/pet/" + newPetInStore.getId())
            .then()
                .spec(responseSpecification)
                .body(matchesJsonSchemaInClasspath("schemas/pets/deletePetSchema.json"))
                .statusCode(200)
                .extract().body().as(Response.class);
    }
}
