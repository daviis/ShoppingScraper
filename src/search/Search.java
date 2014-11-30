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
	
	public Search(SearchInfo anInfo){
		this.info = anInfo;
	}
	
	public ArrayList<Post> findLinks(){
		ArrayList<Post> links = new ArrayList<Post>();
		Document doc;
		try {
	 
			// need http protocol
			String url = info.getSearchUrl()+"search/"+ "?sort=date&areaID=19&query=" + info.getItems() + "&catAbb=sss";
			System.out.println(url);
			doc = Jsoup.connect(url).get();
	 
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
					break;
				}
				
			}
	 
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(Post lnk : links){
			lnk.sysPrint();
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
