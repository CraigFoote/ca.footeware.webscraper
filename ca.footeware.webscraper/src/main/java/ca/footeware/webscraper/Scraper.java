/**
 * 
 */
package ca.footeware.webscraper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author Footeware.ca
 *
 */
public class Scraper {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Document doc = null;
		try {
			doc = Jsoup.connect("http://footeware.ca/gear").get();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Map<String, String> fields = new HashMap<String, String>();

		Element gearList = doc.getElementById("gearList");
		Elements gearItems = gearList.getElementsByTag("li");

		for (Element gearItem : gearItems) {
			Elements links = gearItem.getElementsByTag("a");
			for (Element link : links) {
				String name = link.text();
				String href = link.attr("href");
				fields.put(name, href);
			}
		}

		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(fields);

		System.out.println(json);
	}

}