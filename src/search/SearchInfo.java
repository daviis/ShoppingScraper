package search;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
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
	private String minPrice;
	private String maxPrice;

	
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
	
	public void setItems(String str) throws IOException {
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
	
	public void setMinPrice(String min){
		this.minPrice = min;
	}
	
	public void setMaxPrice(String max){
		this.maxPrice = max;
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
	
	public String getMinPrice(){
		return this.minPrice;
	}
	
	public String getMaxPrice(){
		return this.maxPrice;
	}

	//ctor
	public SearchInfo(String city, String title, String rec, String it, Date lastSearched, String min, String max) throws IOException{
		this.searchTitle = title;
		this.reciever = rec;
		this.lastSearch = lastSearched;
		this.setSearchCity(city);
		this.setItems(it);
		this.setMinPrice(min);
		this.setMaxPrice(max);
	}
	
	public Map<String, Object> dumpMap() throws IOException{
		HashMap<String, Object> retMap = new HashMap<String, Object>();
		
		retMap.put("lastSearch", getLastSearch());
		retMap.put("searchTitle", getSearchTitle());
		retMap.put("searchCity", getSearchCity());
		retMap.put("reciever", getReciever());
		retMap.put("items", URLDecoder.decode(getItems(), "UTF-8"));
		retMap.put("minPrice", getMinPrice());
		retMap.put("maxPrice", getMaxPrice());
		
		return retMap;
	}

}
