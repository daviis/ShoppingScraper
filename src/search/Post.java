package search;

/*
 * This is a container for an item that was posted to craigslist.
 * 
 */


public class Post {
	public String href;
	public String title;
	public String price;
	
	public Post(String link, String ttl, String money){
		href = link;
		title = ttl;
		if (money.equals("")){
			price = "?";
		}
		else{
			price = money;
		}
	}
	
	public String toString(){
		return (price + " : "  + title + " : " + href);
	}
	
	public void sysPrint(){
		System.out.println(href + " : " + title + " : " + price);
	}
	
}
