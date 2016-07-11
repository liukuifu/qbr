package com.nq.qbr;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.BeeFramework.activity.BaseActivity;
import com.BeeFramework.model.BusinessResponse;
import com.external.androidquery.callback.AjaxStatus;
import com.nq.qbr.Banner.OnSingleTouchListener;
import com.nq.qbr.R;
import com.nq.qbr.adapter.UrlAdapter;
import com.nq.qbr.utils.qbrUtils;
import com.nq.qbr.utils.rootUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class IndexMainActivity extends Activity implements OnClickListener {
	private EditText etSearchText;

	private Button btnSearch;
	private Toast tst;
	private String searchContent;
	private LinearLayout ll_news;
	private LinearLayout ll_video;
	private LinearLayout ll_url;
	private LinearLayout ll_image;

	private GridView gv;
	private String[] strHotSpot = { "", "", "", "", "", "", "", "", };
	private String[] strHotSpotUrl = { "", "", "", "", "", "", "", "", };

	// private GridView gv_url;
	//
	// private String[] strUrlUrl = {
	// "http://3g.163.com/touch/all?nav=1&version=v_standard",
	// "http://info.3g.qq.com/g/",
	// "https://m.taobao.com/",
	// "http://sina.cn/",
	// "https://m.baidu.com/",
	// "http://m.sohu.com/",
	// "http://i.ifeng.com/",
	// "",
	// "",
	// "",
	// "",
	// "",
	// "",
	// "",
	// "",
	// "", };
	private ArrayList<View> views;
	private ArrayList<String> gUrl;
	
    private ImageLoader imageLoader = ImageLoader.getInstance();
    public static DisplayImageOptions options;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 内容
		etSearchText = (EditText) this.findViewById(R.id.id_search_text);

		// 搜索按钮
		btnSearch = (Button) this.findViewById(R.id.btn_search);

		// 添加按钮的侦听事件
		btnSearch.setOnClickListener(this);

		ll_news = (LinearLayout) findViewById(R.id.bt_news);
		ll_news.setClickable(true);
		ll_news.setOnClickListener(ocl);
		ll_news.setOnTouchListener(otl);

		ll_video = (LinearLayout) findViewById(R.id.bt_video);
		ll_video.setClickable(true);
		ll_video.setOnClickListener(ocl);
		ll_video.setOnTouchListener(otl);

		ll_url = (LinearLayout) findViewById(R.id.bt_url);
		ll_url.setClickable(true);
		ll_url.setOnClickListener(ocl);
		ll_url.setOnTouchListener(otl);

		ll_image = (LinearLayout) findViewById(R.id.bt_image);
		ll_image.setClickable(true);
		ll_image.setOnClickListener(ocl);
		ll_image.setOnTouchListener(otl);

		if (rootUtils.isDeviceRooted()) {
			 tst = Toast.makeText(this, "有root权限", Toast.LENGTH_SHORT);
			 tst.show();
		} else {
			 tst = Toast.makeText(this, "没有获得root权限", Toast.LENGTH_SHORT);
			 tst.show();
		}

		// 热点请求地址
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");// HH:mm:ss");//可以方便地修改日期格式

		String timestamp = dateFormat.format(now);
		String urlBase = IndexMainActivity.this
				.getString(R.string.url_base_url);
		// 请求热点
		new ReadHttpGet().execute(urlBase + timestamp);

		// gv_url = (GridView) findViewById(R.id.gv_url);
		//
		// // 为GridView设置适配器
		// UrlAdapter ma = new UrlAdapter(this);
		// // ma.strUrl = strUrl;
		// gv_url.setAdapter(ma);
		// // gv.setAdapter(new MyAdapter(this));
		// // 注册监听事件
		// gv_url.setOnItemClickListener(new OnItemClickListener() {
		// public void onItemClick(AdapterView<?> parent, View v,
		// int position, long id) {
		// // Toast.makeText(IndexMainActivity.this, strHotSpotUrl[position],
		// // Toast.LENGTH_SHORT).show();
		// Intent intent = new Intent();
		// intent.setClass(IndexMainActivity.this, WebBrowser.class);
		// /* 通过Bundle对象存储需要传递的数据 */
		// Bundle bundle = new Bundle();
		// /* 字符、字符串、布尔、字节数组、浮点数等等，都可以传 */
		// bundle.putString("flag", "UrlUrl");
		// bundle.putString("searchContent", strUrlUrl[position]);
		// intent.putExtras(bundle);
		// startActivity(intent);
		// }
		// });

		// 组装imageView列表
		// 把要轮播的图片添加到列表里调用setViewPagerViews方法添加到自定义的Viewpager里面就可以使用了
//		views = new ArrayList<View>();
//		gUrl = new ArrayList<String>();
//		Banner pager = (Banner) findViewById(R.id.my_view_pager);
//		ImageView image = new ImageView(this);
//		image.setImageResource(R.drawable.image1);
//		views.add(image);
//		gUrl.add("11111");
//		image = new ImageView(this);
//		image.setImageResource(R.drawable.image2);
//		views.add(image);
//		gUrl.add("22222");
//		image = new ImageView(this);
//		image.setImageResource(R.drawable.image3);
//		views.add(image);
//		gUrl.add("333333");
//		image = new ImageView(this);
//		image.setImageResource(R.drawable.image4);
//		views.add(image);
//		gUrl.add("444444");
//
//		pager.setViewPagerViews(views);
//		pager.setOnSingleTouchListener(new OnSingleTouchListener() {
//
//			@Override
//			public void onSingleTouch(int position) {
//
//				Toast.makeText(IndexMainActivity.this,
//						"当前点击" + gUrl.get(position), 0).show();
//			}
//		});
		options = new DisplayImageOptions.Builder()
		.showStubImage(R.drawable.default_image)			// 设置图片下载期间显示的图片
		.showImageForEmptyUri(R.drawable.default_image)	// 设置图片Uri为空或是错误的时候显示的图片
		.showImageOnFail(R.drawable.default_image)		// 设置图片加载或解码过程中发生错误显示的图片	
		.cacheInMemory(true)						// 设置下载的图片是否缓存在内存中
		.cacheOnDisc(true)							// 设置下载的图片是否缓存在SD卡中
		//.displayer(new RoundedBitmapDisplayer(20))	// 设置成圆角图片
		.bitmapConfig(Bitmap.Config.RGB_565)
		.build();
	}

	class ReadHttpGet extends AsyncTask<Object, Object, Object> {

		@Override
		protected Object doInBackground(Object... params) {

			// TODO Auto-generated method stub
			try {
				HttpGet httpRequest = new HttpGet(params[0].toString());
				HttpClient httpClient = new DefaultHttpClient();
				HttpResponse httpResponse = httpClient.execute(httpRequest);
				if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

					String strResult = EntityUtils.toString(
							httpResponse.getEntity(), "utf-8");
					return strResult;
				} else {
					return "请求出错";
				}
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onCancelled(Object result) {
			// TODO Auto-generated method stub
			super.onCancelled(result);
		}

		@Override
		protected void onPostExecute(Object result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			try {
				// 创建一个JSON对象
				if (result != null) {
					String e = result.toString();
					// JSONObject jsonObject = new
					// JSONObject(result.toString()).getJSONObject("news");
					JSONObject jsonObject = new JSONObject(result.toString());
					JSONArray jsonArray = new JSONArray();
					// 获取某个对象的JSON数组
					if (jsonObject.has("news")){
						jsonArray = jsonObject.getJSONArray("news");
					} else if (jsonObject.has("news_en")){
						jsonArray = jsonObject.getJSONArray("news_en");
					}else if (jsonObject.has("news_ar")){
						jsonArray = jsonObject.getJSONArray("news_ar");
					}else if (jsonObject.has("news_in")){
						jsonArray = jsonObject.getJSONArray("news_in");
					}
					// JSONArray jsonArray = jsonObject.getJSONArray("news");

					// Toast.makeText(getApplicationContext(),
					// jsonArray.toString(),
					// Toast.LENGTH_LONG).show();
					// StringBuilder builder = new StringBuilder();
					String tempTitle = "";

					Banner pager = (Banner) findViewById(R.id.my_view_pager);
					ImageView image = new ImageView(IndexMainActivity.this);
					
					views = new ArrayList<View>();
					gUrl = new ArrayList<String>();
					
					for (int i = 0; i < jsonArray.length(); i++) {
						// 新建一个JSON对象，该对象是某个数组里的其中一个对象
						JSONObject jsonObject2 = (JSONObject) jsonArray.opt(i);
						// builder.append(jsonObject2.getString("id")); // 获取数据
						// builder.append(jsonObject2.getString("title"));
						// builder.append(jsonObject2.getString("name"));
						if (i > 7) {
							break;
						}
						tempTitle = jsonObject2.getString("title");
						if (tempTitle.isEmpty()) {
							tempTitle = "";
						} else {
							if (tempTitle.length() > 20) {
								tempTitle = tempTitle.substring(0, 20);
							}
						}
						strHotSpot[i] = tempTitle;
						String url = jsonObject2.getString("url");
						strHotSpotUrl[i] = url;
						if(i<3){
							String img = "http:"+jsonObject2.getString("img");
							
					        //得到可用的图片  
//					        Bitmap bitmap = getHttpBitmap(img);  
							image = new ImageView(IndexMainActivity.this);
//							image.setImageBitmap(bitmap); 
							// 向大ImageView中加载图片
							imageLoader.init(ImageLoaderConfiguration.createDefault(IndexMainActivity.this));
							imageLoader.displayImage(img, image, IndexMainActivity.this.options);
							views.add(image);
							gUrl.add(url);
						}
						tempTitle = "";
					}
					
					gv = (GridView) findViewById(R.id.gv_Real_time_hot_spot);

					// 为GridView设置适配器
					MyAdapter ma = new MyAdapter(IndexMainActivity.this);
					ma.strHotSpot = strHotSpot;
					gv.setAdapter(ma);
					// gv.setAdapter(new MyAdapter(this));
					// 注册监听事件
					gv.setOnItemClickListener(new OnItemClickListener() {
						public void onItemClick(AdapterView<?> parent, View v,
								int position, long id) {
							// Toast.makeText(IndexMainActivity.this,
							// strHotSpotUrl[position],
							// Toast.LENGTH_SHORT).show();
							Intent intent = new Intent();
							intent.setClass(IndexMainActivity.this,
									WebBrowser.class);
							/* 通过Bundle对象存储需要传递的数据 */
							Bundle bundle = new Bundle();
							/* 字符、字符串、布尔、字节数组、浮点数等等，都可以传 */
							bundle.putString("flag", "HotSpot");
							bundle.putString("searchContent",
									strHotSpotUrl[position]);
							intent.putExtras(bundle);
							startActivity(intent);
						}
					});
					
					pager.setViewPagerViews(views);
					pager.setOnSingleTouchListener(new OnSingleTouchListener() {

						@Override
						public void onSingleTouch(int position) {

							Toast.makeText(IndexMainActivity.this,
									"当前点击" + gUrl.get(position), 0).show();
						}
					});
				}
				// myTextView.setText(builder.toString());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			// super.onPreExecute();
			// Toast.makeText(getApplicationContext(), "开始HTTP GET请求",
			// Toast.LENGTH_LONG).show();
		}

		@Override
		protected void onProgressUpdate(Object... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
		}
	}

	public OnClickListener ocl = new OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			// Toast.makeText(getApplicationContext(), "yes",
			// Toast.LENGTH_SHORT).show();
			Intent intent = new Intent();
			intent.setClass(IndexMainActivity.this, WebBrowser.class);
			/* 通过Bundle对象存储需要传递的数据 */
			Bundle bundle = new Bundle();

			switch (v.getId()) {
			case R.id.bt_news:
				bundle.putString("flag", "news");
				bundle.putString("searchContent", IndexMainActivity.this
						.getString(R.string.news_base_url));
				break;
			case R.id.bt_video:
				bundle.putString("flag", "video");
				bundle.putString("searchContent", IndexMainActivity.this
						.getString(R.string.video_base_url));
				break;
			case R.id.bt_url:
				intent = new Intent();
				intent.setClass(IndexMainActivity.this, UrlActivity.class);
				// bundle.putString("flag", "url");
				// bundle.putString("searchContent",
				// IndexMainActivity.this.getString(R.string.url_base_url));
				break;
			case R.id.bt_image:
				bundle.putString("flag", "image");
				bundle.putString("searchContent", IndexMainActivity.this
						.getString(R.string.image_base_url));
				break;
			}

			/* 字符、字符串、布尔、字节数组、浮点数等等，都可以传 */
			// bundle.putString("flag", "news");
			// bundle.putString("searchContent",
			// IndexMainActivity.this.getString(R.string.news_base_url));
			intent.putExtras(bundle);
			startActivity(intent);
		}
	};

	public OnTouchListener otl = new OnTouchListener() {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub

			switch (v.getId()) {
			case R.id.bt_news:
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					ll_news.setBackgroundColor(getResources().getColor(
							R.color.touch_color));
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					ll_news.setBackgroundColor(Color.TRANSPARENT);
				}
				break;
			case R.id.bt_video:
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					ll_video.setBackgroundColor(getResources().getColor(
							R.color.touch_color));
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					ll_video.setBackgroundColor(Color.TRANSPARENT);
				}
				break;
			case R.id.bt_url:
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					ll_url.setBackgroundColor(getResources().getColor(
							R.color.touch_color));
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					ll_url.setBackgroundColor(Color.TRANSPARENT);
				}
				break;
			case R.id.bt_image:
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					ll_image.setBackgroundColor(getResources().getColor(
							R.color.touch_color));
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					ll_image.setBackgroundColor(Color.TRANSPARENT);
				}
				break;
			}
			return false;
		}
	};

	// /**
	// * 画面主体部的元素
	// */
	// // @Override
	// protected void setViewBody() {
	// // 搜索
	// etSearchText = (TextView) this.findViewById(R.id.id_search_text);
	//
	// // 添加按钮的侦听事件
	// etSearchText.setOnClickListener(this);
	// }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * 按钮点击侦听
	 */
	@Override
	public void onClick(View v) {
		// Resources resource = (Resources) getBaseContext().getResources();
		switch (v.getId()) {
		case R.id.btn_search:
			searchContent = etSearchText.getText().toString();
			this.CloseKeyBoard();
			Intent intent = new Intent();
			intent.setClass(IndexMainActivity.this, WebBrowser.class);

			/* 通过Bundle对象存储需要传递的数据 */
			Bundle bundle = new Bundle();
			/* 字符、字符串、布尔、字节数组、浮点数等等，都可以传 */
			bundle.putString("flag", "search");
			bundle.putString("searchContent", searchContent);
			intent.putExtras(bundle);
			// intent.putExtra("searchContent", searchContent);
			startActivity(intent);
			break;
		}
	}

	// @Override
	// public void OnMessageResponse(String url, JSONObject jo, AjaxStatus
	// status)
	// throws JSONException {
	// // TODO Auto-generated method stub
	//
	// }

	/**
	 * 关闭软键盘
	 */
	private void CloseKeyBoard() {
		etSearchText.clearFocus();
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(etSearchText.getWindowToken(), 0);
	}
	
	/** 
     * 获取网落图片资源  
     * @param url 
     * @return 
     */  
    public static Bitmap getHttpBitmap(String url){  
        URL myFileURL;  
        Bitmap bitmap=null;  
        try{  
            myFileURL = new URL(url);  
            //获得连接  
            HttpURLConnection conn=(HttpURLConnection)myFileURL.openConnection();  
            //设置超时时间为60000毫秒，conn.setConnectionTiem(0);表示没有时间限制  
            conn.setConnectTimeout(60000);  
            //连接设置获得数据流  
            conn.setDoInput(true);  
            //不使用缓存  
            conn.setUseCaches(false);  
            //这句可有可无，没有影响  
            //conn.connect();  
            //得到数据流  
            InputStream is = conn.getInputStream();  
            //解析得到图片  
            bitmap = BitmapFactory.decodeStream(is);  
            //关闭数据流  
            is.close();  
        }catch(Exception e){  
            e.printStackTrace();  
        }  
          
        return bitmap;  
          
    }  
}
