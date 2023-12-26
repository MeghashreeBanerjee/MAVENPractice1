package rahulshettyacademyMavenPractice.Data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader 
{
	
	public List<HashMap<String, String>> getJsonDataToMap() throws IOException
	{
		//read JSON to String
		String jsonContent= FileUtils.readFileToString (new File("C:\\Users\\1640\\eclipse-workspace\\MyFirstJavaProject_v1\\SeleniumFrameworkDesignMaven\\src\\test\\java\\rahulshettyacademyMavenPractice\\Data\\PurchaseOrder.json"), StandardCharsets.UTF_8);
		
		//convert String to HashMap using JACKSON DATABIND
		ObjectMapper mapper = new ObjectMapper();

		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {

		});

		return data;
		 
	}
}
