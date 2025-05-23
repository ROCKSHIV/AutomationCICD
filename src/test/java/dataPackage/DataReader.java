package dataPackage;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
	
	public List<HashMap<String,String>> getJsonDataToMap() throws IOException {
		
		//1) read json to string
		File file = new File("F:\\Eclipse\\SeleniumFrameworkDesign\\src\\test\\java\\dataPackage\\PurchaseOrder.json");
		String jsonContent= FileUtils.readFileToString(file,StandardCharsets.UTF_8);
		//here the utf 8 is the  String encoding type 
	//2)String to HashMap (add dependencies)-> Jackson Databind
	
	ObjectMapper mapper = new ObjectMapper();
	List<HashMap<String,String>> data = mapper.readValue(jsonContent,new TypeReference<List<HashMap<String,String>>>(){
		
	});
	
	return data;
	
	}

}
