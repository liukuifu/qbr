package com.nq.qbr;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.BeeFramework.activity.BaseActivity;
import com.BeeFramework.model.BusinessResponse;
import com.external.androidquery.callback.AjaxStatus;
import com.nq.qbr.R;
import com.nq.qbr.utils.qbrUtils;

public class IndexMainActivity extends Activity implements OnClickListener {
	private EditText etSearchText;

	private Button btnSearch;
	private Toast tst;
	private String searchContent;
	private LinearLayout ll_news;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// �û���
		etSearchText = (EditText) this.findViewById(R.id.id_search_text);
		// ����
		// etUserPwd = (EditText) this.findViewById(R.id.id_login_password);
		// ��½��ť
		btnSearch = (Button) this.findViewById(R.id.btn_search);

		// ��Ӱ�ť�������¼�
		btnSearch.setOnClickListener(this);
		ll_news = (LinearLayout) findViewById(R.id.bt_news);
		ll_news.setClickable(true);
		ll_news.setOnClickListener(ocl);
		ll_news.setOnTouchListener(otl);
	}

	public OnClickListener ocl = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			// Toast.makeText(getApplicationContext(), "yes",
			// Toast.LENGTH_SHORT).show();
			Intent intent = new Intent();
			intent.setClass(IndexMainActivity.this, WebBrowser.class);
			/* ͨ��Bundle����洢��Ҫ���ݵ����� */
			Bundle bundle = new Bundle();
			/* �ַ����ַ������������ֽ����顢�������ȵȣ������Դ� */
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
	// * �������岿��Ԫ��
	// */
	// // @Override
	// protected void setViewBody() {
	// // ����
	// etSearchText = (TextView) this.findViewById(R.id.id_search_text);
	//
	// // ��Ӱ�ť�������¼�
	// etSearchText.setOnClickListener(this);
	// }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * ��ť�������
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

			/* ͨ��Bundle����洢��Ҫ���ݵ����� */
			Bundle bundle = new Bundle();
			/* �ַ����ַ������������ֽ����顢�������ȵȣ������Դ� */
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
	 * �ر������
	 */
	private void CloseKeyBoard() {
		etSearchText.clearFocus();
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(etSearchText.getWindowToken(), 0);
	}
}
