package search;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.UnsupportedCharsetException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SearchInfo {

	private String searchUrl;
	private String searchTitle;
	private String searchCity;
	private String reciever;
	private String items;
	private Date lastSearch;

	
	/*
	 * Setters 
	 */
	public void setSearchUrl(String url){
		this.searchUrl = url;
	}
	
	public void setSearchTitle(String title){
		this.searchTitle = title;
	}
	
	public void setReciever(String rev){
		this.reciever = rev;
	}
	
	public void setItems(String str) throws UnsupportedEncodingException {
		//need to url encode this for jsoup
		this.items = URLEncoder.encode(str, "UTF-8");
	}
	
	public void setLastSearch(Date d){
		this.lastSearch = d;
	}
	
	public void setSearchCity(String city){
		this.searchCity = city;
		this.searchUrl = "http://" + city + ".craigslist.org/";
	}
	
	/*
	 * Getters
	 */
	public String getSearchUrl(){
		return searchUrl;
	}
	
	public String getSearchTitle(){
		return searchTitle;
	}
	
	public String getReciever(){
		return reciever;
	}
	
	public String getItems(){
		return items;
	}
	
	public Date getLastSearch(){
		return lastSearch;
	}
	
	public String getSearchCity(){
		return searchCity;
	}

	//ctor
	public SearchInfo(String city, String title, String rec, String it, Date lastSearched) throws UnsupportedEncodingException{
		this.searchTitle = title;
		this.reciever = rec;
		this.lastSearch = lastSearched;
		this.setSearchCity(city);
		setItems(it);
	}
	
	public Map<String, Object> dumpMap(){
		HashMap<String, Object> retMap = new HashMap<String, Object>();
		
		retMap.put("lastSearch", getLastSearch());
		retMap.put("searchTitle", getSearchTitle());
		retMap.put("searchCity", getSearchCity());
		retMap.put("reciever", getReciever());
		
		try{
			retMap.put("items", URLDecoder.decode(getItems(), "UTF-8"));
		}
		catch(UnsupportedEncodingException ex){
			ex.printStackTrace();
		}
		
		return retMap;
	}

}
