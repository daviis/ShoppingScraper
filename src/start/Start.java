package start;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;

import email.Email;
import search.*;

public class Start {
	private final int MAX_PAGE_LENGTH = 5;
	private final boolean DONT_SEND_WHEN_EMPTY = true;
	
	public Start() throws ParseException{
		Reader read = new Reader();
		try{
			ArrayList<SearchInfo> si = read.getSearchInfo();
			for(SearchInfo info : si){
				Search oneSearch = new Search(info, MAX_PAGE_LENGTH);
				ArrayList<Post> links = oneSearch.findLinks();
				
				if(links.size()!=0 || DONT_SEND_WHEN_EMPTY){
					Email mail = new Email(info.getSearchTitle(), info.getReciever(), links);
					mail.send();
					mail.sysPrint();
				}
				
			}
			ScrWriter wr = new ScrWriter(si);
			wr.writeUpdate();
			
		}
		catch (IOException ex){
			Email errormail = new Email("Craigslist scraper broke", "isaacthrow99@gmail.com", ex.getMessage());
			errormail.errorSend();
		}
	}
	
	public static void main(String[] args) throws ParseException{
		new Start();
	}

}
