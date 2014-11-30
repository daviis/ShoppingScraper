package start;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;


import search.SearchInfo;



public class Reader {
	private static final String fileName = "/etc/www/shoppingScraper/searches.yaml";
	private Yaml yamlFile;
	private ArrayList<SearchInfo> infoList;
	
	public Reader() throws ParseException{
		infoList = new ArrayList<SearchInfo>();
		yamlFile = new Yaml();
		try{
			loadFile();
		}
		catch(IOException ex){
			System.err.println("Server not configured correctly. Create yaml file at " + fileName);
		}
	}
	
	@SuppressWarnings("unchecked")
	private void loadFile() throws IOException, ParseException{
		FileInputStream yamlInputStream = null;
		try{
			File aFile = new File(fileName);
			yamlInputStream = new FileInputStream(aFile);
			
			Map<String, Object> dataMap = (Map<String, Object>) yamlFile.load(yamlInputStream);
			for(Map.Entry<String, Object> key : dataMap.entrySet()){
				Map<String, Object> objectMap = (Map<String, Object>)key.getValue();
				Date date = (Date) objectMap.get("lastSearch");
				SearchInfo si = new SearchInfo((String)objectMap.get("searchCity"), 
						(String)objectMap.get("searchTitle"), 
						(String)objectMap.get("reciever"), 
						(String)objectMap.get("items"), 
						date,
						objectMap.get("minPrice").toString(), 
						objectMap.get("maxPrice").toString());
				infoList.add(si);
			}
		}
		finally{
			yamlInputStream.close();
		}
	}
	
	public ArrayList<SearchInfo> getSearchInfo() throws UnsupportedEncodingException {
		return infoList;
	}
	
	static String getFileName(){
		return fileName;
	}
	
}