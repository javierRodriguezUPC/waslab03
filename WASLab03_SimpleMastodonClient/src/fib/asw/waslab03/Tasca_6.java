package fib.asw.waslab03;

import org.apache.hc.client5.http.fluent.Request;
import org.json.JSONArray;
import org.json.JSONObject;

import jdk.nashorn.internal.parser.JSONParser;

public class Tasca_6 {

	public static void main(String[] args) {
		
		String URI_trends = "https://mastodont.cat/api/v1/trends/tags?limit=10";

		String TOKEN = Token.get();

		try {
			String trends = Request.get(URI_trends)
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
						.addHeader("Authorization","Bearer "+TOKEN)
						.execute()
						.returnContent()
						.asString();
				
				
				JSONArray JSONTweets = new JSONArray(tweets);
			    
				for (int j = 0; j < JSONTweets.length(); j++) {
				    JSONObject tweet = JSONTweets.getJSONObject(j);
				    //String display_name = tweet.getString("display_name");
				    JSONObject account = tweet.getJSONObject("account");
				    String acct = (String) account.getString("acct");
				    String content = (String) tweet.getString("content");
					content = content.replaceAll("<[^>]*>", "");

				    System.out.println("- " + "display_name" + "(" + acct + "): " + content);	
				    System.out.println("-------------------------------------------------");			
				}
				
			}
			

		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
