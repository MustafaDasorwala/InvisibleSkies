package response;

public class Temperature {

	private String mTempC = "";
	private String mTempF = "";

	public Temperature() {
	}

	public Temperature(String tempC, String tempF) {
		super();
		mTempC = tempC;
		mTempF = tempF;
	}

	public String getTempC() {
		return mTempC;
	}

	public void setTempC(String tempC) {
		mTempC = tempC;
	}

	public String getTempF() {
		return mTempF;
	}

	public void setTempF(String tempF) {
		mTempF = tempF;
	}

}
