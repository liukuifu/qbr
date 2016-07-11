package com.nq.qbr;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.nq.qbr.adapter.GoodsAddressAdapter;
import com.nq.qbr.adapter.UrlAdapter;
import com.nq.qbr.entity.AddressProvincesEntity;
import com.nq.qbr.entity.GoodsAddressEntity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class UrlActivity extends Activity {  
    Toast tst;
  
    AutoCompleteTextView url;  
    WebView show;  
    private EditText etSearchText;
    UrlActivity ua = this;
    String[] booksArray = new String[]  
    {    
            "www.baidu.com"
//            "http://maps.google.com",  
//            "http://maps.baidu.com",  
//            "http://qq.com",  
//            "www.baidu.com",  
//            "www.163.com"  
    };  

	private GridView gv_url;

	private String[] strUrlUrl = new String[15];
//	{
//			getApplicationContext().getResources().getString(R.string.hot_url_1),
//			this.getString(R.string.hot_url_2),
//			this.getString(R.string.hot_url_3),
//			this.getString(R.string.hot_url_4),
//			this.getString(R.string.hot_url_5),
//			this.getString(R.string.hot_url_6),
//			this.getString(R.string.hot_url_7),
//			this.getString(R.string.hot_url_8),
//			this.getString(R.string.hot_url_9),
//			this.getString(R.string.hot_url_10),
//			this.getString(R.string.hot_url_11),
//			this.getString(R.string.hot_url_12),
//			this.getString(R.string.hot_url_13),
//			this.getString(R.string.hot_url_14),
//			this.getString(R.string.hot_url_15),
//			this.getString(R.string.hot_url_16),
//			this.getString(R.string.hot_url_17),
//			this.getString(R.string.hot_url_18),
//			this.getString(R.string.hot_url_19),
//			this.getString(R.string.hot_url_20), };

	private ExpandableListView mLvAddress;
	private ArrayList<AddressProvincesEntity> addressProvincesList;
	private GoodsAddressAdapter goodsAddressAdapter;  
    
    @Override  
    public void onCreate(Bundle savedInstanceState)   
    {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activty_urlv); 
		etSearchText = (EditText) this.findViewById(R.id.id_search_text);
		strUrlUrl[0] = this.getString(R.string.hot_url_1);
		strUrlUrl[1] = this.getString(R.string.hot_url_2);
		strUrlUrl[2] = this.getString(R.string.hot_url_3);
		strUrlUrl[3] = this.getString(R.string.hot_url_4);
		strUrlUrl[4] = this.getString(R.string.hot_url_5);
		strUrlUrl[5] = this.getString(R.string.hot_url_6);
		strUrlUrl[6] = this.getString(R.string.hot_url_7);
		strUrlUrl[7] = this.getString(R.string.hot_url_8);
		strUrlUrl[8] = this.getString(R.string.hot_url_9);
		strUrlUrl[9] = this.getString(R.string.hot_url_10);
		strUrlUrl[10] = this.getString(R.string.hot_url_11);
		strUrlUrl[11] = this.getString(R.string.hot_url_12);
		strUrlUrl[12] = this.getString(R.string.hot_url_13);
		strUrlUrl[13] = this.getString(R.string.hot_url_14);
		strUrlUrl[14] = this.getString(R.string.hot_url_15);
//		strUrlUrl[15] = this.getString(R.string.hot_url_16);
//		strUrlUrl[16] = this.getString(R.string.hot_url_17);
//		strUrlUrl[17] = this.getString(R.string.hot_url_18);
//		strUrlUrl[18] = this.getString(R.string.hot_url_19);
//		strUrlUrl[19] = this.getString(R.string.hot_url_20);
        /*获取Intent中的Bundle对象*/
//        Bundle bundle = this.getIntent().getExtras();
        
        /*获取Bundle中的数据，注意类型和key*/
//        String searchContent = bundle.getString("searchContent");        
//        String searchFlag = bundle.getString("flag");

		gv_url = (GridView) findViewById(R.id.gv_url);

		// 为GridView设置适配器
		UrlAdapter ma = new UrlAdapter(this);
//		ma.strUrl = strUrl;
		gv_url.setAdapter(ma);
		// gv.setAdapter(new MyAdapter(this));
		// 注册监听事件
		gv_url.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
//				Toast.makeText(IndexMainActivity.this, strHotSpotUrl[position],
//						Toast.LENGTH_SHORT).show();
				Intent intent = new Intent();
				intent.setClass(UrlActivity.this, WebBrowser.class);
				/* 通过Bundle对象存储需要传递的数据 */
				Bundle bundle = new Bundle();
				/* 字符、字符串、布尔、字节数组、浮点数等等，都可以传 */
				bundle.putString("flag", "UrlUrl");
				bundle.putString("searchContent", strUrlUrl[position]);
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});

		initViews();
		initEvents();
		initData(); 
		
		ImageView homeBtn = (ImageView)findViewById(R.id.id_common_title_left);
        homeBtn.setOnClickListener(new OnClickListener()  
        {  
            public void onClick(View v)  
            {  
                // TODO  
                //show.loadUrl("http://maps.google.com");  
//            	Intent i = new Intent(UrlActivity.this,IndexMainActivity.class);
//            	startActivity(i);
            	finish();
            }  
        });    
    }  

	private void initData() {
		addressProvincesList = new ArrayList<AddressProvincesEntity>();
		
		// f_n_01
		AddressProvincesEntity addressProvincesEntity = new AddressProvincesEntity();
		addressProvincesEntity.setProvinces(this.getString(R.string.f_n_01));
		ArrayList<GoodsAddressEntity> addressArrayList = new ArrayList<GoodsAddressEntity>();
		
		GoodsAddressEntity addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_01_01));
		addressEntity.setUrl(this.getString(R.string.f_u_01_01));
		addressArrayList.add(addressEntity);
		
		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_01_02));
		addressEntity.setUrl(this.getString(R.string.f_u_01_02));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_01_03));
		addressEntity.setUrl(this.getString(R.string.f_u_01_03));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_01_04));
		addressEntity.setUrl(this.getString(R.string.f_u_01_04));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_01_05));
		addressEntity.setUrl(this.getString(R.string.f_u_01_05));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_01_06));
		addressEntity.setUrl(this.getString(R.string.f_u_01_06));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_01_07));
		addressEntity.setUrl(this.getString(R.string.f_u_01_07));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_01_08));
		addressEntity.setUrl(this.getString(R.string.f_u_01_08));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_01_09));
		addressEntity.setUrl(this.getString(R.string.f_u_01_09));
		addressArrayList.add(addressEntity);

		addressProvincesEntity.setAddressEntity(addressArrayList);
		addressProvincesList.add(addressProvincesEntity);

		// f_n_02
		addressProvincesEntity = new AddressProvincesEntity();
		addressProvincesEntity.setProvinces(this.getString(R.string.f_n_02));
		addressArrayList = new ArrayList<GoodsAddressEntity>();
		
		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_02_01));
		addressEntity.setUrl(this.getString(R.string.f_u_02_01));
		addressArrayList.add(addressEntity);
		
		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_02_02));
		addressEntity.setUrl(this.getString(R.string.f_u_02_02));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_02_03));
		addressEntity.setUrl(this.getString(R.string.f_u_02_03));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_02_04));
		addressEntity.setUrl(this.getString(R.string.f_u_02_04));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_02_05));
		addressEntity.setUrl(this.getString(R.string.f_u_02_05));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_02_06));
		addressEntity.setUrl(this.getString(R.string.f_u_02_06));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_02_07));
		addressEntity.setUrl(this.getString(R.string.f_u_02_07));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_02_08));
		addressEntity.setUrl(this.getString(R.string.f_u_02_08));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_02_09));
		addressEntity.setUrl(this.getString(R.string.f_u_02_09));
		addressArrayList.add(addressEntity);

		addressProvincesEntity.setAddressEntity(addressArrayList);
		addressProvincesList.add(addressProvincesEntity);
		

		// f_n_03
		addressProvincesEntity = new AddressProvincesEntity();
		addressProvincesEntity.setProvinces(this.getString(R.string.f_n_03));
		addressArrayList = new ArrayList<GoodsAddressEntity>();
		
		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_03_01));
		addressEntity.setUrl(this.getString(R.string.f_u_03_01));
		addressArrayList.add(addressEntity);
		
		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_03_02));
		addressEntity.setUrl(this.getString(R.string.f_u_03_02));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_03_03));
		addressEntity.setUrl(this.getString(R.string.f_u_03_03));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_03_04));
		addressEntity.setUrl(this.getString(R.string.f_u_03_04));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_03_05));
		addressEntity.setUrl(this.getString(R.string.f_u_03_05));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_03_06));
		addressEntity.setUrl(this.getString(R.string.f_u_03_06));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_03_07));
		addressEntity.setUrl(this.getString(R.string.f_u_03_07));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_03_08));
		addressEntity.setUrl(this.getString(R.string.f_u_03_08));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_03_09));
		addressEntity.setUrl(this.getString(R.string.f_u_03_09));
		addressArrayList.add(addressEntity);

		addressProvincesEntity.setAddressEntity(addressArrayList);
		addressProvincesList.add(addressProvincesEntity);
		
		// f_n_04
		addressProvincesEntity = new AddressProvincesEntity();
		addressProvincesEntity.setProvinces(this.getString(R.string.f_n_04));
		addressArrayList = new ArrayList<GoodsAddressEntity>();
		
		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_04_01));
		addressEntity.setUrl(this.getString(R.string.f_u_04_01));
		addressArrayList.add(addressEntity);
		
		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_04_02));
		addressEntity.setUrl(this.getString(R.string.f_u_04_02));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_04_03));
		addressEntity.setUrl(this.getString(R.string.f_u_04_03));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_04_04));
		addressEntity.setUrl(this.getString(R.string.f_u_04_04));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_04_05));
		addressEntity.setUrl(this.getString(R.string.f_u_04_05));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_04_06));
		addressEntity.setUrl(this.getString(R.string.f_u_04_06));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_04_07));
		addressEntity.setUrl(this.getString(R.string.f_u_04_07));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_04_08));
		addressEntity.setUrl(this.getString(R.string.f_u_04_08));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_04_09));
		addressEntity.setUrl(this.getString(R.string.f_u_04_09));
		addressArrayList.add(addressEntity);

		addressProvincesEntity.setAddressEntity(addressArrayList);
		addressProvincesList.add(addressProvincesEntity);

		// f_n_05
		addressProvincesEntity = new AddressProvincesEntity();
		addressProvincesEntity.setProvinces(this.getString(R.string.f_n_05));
		addressArrayList = new ArrayList<GoodsAddressEntity>();
		
		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_05_01));
		addressEntity.setUrl(this.getString(R.string.f_u_05_01));
		addressArrayList.add(addressEntity);
		
		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_05_02));
		addressEntity.setUrl(this.getString(R.string.f_u_05_02));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_05_03));
		addressEntity.setUrl(this.getString(R.string.f_u_05_03));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_05_04));
		addressEntity.setUrl(this.getString(R.string.f_u_05_04));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_05_05));
		addressEntity.setUrl(this.getString(R.string.f_u_05_05));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_05_06));
		addressEntity.setUrl(this.getString(R.string.f_u_05_06));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_05_07));
		addressEntity.setUrl(this.getString(R.string.f_u_05_07));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_05_08));
		addressEntity.setUrl(this.getString(R.string.f_u_05_08));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_05_09));
		addressEntity.setUrl(this.getString(R.string.f_u_05_09));
		addressArrayList.add(addressEntity);

		addressProvincesEntity.setAddressEntity(addressArrayList);
		addressProvincesList.add(addressProvincesEntity);

		// f_n_06
		addressProvincesEntity = new AddressProvincesEntity();
		addressProvincesEntity.setProvinces(this.getString(R.string.f_n_06));
		addressArrayList = new ArrayList<GoodsAddressEntity>();
		
		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_06_01));
		addressEntity.setUrl(this.getString(R.string.f_u_06_01));
		addressArrayList.add(addressEntity);
		
		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_06_02));
		addressEntity.setUrl(this.getString(R.string.f_u_06_02));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_06_03));
		addressEntity.setUrl(this.getString(R.string.f_u_06_03));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_06_04));
		addressEntity.setUrl(this.getString(R.string.f_u_06_04));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_06_05));
		addressEntity.setUrl(this.getString(R.string.f_u_06_05));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_06_06));
		addressEntity.setUrl(this.getString(R.string.f_u_06_06));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_06_07));
		addressEntity.setUrl(this.getString(R.string.f_u_06_07));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_06_08));
		addressEntity.setUrl(this.getString(R.string.f_u_06_08));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_06_09));
		addressEntity.setUrl(this.getString(R.string.f_u_06_09));
		addressArrayList.add(addressEntity);

		addressProvincesEntity.setAddressEntity(addressArrayList);
		addressProvincesList.add(addressProvincesEntity);

		// f_n_07
		addressProvincesEntity = new AddressProvincesEntity();
		addressProvincesEntity.setProvinces(this.getString(R.string.f_n_07));
		addressArrayList = new ArrayList<GoodsAddressEntity>();
		
		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_07_01));
		addressEntity.setUrl(this.getString(R.string.f_u_07_01));
		addressArrayList.add(addressEntity);
		
		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_07_02));
		addressEntity.setUrl(this.getString(R.string.f_u_07_02));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_07_03));
		addressEntity.setUrl(this.getString(R.string.f_u_07_03));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_07_04));
		addressEntity.setUrl(this.getString(R.string.f_u_07_04));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_07_05));
		addressEntity.setUrl(this.getString(R.string.f_u_07_05));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_07_06));
		addressEntity.setUrl(this.getString(R.string.f_u_07_06));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_07_07));
		addressEntity.setUrl(this.getString(R.string.f_u_07_07));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_07_08));
		addressEntity.setUrl(this.getString(R.string.f_u_07_08));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_07_09));
		addressEntity.setUrl(this.getString(R.string.f_u_07_09));
		addressArrayList.add(addressEntity);

		addressProvincesEntity.setAddressEntity(addressArrayList);
		addressProvincesList.add(addressProvincesEntity);

		// f_n_08
		addressProvincesEntity = new AddressProvincesEntity();
		addressProvincesEntity.setProvinces(this.getString(R.string.f_n_08));
		addressArrayList = new ArrayList<GoodsAddressEntity>();
		
		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_08_01));
		addressEntity.setUrl(this.getString(R.string.f_u_08_01));
		addressArrayList.add(addressEntity);
		
		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_08_02));
		addressEntity.setUrl(this.getString(R.string.f_u_08_02));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_08_03));
		addressEntity.setUrl(this.getString(R.string.f_u_08_03));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_08_04));
		addressEntity.setUrl(this.getString(R.string.f_u_08_04));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_08_05));
		addressEntity.setUrl(this.getString(R.string.f_u_08_05));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_08_06));
		addressEntity.setUrl(this.getString(R.string.f_u_08_06));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_08_07));
		addressEntity.setUrl(this.getString(R.string.f_u_08_07));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_08_08));
		addressEntity.setUrl(this.getString(R.string.f_u_08_08));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_08_09));
		addressEntity.setUrl(this.getString(R.string.f_u_08_09));
		addressArrayList.add(addressEntity);

		addressProvincesEntity.setAddressEntity(addressArrayList);
		addressProvincesList.add(addressProvincesEntity);

		// f_n_09
		addressProvincesEntity = new AddressProvincesEntity();
		addressProvincesEntity.setProvinces(this.getString(R.string.f_n_09));
		addressArrayList = new ArrayList<GoodsAddressEntity>();
		
		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_09_01));
		addressEntity.setUrl(this.getString(R.string.f_u_09_01));
		addressArrayList.add(addressEntity);
		
		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_09_02));
		addressEntity.setUrl(this.getString(R.string.f_u_09_02));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_09_03));
		addressEntity.setUrl(this.getString(R.string.f_u_09_03));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_09_04));
		addressEntity.setUrl(this.getString(R.string.f_u_09_04));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_09_05));
		addressEntity.setUrl(this.getString(R.string.f_u_09_05));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_09_06));
		addressEntity.setUrl(this.getString(R.string.f_u_09_06));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_09_07));
		addressEntity.setUrl(this.getString(R.string.f_u_09_07));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_09_08));
		addressEntity.setUrl(this.getString(R.string.f_u_09_08));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_09_09));
		addressEntity.setUrl(this.getString(R.string.f_u_09_09));
		addressArrayList.add(addressEntity);

		addressProvincesEntity.setAddressEntity(addressArrayList);
		addressProvincesList.add(addressProvincesEntity);

		// f_n_10
		addressProvincesEntity = new AddressProvincesEntity();
		addressProvincesEntity.setProvinces(this.getString(R.string.f_n_10));
		addressArrayList = new ArrayList<GoodsAddressEntity>();
		
		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_10_01));
		addressEntity.setUrl(this.getString(R.string.f_u_10_01));
		addressArrayList.add(addressEntity);
		
		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_10_02));
		addressEntity.setUrl(this.getString(R.string.f_u_10_02));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_10_03));
		addressEntity.setUrl(this.getString(R.string.f_u_10_03));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_10_04));
		addressEntity.setUrl(this.getString(R.string.f_u_10_04));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_10_05));
		addressEntity.setUrl(this.getString(R.string.f_u_10_05));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_10_06));
		addressEntity.setUrl(this.getString(R.string.f_u_10_06));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_10_07));
		addressEntity.setUrl(this.getString(R.string.f_u_10_07));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_10_08));
		addressEntity.setUrl(this.getString(R.string.f_u_10_08));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_10_09));
		addressEntity.setUrl(this.getString(R.string.f_u_10_09));
		addressArrayList.add(addressEntity);

		addressProvincesEntity.setAddressEntity(addressArrayList);
		addressProvincesList.add(addressProvincesEntity);

		// f_n_11
		addressProvincesEntity = new AddressProvincesEntity();
		addressProvincesEntity.setProvinces(this.getString(R.string.f_n_11));
		addressArrayList = new ArrayList<GoodsAddressEntity>();
		
		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_11_01));
		addressEntity.setUrl(this.getString(R.string.f_u_11_01));
		addressArrayList.add(addressEntity);
		
		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_11_02));
		addressEntity.setUrl(this.getString(R.string.f_u_11_02));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_11_03));
		addressEntity.setUrl(this.getString(R.string.f_u_11_03));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_11_04));
		addressEntity.setUrl(this.getString(R.string.f_u_11_04));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_11_05));
		addressEntity.setUrl(this.getString(R.string.f_u_11_05));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_11_06));
		addressEntity.setUrl(this.getString(R.string.f_u_11_06));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_11_07));
		addressEntity.setUrl(this.getString(R.string.f_u_11_07));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_11_08));
		addressEntity.setUrl(this.getString(R.string.f_u_11_08));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_11_09));
		addressEntity.setUrl(this.getString(R.string.f_u_11_09));
		addressArrayList.add(addressEntity);

		addressProvincesEntity.setAddressEntity(addressArrayList);
		addressProvincesList.add(addressProvincesEntity);

		// f_n_12
		addressProvincesEntity = new AddressProvincesEntity();
		addressProvincesEntity.setProvinces(this.getString(R.string.f_n_12));
		addressArrayList = new ArrayList<GoodsAddressEntity>();
		
		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_12_01));
		addressEntity.setUrl(this.getString(R.string.f_u_12_01));
		addressArrayList.add(addressEntity);
		
		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_12_02));
		addressEntity.setUrl(this.getString(R.string.f_u_12_02));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_12_03));
		addressEntity.setUrl(this.getString(R.string.f_u_12_03));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_12_04));
		addressEntity.setUrl(this.getString(R.string.f_u_12_04));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_12_05));
		addressEntity.setUrl(this.getString(R.string.f_u_12_05));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_12_06));
		addressEntity.setUrl(this.getString(R.string.f_u_12_06));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_12_07));
		addressEntity.setUrl(this.getString(R.string.f_u_12_07));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_12_08));
		addressEntity.setUrl(this.getString(R.string.f_u_12_08));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_12_09));
		addressEntity.setUrl(this.getString(R.string.f_u_12_09));
		addressArrayList.add(addressEntity);

		addressProvincesEntity.setAddressEntity(addressArrayList);
		addressProvincesList.add(addressProvincesEntity);

		// f_n_13
		addressProvincesEntity = new AddressProvincesEntity();
		addressProvincesEntity.setProvinces(this.getString(R.string.f_n_13));
		addressArrayList = new ArrayList<GoodsAddressEntity>();
		
		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_13_01));
		addressEntity.setUrl(this.getString(R.string.f_u_13_01));
		addressArrayList.add(addressEntity);
		
		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_13_02));
		addressEntity.setUrl(this.getString(R.string.f_u_13_02));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_13_03));
		addressEntity.setUrl(this.getString(R.string.f_u_13_03));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_13_04));
		addressEntity.setUrl(this.getString(R.string.f_u_13_04));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_13_05));
		addressEntity.setUrl(this.getString(R.string.f_u_13_05));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_13_06));
		addressEntity.setUrl(this.getString(R.string.f_u_13_06));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_13_07));
		addressEntity.setUrl(this.getString(R.string.f_u_13_07));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_13_08));
		addressEntity.setUrl(this.getString(R.string.f_u_13_08));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_13_09));
		addressEntity.setUrl(this.getString(R.string.f_u_13_09));
		addressArrayList.add(addressEntity);

		addressProvincesEntity.setAddressEntity(addressArrayList);
		addressProvincesList.add(addressProvincesEntity);

		// f_n_14
		addressProvincesEntity = new AddressProvincesEntity();
		addressProvincesEntity.setProvinces(this.getString(R.string.f_n_14));
		addressArrayList = new ArrayList<GoodsAddressEntity>();
		
		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_14_01));
		addressEntity.setUrl(this.getString(R.string.f_u_14_01));
		addressArrayList.add(addressEntity);
		
		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_14_02));
		addressEntity.setUrl(this.getString(R.string.f_u_14_02));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_14_03));
		addressEntity.setUrl(this.getString(R.string.f_u_14_03));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_14_04));
		addressEntity.setUrl(this.getString(R.string.f_u_14_04));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_14_05));
		addressEntity.setUrl(this.getString(R.string.f_u_14_05));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_14_06));
		addressEntity.setUrl(this.getString(R.string.f_u_14_06));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_14_07));
		addressEntity.setUrl(this.getString(R.string.f_u_14_07));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_14_08));
		addressEntity.setUrl(this.getString(R.string.f_u_14_08));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_14_09));
		addressEntity.setUrl(this.getString(R.string.f_u_14_09));
		addressArrayList.add(addressEntity);

		addressProvincesEntity.setAddressEntity(addressArrayList);
		addressProvincesList.add(addressProvincesEntity);

		// f_n_15
		addressProvincesEntity = new AddressProvincesEntity();
		addressProvincesEntity.setProvinces(this.getString(R.string.f_n_15));
		addressArrayList = new ArrayList<GoodsAddressEntity>();
		
		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_15_01));
		addressEntity.setUrl(this.getString(R.string.f_u_15_01));
		addressArrayList.add(addressEntity);
		
		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_15_02));
		addressEntity.setUrl(this.getString(R.string.f_u_15_02));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_15_03));
		addressEntity.setUrl(this.getString(R.string.f_u_15_03));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_15_04));
		addressEntity.setUrl(this.getString(R.string.f_u_15_04));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_15_05));
		addressEntity.setUrl(this.getString(R.string.f_u_15_05));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_15_06));
		addressEntity.setUrl(this.getString(R.string.f_u_15_06));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_15_07));
		addressEntity.setUrl(this.getString(R.string.f_u_15_07));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_15_08));
		addressEntity.setUrl(this.getString(R.string.f_u_15_08));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_15_09));
		addressEntity.setUrl(this.getString(R.string.f_u_15_09));
		addressArrayList.add(addressEntity);

		addressProvincesEntity.setAddressEntity(addressArrayList);
		addressProvincesList.add(addressProvincesEntity);

		// f_n_16
		addressProvincesEntity = new AddressProvincesEntity();
		addressProvincesEntity.setProvinces(this.getString(R.string.f_n_16));
		addressArrayList = new ArrayList<GoodsAddressEntity>();
		
		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_16_01));
		addressEntity.setUrl(this.getString(R.string.f_u_16_01));
		addressArrayList.add(addressEntity);
		
		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_16_02));
		addressEntity.setUrl(this.getString(R.string.f_u_16_02));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_16_03));
		addressEntity.setUrl(this.getString(R.string.f_u_16_03));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_16_04));
		addressEntity.setUrl(this.getString(R.string.f_u_16_04));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_16_05));
		addressEntity.setUrl(this.getString(R.string.f_u_16_05));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_16_06));
		addressEntity.setUrl(this.getString(R.string.f_u_16_06));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_16_07));
		addressEntity.setUrl(this.getString(R.string.f_u_16_07));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_16_08));
		addressEntity.setUrl(this.getString(R.string.f_u_16_08));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_16_09));
		addressEntity.setUrl(this.getString(R.string.f_u_16_09));
		addressArrayList.add(addressEntity);

		addressProvincesEntity.setAddressEntity(addressArrayList);
		addressProvincesList.add(addressProvincesEntity);

		// f_n_17
		addressProvincesEntity = new AddressProvincesEntity();
		addressProvincesEntity.setProvinces(this.getString(R.string.f_n_17));
		addressArrayList = new ArrayList<GoodsAddressEntity>();
		
		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_17_01));
		addressEntity.setUrl(this.getString(R.string.f_u_17_01));
		addressArrayList.add(addressEntity);
		
		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_17_02));
		addressEntity.setUrl(this.getString(R.string.f_u_17_02));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_17_03));
		addressEntity.setUrl(this.getString(R.string.f_u_17_03));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_17_04));
		addressEntity.setUrl(this.getString(R.string.f_u_17_04));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_17_05));
		addressEntity.setUrl(this.getString(R.string.f_u_17_05));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_17_06));
		addressEntity.setUrl(this.getString(R.string.f_u_17_06));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_17_07));
		addressEntity.setUrl(this.getString(R.string.f_u_17_07));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_17_08));
		addressEntity.setUrl(this.getString(R.string.f_u_17_08));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_17_09));
		addressEntity.setUrl(this.getString(R.string.f_u_17_09));
		addressArrayList.add(addressEntity);

		addressProvincesEntity.setAddressEntity(addressArrayList);
		addressProvincesList.add(addressProvincesEntity);

		// f_n_18
		addressProvincesEntity = new AddressProvincesEntity();
		addressProvincesEntity.setProvinces(this.getString(R.string.f_n_18));
		addressArrayList = new ArrayList<GoodsAddressEntity>();
		
		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_18_01));
		addressEntity.setUrl(this.getString(R.string.f_u_18_01));
		addressArrayList.add(addressEntity);
		
		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_18_02));
		addressEntity.setUrl(this.getString(R.string.f_u_18_02));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_18_03));
		addressEntity.setUrl(this.getString(R.string.f_u_18_03));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_18_04));
		addressEntity.setUrl(this.getString(R.string.f_u_18_04));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_18_05));
		addressEntity.setUrl(this.getString(R.string.f_u_18_05));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_18_06));
		addressEntity.setUrl(this.getString(R.string.f_u_18_06));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_18_07));
		addressEntity.setUrl(this.getString(R.string.f_u_18_07));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_18_08));
		addressEntity.setUrl(this.getString(R.string.f_u_18_08));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_18_09));
		addressEntity.setUrl(this.getString(R.string.f_u_18_09));
		addressArrayList.add(addressEntity);

		addressProvincesEntity.setAddressEntity(addressArrayList);
		addressProvincesList.add(addressProvincesEntity);

		// f_n_19
		addressProvincesEntity = new AddressProvincesEntity();
		addressProvincesEntity.setProvinces(this.getString(R.string.f_n_19));
		addressArrayList = new ArrayList<GoodsAddressEntity>();
		
		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_19_01));
		addressEntity.setUrl(this.getString(R.string.f_u_19_01));
		addressArrayList.add(addressEntity);
		
		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_19_02));
		addressEntity.setUrl(this.getString(R.string.f_u_19_02));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_19_03));
		addressEntity.setUrl(this.getString(R.string.f_u_19_03));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_19_04));
		addressEntity.setUrl(this.getString(R.string.f_u_19_04));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_19_05));
		addressEntity.setUrl(this.getString(R.string.f_u_19_05));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_19_06));
		addressEntity.setUrl(this.getString(R.string.f_u_19_06));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_19_07));
		addressEntity.setUrl(this.getString(R.string.f_u_19_07));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_19_08));
		addressEntity.setUrl(this.getString(R.string.f_u_19_08));
		addressArrayList.add(addressEntity);

		addressEntity = new GoodsAddressEntity();
		addressEntity.setAddress(this.getString(R.string.f_n_19_09));
		addressEntity.setUrl(this.getString(R.string.f_u_19_09));
		addressArrayList.add(addressEntity);

		addressProvincesEntity.setAddressEntity(addressArrayList);
		addressProvincesList.add(addressProvincesEntity);		
		
		goodsAddressAdapter = new GoodsAddressAdapter(UrlActivity.this,
				addressProvincesList);
		
		mLvAddress.setAdapter(goodsAddressAdapter);

	}

	private void initViews() {
		mLvAddress = (ExpandableListView) findViewById(R.id.elv_goods_address);
		mLvAddress.setGroupIndicator(null);
	}

	private void initEvents() {

	}
//    @Override  
//    public boolean onKeyDown(int keyCode, KeyEvent event) {  
//        // Check if the key event was the Back button and if there's history  
//        if ((keyCode == KeyEvent.KEYCODE_BACK) && show.canGoBack())  
//        {  
//            show.goBack();  
//            return true;  
//        }  
//        // If it wasn't the Back key or there's no web page history, bubble up to the default  
//        // system behavior (probably exit the activity)  
//        return super.onKeyDown(keyCode, event);  
//    }  
  
    @Override  
    public boolean onCreateOptionsMenu(Menu menu) {  
        getMenuInflater().inflate(R.menu.main, menu);  
        return true;  
    }  
    /**
     * 关闭软键盘
     */
    private void CloseKeyBoard() {
    	etSearchText.clearFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(etSearchText.getWindowToken(), 0);
    }
}  