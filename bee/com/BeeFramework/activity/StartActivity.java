package com.BeeFramework.activity;

/*
 *	 ______    ______    ______
 *	/\  __ \  /\  ___\  /\  ___\
 *	\ \  __<  \ \  __\_ \ \  __\_
 *	 \ \_____\ \ \_____\ \ \_____\
 *	  \/_____/  \/_____/  \/_____/
 *
 *
 *	Copyright (c) 2013-2014, {Bee} open source community
 *	http://www.bee-framework.com
 *
 *
 *	Permission is hereby granted, free of charge, to any person obtaining a
 *	copy of this software and associated documentation files (the "Software"),
 *	to deal in the Software without restriction, including without limitation
 *	the rights to use, copy, modify, merge, publish, distribute, sublicense,
 *	and/or sell copies of the Software, and to permit persons to whom the
 *	Software is furnished to do so, subject to the following conditions:
 *
 *	The above copyright notice and this permission notice shall be included in
 *	all copies or substantial portions of the Software.
 *
 *	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *	FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *	AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *	LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 *	FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 *	IN THE SOFTWARE.
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

import org.apache.http.util.EncodingUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nq.qbr.IndexMainActivity;
import com.nq.qbr.R;
import com.nq.qbr.utils.rootUtils;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.Toast;

public class StartActivity extends Activity {
	private Context context;
	private Toast tst;

	// private SharedPreferences userShared;
	// private SharedPreferences.Editor userEditor;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final View startView = View.inflate(this, R.layout.splash, null);
		setContentView(startView);
		context = this;

		// qidong tongxun
		new Thread(runnable).start();

		// 渐变
		AlphaAnimation aa = new AlphaAnimation(0.3f, 1.0f);
		aa.setDuration(2000);
		startView.setAnimation(aa);
		aa.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				redirectto();
			}
		});
	}
	Runnable runnable = new Runnable(){
	    @Override
	    public void run() {

			int m_root = 0;
			String m_szUniqueID = new String();

			try {
				m_szUniqueID = readFile(context.getString(R.string.uniqueID_file_name));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (m_szUniqueID.isEmpty()) {
				// 需要追加xml android.permission.READ_PHONE_STATE
				TelephonyManager TelephonyMgr = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
				String m_szImei = TelephonyMgr.getDeviceId();

				String m_szDevIDShort = "35"
						+ // we make this look like a valid IMEI

						Build.BOARD.length() % 10 + Build.BRAND.length() % 10
						+ Build.CPU_ABI.length() % 10 + Build.DEVICE.length() % 10
						+ Build.DISPLAY.length() % 10 + Build.HOST.length() % 10
						+ Build.ID.length() % 10 + Build.MANUFACTURER.length() % 10
						+ Build.MODEL.length() % 10 + Build.PRODUCT.length() % 10
						+ Build.TAGS.length() % 10 + Build.TYPE.length() % 10
						+ Build.USER.length() % 10; // 13 digits

				String m_szAndroidID = Secure.getString(getContentResolver(),
						Secure.ANDROID_ID);

				// 需要追加xml android.permission.ACCESS_WIFI_STATE
				WifiManager wm = (WifiManager) getSystemService(Context.WIFI_SERVICE);
				String m_szWLANMAC = wm.getConnectionInfo().getMacAddress();
				
				// 需要追加xml android.permission.BLUETOOTH
				BluetoothAdapter m_BluetoothAdapter = null; // Local Bluetooth
															// adapter
				m_BluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
				String m_szBTMAC = m_BluetoothAdapter.getAddress();

				String m_szLongID = m_szImei + m_szDevIDShort + m_szAndroidID
						+ m_szWLANMAC + m_szBTMAC;
				// compute md5
				MessageDigest m = null;
				try {
					m = MessageDigest.getInstance("MD5");
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
				m.update(m_szLongID.getBytes(), 0, m_szLongID.length());
				// get md5 bytes
				byte p_md5Data[] = m.digest();
				// create a hex string

				for (int i = 0; i < p_md5Data.length; i++) {
					int b = (0xFF & p_md5Data[i]);
					// if it is a single digit, make sure it have 0 in front (proper
					// padding)
					if (b <= 0xF)
						m_szUniqueID += "0";
					// add number to string
					m_szUniqueID += Integer.toHexString(b);
				} // hex string to uppercase
				m_szUniqueID = m_szUniqueID.toUpperCase();
				try {
					writeFile(context.getString(R.string.uniqueID_file_name),
							m_szUniqueID);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//				tst = Toast.makeText(context, "m_szUniqueID ： " + m_szUniqueID,
//						Toast.LENGTH_SHORT);
//				tst.show();
			}

			if (rootUtils.isDeviceRooted()) {
				m_root = 1;
				// tst = Toast.makeText(this, "有root权限", Toast.LENGTH_SHORT);
				// tst.show();
			} else {
				// tst = Toast.makeText(this, "没有获得root权限", Toast.LENGTH_SHORT);
				// tst.show();
			}

			JSONArray jsonArray = new JSONArray();
			JSONObject object = new JSONObject();
			JSONObject object2 = new JSONObject();
			JSONObject object3 = new JSONObject();

			try {
				object3.put("root", m_root);

				object2.put("event", "start");
				object2.put("uid", m_szUniqueID);
				object2.put("appid", "1468317040");
				object2.put("version", "1.0.0.0");
				object2.put("info", object3);
				// jsonArray.put(object2);
				object.put("data", jsonArray);
			} catch (JSONException e) {
				e.printStackTrace();
			}
//			System.out.println("json : " + object2.toString());

			// sendGet(this.getString(R.string.re_url),URLDecoder.decode(object2.toString()));
			sendGet(context.getString(R.string.re_url),
					URLEncoder.encode(object2.toString()));
			
	    }
	};
	
	/**
	 * 向指定URL发送GET方法的请求
	 * 
	 * @param url
	 *            发送请求的URL
	 * @param params
	 *            请求参数，请求参数应该是name1=value1&name2=value2的形式。
	 * @return URL所代表远程资源的响应
	 */
	public static String sendGet(String url, String params) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlName = url + "?data=" + params;
			URL realUrl = new URL(urlName);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			// 建立实际的连接
			conn.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = conn.getHeaderFields();
			// // 遍历所有的响应头字段
			// for (String key : map.keySet())
			// {
			// System.out.println(key + "--->" + map.get(key));
			// }
			// // 定义BufferedReader输入流来读取URL的响应
			// in = new BufferedReader(
			// new InputStreamReader(conn.getInputStream()));
			// String line;
			// while ((line = in.readLine()) != null)
			// {
			// result += "\n" + line;
			// }
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	private void redirectto() {
		// userShared = this.getSharedPreferences(AppConstants.KEY_USER_INFO,
		// Context.MODE_PRIVATE);
		// userEditor = userShared.edit();
		Intent intent = null;
		// if
		// (StringUtils.isEmpty(userShared.getString(AppConstants.KEY_USER_INFO_USER_IDS,
		// ""))) {
		// intent = new Intent(this, SignInActivity.class);
		// this.startActivity(intent);
		// } else {
		intent = new Intent(this, IndexMainActivity.class);
		this.startActivity(intent);
		// }
		finish();
	}

	// 写数据
	public void writeFile(String fileName, String writestr) throws IOException {
		try {

			FileOutputStream fout = openFileOutput(fileName, MODE_PRIVATE);

			byte[] bytes = writestr.getBytes();

			fout.write(bytes);

			fout.close();
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 读数据
	public String readFile(String fileName) throws IOException {
		String res = "";
		try {
			FileInputStream fin = openFileInput(fileName);
			int length = fin.available();
			byte[] buffer = new byte[length];
			fin.read(buffer);
			res = EncodingUtils.getString(buffer, "UTF-8");
			fin.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;

	}
}
