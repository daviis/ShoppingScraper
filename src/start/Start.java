package start;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.*;

import email.Email;
import search.*;

public class Start {
	static Date lastSearch = new Date();
	
	public Start() throws ParseException{
		Reader read = new Reader();
		try{
			ArrayList<SearchInfo> si = read.getSearchInfo();
			for(SearchInfo info : si){
//				Search oneSearch = new Search(info);
//				ArrayList<Post> links = oneSearch.findLinks();
//				
//				Email mail = new Email(info.getSearchTitle(), info.getRecever(), links);
//				mail.send();
				//mail.sysPrint();
				
			}
			ScrWriter wr = new ScrWriter(si);
			wr.writeUpdate();
			
		}
		catch (UnsupportedEncodingException ex){
			Email errormail = new Email("Craigslist scraper broke", "isaacthrow99@gmail.com", ex.getMessage());
			errormail.errorSend();
		}
	}
	
	public static void main(String[] args) throws ParseException{
		new Start();
	}

}
