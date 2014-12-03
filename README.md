A educatoinal project that is not intended for production use. 

#Shopping Scrapper

The goal of the project is to email a user new posts that are about products they are searching for. It currently only targets Craigslist becuase they do not expose an API and their cite is very useful.

This project utilises the following external libraries
* [SnakeYaml] (https://code.google.com/p/snakeyaml/downloads/list) 
* [JavaMail] (http://www.oracle.com/technetwork/java/javamail/index-138643.html)
* [JSoup] (http://jsoup.org/download)

###Input yaml file

Every entry starts with a left alligned number followed by a colon. The actual number isn't important but there does need to be one and it needs to be unique. Then there needs to be all of these camalcase phrases: searchCity, reciever, items, maxPrice, lastSearch, minPrice and searchTitle.

The acceptable data for the entries are listed below
* searchCity : any city that there is a CraigsList for. To find these you can go to craigslist.org and they appear on the left hand side.
* reciever : this is an email address that you wish to reciever posts for. As this isn't a production project it doesn't allow users to unsubscribe or verify ownership of the email account they list.
* items : this can be any string for a product that a user would want. This should be the same sort of thing that would be entered in the generic "search box" when interacting with CL in person.
* maxPrice : a positive integer or the word none or no or false if there is no upper limit on price.
* lastSearch : usually the system updates this field after every search but for the initial one it needs to be primed with a date. One suggestion is to go YYYY-MM-DD so an example would be 2014-12-01 but this field is somewhat flexable.
* minPrice : a positive integer or the word none or no or false if there is no lower limit on price.
* searchTitle : the subject line of the email that will be send.

This file should be located on the host at and be called /etc/www/shoppingScrapper/searches.yaml

