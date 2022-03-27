package stepDefinitions;

import java.util.List;
import java.util.Map;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class StepDef {
    private static final String BASE_URL = "https://reqres.in/";

    private static String token;
    private static Response response;
    private static String jsonString;
    private static String bookId;


    @Given("listo usuarios")
    public void lista_de_usuarios() {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        response = request.get("/api/users?page=2");

        jsonString = response.asString();
        System.out.println(jsonString);
        List<Map<String, String>> users = JsonPath.from(jsonString).get("data.email");
        Assert.assertTrue(users.size() > 0);
    }

    @Given("creo usuario")
    public void crear_usuarios() {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        response = request.body("{ \"name\": \"" + "Diana" + "\", \"job\": \"" + "QA" + "\"}")
                .post("/api/users");
    }

    @Then("valido codigo de respuesta {int}")
    public void valido_codigo_de_respuesta(int codigo) {
        Assert.assertEquals(codigo,
                response.getStatusCode());
    }


    @Then("The book is removed")
    public void bookIsRemoved() {
        Assert.assertEquals(204, response.getStatusCode());

        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();

        request.header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json");

         Assert.assertEquals(200, response.getStatusCode());

        jsonString = response.asString();
        List<Map<String, String>> booksOfUser = JsonPath.from(jsonString).get("books");
        Assert.assertEquals(0, booksOfUser.size());
    }

}
