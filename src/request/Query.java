package request;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Query {

	String mQuery="";
	String mForeCastQuery="";
	private static String URL = "http://api.wunderground.com/api/2a597e5b69719297/";
	Context mContext;

	public Query(Context context) {
		super();
		mContext = context;
	}


	public Context getContext() {
		return mContext;
	}
	

	public String getForeCastQuery() {
		return mForeCastQuery;
	}


	public void setForeCastQuery(String foreCastQuery) {
		mForeCastQuery = foreCastQuery;
	}


	public void setContext(Context context) {
		mContext = context;
	}

	public String getQuery() {
		return mQuery;
	}

	public void setQuery(String query) {
		mQuery = query;
	}

	public String[] createQueryForGps() {
		
		double latitude = 0;
		double longitude = 0;
		
		
		GPSTracker lGps = new GPSTracker(mContext);
		//lGps.showSettingsAlert();
		//lGps.showSettingsAlert();
		lGps.getLocation();
		if (lGps.canGetLocation()) {

			latitude = lGps.getLatitude();
			longitude = lGps.getLongitude();
			mQuery = URL +"conditions/q/"+ String.valueOf(latitude) + ","
					+ String.valueOf(longitude) + ".json";
			mForeCastQuery = URL +"forecast10day/q/"+ String.valueOf(latitude) + ","
					+ String.valueOf(longitude) + ".json";

		} else {
			
			lGps.showSettingsAlert();
		}

		return (new String[]{mQuery,mForeCastQuery});

	}
	public String[] createQueryForZip() {
		
		SharedPreferences lSettings;
		lSettings = PreferenceManager.getDefaultSharedPreferences(mContext);
		String pin=lSettings.getString("pin_text", "92284");
		mQuery = URL +"conditions/q/"+ pin + ".json";
		mForeCastQuery = URL +"forecast10day/q/"+ pin + ".json";
		return (new String[]{mQuery,mForeCastQuery});
		
	}
	

}
