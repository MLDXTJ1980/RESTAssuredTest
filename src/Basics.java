import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.testng.Assert;
import files.payLoad;
import files.ReusableMethod;
public class Basics {

	public static void main(String[] args) throws IOException {
		//given - all input details
		//when - Submit the API 
		//Then  - validate the response
		System.out.println("================add a place===============");
		// add a place
		RestAssured.baseURI="https://rahulshettyacademy.com";
		Response response = given().queryParam("key", "qaclick123")
			   .header("Content-Type","application/json")
			   //.body(payLoad.AddPlace())
			   .body(new String(Files.readAllBytes(Paths.get("C:\\workspace\\restAssuredOnlineKurs\\addPlace.json"))))
			   .when().post("/maps/api/place/add/json")
			   .then().log().all().assertThat().statusCode(200).body("scope",equalTo("APP"))
			   .header("Server", "Apache/2.4.18 (Ubuntu)").extract().response();
		
		JsonPath js = ReusableMethod.rawToJson(response);   // for parsing Json
		String placeId = js.getString("place_id");
		System.out.println(placeId);
		
		System.out.println("================update a place===============");
		// update a place 
		given().log().all().queryParam("key", "qaclick123")
		.header("Content-Type","application/json")
		.body("{\r\n" + 
				"\"place_id\":\""+placeId+"\",\r\n" + 
				"\"address\":\"70 Summer walk, USA\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}").
		when().put("/maps/api/place/update/json")
		.then().assertThat().statusCode(200).body("msg",equalTo("Address successfully updated"));
		
		System.out.println("================get a place===============");
		// get a place 
		Response getPlaceResponse = given().log().all().queryParam("key", "qaclick123")
		.queryParam("place_id", placeId)
		.when().get("/maps/api/place/get/json")
		.then().assertThat().log().all().statusCode(200).extract().response();
		
		JsonPath js1 = ReusableMethod.rawToJson(getPlaceResponse);
		String actualAddress = js1.getString("address"); 
		System.out.println(actualAddress);
		Assert.assertEquals(actualAddress, "70 Summer walk, USA");
	}

}
