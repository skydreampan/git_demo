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

				map.put("ItemText", "У��·��");

			}

			if (i == 2) {

				map.put("ItemImage", R.drawable.news);

				map.put("ItemText", "У������");

			}

			if (i == 3) {

				map.put("ItemImage", R.drawable.weather);

				map.put("ItemText", "����ѶϢ");

			}

			if (i == 4) {

				map.put("ItemImage", R.drawable.calendar);

				map.put("ItemText", "�մ�У��");

			}

			if (i == 5) {

				map.put("ItemImage", R.drawable.gateway);

				map.put("ItemText", "���ص�¼");

			}

			if (i == 6) {

				map.put("ItemImage", R.drawable.card);

				map.put("ItemText", "һ��ͨ����");

			}

			if (i == 7) {

				map.put("ItemImage", R.drawable.settings);

				map.put("ItemText", "ϵͳ����");

			}

			if (i == 8) {

				map.put("ItemImage", R.drawable.notice);

				map.put("ItemText", "У��֪ͨ");

			}

			lstImageItem.add(map);
		}

		SimpleAdapter saImageItems = new SimpleAdapter(this, lstImageItem,
				R.layout.grid_item, new String[] { "ItemImage", "ItemText" },
				new int[] { R.id.ItemImage, R.id.ItemText });
		gridview.setAdapter(saImageItems);
		gridview.setOnItemClickListener(new ItemClickListener());

		/*
		 * //��ȡSharedPreferences����Ҫ������ SharedPreferences preferences =
		 * getSharedPreferences("startCount",MODE_PRIVATE); int count =
		 * preferences.getInt("count",0); //�ڶ������������ֵ�����ھͷ���0
		 * //�жϳ����ǵڼ������У�����ǵ�һ����������ת������ҳ�� if (count == 0) { Intent intent = new
		 * Intent(this,FirstStartActivity.class); startActivity(intent);
		 * finish(); } Editor editor = preferences.edit();
		 * editor.putInt("count", ++count); editor.commit();
		 */

		// ����gallery ����ͼƬ
		Integer[] images = { R.drawable.bus, R.drawable.calendar,

		R.drawable.card, R.drawable.gateway, R.drawable.news,

		R.drawable.settings, R.drawable.weather }; // ����ͼƬ����

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

	// �˳��Ի���
	protected void dialog() {
		AlertDialog.Builder builder = new Builder(MainActivity.this); // ���ؼ�����ȡ��
		builder.setMessage("ȷ���˳���").setCancelable(false)
				.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
					// �˳����򣬶���淽���˳���finish����
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						/*
						 * Intent intent = new Intent(Intent.ACTION_MAIN);
						 * //�ȵ�home���棬�ٽ�������
						 * intent.addCategory(Intent.CATEGORY_HOME);
						 * intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						 * startActivity(intent);
						 */
						android.os.Process.killProcess(Process.myPid());
					}
				})
				.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						dialog.cancel();
					}
				}); // �����Ի������
		builder.create().show();

	}

}
