package files;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import files.payLoad;
import files.ReusableMethod;




public class DynamicJson {
	
	@Test
	public void addBook() {
		
		        RestAssured.baseURI = "http://216.10.245.166";

		        // add a book
				String resp = given().log().all()
				.header("Content-Type","application/json")
				.body(payLoad.Addbook())
				.when()
				.post("/Library/Addbook.php")
				.then().log().all().assertThat().statusCode(200)
				.extract().response().asString();
				
				System.out.println(resp);
								 
//				JsonPath js = ReusableMethod.rawToJson(resp);
//				String id = js.get("ID");
//				System.out.println(id);
				
				// delete a book
				
	}
	
//	@DataProvider(name = "BooksData")
//	public Object[][] getData(){
//		// array = collection of elements
//		// multidimensional array = collection of arrays
//		return new Object[][]{{"cat","1452"},{"dog","2046"},{"maus","2356"}};
//		
//	}
	

	

}
