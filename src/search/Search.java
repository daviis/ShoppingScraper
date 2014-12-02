package search;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Search {
	private SearchInfo info;
	private int maxPageCount;
	
	public Search(SearchInfo anInfo, int maxLength){
		this.info = anInfo;
		this.maxPageCount = maxLength;
	}
	
	public ArrayList<Post> findLinks(){
		ArrayList<Post> links = new ArrayList<Post>();
		Document doc;
		int pageCount = 0;
		boolean searchFurtherBack = true;
		try {
			while(pageCount < maxPageCount && searchFurtherBack){
				// need http protocol
				int postCount = 100 * pageCount;
				StringBuilder url = new StringBuilder(info.getSearchUrl()+"search/"+ "?sort=date&query=" + info.getItems() + "&catAbb=sss&s=" + postCount);
				
				if(info.getMinPrice().contains("no") || info.getMinPrice().contains("fal")){}
				else{
					url.append("&minAsk=" + info.getMinPrice());
				}
				if(info.getMaxPrice().contains("no") || info.getMaxPrice().contains("fal")){}
				else{
					url.append("&maxAsk=" + info.getMaxPrice());
				}
				doc = Jsoup.connect(url.toString()).get();
		 
				// get page title
		 
				// get all rows
				Elements rows = doc.select("p[class*=row]");
				//all rows
				for (Element row : rows) {
					Elements title = row.select("span[class*=pl]").select("a");
					Elements price = row.select("span[class*=l2]").select("span[class*=price]");
					String dateTime = row.select("time").attr("datetime");
					if (includePost(dateTime, this.info.getLastSearch())){
						Post pst = new Post(info.getSearchUrl()+title.attr("href"), title.text(), price.text());
						links.add(pst);}
					else{
						searchFurtherBack = false;
						break;
					}
				}
				pageCount++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return links;
	}
	
	private boolean includePost(String postTime, Date lastSearch){
		try {
			Date postTimeDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(postTime);

			return lastSearch.before(postTimeDate);
			
		} catch (ParseException e) {
			e.printStackTrace();
			return true;
		}
	}
}