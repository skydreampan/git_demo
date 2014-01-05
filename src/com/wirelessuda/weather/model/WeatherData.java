package com.wirelessuda.weather.model;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class WeatherData {
	public static int INVALID_TEMP;
	private static String LOGTAG = WeatherData.class.getName();
	public long begins;
	public String cityId;
	public String cityName;
	public String description;
	public String humidity;
	public String info;
	private JSONObject infoJasonObject;
	private WeatherData mContext;
	public String publishTime;
	public String sunrise;
	public String sunset;
	public int tempHigh = INVALID_TEMP;
	public int tempLow = INVALID_TEMP;
	public String temperature;
	private String temperatureRange;
	public long timestamp;
	public String weather_type;
	public String wind;

	static {
		INVALID_TEMP = -999;
	}

	public WeatherData(WeatherData weatherData) {
		this.mContext = weatherData;
	}

	public static long getTimeFromYMDHM(String paramString) {
		long l = 0L;
		if (TextUtils.isEmpty(paramString))
			return 0;
		while (true) {
			String[] arrayOfString1 = paramString.trim().split(" ");
			if ((arrayOfString1 == null) || (arrayOfString1.length != 2))
				continue;
			String[] arrayOfString2 = arrayOfString1[0].split("-");
			String[] arrayOfString3 = arrayOfString1[1].split(":");
			if ((arrayOfString2 == null) || (arrayOfString3 == null)
					|| (arrayOfString2.length != 3)
					|| (arrayOfString3.length != 2))
				continue;
			Calendar localCalendar = Calendar.getInstance();
			localCalendar.set(Integer.parseInt(arrayOfString2[0]),
					-1 + Integer.parseInt(arrayOfString2[1]),
					Integer.parseInt(arrayOfString2[2]),
					Integer.parseInt(arrayOfString3[0]),
					Integer.parseInt(arrayOfString3[1]));
			l = localCalendar.getTimeInMillis();
			return l;
		}
	}
/*
	private String getWindEN(String paramString) {
		WeatherData local1 = new WeatherData(this);
		if (TextUtils.isEmpty(paramString))
			return "";
		String str1 = "";
		while (true) {
			int i = paramString.indexOf("风");
			if (i <= 0)
				break;
			String str2 = paramString.substring(0, i);
			String str3 = paramString.substring(i + 1, paramString.length());
			str1 = "";
//			for (int j = str2.length(); j > 0; --j)
				str1 = str1 + (String)  local1.get(str2.substring(str2 - 1, j));
			int k = str3.indexOf("级");
			if (k <= 0)
				continue;
			str1 = str1 + "  " + "L." + str3.substring(0, k);
			return str1;
		}
	}
*/
	
	/**
	 * 判断是否是夜晚
	 * @param paramString1 白天的起始时间
	 * @param paramString2 白天的结束时间(单位都是H)
	 */
	private static boolean isNight(String paramString1, String paramString2){
		boolean result = false;
	    int i = 0;
	    int j;
	    int k;
	    if ((TextUtils.isEmpty(paramString1))) {
	    	j = 6;
	    }
	    else{
	    	j = Integer.parseInt(paramString1);
	    }
	    if ((TextUtils.isEmpty(paramString2))) {
	    	k = 6;
	    }
	    else{
	    	k = Integer.parseInt(paramString2);
	    }
	    Calendar calendar = new GregorianCalendar(); 
	    Date trialTime = new Date(); 
	    calendar.setTime(trialTime);
	    i = calendar.get(Calendar.HOUR_OF_DAY);
	    if((i < j) || (i > k)){
	    	result = true;
	    }
	/*    String[] arrayOfString2;
	    for (int i1 = 0; ; i1 = Integer.parseInt(arrayOfString2[1])) {
	    	Calendar localCalendar = Calendar.getInstance();
	    	localCalendar.setTimeInMillis(System.currentTimeMillis());
	    	int i2 = localCalendar.get(11);
	    	int i3 = localCalendar.get(12);
	    	if ((i2 < j) || (i2 > k) || ((i2 == j) && (i3 < l)) || ((i2 == k) && (i3 > i1)))
	    		return true;
	    	String[] arrayOfString1 = paramString1.split(":");
	    	if ((arrayOfString1 == null) || (arrayOfString1.length != 2))
	    		j = 6;
	    	for (l = 0; ; l = Integer.parseInt(arrayOfString1[1])) {
		        arrayOfString2 = paramString2.split(":");
		        if ((arrayOfString2 != null) && (arrayOfString2.length == 2))
		          break;
		        k = 18;
		        i1 = 0;
		        break label29:
		        j = Integer.parseInt(arrayOfString1[0]);
	    	}
	    	k = Integer.parseInt(arrayOfString2[0]);
	    }	*/
	    return result;
	}

	public String getCity() {
		return this.cityName;
	}

	public String getCityId() {
		return this.cityId;
	}

	/**
	 * 时间格式"yyyyMMddHHmmss"
	 */
	public String getDate() {
		Calendar calendar = GregorianCalendar.getInstance();
        Date date = calendar.getTime();
		SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
		return d.format(date).toString();
	}
/*
	public String getDateAll() {
		if (Config.INT_SYS_LANGUAGE == 1)
			;
		for (String str = getDate();; str = getDate() + " " + getLunarDate())
			return str;
	}

	public String getLunarDate() {
		Calendar localCalendar = Calendar.getInstance();
		localCalendar.setTimeInMillis(this.begins);
		return LunarDate.getString(this.mContext.getResources(), localCalendar);
	}*/

	public long getPubTime() {
		return getTimeFromYMDHM(this.publishTime);
	}

	public String getRc() {
		return this.sunrise;
	}

	public String getRl() {
		return this.sunset;
	}
/*
	public String getSkWind(){
	    String str1 = getValue("fx");
	    String str2 = getValue("fl");
	    Object localObject;
	    if ((TextUtils.isEmpty(str1)) || (TextUtils.isEmpty(str2)))
	    {
	      localObject = null;
	      label30: return localObject;
	    }
	    if (Config.INT_SYS_LANGUAGE == 1);
	    for (String str3 = getWindEN(str1 + str2); ; str3 = str1 + str2)
	    {
	      localObject = str3;
	      break label30:
	    }
	}

	public String getSktq() {
		if (TextUtils.isEmpty(this.temperature))
			;
		for (String str = null;; str = this.temperature)
			return str;
	}

	public String getTemp() {
		String str = "";
		if (!TextUtils.isEmpty(this.description))
			switch (Config.INT_SYS_LANGUAGE) {
			default:
			case 1:
			case 2:
			}
		for (str = this.temperatureRange;; str = this.temperatureRange)
			while (true) {
				return str;
				if (this.temperatureRange.contains("低温"))
					str = this.temperatureRange.replace("低温", "low temp");
				str = this.temperatureRange;
				continue;
				if (!this.temperatureRange.contains("低温"))
					break;
				str = this.temperatureRange.replace("低温", "低溫");
			}
	}

	public int getTempHigh() {
		return this.tempHigh;
	}

	public int getTempLow() {
		return this.tempLow;
	}

	/**
	 * 从天气信息中读取相应的数据
	 * 	@param paramString 数据的Key
	 */
	public String getValue(String paramString){
	    Object localObject = null;
	    if (TextUtils.isEmpty(this.info))
	      return (String) localObject;
	    if (this.infoJasonObject == null);
	    try{
	    	this.infoJasonObject = new JSONObject(this.info).getJSONObject("weatherinfo");
		  	if (this.infoJasonObject == null)
		  		return "";
		  	else{
				return (this.infoJasonObject).getJSONObject(paramString).toString();
		  	}
	    }
	    catch (JSONException localJSONException2) {
	    }
	    return "";
	}
/*
	public String getWeather() {
		String str = "";
		if (!TextUtils.isEmpty(this.description))
			switch (Config.INT_SYS_LANGUAGE) {
			default:
			case 1:
			case 2:
			}
		for (str = this.description;; str = this.mContext.getResources()
				.getStringArray(2131296259)[Integer.parseInt(this.weather_type)])
			return str;
	}

	public String getWind() {
		return this.wind;
	}

	public boolean isExpired() {
		if ((System.currentTimeMillis() - this.timestamp > 1800000L)
				|| (System.currentTimeMillis() < this.timestamp))
			;
		for (int i = 1;; i = 0)
			return i;
	}

	public boolean isFirstDay() {
		if (!TextUtils.isEmpty(this.info))
			;
		for (int i = 1;; i = 0)
			return i;
	}

	public boolean isNight() {
		return isNight(getRc(), getRl());
	}

	public boolean isOutOfDate() {
		if ((System.currentTimeMillis() < this.begins)
				|| (System.currentTimeMillis() - this.begins > 86400000L))
			;
		for (int i = 1;; i = 0)
			return i;
	}

	public boolean isUpToDate() {
		if ((System.currentTimeMillis() - this.timestamp < 60000L)
				|| (System.currentTimeMillis() < this.timestamp))
			;
		for (int i = 1;; i = 0)
			return i;
	}

	public void setTempRange(String paramString) {
		int i = 0;
		this.temperatureRange = paramString;
		if (TextUtils.isEmpty(this.temperatureRange))
			;
		while (true) {
			return;
			int j = this.temperatureRange.indexOf("~");
			if (j < 0) {
				int i1 = this.temperatureRange.indexOf(":");
				String str = this.temperatureRange;
				if (i1 > -1)
					i = i1 + 1;
				this.tempLow = Integer.parseInt(str.substring(i, -1
						+ this.temperatureRange.length()));
			}
			int k = Integer.parseInt(this.temperatureRange.substring(0, j - 1));
			int l = Integer.parseInt(this.temperatureRange.substring(j + 1, -1
					+ this.temperatureRange.length()));
			this.tempLow = Math.min(k, l);
			this.tempHigh = Math.max(k, l);
		}
	}*/
}

/*
 * Location: C:\Users\moon\Desktop\classes-dex2jar.jar Qualified Name:
 * com.miui.weather2.model.WeatherData JD-Core Version: 0.5.4
 */