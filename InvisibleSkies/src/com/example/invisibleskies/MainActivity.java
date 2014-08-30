package com.example.invisibleskies;

import response.WeatherData;
import json.ParseJson;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.audiofx.BassBoost.Settings;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView _m_tvTemp;
	SharedPreferences _mSettings;
	WeatherData _mWeatherData;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
		_mSettings = PreferenceManager.getDefaultSharedPreferences(this);
		ParseJson lJp = new ParseJson();
		_mWeatherData = lJp.jsonParser();
		_m_tvTemp = (TextView) findViewById(R.id.temperature);
		updateData();
	}

	public void updateData() {
		if (_mSettings.getBoolean("temp_checkbox", false)) {
			_m_tvTemp.setText(_mWeatherData.getWeather().getTemp().getTempC()
					+ " C");
		} else {
			_m_tvTemp.setText(_mWeatherData.getWeather().getTemp().getTempF()
					+ " F");
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
