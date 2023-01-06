package fatk.idelka.specs.users;

import io.restassured.specification.RequestSpecification;

import static helpers.AllureCustomListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;

public class UsersRequest {
    public static RequestSpecification userRequestSpec =
            with()
                    .filters(withCustomTemplates())
                    .baseUri("https://petstore.swagger.io/v2")
                    .contentType(JSON)
                    .log().all();
}
