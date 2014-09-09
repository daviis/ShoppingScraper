package search;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


public class SearchInfo {

	private String searchUrl;
	private String searchTitle;
	private String recever;
	private String items;
	
	/*
	 * Setters 
	 */
	public void setSearchUrl(String url){
		this.searchUrl = url;
	}
	
	public void setSearchTitle(String title){
		this.searchTitle = title;
	}
	
	public void setRecever(String rev){
		this.recever = rev;
	}
	
	public void setItems(String str) throws UnsupportedEncodingException {
		//need to url encode this for jsoup
		this.items = URLEncoder.encode(str, "UTF-8");
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
	
	public String getRecever(){
		return recever;
	}
	
	public String getItems(){
		return items;
	}

	//ctor
	public SearchInfo(String url, String title, String rec, String it) throws UnsupportedEncodingException{
		this.searchUrl = url;
		this.searchTitle = title;
		this.recever = rec;
		setItems(it);
	}

}
