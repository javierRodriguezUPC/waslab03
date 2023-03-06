package fib.asw.waslab03;

import org.apache.hc.client5.http.fluent.Request;
import org.json.JSONArray;
import org.json.JSONObject;

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
			    String name = (String) explrObject.get("name");
				System.out.println(name);
				
				String tweets = Request.get("https://mastodont.cat/api/v1/timelines/tag/" + name + "?limit=5")
						.addHeader("Authorization","Bearer "+TOKEN)
						.execute()
						.returnContent()
						.asString();

				
				
				/*JSONArray JSONTweets = new JSONArray(tweets);
			    System.out.println(JSONTweets);		
			    
			    
			    
				for (int j = 0; j < JSONTweets.length(); j++) {
				    JSONObject tweet = JSONTweets.getJSONObject(j);
				    //String asasas = (String) explrObject2.get("display_name");
				    //System.out.println(result2);					
				}*/
				
			}
			

		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
