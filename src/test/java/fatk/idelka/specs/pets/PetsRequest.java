package fatk.idelka.specs.pets;

import io.restassured.specification.RequestSpecification;

import static helpers.AllureCustomListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;

public class PetsRequest {
    public static RequestSpecification petsRequestSpec = with()
            .baseUri("https://petstore.swagger.io/v2")
            .filter(withCustomTemplates())
            .contentType(JSON)
            .log().all();
}
