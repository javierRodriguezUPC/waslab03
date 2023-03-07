package fib.asw.waslab03;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.core5.http.ContentType;
import org.json.JSONArray;
import org.json.JSONObject;

import jdk.nashorn.internal.parser.JSONParser;

public class Tasca_6 {
	private static final String LOCALE = "ca";

	public static void main(String[] args) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 'a les' HH:mm:ss", new Locale(LOCALE));
		String now = sdf.format(new Date());

		String my_status = "Hola, @fib_asw@mastodont.cat, ja he arribat! #waslab03\n[" + now + "]";
		
		JSONObject body = new JSONObject();
		
		String URI_trends = "https://mastodont.cat/api/v1/trends/tags?limit=10";

		String TOKEN = Token.get();

		try {
			String trends = Request.get(URI_trends)
					.bodyString(body.toString(), ContentType.parse("application/json"))
					.addHeader("Authorization","Bearer "+TOKEN)
					.execute()
					.returnContent()
					.asString();
			
			
			JSONArray result = new JSONArray(trends);
			
			for (int i = 0; i < result.length(); i++) {
			    JSONObject explrObject = result.getJSONObject(i);
			    String name = (String) explrObject.getString("name");
			    

				System.out.println("*************************************************");
				System.out.println("tags: " + name);
				System.out.println("*************************************************");
				
				String tweets = Request.get("https://mastodont.cat/api/v1/timelines/tag/" + name + "?limit=5")
						.bodyString(body.toString(), ContentType.parse("application/json"))
						.addHeader("Authorization","Bearer "+TOKEN)
						.execute()
						.returnContent()
						.asString();
				
				
				JSONArray JSONTweets = new JSONArray(tweets);
			    
				for (int j = 0; j < JSONTweets.length(); j++) {
				    JSONObject JSONtweet = JSONTweets.getJSONObject(j);
				    String id = JSONtweet.getString("id");
				    JSONObject account = JSONtweet.getJSONObject("account");
				    String acct = (String) account.getString("acct");
				    String display_name = account.getString("display_name");
				    String content = (String) JSONtweet.getString("content");
					content = content.replaceAll("<[^>]*>", "");
					

				    System.out.println("- " + display_name + "(" + acct + "): " + content);	
				    
				    System.out.println("-------------------------------------------------");			
				}
				
			}
			

		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
