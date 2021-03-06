package com.wirelessuda.activity;

import java.util.ArrayList;
import java.util.HashMap;

import com.wirelessuda.layout.GalleryFlow;
import com.wirelessuda.layout.ImageAdapter;
import com.wirelessuda.R;

import android.net.Uri;
import android.os.Bundle;
import android.os.Process;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity {

	ImageButton mImgBtnNews, mImgBtnGateway, mImgBtnCard, mImgBtnNotice;
	GridView gridview;

	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		gridview = (GridView) findViewById(R.id.gridview);

		ArrayList<HashMap<String, Object>> lstImageItem = new ArrayList<HashMap<String, Object>>();

		for (int i = 1; i < 9; i++)

		{

			HashMap<String, Object> map = new HashMap<String, Object>();

			if (i == 1) {

				map.put("ItemImage", R.drawable.bus);

				map.put("ItemText", "校车路线");

			}

			if (i == 2) {

				map.put("ItemImage", R.drawable.news);

				map.put("ItemText", "校内新闻");

			}

			if (i == 3) {

				map.put("ItemImage", R.drawable.weather);

				map.put("ItemText", "天气讯息");

			}

			if (i == 4) {

				map.put("ItemImage", R.drawable.calendar);

				map.put("ItemText", "苏大校历");

			}

			if (i == 5) {

				map.put("ItemImage", R.drawable.gateway);

				map.put("ItemText", "网关登录");

			}

			if (i == 6) {

				map.put("ItemImage", R.drawable.card);

				map.put("ItemText", "一卡通服务");

			}

			if (i == 7) {

				map.put("ItemImage", R.drawable.settings);

				map.put("ItemText", "系统设置");

			}

			if (i == 8) {

				map.put("ItemImage", R.drawable.notice);

				map.put("ItemText", "校内通知");

			}

			lstImageItem.add(map);
		}

		SimpleAdapter saImageItems = new SimpleAdapter(this, lstImageItem,
				R.layout.grid_item, new String[] { "ItemImage", "ItemText" },
				new int[] { R.id.ItemImage, R.id.ItemText });
		gridview.setAdapter(saImageItems);
		gridview.setOnItemClickListener(new ItemClickListener());

		/*
		 * //读取SharedPreferences中需要的数据 SharedPreferences preferences =
		 * getSharedPreferences("startCount",MODE_PRIVATE); int count =
		 * preferences.getInt("count",0); //第二个参数：如果值不存在就返回0
		 * //判断程序是第几次运行，如果是第一次运行则跳转到引导页面 if (count == 0) { Intent intent = new
		 * Intent(this,FirstStartActivity.class); startActivity(intent);
		 * finish(); } Editor editor = preferences.edit();
		 * editor.putInt("count", ++count); editor.commit();
		 */

		// 加载gallery 滚动图片
		Integer[] images = { R.drawable.bus, R.drawable.calendar,

		R.drawable.card, R.drawable.gateway, R.drawable.news,

		R.drawable.settings, R.drawable.weather }; // 定义图片数组

		ImageAdapter adapter = new ImageAdapter(this, images);

		adapter.createReflectedImages();

		GalleryFlow galleryFlow = (GalleryFlow) findViewById(R.id.Gallery01);

		galleryFlow.setAdapter(adapter);
		/*
		 * mImgBtnNews = (ImageButton) findViewById(R.id.imageButtonNews);
		 * mImgBtnGateway = (ImageButton) findViewById(R.id.imageButtonGateway);
		 * mImgBtnCard = (ImageButton) findViewById(R.id.imageButtonCard);
		 * mImgBtnNotice=(ImageButton) findViewById(R.id.imageButtonNotice);
		 * 
		 * mImgBtnNews.setOnClickListener(this);
		 * mImgBtnGateway.setOnClickListener(this);
		 * mImgBtnCard.setOnClickListener(this);
		 * mImgBtnNotice.setOnClickListener(this);
		 */
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.main, menu);
		menu.add(menu.NONE, menu.FIRST + 1, 1, "bus").setIcon(R.drawable.bus);
		menu.add(menu.NONE, menu.FIRST + 2, 2, "calendar").setIcon(
				R.drawable.calendar);
		menu.add(menu.NONE, menu.FIRST + 3, 3, "card").setIcon(R.drawable.card);
		menu.add(menu.NONE, menu.FIRST + 4, 4, "gateway").setIcon(
				R.drawable.gateway);
		menu.add(menu.NONE, menu.FIRST + 5, 5, "news").setIcon(R.drawable.news);
		menu.add(menu.NONE, menu.FIRST + 6, 6, "weather").setIcon(
				R.drawable.weather);
		menu.add(menu.NONE, menu.FIRST + 7, 7, "settings").setIcon(
				R.drawable.settings);
		return true;
	}

	class ItemClickListener implements OnItemClickListener {

		@SuppressWarnings("unchecked")
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			HashMap<String, Object> item = (HashMap<String, Object>) arg0
					.getItemAtPosition(arg2);

			if (item.get("ItemImage").equals(R.drawable.bus)) {
				Intent intent = new Intent(MainActivity.this, BusActivity.class);
				startActivity(intent);
			}
			if (item.get("ItemImage").equals(R.drawable.news)) {
				Intent intent = new Intent(MainActivity.this,
						NewsindexActivity.class);
				startActivity(intent);
			}
			if (item.get("ItemImage").equals(R.drawable.weather)) {
				Intent intent = new Intent(MainActivity.this,
						WeatherActivity.class);
				startActivity(intent);
			}
			if (item.get("ItemImage").equals(R.drawable.calendar)) {
				Intent intent = new Intent(MainActivity.this,
						CalendarActivity.class);
				startActivity(intent);
			}
			if (item.get("ItemImage").equals(R.drawable.gateway)) {
				Intent intent = new Intent(MainActivity.this,
						GatewayActivity.class);
				startActivity(intent);
			}
			if (item.get("ItemImage").equals(R.drawable.card)) {
				Intent intent = new Intent(MainActivity.this,
						CardActivity.class);
				startActivity(intent);
			}
			if (item.get("ItemImage").equals(R.drawable.settings)) {
				// Intent intent = new
				// Intent(MainActivity.this,BusActivity.class);
				// startActivity(intent);
			}
			if (item.get("ItemImage").equals(R.drawable.notice)) {
				Intent intent = new Intent(MainActivity.this,
						NoticeindexActivity.class);
				startActivity(intent);
			}
		}
	}

	/*
	 * public void onClick(View v) { // TODO Auto-generated method stub
	 * 
	 * if(v == mImgBtnNews){ Intent newsIntent = new
	 * Intent(this,NewsindexActivity.class); startActivity(newsIntent); } if(v
	 * == mImgBtnGateway){ Intent intent = new
	 * Intent(this,GatewayActivity.class); startActivity(intent); } if(v ==
	 * mImgBtnCard){ Intent intent = new Intent(this,CardActivity.class);
	 * startActivity(intent); } if(v == mImgBtnNotice){ Intent newsIntent = new
	 * Intent(this,NoticeindexActivity.class); startActivity(newsIntent); } }
	 */
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		dialog();
	}

	// 退出对话框
	protected void dialog() {
		AlertDialog.Builder builder = new Builder(MainActivity.this); // 返回键不可取消
		builder.setMessage("确认退出吗？").setCancelable(false)
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					// 退出程序，多界面方法退出，finish不行
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						/*
						 * Intent intent = new Intent(Intent.ACTION_MAIN);
						 * //先到home界面，再结束进程
						 * intent.addCategory(Intent.CATEGORY_HOME);
						 * intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						 * startActivity(intent);
						 */
						android.os.Process.killProcess(Process.myPid());
					}
				})
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						dialog.cancel();
					}
				}); // 创建对话框完毕
		builder.create().show();

	}

}
