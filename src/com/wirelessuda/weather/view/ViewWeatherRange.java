package com.wirelessuda.weather.view;
/*
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import com.miui.weather2.ActivityWeatherCycle;
import com.miui.weather2.ActivityWeatherRange;
import com.miui.weather2.model.AdapterWeatherCycle;
import com.miui.weather2.model.CityData;
import com.miui.weather2.model.ModelCityBrown;
import com.miui.weather2.model.ModelCityBrown.CityInfo;
import com.miui.weather2.model.ModelCityBrown.WeatherInfo;
import com.miui.weather2.model.WeatherData;
import com.miui.weather2.source.WeatherParser;
import com.miui.weather2.tools.ToolUtils;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ViewWeatherRange extends View
{
  private static int N_SCREEN_HEIGHT;
  private static int N_SCREEN_WIDTH = 0;
  private ActivityWeatherRange activity = null;
  private boolean blnMoveFlag;
  private Bitmap[] imgFrameBg = null;
  private HashMap<Integer, Integer> imgWeather;
  private long lOperateTime = 0L;
  private boolean mDataChanged = false;
  private boolean mLayoutFinished = false;
  private int mOriginalOffsetX;
  private int mOriginalOffsetY;
  private int nCacheHeight;
  private int nCacheWidth;
  private int nMoveX;
  private int nMoveY;
  private int nOffsetX;
  private int nOffsetY;
  private int nPointX;
  private int nPointY;
  private int nSelectedIndex = 0;
  private ArrayList<ModelCityBrown.CityInfo> objSrc = null;
  private int[][] objSrcSide = (int[][])null;
  private ArrayList<ModelCityBrown.WeatherInfo> objWeatherSrc = null;

  static
  {
    N_SCREEN_HEIGHT = 0;
  }

  public ViewWeatherRange(Context paramContext)
  {
    super(paramContext);
    initSrc(paramContext);
  }

  public ViewWeatherRange(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initSrc(paramContext);
  }

  private void clampOffset()
  {
    this.nOffsetX = Math.min(0, this.nOffsetX);
    this.nOffsetX = Math.max(this.nOffsetX, N_SCREEN_WIDTH - this.nCacheWidth);
    this.nOffsetY = Math.min(0, this.nOffsetY);
    this.nOffsetY = Math.max(N_SCREEN_HEIGHT - this.nCacheHeight, this.nOffsetY);
  }

  private void drawBgLine(Canvas paramCanvas, Paint paramPaint)
  {
    paramPaint.setColor(436207616);
    for (int i = 70; i < this.nCacheWidth; i += 70)
      paramCanvas.drawLine(i, 0.0F, i, this.nCacheHeight, paramPaint);
    for (int j = 70; j < this.nCacheHeight; j += 70)
      paramCanvas.drawLine(0.0F, j, this.nCacheWidth, j, paramPaint);
  }

  private void drawBody(Canvas paramCanvas, Paint paramPaint, int paramInt, boolean paramBoolean)
  {
    ModelCityBrown.CityInfo localCityInfo = (ModelCityBrown.CityInfo)this.objSrc.get(paramInt);
    if (paramBoolean)
      paramCanvas.drawBitmap(this.imgFrameBg[1], this.objSrcSide[paramInt][0], this.objSrcSide[paramInt][1], null);
    while (true)
    {
      if ((this.objWeatherSrc != null) && (paramInt < this.objWeatherSrc.size()))
      {
        ModelCityBrown.WeatherInfo localWeatherInfo = (ModelCityBrown.WeatherInfo)this.objWeatherSrc.get(paramInt);
        String str1 = localWeatherInfo.weather;
        String str2 = localWeatherInfo.temp;
        AdapterWeatherCycle localAdapterWeatherCycle = (AdapterWeatherCycle)ActivityWeatherCycle.getAdapterRoot().get(localWeatherInfo.pid);
        if ((localAdapterWeatherCycle != null) && (localAdapterWeatherCycle.getData() != null) && (localAdapterWeatherCycle.getData().size() != 0))
        {
          WeatherData localWeatherData = (WeatherData)localAdapterWeatherCycle.getData().get(0);
          str1 = localWeatherData.description;
          str2 = localWeatherData.getTemp();
          int l = localWeatherData.getTempLow();
          if ((localWeatherData.getTempHigh() == WeatherData.INVALID_TEMP) && (localAdapterWeatherCycle.getData().size() > 1))
          {
            int i1 = ((WeatherData)localAdapterWeatherCycle.getData().get(1)).getTempHigh();
            str2 = l + WeatherParser.TEMP_UNIT + "~" + i1 + WeatherParser.TEMP_UNIT;
          }
        }
        paramPaint.setColor(-16777216);
        int k = ToolUtils.getWeatherIndex(this.mContext, str1);
        paramCanvas.drawBitmap(((BitmapDrawable)this.mContext.getResources().getDrawable(((Integer)this.imgWeather.get(Integer.valueOf(k))).intValue())).getBitmap(), -13 + this.objSrcSide[paramInt][0], -40 + this.objSrcSide[paramInt][1], paramPaint);
        if ((!TextUtils.isEmpty(str2)) && (!str2.equals("null")))
        {
          paramPaint.setColor(-1);
          paramPaint.setTextSize(16.0F);
          paramCanvas.drawText(str2, (85 - (int)paramPaint.measureText(str2)) / 2 + this.objSrcSide[paramInt][0], 38 + this.objSrcSide[paramInt][1], paramPaint);
        }
      }
      paramPaint.setColor(-1056964609);
      paramPaint.setTextSize(18.0F);
      int i = (int)paramPaint.measureText(localCityInfo.showName);
      if (i > 85)
      {
        paramPaint.setTextSize(85 / localCityInfo.showName.length());
        i = (int)paramPaint.measureText(localCityInfo.showName);
      }
      int j = (85 - i) / 2;
      paramCanvas.drawText(localCityInfo.showName, j + this.objSrcSide[paramInt][0], 55 + this.objSrcSide[paramInt][1], paramPaint);
      return;
      paramCanvas.drawBitmap(this.imgFrameBg[0], this.objSrcSide[paramInt][0], this.objSrcSide[paramInt][1], null);
    }
  }

  private void drawSrcroll(Canvas paramCanvas, int paramInt1, int paramInt2)
  {
    Paint localPaint = new Paint();
    localPaint.setColor(872415231);
    int i = (int)Math.abs((-this.nCacheWidth + N_SCREEN_WIDTH) / this.nCacheWidth * N_SCREEN_WIDTH);
    int j = (int)Math.abs((-this.nCacheHeight + N_SCREEN_HEIGHT) / this.nCacheHeight * N_SCREEN_HEIGHT);
    int k = (int)(Math.abs(paramInt1 / (-this.nCacheWidth + N_SCREEN_WIDTH)) * (N_SCREEN_WIDTH - i));
    int l = (int)(Math.abs(paramInt2 / (-this.nCacheHeight + N_SCREEN_HEIGHT)) * (N_SCREEN_HEIGHT - j));
    if (this.nCacheWidth > N_SCREEN_WIDTH)
      paramCanvas.drawRect(k, -4 + N_SCREEN_HEIGHT, k + i, N_SCREEN_HEIGHT, localPaint);
    if (this.nCacheHeight <= N_SCREEN_HEIGHT)
      return;
    paramCanvas.drawRect(-4 + N_SCREEN_WIDTH, l, N_SCREEN_WIDTH, l + j, localPaint);
  }

  private static int[] getIntegerTemp(String paramString)
  {
    int[] arrayOfInt = new int[2];
    arrayOfInt[0] = 0;
    arrayOfInt[1] = 0;
    String[] arrayOfString = getTemp(paramString);
    if ((arrayOfString == null) || (arrayOfString.length != 2));
    int i;
    do
    {
      return arrayOfInt;
      label31: i = 0;
    }
    while (i >= arrayOfString.length);
    int j = 0;
    for (int k = 0; ; ++k)
    {
      if (k < arrayOfString[i].length())
      {
        int l = arrayOfString[i].charAt(k);
        if (((l >= 48) && (l <= 57)) || (l == 45))
          continue;
        j = k;
      }
      arrayOfInt[i] = Integer.parseInt(arrayOfString[i].substring(0, j));
      ++i;
      break label31:
    }
  }

  private float getMinDistance()
  {
    float f1 = 0.0F;
    if (this.objSrc == null);
    int i;
    do
    {
      return f1;
      i = this.objSrc.size();
    }
    while (i < 1);
    f1 = 100.0F;
    for (int j = 0; ; ++j)
    {
      if (j < i - 1);
      int k = j + 1;
      label42: if (k >= i)
        continue;
      ModelCityBrown.CityInfo localCityInfo1 = (ModelCityBrown.CityInfo)this.objSrc.get(j);
      ModelCityBrown.CityInfo localCityInfo2 = (ModelCityBrown.CityInfo)this.objSrc.get(k);
      float f2 = Math.abs(localCityInfo1.x - localCityInfo2.x) + Math.abs(localCityInfo1.y - localCityInfo2.y);
      if (f2 < 0.1D);
      while (true)
      {
        ++k;
        break label42:
        if (f2 >= f1)
          continue;
        f1 = f2;
      }
    }
  }

  private float[] getRect()
  {
    float[] arrayOfFloat = null;
    if (this.objSrc == null);
    int i;
    do
    {
      return arrayOfFloat;
      i = this.objSrc.size();
    }
    while (i < 1);
    arrayOfFloat = new float[6];
    ModelCityBrown.CityInfo localCityInfo1 = (ModelCityBrown.CityInfo)this.objSrc.get(0);
    if (localCityInfo1 != null)
    {
      arrayOfFloat[0] = localCityInfo1.x;
      arrayOfFloat[1] = localCityInfo1.y;
      arrayOfFloat[2] = localCityInfo1.x;
      arrayOfFloat[3] = localCityInfo1.y;
    }
    for (int j = 1; ; ++j)
    {
      if (j < i);
      ModelCityBrown.CityInfo localCityInfo2 = (ModelCityBrown.CityInfo)this.objSrc.get(j);
      if (localCityInfo2.x < arrayOfFloat[0])
        arrayOfFloat[0] = localCityInfo2.x;
      if (localCityInfo2.x > arrayOfFloat[2])
        arrayOfFloat[2] = localCityInfo2.x;
      if (localCityInfo2.y < arrayOfFloat[1])
        arrayOfFloat[1] = localCityInfo2.y;
      if (localCityInfo2.y <= arrayOfFloat[3])
        continue;
      arrayOfFloat[3] = localCityInfo2.y;
    }
  }

  private static String[] getTemp(String paramString)
  {
    if (paramString == null);
    String[] arrayOfString;
    for (Object localObject = null; ; localObject = arrayOfString)
    {
      return localObject;
      arrayOfString = new String[2];
      int i = paramString.indexOf("~");
      arrayOfString[0] = paramString.substring(0, i);
      arrayOfString[1] = paramString.substring(i + 1);
    }
  }

  private void initSrc(Context paramContext)
  {
    this.activity = ((ActivityWeatherRange)paramContext);
    if (this.imgFrameBg != null)
      return;
    this.imgFrameBg = new Bitmap[2];
    this.imgFrameBg[0] = ((BitmapDrawable)this.mContext.getResources().getDrawable(2130837533)).getBitmap();
    this.imgFrameBg[1] = ((BitmapDrawable)this.mContext.getResources().getDrawable(2130837534)).getBitmap();
    this.imgWeather = new HashMap();
    this.imgWeather.put(Integer.valueOf(0), Integer.valueOf(2130837504));
    this.imgWeather.put(Integer.valueOf(1), Integer.valueOf(2130837505));
    this.imgWeather.put(Integer.valueOf(2), Integer.valueOf(2130837506));
    this.imgWeather.put(Integer.valueOf(3), Integer.valueOf(2130837507));
    this.imgWeather.put(Integer.valueOf(4), Integer.valueOf(2130837508));
    this.imgWeather.put(Integer.valueOf(5), Integer.valueOf(2130837508));
    this.imgWeather.put(Integer.valueOf(6), Integer.valueOf(2130837508));
    this.imgWeather.put(Integer.valueOf(7), Integer.valueOf(2130837509));
    this.imgWeather.put(Integer.valueOf(8), Integer.valueOf(2130837510));
    this.imgWeather.put(Integer.valueOf(9), Integer.valueOf(2130837511));
    this.imgWeather.put(Integer.valueOf(10), Integer.valueOf(2130837512));
    this.imgWeather.put(Integer.valueOf(11), Integer.valueOf(2130837513));
    this.imgWeather.put(Integer.valueOf(12), Integer.valueOf(2130837514));
    this.imgWeather.put(Integer.valueOf(13), Integer.valueOf(2130837515));
    this.imgWeather.put(Integer.valueOf(14), Integer.valueOf(2130837516));
    this.imgWeather.put(Integer.valueOf(15), Integer.valueOf(2130837517));
    this.imgWeather.put(Integer.valueOf(16), Integer.valueOf(2130837518));
    this.imgWeather.put(Integer.valueOf(17), Integer.valueOf(2130837519));
    this.imgWeather.put(Integer.valueOf(18), Integer.valueOf(2130837520));
    this.imgWeather.put(Integer.valueOf(19), Integer.valueOf(2130837520));
    this.imgWeather.put(Integer.valueOf(20), Integer.valueOf(2130837521));
    this.imgWeather.put(Integer.valueOf(21), Integer.valueOf(2130837521));
    this.imgWeather.put(Integer.valueOf(22), Integer.valueOf(2130837522));
    this.imgWeather.put(Integer.valueOf(23), Integer.valueOf(2130837521));
  }

  private void pointerDown(int paramInt1, int paramInt2)
  {
    if (this.objSrc == null);
    int i;
    int j;
    do
    {
      do
      {
        return;
        i = this.objSrc.size();
      }
      while (i < 1);
      label24: j = 0;
    }
    while (j >= i);
    int k = this.objSrcSide[j][0] + this.nOffsetX;
    int l = 0 + (this.objSrcSide[j][1] + this.nOffsetY);
    if ((k < paramInt1) && (l < paramInt2) && (Math.abs(k - paramInt1) < 85) && (Math.abs(l - paramInt2) < 85))
    {
      if ((this.nSelectedIndex != j) || (System.currentTimeMillis() - this.lOperateTime >= 1500L))
        break label161;
      ModelCityBrown.CityInfo localCityInfo = (ModelCityBrown.CityInfo)this.objSrc.get(j);
      this.activity.gotoDistCity(localCityInfo.name, localCityInfo.pid);
    }
    while (true)
    {
      ++j;
      break label24:
      label161: this.lOperateTime = System.currentTimeMillis();
      this.nSelectedIndex = j;
      postInvalidate();
    }
  }

  private void sumSide()
  {
    if (this.objSrc == null);
    while (true)
    {
      return;
      int i = this.objSrc.size();
      int[] arrayOfInt = new int[2];
      arrayOfInt[0] = i;
      arrayOfInt[1] = 2;
      this.objSrcSide = ((int[][])Array.newInstance(Integer.TYPE, arrayOfInt));
      float[] arrayOfFloat = getRect();
      float f1 = arrayOfFloat[2] - arrayOfFloat[0];
      float f2 = arrayOfFloat[3] - arrayOfFloat[1];
      float f3 = getMinDistance();
      float f4 = Math.min(f1, f2) / 4.0F / f3;
      if (f4 < 0.4F)
        f4 = 0.4F;
      if (f4 > 3.0F)
        f4 = 3.0F;
      float f5 = f4;
      float f6 = f4;
      this.nCacheWidth = (int)(255.0F + f5 * N_SCREEN_WIDTH);
      this.nCacheHeight = (int)(255.0F + f4 * N_SCREEN_HEIGHT);
      if (this.nCacheWidth < N_SCREEN_WIDTH)
      {
        this.nCacheWidth = N_SCREEN_WIDTH;
        f5 = (-255 + N_SCREEN_WIDTH) / N_SCREEN_WIDTH;
      }
      if (this.nCacheHeight < N_SCREEN_HEIGHT)
      {
        this.nCacheHeight = N_SCREEN_HEIGHT;
        f6 = (-255 + N_SCREEN_HEIGHT) / N_SCREEN_HEIGHT;
      }
      int j = this.objSrc.size();
      for (int k = 0; k < j; ++k)
      {
        ModelCityBrown.CityInfo localCityInfo = (ModelCityBrown.CityInfo)this.objSrc.get(k);
        float f7 = f5 * ((localCityInfo.x - arrayOfFloat[0]) * N_SCREEN_WIDTH) / f1;
        float f8 = f6 * (-(localCityInfo.y - arrayOfFloat[1]) * N_SCREEN_HEIGHT) / f2;
        float f9 = f7 + 85.0F;
        float f10 = 85.0F + (f8 + f6 * N_SCREEN_HEIGHT);
        this.objSrcSide[k][0] = (int)f9;
        this.objSrcSide[k][1] = (int)f10;
      }
      this.nOffsetX = (N_SCREEN_WIDTH / 2 - this.objSrcSide[0][0]);
      this.nOffsetY = (N_SCREEN_HEIGHT / 2 - this.objSrcSide[0][1]);
      clampOffset();
    }
  }

  public void destroySrc()
  {
    if (this.imgFrameBg == null)
      return;
    this.imgFrameBg[0] = null;
    this.imgFrameBg[1] = null;
    this.imgFrameBg = null;
    if (this.imgWeather == null)
      return;
    this.imgWeather.clear();
    this.imgWeather = null;
  }

  public boolean initData(String paramString, boolean paramBoolean)
  {
    int i = 0;
    if (paramBoolean)
    {
      this.imgWeather.put(Integer.valueOf(0), Integer.valueOf(2130837536));
      this.imgWeather.put(Integer.valueOf(1), Integer.valueOf(2130837537));
    }
    this.objWeatherSrc = new ModelCityBrown().load(paramString);
    if (this.objWeatherSrc == null);
    while (true)
    {
      return i;
      for (int j = 0; j < this.objWeatherSrc.size(); ++j)
      {
        ModelCityBrown.WeatherInfo localWeatherInfo = (ModelCityBrown.WeatherInfo)this.objWeatherSrc.get(j);
        if ((TextUtils.isEmpty(localWeatherInfo.temp)) || (localWeatherInfo.temp.equals("null")))
          continue;
        int[] arrayOfInt = getIntegerTemp(localWeatherInfo.temp);
        if ((arrayOfInt == null) || (arrayOfInt.length != 2))
          continue;
        localWeatherInfo.temp = (Math.min(arrayOfInt[0], arrayOfInt[1]) + WeatherParser.TEMP_UNIT + "~" + Math.max(arrayOfInt[0], arrayOfInt[1]) + WeatherParser.TEMP_UNIT);
      }
      postInvalidate();
      i = 1;
    }
  }

  public boolean initData(ArrayList<CityData> paramArrayList, String paramString)
  {
    this.objSrc = new ModelCityBrown().load(paramArrayList);
    this.objWeatherSrc = null;
    int i = 0;
    if (i < paramArrayList.size())
    {
      label22: if (!((CityData)paramArrayList.get(i)).pid.equals(paramString))
        break label75;
      this.nSelectedIndex = i;
    }
    if (this.mLayoutFinished)
      sumSide();
    for (this.mDataChanged = false; ; this.mDataChanged = true)
    {
      postInvalidate();
      return true;
      label75: ++i;
      break label22:
    }
  }

  protected void onDraw(Canvas paramCanvas)
  {
    int i = this.nOffsetX;
    int j = this.nOffsetY;
    updateScreenCache(paramCanvas, i, j);
    drawSrcroll(paramCanvas, i, j);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    N_SCREEN_WIDTH = getWidth();
    N_SCREEN_HEIGHT = getHeight();
    this.mLayoutFinished = true;
    if (this.mDataChanged)
    {
      sumSide();
      this.mDataChanged = false;
    }
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    setMeasuredDimension(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    int i = (int)paramMotionEvent.getX();
    int j = (int)paramMotionEvent.getY();
    switch (paramMotionEvent.getAction())
    {
    default:
    case 0:
    case 2:
    case 1:
    }
    while (true)
    {
      return true;
      this.nPointX = i;
      this.nPointY = j;
      this.mOriginalOffsetX = this.nOffsetX;
      this.mOriginalOffsetY = this.nOffsetY;
      this.blnMoveFlag = false;
      continue;
      if ((!this.blnMoveFlag) && (ToolUtils.isMinMoveDistance(40, i, j, this.nPointX, this.nPointY)))
        continue;
      this.blnMoveFlag = true;
      this.nMoveX = (i - this.nPointX);
      this.nMoveY = (j - this.nPointY);
      this.nOffsetX = (this.mOriginalOffsetX + this.nMoveX);
      this.nOffsetY = (this.mOriginalOffsetY + this.nMoveY);
      clampOffset();
      postInvalidate();
      continue;
      if ((!this.blnMoveFlag) && (ToolUtils.isMinMoveDistance(40, i, j, this.nPointX, this.nPointY)))
      {
        pointerDown(i, j);
        postInvalidate();
      }
      this.nMoveX = 0;
      this.nMoveY = 0;
    }
  }

  public void updateScreenCache(Canvas paramCanvas, int paramInt1, int paramInt2)
  {
    if (this.objSrc == null);
    while (true)
    {
      return;
      paramCanvas.translate(paramInt1, paramInt2);
      Paint localPaint = new Paint();
      localPaint.setAntiAlias(true);
      localPaint.setTextSize(20.0F);
      drawBgLine(paramCanvas, localPaint);
      int i = this.objSrc.size();
      int j = 0;
      if (j < i)
      {
        label58: if (j == this.nSelectedIndex);
        while (true)
        {
          ++j;
          break label58:
          drawBody(paramCanvas, localPaint, j, false);
        }
      }
      if ((this.nSelectedIndex >= 0) && (this.nSelectedIndex < i))
        drawBody(paramCanvas, localPaint, this.nSelectedIndex, true);
      paramCanvas.translate(-paramInt1, -paramInt2);
    }
  }
}

/* Location:           C:\Users\moon\Desktop\classes-dex2jar.jar
 * Qualified Name:     com.miui.weather2.view.ViewWeatherRange
 * JD-Core Version:    0.5.4
 */