package start;

import java.io.UnsupportedEncodingException;
import java.util.*;

import email.Email;
import search.*;

public class Start {
	static final String confFile = "etc/www/clScraper/searches.xml";
	static Date lastSearch = new Date();
	
	public Start(){
		Reader read = new Reader();
		try{
			ArrayList<SearchInfo> si = read.getSearchInfo();
			for(SearchInfo info : si){
				Search oneSearch = new Search(info);
				ArrayList<Post> links = oneSearch.findLinks();
				
				Email mail = new Email(info.getSearchTitle(), info.getRecever(), links);
				mail.send();
				//mail.sysPrint();
			}
		}
		catch (UnsupportedEncodingException ex){
			Email errormail = new Email("Craigslist scraper broke", "isaacspl@gmail.com", ex.getMessage());
			errormail.errorSend();
		}
	}
	
	public static void main(String[] args){
		new Start();
	}

}
