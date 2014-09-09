package search;

import java.io.IOException;
import java.util.ArrayList;

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
			Integer counter = 0;
			for (Element row : rows) {
				Elements title = row.select("span[class*=pl]").select("a");
				Elements price = row.select("span[class*=l2]").select("span[class*=price]");
				Post pst = new Post(info.getSearchUrl()+title.attr("href"), title.text(), price.text());
				counter++;
				links.add(pst);
				
			}
	 
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(Post lnk : links){
			lnk.sysPrint();
		}
		return links;
	}
}
