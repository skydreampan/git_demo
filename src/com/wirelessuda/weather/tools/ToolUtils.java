package com.wirelessuda.weather.tools;
/*
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.text.format.DateFormat;
import android.widget.Toast;
import com.miui.weather2.ActivityAbout;
import com.miui.weather2.ActivityFindCity;
import com.miui.weather2.ActivityLove;
import com.miui.weather2.ActivitySetCity;
import com.miui.weather2.ActivityShare;
import com.miui.weather2.ActivityWeatherCycle;
import com.miui.weather2.ActivityWeatherRange;
import com.miui.weather2.source.WeatherParser;
import java.util.Calendar;

public class ToolUtils
{
  public static int getCityLevel(String paramString)
  {
    int i = 5;
    if (paramString == null);
    while (true)
    {
      return i;
      if (paramString.length() != 9)
        continue;
      String str1 = paramString.substring(i, 7);
      String str2 = paramString.substring(7);
      if (str2.equals("00"))
      {
        if (str1.equals("01"))
          i = 0;
        i = 1;
      }
      if (str2.equals("01"))
      {
        if (str1.equals("01"))
          i = 2;
        i = 3;
      }
      i = 4;
    }
  }

  public static String getTime_MDHM(Context paramContext, long paramLong)
  {
    if (paramLong == 0L);
    for (String str = null; ; str = DateFormat.format(paramContext.getResources().getString(2131230786), paramLong).toString())
      return str;
  }

  public static int getWeatherIndex(Context paramContext, String paramString)
  {
    if (paramString == null)
    {
      j = 0;
      label7: return j;
    }
    String[] arrayOfString = WeatherParser.weather_types;
    int i = arrayOfString.length;
    int j = 0;
    String str = WeatherParser.getWeather(paramString);
    for (int k = 0; ; ++k)
    {
      if (k < i);
      if (str.indexOf(arrayOfString[k]) < 0)
        continue;
      j = k;
      break label7:
    }
  }

  public static void gotoAbout(Context paramContext)
  {
    Intent localIntent = new Intent();
    localIntent.setClass(paramContext, ActivityAbout.class);
    paramContext.startActivity(localIntent);
  }

  public static void gotoFindCity(Context paramContext)
  {
    Intent localIntent = new Intent();
    localIntent.setClass(paramContext, ActivityFindCity.class);
    paramContext.startActivity(localIntent);
  }

  public static void gotoLove(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    Intent localIntent = new Intent();
    localIntent.setClass(paramContext, ActivityLove.class);
    localIntent.putExtra("intent_key_activity_src", paramString1);
    localIntent.putExtra("intent_key_activity_param_1", paramString2);
    localIntent.putExtra("intent_key_activity_param_2", paramString3);
    paramContext.startActivity(localIntent);
  }

  public static void gotoSetCity(Context paramContext, String paramString1, String paramString2)
  {
    Intent localIntent = new Intent();
    localIntent.setClass(paramContext, ActivitySetCity.class);
    localIntent.putExtra("intent_key_city_name", paramString1);
    localIntent.putExtra("intent_key_city_id", paramString2);
    paramContext.startActivity(localIntent);
  }

  public static void gotoSetting(Context paramContext)
  {
    Intent localIntent = new Intent();
    localIntent.setClassName("com.android.settings", "com.android.settings.Settings$LocationSettingsActivity");
    paramContext.startActivity(localIntent);
  }

  public static void gotoShareData(Context paramContext, String paramString1, String paramString2)
  {
    Intent localIntent = new Intent();
    localIntent.setClass(paramContext, ActivityShare.class);
    localIntent.putExtra("intent_key_city_id", paramString1);
    localIntent.putExtra("intent_share_shot_name", paramString2);
    paramContext.startActivity(localIntent);
  }

  public static void gotoWeatherCycle(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    Intent localIntent = new Intent();
    localIntent.setClass(paramContext, ActivityWeatherCycle.class);
    localIntent.putExtra("intent_key_activity_src", paramString1);
    localIntent.putExtra("intent_key_city_name", paramString2);
    localIntent.putExtra("intent_key_city_id", paramString3);
    paramContext.startActivity(localIntent);
  }

  public static void gotoWeatherRange(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    Intent localIntent = new Intent();
    localIntent.setClass(paramContext, ActivityWeatherRange.class);
    localIntent.putExtra("intent_key_city_id", paramString2);
    localIntent.putExtra("intent_key_city_name", paramString1);
    localIntent.putExtra("intent_key_activity_param_1", paramString3);
    paramContext.startActivity(localIntent);
  }

  public static boolean isMinMoveDistance(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    int i = (paramInt2 - paramInt4) * (paramInt2 - paramInt4) + (paramInt3 - paramInt5) * (paramInt3 - paramInt5);
    if (paramInt1 * paramInt1 < i);
    for (int j = 0; ; j = 1)
      return j;
  }

  public static boolean isNight()
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTimeInMillis(System.currentTimeMillis());
    int i = localCalendar.get(11);
    if ((i < 6) || (i >= 18));
    for (int j = 1; ; j = 0)
      return j;
  }

  public static void toastNoHaveNet(Context paramContext)
  {
    Toast.makeText(paramContext, 2131230739, 0).show();
  }

  public static void toastUnquietNet(Context paramContext)
  {
    Toast.makeText(paramContext, 2131230740, 0).show();
  }
}

/* Location:           C:\Users\moon\Desktop\classes-dex2jar.jar
 * Qualified Name:     com.miui.weather2.tools.ToolUtils
 * JD-Core Version:    0.5.4
 */