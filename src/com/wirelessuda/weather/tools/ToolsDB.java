package com.wirelessuda.weather.tools;
/*
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.miui.weather2.Config;
import com.miui.weather2.model.CityData;
import com.miui.weather2.model.HotCityData;
import com.miui.weather2.model.LinkCityData;
import com.miui.weather2.service.ServiceUpdateWeather;
import java.util.ArrayList;
import java.util.List;
import miui.provider.Weather.HotCity;
import miui.provider.Weather.SelectedCity;

public class ToolsDB
{
  private static int nextPos = -1;
  private SQLiteDatabase connMain = null;

  public static void RemoveGpsCity(Context paramContext)
  {
    ArrayList localArrayList = getLinkCity(paramContext, "flag='1'");
    if ((localArrayList == null) || (localArrayList.size() <= 0))
      return;
    deleteLinkCity(paramContext, ((LinkCityData)localArrayList.get(0)).id);
  }

  public static void deleteLinkCity(Context paramContext, int paramInt)
  {
    paramContext.getContentResolver().delete(Uri.withAppendedPath(Weather.SelectedCity.CONTENT_URI, String.valueOf(paramInt)), null, null);
  }

  // ERROR //
  public static ArrayList<CityData> getCity(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aload_0
    //   3: invokevirtual 50	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   6: getstatic 80	miui/provider/Weather$City:CONTENT_URI	Landroid/net/Uri;
    //   9: aconst_null
    //   10: aload_1
    //   11: aconst_null
    //   12: aconst_null
    //   13: invokevirtual 84	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   16: astore_3
    //   17: aconst_null
    //   18: astore 4
    //   20: aload_3
    //   21: ifnull +230 -> 251
    //   24: aload_3
    //   25: invokeinterface 89 1 0
    //   30: istore 8
    //   32: iload 8
    //   34: ifne +11 -> 45
    //   37: aload_3
    //   38: invokeinterface 92 1 0
    //   43: aload_2
    //   44: areturn
    //   45: aload_3
    //   46: invokeinterface 96 1 0
    //   51: pop
    //   52: new 98	java/util/ArrayList
    //   55: dup
    //   56: invokespecial 99	java/util/ArrayList:<init>	()V
    //   59: astore 10
    //   61: new 101	com/miui/weather2/model/CityData
    //   64: dup
    //   65: invokespecial 102	com/miui/weather2/model/CityData:<init>	()V
    //   68: astore 11
    //   70: aload 11
    //   72: aload_3
    //   73: aload_3
    //   74: ldc 104
    //   76: invokeinterface 108 2 0
    //   81: invokeinterface 111 2 0
    //   86: putfield 114	com/miui/weather2/model/CityData:root	Ljava/lang/String;
    //   89: aload 11
    //   91: aload_3
    //   92: aload_3
    //   93: ldc 116
    //   95: invokeinterface 108 2 0
    //   100: invokeinterface 111 2 0
    //   105: putfield 118	com/miui/weather2/model/CityData:parent	Ljava/lang/String;
    //   108: aload 11
    //   110: aload_3
    //   111: aload_3
    //   112: ldc 120
    //   114: invokeinterface 108 2 0
    //   119: invokeinterface 111 2 0
    //   124: putfield 122	com/miui/weather2/model/CityData:pinyin	Ljava/lang/String;
    //   127: aload 11
    //   129: aload_3
    //   130: aload_3
    //   131: ldc 124
    //   133: invokeinterface 108 2 0
    //   138: invokeinterface 111 2 0
    //   143: putfield 126	com/miui/weather2/model/CityData:name	Ljava/lang/String;
    //   146: aload 11
    //   148: aload_3
    //   149: aload_3
    //   150: ldc 128
    //   152: invokeinterface 108 2 0
    //   157: invokeinterface 111 2 0
    //   162: putfield 131	com/miui/weather2/model/CityData:pid	Ljava/lang/String;
    //   165: aload 11
    //   167: aload_3
    //   168: aload_3
    //   169: ldc 133
    //   171: invokeinterface 108 2 0
    //   176: invokeinterface 111 2 0
    //   181: putfield 136	com/miui/weather2/model/CityData:p_x	Ljava/lang/String;
    //   184: aload 11
    //   186: aload_3
    //   187: aload_3
    //   188: ldc 138
    //   190: invokeinterface 108 2 0
    //   195: invokeinterface 111 2 0
    //   200: putfield 141	com/miui/weather2/model/CityData:p_y	Ljava/lang/String;
    //   203: getstatic 146	com/miui/weather2/Config:INT_SYS_LANGUAGE	I
    //   206: iconst_1
    //   207: if_icmpne +50 -> 257
    //   210: aload 11
    //   212: aload 11
    //   214: getfield 122	com/miui/weather2/model/CityData:pinyin	Ljava/lang/String;
    //   217: putfield 149	com/miui/weather2/model/CityData:showName	Ljava/lang/String;
    //   220: aload 10
    //   222: aload 11
    //   224: invokevirtual 153	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   227: pop
    //   228: aload_3
    //   229: invokeinterface 156 1 0
    //   234: istore 13
    //   236: iload 13
    //   238: ifne -177 -> 61
    //   241: aload_3
    //   242: invokeinterface 92 1 0
    //   247: aload 10
    //   249: astore 4
    //   251: aload 4
    //   253: astore_2
    //   254: goto -211 -> 43
    //   257: aload 11
    //   259: aload 11
    //   261: getfield 126	com/miui/weather2/model/CityData:name	Ljava/lang/String;
    //   264: putfield 149	com/miui/weather2/model/CityData:showName	Ljava/lang/String;
    //   267: goto -47 -> 220
    //   270: astore 5
    //   272: aload 10
    //   274: astore 4
    //   276: ldc 158
    //   278: ldc 160
    //   280: aload 5
    //   282: invokestatic 166	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   285: pop
    //   286: aload_3
    //   287: invokeinterface 92 1 0
    //   292: goto -41 -> 251
    //   295: astore 6
    //   297: aload_3
    //   298: invokeinterface 92 1 0
    //   303: aload 6
    //   305: athrow
    //   306: astore 6
    //   308: goto -11 -> 297
    //   311: astore 5
    //   313: goto -37 -> 276
    //
    // Exception table:
    //   from	to	target	type
    //   61	236	270	android/database/sqlite/SQLiteException
    //   257	267	270	android/database/sqlite/SQLiteException
    //   24	32	295	finally
    //   45	61	295	finally
    //   276	286	295	finally
    //   61	236	306	finally
    //   257	267	306	finally
    //   24	32	311	android/database/sqlite/SQLiteException
    //   45	61	311	android/database/sqlite/SQLiteException
  }

  public static ArrayList<CityData> getCityByPID(Context paramContext, String paramString)
  {
    return getCity(paramContext, "posID = \"" + paramString + "\"");
  }

  // ERROR //
  public static CityData getCityID(Context paramContext, android.location.Address paramAddress)
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnonnull +9 -> 10
    //   4: aconst_null
    //   5: astore 7
    //   7: aload 7
    //   9: areturn
    //   10: iconst_3
    //   11: anewarray 4	java/lang/Object
    //   14: astore_2
    //   15: aload_2
    //   16: iconst_0
    //   17: aload_1
    //   18: invokevirtual 191	android/location/Address:getAdminArea	()Ljava/lang/String;
    //   21: aastore
    //   22: aload_2
    //   23: iconst_1
    //   24: aload_1
    //   25: invokevirtual 194	android/location/Address:getLocality	()Ljava/lang/String;
    //   28: aastore
    //   29: aload_2
    //   30: iconst_2
    //   31: aload_1
    //   32: invokevirtual 197	android/location/Address:getSubLocality	()Ljava/lang/String;
    //   35: aastore
    //   36: ldc 158
    //   38: ldc 199
    //   40: aload_2
    //   41: invokestatic 203	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   44: invokestatic 207	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   47: pop
    //   48: aload_1
    //   49: invokevirtual 191	android/location/Address:getAdminArea	()Ljava/lang/String;
    //   52: astore 4
    //   54: aload_1
    //   55: invokevirtual 194	android/location/Address:getLocality	()Ljava/lang/String;
    //   58: astore 5
    //   60: aload_1
    //   61: invokevirtual 197	android/location/Address:getSubLocality	()Ljava/lang/String;
    //   64: astore 6
    //   66: aload 4
    //   68: invokestatic 213	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   71: ifne +11 -> 82
    //   74: aload 5
    //   76: invokestatic 213	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   79: ifeq +9 -> 88
    //   82: aconst_null
    //   83: astore 7
    //   85: goto -78 -> 7
    //   88: aload 6
    //   90: invokestatic 213	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   93: ifeq +7 -> 100
    //   96: aload 5
    //   98: astore 6
    //   100: aconst_null
    //   101: astore 8
    //   103: aconst_null
    //   104: astore 9
    //   106: aload_0
    //   107: invokevirtual 50	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   110: getstatic 80	miui/provider/Weather$City:CONTENT_URI	Landroid/net/Uri;
    //   113: aconst_null
    //   114: aconst_null
    //   115: aconst_null
    //   116: aconst_null
    //   117: invokevirtual 84	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   120: astore 13
    //   122: aload 13
    //   124: astore 8
    //   126: aload 8
    //   128: ifnonnull +21 -> 149
    //   131: aconst_null
    //   132: astore 7
    //   134: aload 8
    //   136: ifnull -129 -> 7
    //   139: aload 8
    //   141: invokeinterface 92 1 0
    //   146: goto -139 -> 7
    //   149: aload 8
    //   151: invokeinterface 89 1 0
    //   156: istore 14
    //   158: iload 14
    //   160: ifne +21 -> 181
    //   163: aconst_null
    //   164: astore 7
    //   166: aload 8
    //   168: ifnull -161 -> 7
    //   171: aload 8
    //   173: invokeinterface 92 1 0
    //   178: goto -171 -> 7
    //   181: aload 8
    //   183: invokeinterface 216 1 0
    //   188: pop
    //   189: aload 9
    //   191: astore 16
    //   193: aload 8
    //   195: aload 8
    //   197: ldc 104
    //   199: invokeinterface 108 2 0
    //   204: invokeinterface 111 2 0
    //   209: astore 17
    //   211: aload 8
    //   213: aload 8
    //   215: ldc 116
    //   217: invokeinterface 108 2 0
    //   222: invokeinterface 111 2 0
    //   227: astore 18
    //   229: aload 8
    //   231: aload 8
    //   233: ldc 124
    //   235: invokeinterface 108 2 0
    //   240: invokeinterface 111 2 0
    //   245: astore 19
    //   247: aload 4
    //   249: aload 17
    //   251: invokevirtual 219	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   254: ifeq +300 -> 554
    //   257: aload 5
    //   259: aload 18
    //   261: invokevirtual 219	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   264: ifeq +290 -> 554
    //   267: aload 6
    //   269: aload 19
    //   271: invokevirtual 219	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   274: ifeq +129 -> 403
    //   277: new 101	com/miui/weather2/model/CityData
    //   280: dup
    //   281: invokespecial 102	com/miui/weather2/model/CityData:<init>	()V
    //   284: astore 7
    //   286: aload 7
    //   288: aload 19
    //   290: putfield 126	com/miui/weather2/model/CityData:name	Ljava/lang/String;
    //   293: aload 7
    //   295: aload 8
    //   297: aload 8
    //   299: ldc 128
    //   301: invokeinterface 108 2 0
    //   306: invokeinterface 111 2 0
    //   311: putfield 131	com/miui/weather2/model/CityData:pid	Ljava/lang/String;
    //   314: getstatic 146	com/miui/weather2/Config:INT_SYS_LANGUAGE	I
    //   317: iconst_1
    //   318: if_icmpne +42 -> 360
    //   321: aload 7
    //   323: aload_0
    //   324: aload 7
    //   326: getfield 131	com/miui/weather2/model/CityData:pid	Ljava/lang/String;
    //   329: invokestatic 221	com/miui/weather2/tools/ToolsDB:getCityByPID	(Landroid/content/Context;Ljava/lang/String;)Ljava/util/ArrayList;
    //   332: iconst_0
    //   333: invokevirtual 222	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   336: checkcast 101	com/miui/weather2/model/CityData
    //   339: getfield 149	com/miui/weather2/model/CityData:showName	Ljava/lang/String;
    //   342: putfield 149	com/miui/weather2/model/CityData:showName	Ljava/lang/String;
    //   345: aload 8
    //   347: ifnull -340 -> 7
    //   350: aload 8
    //   352: invokeinterface 92 1 0
    //   357: goto -350 -> 7
    //   360: aload 7
    //   362: aload 7
    //   364: getfield 126	com/miui/weather2/model/CityData:name	Ljava/lang/String;
    //   367: putfield 149	com/miui/weather2/model/CityData:showName	Ljava/lang/String;
    //   370: goto -25 -> 345
    //   373: astore 11
    //   375: ldc 158
    //   377: ldc 160
    //   379: aload 11
    //   381: invokestatic 166	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   384: pop
    //   385: aconst_null
    //   386: astore 7
    //   388: aload 8
    //   390: ifnull -383 -> 7
    //   393: aload 8
    //   395: invokeinterface 92 1 0
    //   400: goto -393 -> 7
    //   403: aload 5
    //   405: aload 19
    //   407: invokevirtual 219	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   410: ifeq +144 -> 554
    //   413: new 101	com/miui/weather2/model/CityData
    //   416: dup
    //   417: invokespecial 102	com/miui/weather2/model/CityData:<init>	()V
    //   420: astore 9
    //   422: aload 9
    //   424: aload 19
    //   426: putfield 126	com/miui/weather2/model/CityData:name	Ljava/lang/String;
    //   429: aload 9
    //   431: aload 8
    //   433: aload 8
    //   435: ldc 128
    //   437: invokeinterface 108 2 0
    //   442: invokeinterface 111 2 0
    //   447: putfield 131	com/miui/weather2/model/CityData:pid	Ljava/lang/String;
    //   450: getstatic 146	com/miui/weather2/Config:INT_SYS_LANGUAGE	I
    //   453: iconst_1
    //   454: if_icmpne +60 -> 514
    //   457: aload 9
    //   459: aload_0
    //   460: aload 9
    //   462: getfield 131	com/miui/weather2/model/CityData:pid	Ljava/lang/String;
    //   465: invokestatic 221	com/miui/weather2/tools/ToolsDB:getCityByPID	(Landroid/content/Context;Ljava/lang/String;)Ljava/util/ArrayList;
    //   468: iconst_0
    //   469: invokevirtual 222	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   472: checkcast 101	com/miui/weather2/model/CityData
    //   475: getfield 149	com/miui/weather2/model/CityData:showName	Ljava/lang/String;
    //   478: putfield 149	com/miui/weather2/model/CityData:showName	Ljava/lang/String;
    //   481: aload 8
    //   483: invokeinterface 225 1 0
    //   488: istore 20
    //   490: iload 20
    //   492: ifne -303 -> 189
    //   495: aload 8
    //   497: ifnull +10 -> 507
    //   500: aload 8
    //   502: invokeinterface 92 1 0
    //   507: aload 9
    //   509: astore 7
    //   511: goto -504 -> 7
    //   514: aload 9
    //   516: aload 9
    //   518: getfield 126	com/miui/weather2/model/CityData:name	Ljava/lang/String;
    //   521: putfield 149	com/miui/weather2/model/CityData:showName	Ljava/lang/String;
    //   524: goto -43 -> 481
    //   527: astore 11
    //   529: goto -154 -> 375
    //   532: astore 10
    //   534: aload 8
    //   536: ifnull +10 -> 546
    //   539: aload 8
    //   541: invokeinterface 92 1 0
    //   546: aload 10
    //   548: athrow
    //   549: astore 10
    //   551: goto -17 -> 534
    //   554: aload 16
    //   556: astore 9
    //   558: goto -77 -> 481
    //
    // Exception table:
    //   from	to	target	type
    //   193	345	373	android/database/sqlite/SQLiteException
    //   360	370	373	android/database/sqlite/SQLiteException
    //   403	422	373	android/database/sqlite/SQLiteException
    //   106	122	527	android/database/sqlite/SQLiteException
    //   149	158	527	android/database/sqlite/SQLiteException
    //   181	189	527	android/database/sqlite/SQLiteException
    //   422	490	527	android/database/sqlite/SQLiteException
    //   514	524	527	android/database/sqlite/SQLiteException
    //   106	122	532	finally
    //   149	158	532	finally
    //   181	189	532	finally
    //   375	385	532	finally
    //   422	490	532	finally
    //   514	524	532	finally
    //   193	345	549	finally
    //   360	370	549	finally
    //   403	422	549	finally
  }

  // ERROR //
  public static CityData getCityIDByAddressComponent(Context paramContext, AddressComponentInfo paramAddressComponentInfo)
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnonnull +9 -> 10
    //   4: aconst_null
    //   5: astore 9
    //   7: aload 9
    //   9: areturn
    //   10: bipush 6
    //   12: anewarray 4	java/lang/Object
    //   15: astore_2
    //   16: aload_2
    //   17: iconst_0
    //   18: aload_1
    //   19: getfield 232	com/miui/weather2/tools/AddressComponentInfo:administrative_area_level_1_short	Ljava/lang/String;
    //   22: aastore
    //   23: aload_2
    //   24: iconst_1
    //   25: aload_1
    //   26: getfield 235	com/miui/weather2/tools/AddressComponentInfo:administrative_area_level_1_long	Ljava/lang/String;
    //   29: aastore
    //   30: aload_2
    //   31: iconst_2
    //   32: aload_1
    //   33: getfield 238	com/miui/weather2/tools/AddressComponentInfo:locality_short	Ljava/lang/String;
    //   36: aastore
    //   37: aload_2
    //   38: iconst_3
    //   39: aload_1
    //   40: getfield 241	com/miui/weather2/tools/AddressComponentInfo:locality_long	Ljava/lang/String;
    //   43: aastore
    //   44: aload_2
    //   45: iconst_4
    //   46: aload_1
    //   47: getfield 244	com/miui/weather2/tools/AddressComponentInfo:sublocality_short	Ljava/lang/String;
    //   50: aastore
    //   51: aload_2
    //   52: iconst_5
    //   53: aload_1
    //   54: getfield 247	com/miui/weather2/tools/AddressComponentInfo:sublocality_long	Ljava/lang/String;
    //   57: aastore
    //   58: ldc 158
    //   60: ldc 249
    //   62: aload_2
    //   63: invokestatic 203	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   66: invokestatic 207	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   69: pop
    //   70: aload_1
    //   71: invokevirtual 252	com/miui/weather2/tools/AddressComponentInfo:isAllAddressComponentsValid	()Z
    //   74: ifne +33 -> 107
    //   77: aload_1
    //   78: invokevirtual 255	com/miui/weather2/tools/AddressComponentInfo:isAdminAreaValid	()Z
    //   81: ifeq +26 -> 107
    //   84: aload_1
    //   85: invokevirtual 258	com/miui/weather2/tools/AddressComponentInfo:isLocalityValid	()Z
    //   88: ifeq +19 -> 107
    //   91: aload_1
    //   92: aload_1
    //   93: getfield 238	com/miui/weather2/tools/AddressComponentInfo:locality_short	Ljava/lang/String;
    //   96: putfield 244	com/miui/weather2/tools/AddressComponentInfo:sublocality_short	Ljava/lang/String;
    //   99: aload_1
    //   100: aload_1
    //   101: getfield 241	com/miui/weather2/tools/AddressComponentInfo:locality_long	Ljava/lang/String;
    //   104: putfield 247	com/miui/weather2/tools/AddressComponentInfo:sublocality_long	Ljava/lang/String;
    //   107: aconst_null
    //   108: astore 4
    //   110: aconst_null
    //   111: astore 5
    //   113: aload_0
    //   114: invokevirtual 50	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   117: getstatic 80	miui/provider/Weather$City:CONTENT_URI	Landroid/net/Uri;
    //   120: aconst_null
    //   121: aconst_null
    //   122: aconst_null
    //   123: aconst_null
    //   124: invokevirtual 84	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   127: astore 10
    //   129: aload 10
    //   131: astore 4
    //   133: aload 4
    //   135: ifnonnull +21 -> 156
    //   138: aconst_null
    //   139: astore 9
    //   141: aload 4
    //   143: ifnull -136 -> 7
    //   146: aload 4
    //   148: invokeinterface 92 1 0
    //   153: goto -146 -> 7
    //   156: aload 4
    //   158: invokeinterface 89 1 0
    //   163: istore 11
    //   165: iload 11
    //   167: ifne +21 -> 188
    //   170: aconst_null
    //   171: astore 9
    //   173: aload 4
    //   175: ifnull -168 -> 7
    //   178: aload 4
    //   180: invokeinterface 92 1 0
    //   185: goto -178 -> 7
    //   188: aload 4
    //   190: invokeinterface 216 1 0
    //   195: pop
    //   196: aload 5
    //   198: astore 13
    //   200: aload 4
    //   202: aload 4
    //   204: ldc 104
    //   206: invokeinterface 108 2 0
    //   211: invokeinterface 111 2 0
    //   216: astore 14
    //   218: aload 4
    //   220: aload 4
    //   222: ldc 116
    //   224: invokeinterface 108 2 0
    //   229: invokeinterface 111 2 0
    //   234: astore 15
    //   236: aload 4
    //   238: aload 4
    //   240: ldc 124
    //   242: invokeinterface 108 2 0
    //   247: invokeinterface 111 2 0
    //   252: astore 16
    //   254: aload_1
    //   255: aload 14
    //   257: invokevirtual 262	com/miui/weather2/tools/AddressComponentInfo:match_administrative_area_level_1	(Ljava/lang/String;)Z
    //   260: ifeq +297 -> 557
    //   263: aload_1
    //   264: aload 15
    //   266: invokevirtual 265	com/miui/weather2/tools/AddressComponentInfo:match_locality	(Ljava/lang/String;)Z
    //   269: ifeq +288 -> 557
    //   272: aload_1
    //   273: aload 16
    //   275: invokevirtual 268	com/miui/weather2/tools/AddressComponentInfo:match_sublocality	(Ljava/lang/String;)Z
    //   278: ifeq +129 -> 407
    //   281: new 101	com/miui/weather2/model/CityData
    //   284: dup
    //   285: invokespecial 102	com/miui/weather2/model/CityData:<init>	()V
    //   288: astore 9
    //   290: aload 9
    //   292: aload 16
    //   294: putfield 126	com/miui/weather2/model/CityData:name	Ljava/lang/String;
    //   297: aload 9
    //   299: aload 4
    //   301: aload 4
    //   303: ldc 128
    //   305: invokeinterface 108 2 0
    //   310: invokeinterface 111 2 0
    //   315: putfield 131	com/miui/weather2/model/CityData:pid	Ljava/lang/String;
    //   318: getstatic 146	com/miui/weather2/Config:INT_SYS_LANGUAGE	I
    //   321: iconst_1
    //   322: if_icmpne +42 -> 364
    //   325: aload 9
    //   327: aload_0
    //   328: aload 9
    //   330: getfield 131	com/miui/weather2/model/CityData:pid	Ljava/lang/String;
    //   333: invokestatic 221	com/miui/weather2/tools/ToolsDB:getCityByPID	(Landroid/content/Context;Ljava/lang/String;)Ljava/util/ArrayList;
    //   336: iconst_0
    //   337: invokevirtual 222	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   340: checkcast 101	com/miui/weather2/model/CityData
    //   343: getfield 149	com/miui/weather2/model/CityData:showName	Ljava/lang/String;
    //   346: putfield 149	com/miui/weather2/model/CityData:showName	Ljava/lang/String;
    //   349: aload 4
    //   351: ifnull -344 -> 7
    //   354: aload 4
    //   356: invokeinterface 92 1 0
    //   361: goto -354 -> 7
    //   364: aload 9
    //   366: aload 9
    //   368: getfield 126	com/miui/weather2/model/CityData:name	Ljava/lang/String;
    //   371: putfield 149	com/miui/weather2/model/CityData:showName	Ljava/lang/String;
    //   374: goto -25 -> 349
    //   377: astore 7
    //   379: ldc 158
    //   381: ldc 160
    //   383: aload 7
    //   385: invokestatic 166	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   388: pop
    //   389: aconst_null
    //   390: astore 9
    //   392: aload 4
    //   394: ifnull -387 -> 7
    //   397: aload 4
    //   399: invokeinterface 92 1 0
    //   404: goto -397 -> 7
    //   407: aload_1
    //   408: aload 16
    //   410: invokevirtual 265	com/miui/weather2/tools/AddressComponentInfo:match_locality	(Ljava/lang/String;)Z
    //   413: ifeq +144 -> 557
    //   416: new 101	com/miui/weather2/model/CityData
    //   419: dup
    //   420: invokespecial 102	com/miui/weather2/model/CityData:<init>	()V
    //   423: astore 5
    //   425: aload 5
    //   427: aload 16
    //   429: putfield 126	com/miui/weather2/model/CityData:name	Ljava/lang/String;
    //   432: aload 5
    //   434: aload 4
    //   436: aload 4
    //   438: ldc 128
    //   440: invokeinterface 108 2 0
    //   445: invokeinterface 111 2 0
    //   450: putfield 131	com/miui/weather2/model/CityData:pid	Ljava/lang/String;
    //   453: getstatic 146	com/miui/weather2/Config:INT_SYS_LANGUAGE	I
    //   456: iconst_1
    //   457: if_icmpne +60 -> 517
    //   460: aload 5
    //   462: aload_0
    //   463: aload 5
    //   465: getfield 131	com/miui/weather2/model/CityData:pid	Ljava/lang/String;
    //   468: invokestatic 221	com/miui/weather2/tools/ToolsDB:getCityByPID	(Landroid/content/Context;Ljava/lang/String;)Ljava/util/ArrayList;
    //   471: iconst_0
    //   472: invokevirtual 222	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   475: checkcast 101	com/miui/weather2/model/CityData
    //   478: getfield 149	com/miui/weather2/model/CityData:showName	Ljava/lang/String;
    //   481: putfield 149	com/miui/weather2/model/CityData:showName	Ljava/lang/String;
    //   484: aload 4
    //   486: invokeinterface 225 1 0
    //   491: istore 17
    //   493: iload 17
    //   495: ifne -299 -> 196
    //   498: aload 4
    //   500: ifnull +10 -> 510
    //   503: aload 4
    //   505: invokeinterface 92 1 0
    //   510: aload 5
    //   512: astore 9
    //   514: goto -507 -> 7
    //   517: aload 5
    //   519: aload 5
    //   521: getfield 126	com/miui/weather2/model/CityData:name	Ljava/lang/String;
    //   524: putfield 149	com/miui/weather2/model/CityData:showName	Ljava/lang/String;
    //   527: goto -43 -> 484
    //   530: astore 7
    //   532: goto -153 -> 379
    //   535: astore 6
    //   537: aload 4
    //   539: ifnull +10 -> 549
    //   542: aload 4
    //   544: invokeinterface 92 1 0
    //   549: aload 6
    //   551: athrow
    //   552: astore 6
    //   554: goto -17 -> 537
    //   557: aload 13
    //   559: astore 5
    //   561: goto -77 -> 484
    //
    // Exception table:
    //   from	to	target	type
    //   200	349	377	android/database/sqlite/SQLiteException
    //   364	374	377	android/database/sqlite/SQLiteException
    //   407	425	377	android/database/sqlite/SQLiteException
    //   113	129	530	android/database/sqlite/SQLiteException
    //   156	165	530	android/database/sqlite/SQLiteException
    //   188	196	530	android/database/sqlite/SQLiteException
    //   425	493	530	android/database/sqlite/SQLiteException
    //   517	527	530	android/database/sqlite/SQLiteException
    //   113	129	535	finally
    //   156	165	535	finally
    //   188	196	535	finally
    //   379	389	535	finally
    //   425	493	535	finally
    //   517	527	535	finally
    //   200	349	552	finally
    //   364	374	552	finally
    //   407	425	552	finally
  }

  public static ArrayList<HotCityData> getHotCity(Context paramContext)
  {
    switch (Config.INT_SYS_LANGUAGE)
    {
    default:
    case 1:
    case 0:
    case 2:
    }
    for (ArrayList localArrayList = getHotCityDefault(paramContext, false); ; localArrayList = getHotCityTW(paramContext))
      while (true)
      {
        return localArrayList;
        localArrayList = getHotCityDefault(paramContext, true);
        continue;
        localArrayList = getHotCityDefault(paramContext, false);
      }
  }

  public static ArrayList<HotCityData> getHotCityDefault(Context paramContext, boolean paramBoolean)
  {
    Object localObject1 = null;
    Object localObject2;
    try
    {
      Cursor localCursor = paramContext.getContentResolver().query(Weather.HotCity.CONTENT_URI, null, null, null, null);
      localObject1 = localCursor;
      localObject2 = null;
      label50: label195: label210: if (localObject1 == null);
    }
    catch (SQLiteException localSQLiteException)
    {
      try
      {
        int i = localObject1.getCount();
        Object localObject3;
        if (i == 0)
        {
          localObject1.close();
          return localObject3;
          localSQLiteException = localSQLiteException;
          Log.v("weather.tools.ToolsDB", "error", localSQLiteException);
        }
        localObject1.moveToFirst();
        while (true)
        {
          ArrayList localArrayList;
          HotCityData localHotCityData;
          try
          {
            boolean bool;
            do
            {
              localHotCityData = new HotCityData();
              localHotCityData.name = localObject1.getString(localObject1.getColumnIndex("name"));
              localHotCityData.pid = localObject1.getString(localObject1.getColumnIndex("posID"));
              if (!paramBoolean)
                break label195;
              localHotCityData.showName = ((CityData)getCityByPID(paramContext, localHotCityData.pid).get(0)).pinyin;
              localArrayList.add(localHotCityData);
              bool = localObject1.moveToNext();
            }
            while (bool);
            localObject1.close();
            localObject2 = localArrayList;
            localObject3 = localObject2;
            break label50:
          }
          finally
          {
            localObject1.close();
          }
        }
      }
      finally
      {
        break label210:
      }
    }
  }

  // ERROR //
  public static ArrayList<HotCityData> getHotCityTW(Context paramContext)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: new 296	org/json/JSONObject
    //   5: dup
    //   6: aload_0
    //   7: invokevirtual 300	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   10: ldc_w 301
    //   13: invokevirtual 307	android/content/res/Resources:openRawResource	(I)Ljava/io/InputStream;
    //   16: invokestatic 313	com/miui/weather2/common/Strings:convertStreamToString	(Ljava/io/InputStream;)Ljava/lang/String;
    //   19: invokespecial 316	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   22: ldc_w 318
    //   25: invokevirtual 322	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   28: astore 8
    //   30: new 98	java/util/ArrayList
    //   33: dup
    //   34: invokespecial 99	java/util/ArrayList:<init>	()V
    //   37: astore 9
    //   39: iconst_0
    //   40: istore 10
    //   42: iload 10
    //   44: aload 8
    //   46: invokevirtual 327	org/json/JSONArray:length	()I
    //   49: if_icmpge +91 -> 140
    //   52: aload 8
    //   54: iload 10
    //   56: invokevirtual 331	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
    //   59: astore 11
    //   61: aload 11
    //   63: ldc 124
    //   65: invokevirtual 334	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   68: astore 12
    //   70: aload 11
    //   72: ldc_w 335
    //   75: invokevirtual 334	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   78: astore 13
    //   80: aload 12
    //   82: invokestatic 213	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   85: ifne +49 -> 134
    //   88: aload 13
    //   90: invokestatic 213	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   93: ifne +41 -> 134
    //   96: new 284	com/miui/weather2/model/HotCityData
    //   99: dup
    //   100: invokespecial 285	com/miui/weather2/model/HotCityData:<init>	()V
    //   103: astore 14
    //   105: aload 14
    //   107: aload 12
    //   109: putfield 286	com/miui/weather2/model/HotCityData:name	Ljava/lang/String;
    //   112: aload 14
    //   114: aload 13
    //   116: putfield 287	com/miui/weather2/model/HotCityData:pid	Ljava/lang/String;
    //   119: aload 14
    //   121: aload 12
    //   123: putfield 288	com/miui/weather2/model/HotCityData:showName	Ljava/lang/String;
    //   126: aload 9
    //   128: aload 14
    //   130: invokevirtual 153	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   133: pop
    //   134: iinc 10 1
    //   137: goto -95 -> 42
    //   140: aload 9
    //   142: astore_1
    //   143: aload_1
    //   144: areturn
    //   145: astore 6
    //   147: ldc 158
    //   149: new 169	java/lang/StringBuilder
    //   152: dup
    //   153: invokespecial 170	java/lang/StringBuilder:<init>	()V
    //   156: ldc_w 337
    //   159: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   162: aload 6
    //   164: invokevirtual 338	android/content/res/Resources$NotFoundException:toString	()Ljava/lang/String;
    //   167: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   170: invokevirtual 182	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   173: invokestatic 341	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   176: pop
    //   177: goto -34 -> 143
    //   180: astore 4
    //   182: ldc 158
    //   184: new 169	java/lang/StringBuilder
    //   187: dup
    //   188: invokespecial 170	java/lang/StringBuilder:<init>	()V
    //   191: ldc_w 337
    //   194: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   197: aload 4
    //   199: invokevirtual 342	java/io/IOException:toString	()Ljava/lang/String;
    //   202: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   205: invokevirtual 182	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   208: invokestatic 341	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   211: pop
    //   212: goto -69 -> 143
    //   215: astore_2
    //   216: ldc 158
    //   218: new 169	java/lang/StringBuilder
    //   221: dup
    //   222: invokespecial 170	java/lang/StringBuilder:<init>	()V
    //   225: ldc_w 337
    //   228: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   231: aload_2
    //   232: invokevirtual 343	org/json/JSONException:toString	()Ljava/lang/String;
    //   235: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   238: invokevirtual 182	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   241: invokestatic 341	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   244: pop
    //   245: goto -102 -> 143
    //   248: astore_2
    //   249: aload 9
    //   251: astore_1
    //   252: goto -36 -> 216
    //   255: astore 4
    //   257: aload 9
    //   259: astore_1
    //   260: goto -78 -> 182
    //   263: astore 6
    //   265: aload 9
    //   267: astore_1
    //   268: goto -121 -> 147
    //
    // Exception table:
    //   from	to	target	type
    //   2	39	145	android/content/res/Resources$NotFoundException
    //   2	39	180	java/io/IOException
    //   2	39	215	org/json/JSONException
    //   42	134	248	org/json/JSONException
    //   42	134	255	java/io/IOException
    //   42	134	263	android/content/res/Resources$NotFoundException
  }

  public static ArrayList<LinkCityData> getLinkCity(Context paramContext, String paramString)
  {
    Object localObject1 = null;
    Object localObject2 = null;
    Cursor localCursor = paramContext.getContentResolver().query(Weather.SelectedCity.CONTENT_URI, null, paramString, null, "position ,_id");
    if (localCursor != null);
    try
    {
      int i = localCursor.getCount();
      if (i == 0)
        label48: return localObject1;
      localCursor.moveToFirst();
      while (true)
      {
        ArrayList localArrayList;
        LinkCityData localLinkCityData;
        try
        {
          boolean bool;
          do
          {
            localLinkCityData = new LinkCityData();
            localLinkCityData.id = localCursor.getInt(localCursor.getColumnIndex("_id"));
            localLinkCityData.name = localCursor.getString(localCursor.getColumnIndex("name"));
            localLinkCityData.pid = localCursor.getString(localCursor.getColumnIndex("posID"));
            localLinkCityData.no = localCursor.getInt(localCursor.getColumnIndex("position"));
            localLinkCityData.flag = localCursor.getInt(localCursor.getColumnIndex("flag"));
            if (Config.INT_SYS_LANGUAGE != 1)
              break label252;
            localLinkCityData.showName = ((CityData)getCityByPID(paramContext, localLinkCityData.pid).get(0)).pinyin;
            localArrayList.add(localLinkCityData);
            bool = localCursor.moveToNext();
          }
          while (bool);
          localCursor.close();
          localObject2 = localArrayList;
          localObject1 = localObject2;
          label267: label252: break label48:
        }
        finally
        {
          localCursor.close();
        }
      }
    }
    finally
    {
      break label267:
    }
  }

  public static int getNextCityPosition(Context paramContext)
  {
    monitorenter;
    try
    {
      Cursor localCursor;
      if (nextPos == -1)
      {
        nextPos = 0;
        localCursor = paramContext.getContentResolver().query(Weather.SelectedCity.CONTENT_URI, null, null, null, "position DESC");
      }
      try
      {
        if (localCursor.moveToFirst())
          nextPos = localCursor.getInt(localCursor.getColumnIndex("position"));
        localCursor.close();
        nextPos = 1 + nextPos;
        int i = nextPos;
        monitorexit;
        return i;
      }
      finally
      {
        localObject2 = finally;
        localCursor.close();
        throw localObject2;
      }
    }
    finally
    {
      monitorexit;
    }
  }

  public static Uri insertLinkCity(Context paramContext, String paramString1, String paramString2, int paramInt1, int paramInt2)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("name", paramString1);
    localContentValues.put("posID", paramString2);
    if (paramInt1 == -1)
      paramInt1 = getNextCityPosition(paramContext);
    localContentValues.put("position", Integer.valueOf(paramInt1));
    localContentValues.put("flag", Integer.valueOf(paramInt2));
    Uri localUri = paramContext.getContentResolver().insert(Weather.SelectedCity.CONTENT_URI, localContentValues);
    ServiceUpdateWeather.startUpdate(paramContext, false);
    return localUri;
  }

  public static void updateGpsCity(Context paramContext, String paramString1, String paramString2)
  {
    if ((TextUtils.isEmpty(paramString1)) || (TextUtils.isEmpty(paramString2)));
    while (true)
    {
      return;
      ArrayList localArrayList = getLinkCity(paramContext, "flag='1'");
      if ((localArrayList == null) || (localArrayList.size() == 0))
        insertLinkCity(paramContext, paramString1, paramString2, 0, 1);
      int i = ((LinkCityData)localArrayList.get(0)).no;
      int j = ((LinkCityData)localArrayList.get(0)).id;
      if (paramString2.equals(((LinkCityData)localArrayList.get(0)).pid))
        continue;
      deleteLinkCity(paramContext, j);
      insertLinkCity(paramContext, paramString1, paramString2, i, 1);
    }
  }

  public static void updateLinkCityOrder(Context paramContext, int paramInt1, int paramInt2)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("position", Integer.valueOf(paramInt2));
    paramContext.getContentResolver().update(Uri.withAppendedPath(Weather.SelectedCity.CONTENT_URI, String.valueOf(paramInt1)), localContentValues, null, null);
  }

  public void close()
  {
    if (this.connMain == null)
      return;
    this.connMain.close();
  }

  public void deleteFriend(String paramString, int paramInt)
  {
    this.connMain.delete("friend", "_posID=" + paramString + " AND " + "flag" + "=" + paramInt, null);
  }

  // ERROR //
  public ArrayList<com.miui.weather2.model.UserData> getFriend(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aconst_null
    //   3: astore_3
    //   4: aload_0
    //   5: getfield 17	com/miui/weather2/tools/ToolsDB:connMain	Landroid/database/sqlite/SQLiteDatabase;
    //   8: ldc_w 417
    //   11: aconst_null
    //   12: aload_1
    //   13: aconst_null
    //   14: aconst_null
    //   15: aconst_null
    //   16: aconst_null
    //   17: invokevirtual 434	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   20: astore 4
    //   22: aload 4
    //   24: ifnull +199 -> 223
    //   27: aload 4
    //   29: invokeinterface 89 1 0
    //   34: istore 6
    //   36: iload 6
    //   38: ifne +12 -> 50
    //   41: aload 4
    //   43: invokeinterface 92 1 0
    //   48: aload_2
    //   49: areturn
    //   50: aload 4
    //   52: invokeinterface 96 1 0
    //   57: pop
    //   58: new 98	java/util/ArrayList
    //   61: dup
    //   62: invokespecial 99	java/util/ArrayList:<init>	()V
    //   65: astore 8
    //   67: new 436	com/miui/weather2/model/UserData
    //   70: dup
    //   71: invokespecial 437	com/miui/weather2/model/UserData:<init>	()V
    //   74: astore 9
    //   76: aload 9
    //   78: aload 4
    //   80: aload 4
    //   82: ldc_w 348
    //   85: invokeinterface 108 2 0
    //   90: invokeinterface 352 2 0
    //   95: invokestatic 62	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   98: putfield 439	com/miui/weather2/model/UserData:id	Ljava/lang/String;
    //   101: aload 9
    //   103: aload 4
    //   105: aload 4
    //   107: ldc_w 441
    //   110: invokeinterface 108 2 0
    //   115: invokeinterface 111 2 0
    //   120: putfield 442	com/miui/weather2/model/UserData:pid	Ljava/lang/String;
    //   123: aload 9
    //   125: aload 4
    //   127: aload 4
    //   129: ldc 124
    //   131: invokeinterface 108 2 0
    //   136: invokeinterface 111 2 0
    //   141: putfield 443	com/miui/weather2/model/UserData:name	Ljava/lang/String;
    //   144: aload 9
    //   146: aload 4
    //   148: aload 4
    //   150: ldc_w 445
    //   153: invokeinterface 108 2 0
    //   158: invokeinterface 111 2 0
    //   163: putfield 447	com/miui/weather2/model/UserData:phone	Ljava/lang/String;
    //   166: aload 9
    //   168: aload 4
    //   170: aload 4
    //   172: ldc_w 361
    //   175: invokeinterface 108 2 0
    //   180: invokeinterface 352 2 0
    //   185: invokestatic 62	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   188: putfield 449	com/miui/weather2/model/UserData:flag	Ljava/lang/String;
    //   191: aload 8
    //   193: aload 9
    //   195: invokevirtual 153	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   198: pop
    //   199: aload 4
    //   201: invokeinterface 156 1 0
    //   206: istore 11
    //   208: iload 11
    //   210: ifne -143 -> 67
    //   213: aload 4
    //   215: invokeinterface 92 1 0
    //   220: aload 8
    //   222: astore_3
    //   223: aload_3
    //   224: astore_2
    //   225: goto -177 -> 48
    //   228: astore 5
    //   230: aload 4
    //   232: invokeinterface 92 1 0
    //   237: aload 5
    //   239: athrow
    //   240: astore 5
    //   242: goto -12 -> 230
    //
    // Exception table:
    //   from	to	target	type
    //   27	36	228	finally
    //   50	67	228	finally
    //   67	208	240	finally
  }

  public long insertFriend(String paramString1, String paramString2, String paramString3, int paramInt)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("_posID", paramString1);
    if (paramString2 == null)
      paramString2 = "";
    localContentValues.put("name", paramString2);
    if (paramString3 == null)
      paramString3 = "";
    localContentValues.put("phone", paramString3);
    localContentValues.put("flag", Integer.valueOf(paramInt));
    return this.connMain.insert("friend", null, localContentValues);
  }

  public void openDB(Context paramContext)
  {
    this.connMain = paramContext.openOrCreateDatabase("xm_weather.db", 0, null);
    this.connMain.execSQL("CREATE TABLE IF NOT EXISTS friend (_id INTEGER PRIMARY KEY , _posID VARCHAR(50) , name VARCHAR(50) , phone VARCHAR(50) , flag INTEGER  )");
  }
}

/* Location:           C:\Users\moon\Desktop\classes-dex2jar.jar
 * Qualified Name:     com.miui.weather2.tools.ToolsDB
 * JD-Core Version:    0.5.4
 */