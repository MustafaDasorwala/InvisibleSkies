package response;

public class ForeCastData {
	String mIcon = "";
	String mIconUrl = "";
	String mTitle = "";
	String mFcttext = "";
	String mFcttext_metric = "";
	WeatherIcon mWeatherIcon = null;

	public ForeCastData() {

	}

	public ForeCastData(String icon, String iconUrl, String title,
			String fcttext, String mfcttext_metric, WeatherIcon weatherIcon) {
		super();
		mIcon = icon;
		mIconUrl = iconUrl;
		mTitle = title;
		mFcttext = fcttext;
		this.mFcttext_metric = mfcttext_metric;
		mWeatherIcon = weatherIcon;
	}

	public String getIcon() {
		return mIcon;
	}

	public void setIcon(String icon) {
		mIcon = icon;
	}

	public String getIconUrl() {
		return mIconUrl;
	}

	public void setIconUrl(String iconUrl) {
		mIconUrl = iconUrl;
	}

	public String getTitle() {
		return mTitle;
	}

	public void setTitle(String title) {
		mTitle = title;
	}

	public String getFcttext() {
		return mFcttext;
	}

	public void setFcttext(String fcttext) {
		mFcttext = fcttext;
	}

	public String getMfcttext_metric() {
		return mFcttext_metric;
	}

	public void setMfcttext_metric(String mfcttext_metric) {
		this.mFcttext_metric = mfcttext_metric;
	}

	public WeatherIcon getWeatherIcon() {
		return mWeatherIcon;
	}

	public void setWeatherIcon(WeatherIcon weatherIcon) {
		mWeatherIcon = weatherIcon;
	}

}
