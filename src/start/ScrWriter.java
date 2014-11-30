package start;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import search.SearchInfo;

class ScrWriter {
	private ArrayList<SearchInfo> sis;
	
	ScrWriter(ArrayList<SearchInfo> sInfo){
		this.sis = sInfo;
	}
	
	
	void writeUpdate(){
		Date curTime = new Date();
		
		DumperOptions options = new DumperOptions();
		options.setIndent(4);
		Yaml yaml = new Yaml(options);
		
		StringBuilder output = new StringBuilder();
		output.append("---\n");

		Integer counter = 1;
		Map<Integer, Map<String, Object>> fullMap = new HashMap<Integer, Map<String, Object>>();
		for(SearchInfo si : sis){
			si.setLastSearch(curTime);
			Map<String, Object> oneMap = si.dumpMap();
			fullMap.put(counter, oneMap);
			counter++;
		}
		output.append(yaml.dumpAsMap(fullMap));
		

		PrintStream printer = null;
		try{
			printer = new PrintStream(new BufferedOutputStream(new FileOutputStream(new File(Reader.getFileName()))));
			printer.print(output.toString());
		}
		catch(FileNotFoundException ex){
			ex.printStackTrace();
		}
		finally{
			printer.close();
		}
		
			
	}
	
}