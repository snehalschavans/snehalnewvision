package snehalacademy.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

//import net.bytebuddy.jar.asm.TypeReference;

public class DataReader {
	
	public List<HashMap<String, String>> getJsondataToMap() throws IOException
	{
		//read json to String
		String JsonData=FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//src//test//java//snehalacademy//data//PurchaseOrder.json")
				,StandardCharsets.UTF_8);
		//Convert String to Hashmap by using dependency Jackson Databind
		ObjectMapper mapper=new ObjectMapper();
	List<HashMap<String,String>> data=mapper.readValue(JsonData, new TypeReference<List<HashMap<String,String>>>(){});
	
	
	//return {map,map}
	return data;
	
		
	}
	
	

}
