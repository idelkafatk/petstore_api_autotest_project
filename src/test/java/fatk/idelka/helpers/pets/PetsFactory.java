package fatk.idelka.helpers.pets;

import fatk.idelka.models.pets.Pet;
import io.qameta.allure.Step;

import static fatk.idelka.helpers.RandomUtils.*;
import static fatk.idelka.specs.pets.PetsRequest.petsRequestSpec;
import static fatk.idelka.specs.pets.PetsResponse.petsResponseSpec;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class PetsFactory {

    @Step("Создаем нового питомца")
    public static Pet createNewPet() {
        Pet petsData = new Pet();
        petsData.setId(id);
        petsData.setName(petName);
        petsData.setPhotoUrls(getPhotoUrls());
        return petsData;
    }

    @Step("Добавляем нового питомца в магазин")
    public static Pet addNewPetToStore(Pet petInfo) {
        return given()
                .spec(petsRequestSpec)
                .when()
                .body(petInfo)
                .post("/pet")
                .then()
                .spec(petsResponseSpec)
                .body(matchesJsonSchemaInClasspath("schemas/pets/postPetSchema.json"))
                .statusCode(200)
                .extract().as(Pet.class);
    }

}
