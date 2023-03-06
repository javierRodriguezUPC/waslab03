package fib.asw.waslab03;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.core5.http.ContentType;
import org.json.JSONObject;

public class Tasca_5 {

	public static void main(String[] args) {
		String URI = "https://mastodont.cat/api/v1/accounts/109862447110628983/statuses?limit=1";
		String URI2 = "https://mastodont.cat/api/v1/statuses/";
		String TOKEN = Token.get();

		try {
			String output = Request.get(URI)
					.addHeader("Authorization","Bearer "+TOKEN)
					.execute()
					.returnContent()
					.asString();
			

			JSONObject result = new JSONObject(output.substring(1));
			String content = result.getString("content");
			content = content.replaceAll("<[^>]*>", "");
			System.out.println(content);
			
			String id = result.getString("id");
			
			String output2 = Request.post(URI2+id+"/reblog")
					.addHeader("Authorization","Bearer "+TOKEN)
					.execute()
					.returnContent()
					.asString();

		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
