package com.wirelessuda.weather;
/*
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.wirelessuda.weather.model.CityData;
import com.wirelessuda.weather.model.LinkCityData;
import com.wirelessuda.weather.model.WeatherData;
import com.wirelessuda.weather.model.WeatherSession;
import com.wirelessuda.weather.model.WeatherSession.WeatherDataResult;
import com.wirelessuda.weather.tools.ToolUtils;
import com.wirelessuda.weather.tools.ToolsDB;
import com.wirelessuda.weather.view.ViewWeatherRange;
import java.util.ArrayList;
import java.util.List;

public class ActivityWeatherRange extends ActivityParentWeather
{
  private boolean blnNight;
  private TextView mCityNameView;
  protected WeatherSession mSession;
  public String strCityId = null;
  public String strCityName = null;
  protected ProgressBar vLoadBar;
  private ViewWeatherRange vWorld;

  private void initData()
  {
    if (!TextUtils.isEmpty(getIntent().getStringExtra("intent_key_activity_param_1")));
    for (this.blnNight = true; ; this.blnNight = false)
    {
      resetSource(this.strCityName, this.strCityId);
      return;
    }
  }

  private void openDialog()
  {
    ArrayList localArrayList = ToolsDB.getLinkCity(this, null);
    int i = localArrayList.size();
    int j = 0;
    String[] arrayOfString = new String[i];
    for (int k = 0; k < i; ++k)
    {
      arrayOfString[k] = ((LinkCityData)localArrayList.get(k)).showName;
      if (!this.strCityName.equalsIgnoreCase(arrayOfString[k]))
        continue;
      j = k;
    }
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this).setTitle(2131230756).setPositiveButton(null, null).setNegativeButton(2131230734, new ActivityWeatherRange.2(this));
    localBuilder.setSingleChoiceItems(arrayOfString, j, new ActivityWeatherRange.3(this, localArrayList));
    localBuilder.create().show();
  }

  private void paintError(String paramString, int paramInt)
  {
    switch (paramInt)
    {
    default:
    case 0:
    case 1:
    }
    while (true)
    {
      return;
      ToolUtils.toastNoHaveNet(this);
      continue;
      ToolUtils.toastUnquietNet(this);
    }
  }

  private void requestData(String paramString)
  {
    this.mSession.getWeathersFromNetAsync(paramString);
    setLoadBarVisibility(true);
  }

  private void responseData(WeatherSession.WeatherDataResult paramWeatherDataResult)
  {
    if (this.mDestroyed == true)
      Log.d("ActivityWeatherRange", "ignore responseData() after onDestroy()");
    while (true)
    {
      return;
      if ((paramWeatherDataResult == null) || (paramWeatherDataResult.data.size() == 0) || (TextUtils.isEmpty(((WeatherData)paramWeatherDataResult.data.get(0)).info)))
        paintError(paramWeatherDataResult.cityId, 0);
      if (this.vWorld.initData(((WeatherData)paramWeatherDataResult.data.get(0)).info, this.blnNight))
        continue;
      paintError(paramWeatherDataResult.cityId, 0);
    }
  }

  private void setLoadBarVisibility(boolean paramBoolean)
  {
    if (this.vLoadBar == null);
    while (true)
    {
      return;
      if (paramBoolean)
        this.vLoadBar.setVisibility(0);
      this.vLoadBar.setVisibility(8);
    }
  }

  public void gotoDistCity(String paramString1, String paramString2)
  {
    if (ToolsDB.getLinkCity(this, "posID=" + paramString2) == null)
      ToolsDB.insertLinkCity(this, paramString1, paramString2, -1, 0);
    ToolUtils.gotoWeatherCycle(this, getLocalClassName(), paramString1, paramString2);
  }

  protected void init()
  {
    setContentView(2130903046);
    this.vWorld = ((ViewWeatherRange)findViewById(2131492906));
    findViewById(2131492903).setOnClickListener(new ActivityWeatherRange.1(this));
    this.mCityNameView = ((TextView)findViewById(2131492904));
  }

  public void onBackPressed()
  {
    ToolUtils.gotoWeatherCycle(this, getLocalClassName(), this.strCityName, this.strCityId);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.mSession = new WeatherSession(this, this);
    this.strCityId = getIntent().getStringExtra("intent_key_city_id");
    this.strCityName = getIntent().getStringExtra("intent_key_city_name");
    init();
    this.vLoadBar = ((ProgressBar)findViewById(2131492907));
    if (this.vLoadBar != null)
      this.vLoadBar.setVisibility(8);
    ActionBar localActionBar = getActionBar();
    if (localActionBar == null)
      return;
    localActionBar.setHomeButtonEnabled(true);
  }

  protected void onDestroy()
  {
    super.onDestroy();
    if (this.vWorld == null)
      return;
    this.vWorld.destroySrc();
  }

  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    default:
    case 16908332:
    }
    for (boolean bool = super.onOptionsItemSelected(paramMenuItem); ; bool = true)
    {
      return bool;
      onBackPressed();
    }
  }

  protected void onResume()
  {
    super.onResume();
    initData();
  }

  public void onWeatherDataRead(WeatherSession.WeatherDataResult paramWeatherDataResult)
  {
    super.onWeatherDataRead(paramWeatherDataResult);
    if (this.mDestroyed);
    while (true)
    {
      return;
      setLoadBarVisibility(false);
      responseData(paramWeatherDataResult);
    }
  }

  public void resetSource(String paramString1, String paramString2)
  {
    this.strCityName = paramString1;
    this.strCityId = paramString2;
    this.mCityNameView.setText(this.strCityName);
    int i = ToolUtils.getCityLevel(paramString2);
    StringBuilder localStringBuilder = new StringBuilder();
    switch (i)
    {
    default:
    case 0:
    case 1:
    }
    ArrayList localArrayList;
    for (String str = paramString2.substring(0, 7); ; str = paramString2.substring(0, 5))
    {
      localArrayList = ToolsDB.getCity(this, "posID like '" + str + "%'");
      for (int j = 0; ; ++j)
      {
        if (j >= localArrayList.size())
          break label160;
        localStringBuilder.append(((CityData)localArrayList.get(j)).pid);
        localStringBuilder.append(",");
      }
    }
    label160: this.vWorld.initData(localArrayList, paramString2);
    requestData(localStringBuilder.toString());
  }
}

/* Location:           C:\Users\moon\Desktop\classes-dex2jar.jar
 * Qualified Name:     com.miui.weather2.ActivityWeatherRange
 * JD-Core Version:    0.5.4
 */
