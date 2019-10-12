import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.fail;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.text.html.HTMLDocument.Iterator;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;





public class CheckStatus {



	@Test
	public void checkstatus()
	{


		SoftAssert softassertion=new SoftAssert();

		Response response =given()

				//assignimg the values through parameters
				.pathParam("name", "worldwide")
				.when()
				//Retrieving the input from api using get call
				.get("https://istheapplestoredown.com/api/v1/status/{name}")
				.then()

				//validating the response
				.statusCode(200)  
				.assertThat().contentType("application/json")
				.extract().response();



		//Parsing the JSON response

		//Adding the response to HashMap
		HashMap<String,HashMap<String,String> > jsonResponse = response.jsonPath().get("$");

		//Using Iterator to iterate the entry values
		java.util.Iterator<Entry<String,HashMap<String,String> >> hmIterator = jsonResponse.entrySet().iterator();    

		System.out.println(jsonResponse.size());
		
		//Printing the Response after parsing
		System.out.println(jsonResponse);
		
		// String countrynames = response.jsonPath().getString("name");
		//System.out.println(countrynames);

		//Checking the Status value
		while (hmIterator.hasNext()) { 
			Entry<String, HashMap<String, String>> mapElement = hmIterator.next();
			
			HashMap<String,String> countryname_status =  mapElement.getValue(); 
			//   System.out.println(countryname_status.keySet());
			// System.out.println(mapElement.getKey() + "," + countryname_status.get("name")+","+countryname_status.get("status"));	
			if(countryname_status.get("status").contentEquals("y"))
			{
				System.out.println(countryname_status.get("name"));
				assert(1==0);
			}


		} 
	}


}
