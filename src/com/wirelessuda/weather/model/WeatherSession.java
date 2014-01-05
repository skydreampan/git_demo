package com.wirelessuda.weather.model;
/*
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.miui.weather2.source.WeatherParser;
import com.miui.weather2.source.WeatherReader;
import com.miui.weather2.source.WeatherReader.WeatherResult;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import miui.provider.Weather.DailyWeather;

public class WeatherSession
{
  private static final String LOG_TAG = WeatherSession.class.getName();
  private IWeatherLisener mCallback;
  private Context mContext;
  private String[] mProjection;

  public WeatherSession(Context paramContext, IWeatherLisener paramIWeatherLisener)
  {
    String[] arrayOfString = new String[14];
    arrayOfString[0] = "city_id";
    arrayOfString[1] = "city_name";
    arrayOfString[2] = "description";
    arrayOfString[3] = "humidity";
    arrayOfString[4] = "publish_time";
    arrayOfString[5] = "temperature";
    arrayOfString[6] = "temperature_range";
    arrayOfString[7] = "timestamp";
    arrayOfString[8] = "wind";
    arrayOfString[9] = "sunrise";
    arrayOfString[10] = "sunset";
    arrayOfString[11] = "data1";
    arrayOfString[12] = "begins";
    arrayOfString[13] = "weather_type";
    this.mProjection = arrayOfString;
    this.mContext = paramContext;
    this.mCallback = paramIWeatherLisener;
  }

  private void InsertWeather(WeatherParser paramWeatherParser)
  {
    long l1 = System.currentTimeMillis();
    for (int i = 0; i < 6; ++i)
    {
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("city_name", paramWeatherParser.getCity());
      localContentValues.put("timestamp", Long.valueOf(System.currentTimeMillis()));
      localContentValues.put("publish_time", paramWeatherParser.getPublishTime());
      localContentValues.put("description", paramWeatherParser.getWeather(i));
      localContentValues.put("temperature_range", paramWeatherParser.getTemp(i));
      localContentValues.put("wind", paramWeatherParser.getWind(i));
      localContentValues.put("weather_type", Integer.valueOf(paramWeatherParser.getWeatherType(i)));
      long l2 = paramWeatherParser.getBaseTime() + 1000 * (60 * (60 * (i * 24)));
      long l3 = 86400000L + l2 - 1L;
      localContentValues.put("begins", Long.valueOf(l2));
      localContentValues.put("ends", Long.valueOf(l3));
      if ((i == 0) || (i == 1))
      {
        localContentValues.put("temperature", paramWeatherParser.getSktq());
        localContentValues.put("humidity", paramWeatherParser.getHumidity());
        localContentValues.put("data1", paramWeatherParser.getJasonString());
      }
      this.mContext.getContentResolver().insert(Uri.withAppendedPath(Weather.DailyWeather.CONTENT_URI, paramWeatherParser.getCityId()), localContentValues);
    }
    (System.currentTimeMillis() - l1);
  }

  private void touch(String paramString)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("timestamp", Long.valueOf(System.currentTimeMillis()));
    this.mContext.getContentResolver().update(Uri.withAppendedPath(Weather.DailyWeather.CONTENT_URI, paramString), localContentValues, null, null);
  }

  private void updateWeather(String paramString, long paramLong)
  {
    WeatherReader.WeatherResult localWeatherResult;
    try
    {
      localWeatherResult = new WeatherReader(this.mContext).getWeather(paramString);
      if (!TextUtils.isEmpty(localWeatherResult.data))
      {
        if (!updateWeather(paramString, WeatherParser.load(this.mContext, localWeatherResult.data), paramLong))
          touch(paramString);
        return;
      }
    }
    finally
    {
      if (0 == 0)
        touch(paramString);
    }
  }

  private boolean updateWeather(String paramString, WeatherParser paramWeatherParser, long paramLong)
  {
    if (paramWeatherParser == null);
    for (int i = 0; ; i = 1)
    {
      return i;
      this.mContext.getContentResolver().delete(Uri.withAppendedPath(Weather.DailyWeather.CONTENT_URI, paramString), null, null);
      InsertWeather(paramWeatherParser);
    }
  }

  public WeatherDataResult getWeather(String paramString, int paramInt)
  {
    WeatherDataResult localWeatherDataResult = getWeatherLocal(paramString, paramInt);
    long l = 0L;
    if ((localWeatherDataResult.data.size() == 0) || (((WeatherData)localWeatherDataResult.data.get(0)).isExpired()))
    {
      if (localWeatherDataResult.data.size() > 0)
        l = ((WeatherData)localWeatherDataResult.data.get(0)).getPubTime();
      updateWeather(paramString, l);
      localWeatherDataResult = getWeatherLocal(paramString, paramInt);
    }
    return localWeatherDataResult;
  }

  public void getWeatherAsync(String paramString, int paramInt)
  {
    new AsyncTask(paramString, paramInt)
    {
      protected WeatherSession.WeatherDataResult doInBackground(Void[] paramArrayOfVoid)
      {
        return WeatherSession.this.getWeather(this.val$cityId, this.val$number);
      }

      protected void onPostExecute(WeatherSession.WeatherDataResult paramWeatherDataResult)
      {
        if (WeatherSession.this.mCallback == null)
          return;
        WeatherSession.this.mCallback.onWeatherDataRead(paramWeatherDataResult);
      }
    }
    .execute(new Void[0]);
  }

  public WeatherDataResult getWeatherFromNet(String paramString, int paramInt)
  {
    WeatherDataResult localWeatherDataResult1 = getWeatherLocal(paramString, 1);
    long l = 0L;
    if (localWeatherDataResult1.data.size() > 0)
      l = ((WeatherData)localWeatherDataResult1.data.get(0)).getPubTime();
    updateWeather(paramString, l);
    WeatherDataResult localWeatherDataResult2 = getWeatherLocal(paramString, paramInt);
    if (localWeatherDataResult2 != null)
      localWeatherDataResult2.forceUpdateReqest = true;
    return localWeatherDataResult2;
  }

  public void getWeatherFromNetAsync(String paramString, int paramInt)
  {
    new AsyncTask(paramString, paramInt)
    {
      protected WeatherSession.WeatherDataResult doInBackground(Void[] paramArrayOfVoid)
      {
        return WeatherSession.this.getWeatherFromNet(this.val$cityId, this.val$number);
      }

      protected void onPostExecute(WeatherSession.WeatherDataResult paramWeatherDataResult)
      {
        if (WeatherSession.this.mCallback == null)
          return;
        WeatherSession.this.mCallback.onWeatherDataRead(paramWeatherDataResult);
      }
    }
    .execute(new Void[0]);
  }

  public WeatherDataResult getWeatherLocal(String paramString, int paramInt)
  {
    long l1 = System.currentTimeMillis();
    WeatherDataResult localWeatherDataResult = new WeatherDataResult();
    localWeatherDataResult.cityId = paramString;
    localWeatherDataResult.data = new ArrayList();
    if (TextUtils.isEmpty(paramString));
    while (true)
    {
      return localWeatherDataResult;
      Calendar localCalendar = Calendar.getInstance();
      localCalendar.set(11, 0);
      localCalendar.set(12, 0);
      localCalendar.set(13, 0);
      localCalendar.set(14, 0);
      long l2 = localCalendar.getTimeInMillis() - 10L;
      long l3 = l2 + 1000 * (60 * (60 * (paramInt * 24)));
      ContentResolver localContentResolver = this.mContext.getContentResolver();
      Uri localUri = Uri.withAppendedPath(Weather.DailyWeather.CONTENT_URI, paramString);
      String[] arrayOfString1 = this.mProjection;
      String[] arrayOfString2 = new String[2];
      arrayOfString2[0] = String.valueOf(l2);
      arrayOfString2[1] = String.valueOf(l3);
      Cursor localCursor = localContentResolver.query(localUri, arrayOfString1, "begins>? AND begins<?", arrayOfString2, "begins");
      if (localCursor != null)
        while (true)
          try
          {
            if (!localCursor.moveToNext())
              break label409;
            WeatherData localWeatherData = new WeatherData(this.mContext);
            localWeatherData.cityId = localCursor.getString(0);
            localWeatherData.cityName = localCursor.getString(1);
            localWeatherData.description = localCursor.getString(2);
            localWeatherData.humidity = localCursor.getString(3);
            localWeatherData.publishTime = localCursor.getString(4);
            localWeatherData.temperature = localCursor.getString(5);
            localWeatherData.setTempRange(localCursor.getString(6));
            localWeatherData.timestamp = localCursor.getLong(7);
            localWeatherData.wind = localCursor.getString(8);
            localWeatherData.sunrise = localCursor.getString(9);
            localWeatherData.sunset = localCursor.getString(10);
            localWeatherData.info = localCursor.getString(11);
            localWeatherData.begins = localCursor.getLong(12);
            localWeatherData.weather_type = localCursor.getString(13);
            label409: localWeatherDataResult.data.add(localWeatherData);
          }
          finally
          {
            localCursor.close();
          }
      (System.currentTimeMillis() - l1);
    }
  }

  public void getWeatherLocalAsync(String paramString, int paramInt)
  {
    new AsyncTask(paramString, paramInt)
    {
      protected WeatherSession.WeatherDataResult doInBackground(Void[] paramArrayOfVoid)
      {
        return WeatherSession.this.getWeatherLocal(this.val$cityId, this.val$number);
      }

      protected void onPostExecute(WeatherSession.WeatherDataResult paramWeatherDataResult)
      {
        if (WeatherSession.this.mCallback == null)
          return;
        WeatherSession.this.mCallback.onWeatherDataRead(paramWeatherDataResult);
      }
    }
    .execute(new Void[0]);
  }

  public void getWeathersFromNetAsync(String paramString)
  {
    new AsyncTask(paramString)
    {
      protected WeatherSession.WeatherDataResult doInBackground(Void[] paramArrayOfVoid)
      {
        String str = new WeatherReader(WeatherSession.this.mContext).getRangeWeather(this.val$cityIds);
        WeatherSession.WeatherDataResult localWeatherDataResult = new WeatherSession.WeatherDataResult();
        localWeatherDataResult.data = new ArrayList();
        WeatherData localWeatherData = new WeatherData(WeatherSession.this.mContext);
        localWeatherData.cityId = this.val$cityIds;
        localWeatherData.info = str;
        localWeatherData.timestamp = System.currentTimeMillis();
        localWeatherDataResult.data.add(localWeatherData);
        return localWeatherDataResult;
      }

      protected void onPostExecute(WeatherSession.WeatherDataResult paramWeatherDataResult)
      {
        if (WeatherSession.this.mCallback == null)
          return;
        WeatherSession.this.mCallback.onWeatherDataRead(paramWeatherDataResult);
      }
    }
    .execute(new Void[0]);
  }

  public static abstract interface IWeatherLisener
  {
    public abstract void onWeatherDataRead(WeatherSession.WeatherDataResult paramWeatherDataResult);
  }

  public static class WeatherDataResult
  {
    public String cityId;
    public List<WeatherData> data;
    public boolean forceUpdateReqest;
  }
}

/* Location:           C:\Users\moon\Desktop\classes-dex2jar.jar
 * Qualified Name:     com.miui.weather2.model.WeatherSession
 * JD-Core Version:    0.5.4
 */