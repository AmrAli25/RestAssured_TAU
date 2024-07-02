package examples;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

public class Chapter4Test {

    public RequestSpecification requestSpec;
    public ResponseSpecification responseSpec;


    @BeforeClass
    public void createRequestSpecification() {     // this is the request specs create method
        requestSpec = new RequestSpecBuilder().
                setBaseUri("http://api.zippopotam.us").
                build();
    }

    @BeforeClass
    public void createResponseSpecifications() {           // this is the response specs create method
        responseSpec = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                build();
    }

    @Test
    public void checkStatusCodeInResponseBody() {
        given().
                spec(requestSpec).      // the request spec is used after the given() function
                when().
                get("us/90210").
                then().
                assertThat().
                statusCode(200);
    }

    @Test
    public void checkPlaceNameInResponseBody() {
        given().
                spec(requestSpec). // the request spec is used after the given() function
                when().
                get("us/90210").
                then().
                spec(responseSpec).and().   // the response spec is after the then() function
                assertThat().
                body("places[0].'place name'", equalTo("Beverly Hills"));
    }

    @Test
    public void extractPlaceNameFromResponseBody() {
        String placeName = given().
                spec(requestSpec). // the request spec is used after the given() function
                when().
                get("us/90210").
                then().
                extract().path("places[0].'place name'");
        assertEquals(placeName, "Beverly Hills");
    }

}
