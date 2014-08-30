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

import request.HttpTask;
import response.ForeCastData;
import response.LocationInfo;
import response.Temperature;
import response.Time;
import response.Weather;
import response.WeatherData;
import response.WeatherIcon;

public class ParseJson {
	static InputStream is = null;
	static JSONObject jObj = null;
	static String json = "";
	String weatherData = null;

	public WeatherData jsonParser(String query) {
		WeatherData wd;
		JSONObject jsonObject;
		JSONObject jObj, lDispLoc;
		// String query = "";
		// String weatherData = getWeatherData(query);
		String weatherData = (new HttpTask()).doInBackground(query);
		Temperature lT = null;
		Time lTime = null;
		Weather lW = null;
		LocationInfo lLocInfo = null;
		WeatherIcon lWIcon = null;

		try {
			jsonObject = new JSONObject(weatherData);
			jObj = jsonObject.getJSONObject("current_observation");
			lDispLoc = jObj.getJSONObject("display_location");
			lT = new Temperature(jObj.getString("temp_c"),
					jObj.getString("temp_f"));
			lTime = new Time(jObj.getString("observation_time_rfc822"));
			lW = new Weather(jObj.getString("weather"),
					jObj.getString("relative_humidity"), lT);
			lLocInfo = new LocationInfo(lDispLoc.getString("full"),
					lDispLoc.getString("city"), lDispLoc.getString("state"),
					lDispLoc.getString("state_name"),
					lDispLoc.getString("country"),
					lDispLoc.getString("country_iso3166"),
					lDispLoc.getString("zip"), lDispLoc.getString("latitude"),
					lDispLoc.getString("longitude"),
					lDispLoc.getString("elevation"));
			lWIcon = new WeatherIcon(jObj.getString("icon_url"));
			wd = new WeatherData(lLocInfo, lTime, lW, lWIcon);
			return wd;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ForeCastData[] jsonParserForForecast(String query) {

		ForeCastData[] lFd = new ForeCastData[20];
		String lIcon = "";
		String lIconUrl = "";
		String lTitle = "";
		String lFcttext = "";
		String lFcttext_metric = "";
		WeatherIcon lWeatherIcon = null;
		String lForecastData = (new HttpTask()).doInBackground(query);

		JSONObject jsonObject, jObj;
		try {
			jsonObject = new JSONObject(lForecastData);
			jObj = new JSONObject(lForecastData);;//jsonObject.getJSONObject("current_observation");
			
			JSONObject JF = jObj.getJSONObject("forecast").getJSONObject("txt_forecast");
			JSONArray Jarr = JF.getJSONArray("forecastday");
			
			for(int i=0;i<20;i++)
			{
				JSONObject lJo = Jarr.getJSONObject(i);
				lIcon = lJo.getString("icon");
				lIconUrl =  lJo.getString("icon_url");
				lTitle = lJo.getString("title");
				lFcttext = lJo.getString("fcttext");
				lFcttext_metric= lJo.getString("fcttext_metric");
				lWeatherIcon =new WeatherIcon(lIconUrl);
				lFd[i]= new ForeCastData(lIcon, lIconUrl, lTitle, lFcttext, lFcttext_metric, lWeatherIcon);
			}

			return lFd;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lFd;
	}

	/*
	 * public String getWeatherData(String query) { StringBuilder builder = new
	 * StringBuilder(); HttpClient client = new DefaultHttpClient(); HttpGet
	 * httpGet = new HttpGet(
	 * "http://api.wunderground.com/api/2a597e5b69719297/conditions/q/CA/San_Francisco.json"
	 * ); HttpGet httpGet = new HttpGet(query); try { HttpResponse response =
	 * client.execute(httpGet); StatusLine statusLine =
	 * response.getStatusLine(); int statusCode = statusLine.getStatusCode(); if
	 * (statusCode == 200) { HttpEntity entity = response.getEntity();
	 * InputStream content = entity.getContent(); BufferedReader reader = new
	 * BufferedReader( new InputStreamReader(content)); String line; while
	 * ((line = reader.readLine()) != null) { builder.append(line); } } else {
	 * // Log.e(ParseJSON.class.toString(), "Failed to download file"); } }
	 * catch (ClientProtocolException e) { e.printStackTrace(); } catch
	 * (IOException e) { e.printStackTrace(); } return builder.toString(); }
	 */

}
