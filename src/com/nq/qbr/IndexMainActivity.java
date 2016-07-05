package com.nq.qbr;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
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
import com.nq.qbr.utils.qbrUtils;
import com.nq.qbr.utils.rootUtils;

public class IndexMainActivity extends Activity implements OnClickListener {
	private EditText etSearchText;

	private Button btnSearch;
	private Toast tst;
	private String searchContent;
	private LinearLayout ll_news;
	private GridView gv;
	private String[] strHotSpot = { 
    		"学生道歉拖后腿",
    		"多女遭泼不明液体",
    		"李易峰现身广州",
    		"女球迷喝哭三里屯",
    		"陈思诚青涩旧照",
    		"剧版致青春将播",
    		"汽车坠河救5条命",
    		"小车追撞奔驰车", 
    };
	private String[] strHotSpotUrl = { 
    		"www.baidu.com/s?cl=3&tn=baidutop10&fr=top1000&wd=%E5%AD%A6%E7%94%9F%E9%81%93%E6%AD%89%E6%8B%96%E5%90%8E%E8%85%BF&rsv_idx=2",
    		"www.baidu.com/s?cl=3&tn=baidutop10&fr=top1000&wd=%E5%A4%9A%E5%A5%B3%E9%81%AD%E6%B3%BC%E4%B8%8D%E6%98%8E%E6%B6%B2%E4%BD%93&rsv_idx=2",
    		"www.baidu.com/s?cl=3&tn=baidutop10&fr=top1000&wd=%E6%9D%8E%E6%98%93%E5%B3%B0%E7%8E%B0%E8%BA%AB%E5%B9%BF%E5%B7%9E&rsv_idx=2",
    		"www.baidu.com/s?cl=3&tn=baidutop10&fr=top1000&wd=%E5%A5%B3%E7%90%83%E8%BF%B7%E5%96%9D%E5%93%AD%E4%B8%89%E9%87%8C%E5%B1%AF&rsv_idx=2",
    		"www.baidu.com/s?cl=3&tn=baidutop10&fr=top1000&wd=%E9%99%88%E6%80%9D%E8%AF%9A%E9%9D%92%E6%B6%A9%E6%97%A7%E7%85%A7&rsv_idx=2",
    		"www.baidu.com/s?cl=3&tn=baidutop10&fr=top1000&wd=%E5%89%A7%E7%89%88%E8%87%B4%E9%9D%92%E6%98%A5%E5%B0%86%E6%92%AD&rsv_idx=2",
    		"www.baidu.com/s?cl=3&tn=baidutop10&fr=top1000&wd=%E6%B1%BD%E8%BD%A6%E5%9D%A0%E6%B2%B3%E6%95%915%E6%9D%A1%E5%91%BD&rsv_idx=2",
    		"www.baidu.com/s?cl=3&tn=baidutop10&fr=top1000&wd=%E5%B0%8F%E8%BD%A6%E8%BF%BD%E6%92%9E%E5%A5%94%E9%A9%B0%E8%BD%A6&rsv_idx=2", 
    };
	private ArrayList<View> views;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 用户名
		etSearchText = (EditText) this.findViewById(R.id.id_search_text);
		// 密码
		// etUserPwd = (EditText) this.findViewById(R.id.id_login_password);
		// 登陆按钮
		btnSearch = (Button) this.findViewById(R.id.btn_search);

		// 添加按钮的侦听事件
		btnSearch.setOnClickListener(this);
		ll_news = (LinearLayout) findViewById(R.id.bt_news);
		ll_news.setClickable(true);
		ll_news.setOnClickListener(ocl);
		ll_news.setOnTouchListener(otl);
		
		if(rootUtils.isDeviceRooted())
		{
			 tst = Toast.makeText(this, "有root权限", Toast.LENGTH_SHORT);
			 tst.show();
		}
		else{
			 tst = Toast.makeText(this, "没有获得root权限", Toast.LENGTH_SHORT);
			 tst.show();			
		}
		
		gv = (GridView)findViewById(R.id.gv_Real_time_hot_spot); 
        //为GridView设置适配器 
		MyAdapter ma = new MyAdapter(this);
		ma.strHotSpot = strHotSpot;
        gv.setAdapter(ma); 
//        gv.setAdapter(new MyAdapter(this)); 
        //注册监听事件 
        gv.setOnItemClickListener(new OnItemClickListener() 
        { 
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) 
            { 
                Toast.makeText(IndexMainActivity.this, strHotSpotUrl[position], Toast.LENGTH_SHORT).show(); 
    			Intent intent = new Intent();
    			intent.setClass(IndexMainActivity.this, WebBrowser.class);
    			/* 通过Bundle对象存储需要传递的数据 */
    			Bundle bundle = new Bundle();
    			/* 字符、字符串、布尔、字节数组、浮点数等等，都可以传 */
    			bundle.putString("flag", "HotSpot");
    			bundle.putString("searchContent", strHotSpotUrl[position]);
    			intent.putExtras(bundle);
    			startActivity(intent);
            } 
        }); 

        // 组装imageView列表
		// 把要轮播的图片添加到列表里调用setViewPagerViews方法添加到自定义的Viewpager里面就可以使用了ßß
		views = new ArrayList<View>();
		Banner pager = (Banner)findViewById(R.id.my_view_pager);
		ImageView image = new ImageView(this);
		image.setImageResource(R.drawable.image1);
		views.add(image);
		image = new ImageView(this);
		image.setImageResource(R.drawable.image2);
		views.add(image);
		image = new ImageView(this);
		image.setImageResource(R.drawable.image3);
		views.add(image);
		image = new ImageView(this);
		image.setImageResource(R.drawable.image4);
		views.add(image);
		
		pager.setViewPagerViews(views);
		pager.setOnSingleTouchListener(new OnSingleTouchListener() {
			
			@Override
			public void onSingleTouch(int position) {
				Toast.makeText(IndexMainActivity.this, "当前点击"+position, 0).show();
			}
		});

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
			/* 字符、字符串、布尔、字节数组、浮点数等等，都可以传 */
			bundle.putString("flag", "news");
			bundle.putString("searchContent", IndexMainActivity.this.getString(R.string.news_base_url));
			intent.putExtras(bundle);
			startActivity(intent);
		}
	};
	public OnTouchListener otl = new OnTouchListener() {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				ll_news.setBackgroundColor(Color.rgb(127, 127, 127));
			} else if (event.getAction() == MotionEvent.ACTION_UP) {
				ll_news.setBackgroundColor(Color.TRANSPARENT);
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
		Resources resource = (Resources) getBaseContext().getResources();
		switch (v.getId()) {
		case R.id.btn_search:
			searchContent = etSearchText.getText().toString();
			this.CloseKeyBoard();
			// tst = Toast.makeText(this, searchContent, Toast.LENGTH_SHORT);
			// tst.show();
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

			// qbrUtils.showMsgCenter(this,
			// resource.getString(R.string.show_msg_name));

			// name = etUserName.getText().toString();
			// pwd = etUserPwd.getText().toString();
			//
			// if (StringUtils.isEmpty(name)) {
			// DeliveryUtils.showMsgCenter(this,
			// resource.getString(R.string.show_err_msg_name));
			// } else if (StringUtils.isEmpty(pwd)) {
			// DeliveryUtils.showMsgCenter(this,
			// resource.getString(R.string.show_err_msg_password));
			// } else {
			// userModel = new UserModel(this);
			// userModel.addResponseListener(this);
			// userModel.signin(name, pwd);
			// this.CloseKeyBoard();
			// }

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
}
