package com.example.invisibleskies;

import request.Query;
import response.ForeCastData;
import response.WeatherData;
import json.ParseJson;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.media.audiofx.BassBoost.Settings;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView _m_tvTemp;
	TextView _m_tvCondition;
	TextView _m_tvCity;
	TextView _m_tvHumidity;
	TextView[] _m_tvDay;
	TextView[] _m_tvInfo;
	ImageView _m_ivWeatherIcon;
	ImageView[] _m_ivForeCastIcon;

	SharedPreferences _mSettings;
	WeatherData _mWeatherData;
	ForeCastData[] _mForeCastWeatherData;
	Query _mQuery;
	ParseJson _mJasonParser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
		_mSettings = PreferenceManager.getDefaultSharedPreferences(this);
		_m_tvTemp = (TextView) findViewById(R.id.temperature);
		_m_tvCondition = (TextView) findViewById(R.id.condition);
		_m_tvCity = (TextView) findViewById(R.id.city);
		_m_tvHumidity = (TextView) findViewById(R.id.humidity);
		_m_tvDay = new TextView[3];
		_m_tvInfo = new TextView[3];
		_m_ivWeatherIcon = (ImageView) findViewById(R.id.wicon);
		// String day = "R.id.day" + i;
		/*for(int i=0;i<3;i++)
		{
			_m_tvDay[i]= new TextView(this);
			_m_tvInfo[i]= new TextView(this);
			_m_ivForeCastIcon[i]= new ImageView(this);
			
		}*/
		_m_tvDay[0] = (TextView) findViewById(R.id.day0);
		_m_tvDay[1] = (TextView) findViewById(R.id.day1);
		_m_tvDay[2] = (TextView) findViewById(R.id.day2);

		_m_tvInfo[0] = (TextView) findViewById(R.id.info0);
		_m_tvInfo[1] = (TextView) findViewById(R.id.info1);
		_m_tvInfo[2] = (TextView) findViewById(R.id.info2);

		/*_m_ivForeCastIcon[0] = (ImageView) findViewById(R.id.iv0);
		_m_ivForeCastIcon[1] = (ImageView) findViewById(R.id.iv1);
		_m_ivForeCastIcon[2] = (ImageView) findViewById(R.id.iv2);*/

		initData();

	}

	public void initData() {
		_mQuery = new Query(this);
		_mJasonParser = new ParseJson();
		_mForeCastWeatherData = new ForeCastData[20];

		/*
		 * _mQuery.setQuery("http://api.wunderground.com/api/2a597e5b69719297/"
		 * + _mSettings.getString("pin_text", "20037") + ".json"); _mWeatherData
		 * = _mJasonParser.jsonParser(_mQuery.getQuery());
		 */
		_mQuery.createQueryForGps();
		_mWeatherData = _mJasonParser.jsonParser(_mQuery.getQuery());
		_mForeCastWeatherData = _mJasonParser.jsonParserForForecast(_mQuery
				.getForeCastQuery());
		Editor editor = _mSettings.edit();
		editor.putString("pin_text", _mWeatherData.getLoc().getZip());
		editor.commit();
		updateData();
	}

	public void updateData() {
		_mQuery.createQueryForZip();
		_mWeatherData = _mJasonParser.jsonParser(_mQuery.getQuery());
		_mForeCastWeatherData = _mJasonParser.jsonParserForForecast(_mQuery
				.getForeCastQuery());
		_m_tvCity.setText(_mWeatherData.getLoc().getCity());
		_m_tvCondition.setText(_mWeatherData.getWeather().getWeather());
		_m_tvHumidity.setText("Humidity: "+_mWeatherData.getWeather().getHumidity());
		_m_ivWeatherIcon.setImageBitmap(_mWeatherData.getWeatherIcon()
				.getBitmap());
		if (_mSettings.getBoolean("temp_checkbox", false)) {
			_m_tvTemp.setText(_mWeatherData.getWeather().getTemp().getTempC()
					+ " C");

		} else {
			_m_tvTemp.setText(_mWeatherData.getWeather().getTemp().getTempF()
					+ " F");

		}
		for (int i = 0; i < 3; i++) {
			_m_tvDay[i].setText(_mForeCastWeatherData[i].getTitle());
			/*_m_ivForeCastIcon[i].setImageBitmap(_mForeCastWeatherData[i]
					.getWeatherIcon().getBitmap());*/
			if (_mSettings.getBoolean("temp_checkbox", false)) {
				_m_tvInfo[i].setText(_mForeCastWeatherData[i]
						.getMfcttext_metric());

			} else {
				_m_tvInfo[i].setText(_mForeCastWeatherData[i].getFcttext());
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.action_settings:
			Intent in = new Intent(MainActivity.this, SettingsActivity.class);
			startActivity(in);
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		updateData();

	}

}
