package json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import response.Location;
import response.Temperature;
import response.Time;
import response.Weather;
import response.WeatherData;

public class ParseJson {
	static InputStream is = null;
	static JSONObject jObj = null;
	static String json = "";
	private static String url = "http://api.wunderground.com/api/2a597e5b69719297/";

	// JSON Node names

	// contacts JSONArray
	String weatherData = null;
	
	
	public WeatherData jsonParser()
	{
		WeatherData wd;
		JSONObject jsonObject;
		JSONObject jObj,qq;
		String query="";
		String y="yo" ,s="so";
		String weatherData = getWeatherData(query);
		try {
			//JSONArray jsonArray = new JSONArray("display_location");
			/*
			 * Log.i(ParseJSON.class.getName(), "Number of entries " +
			 * jsonArray.length());
			 */
			// for (int i = 0; i < jsonArray.length(); i++) {
			qq = new JSONObject();
			jsonObject = new JSONObject(weatherData);
			//JSONArray jsonArray = new JSONArray(jsonObject.toString());
			jObj = jsonObject.getJSONObject("current_observation");
			s =jObj.toString();
			//JSONArray jsonArray = new JSONArray(jObj.toString());
			
			
			y = new String(s);
			qq = jObj.getJSONObject("display_location");
			y=s;
			JSONObject jo = new JSONObject();
			jo = jsonObject.getJSONObject("current_observation").getJSONObject("display_location");
			//JSONArray jsonArray = new JSONArray(jObj.toString());
			//String z = jsonObject.getString("full");
			//y= jsonArray.getString(0);
			y = jObj.getString("temp_c");
			s = jObj.getString("temp_f");
			jo = jsonObject.getJSONObject("current_observation").getJSONObject("display_location");
			// Log.i(ParseJSON.class.getName(), jsonObject.getString("text"));
			// }
			
			//wd.getWeather().setTemp(new Temperature(y,s));
		} catch (Exception e) {
			e.printStackTrace();
		}
		wd = new WeatherData(new Location(), new Time(), new Weather("cool", "very humid", new Temperature(y, s)));
		return wd;
	}
	
	public String getWeatherData(String query) {
		StringBuilder builder = new StringBuilder();
		HttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(
				"http://api.wunderground.com/api/2a597e5b69719297/conditions/q/CA/San_Francisco.json");
		try {
			HttpResponse response = client.execute(httpGet);
			StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			if (statusCode == 200) {
				HttpEntity entity = response.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(content));
				String line;
				while ((line = reader.readLine()) != null) {
					builder.append(line);
				}
			} else {
				// Log.e(ParseJSON.class.toString(), "Failed to download file");
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return builder.toString();
	}

}
