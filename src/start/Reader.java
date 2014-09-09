package start;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import search.SearchInfo;



public class Reader {

	//private static final String fileName = "etc/www/clScraper/searches.xml";
	//use the file from Start.confFile
	
	public ArrayList<SearchInfo> getSearchInfo() throws UnsupportedEncodingException {
		ArrayList<SearchInfo> al = new ArrayList<SearchInfo>();
		//need to add in yaml reading here
		SearchInfo si = new SearchInfo("", "", "", "");
		si.setRecever("isaacspl@gmail.com");
		si.setSearchTitle("poop");
		si.setSearchUrl("http://minneapolis.craigslist.org/");
		si.setItems("sleep number king");
		al.add(si);
		//to here
		return al;
	}

}
