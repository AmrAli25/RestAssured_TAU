package examples;

import dataentities.Location;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class Chapter6Test {

    @Test
    public void checkPlaceNameInResponseBody(){
        Location location =
                given().when().get("http://api.zippopotam.us/us/90210").
                        as(Location.class);
        assertEquals("Beverly Hills", location.getPlaces().getFirst().getPlaceName());
    }
}
