package com.nq.qbr;


import java.util.Timer;  
import java.util.TimerTask;  
import java.util.regex.Matcher;  
import java.util.regex.Pattern;  
  
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
import android.widget.ArrayAdapter;  
import android.widget.AutoCompleteTextView;  
import android.widget.Button;  
import android.widget.EditText;
import android.widget.Toast;  
  
public class WebBrowser extends Activity {  
    Toast tst;
  
    AutoCompleteTextView url;  
    WebView show;  
    private EditText etSearchText;
    WebBrowser wb = this;
    String[] booksArray = new String[]  
    {    
            "www.baidu.com"
//            "http://maps.google.com",  
//            "http://maps.baidu.com",  
//            "http://qq.com",  
//            "www.baidu.com",  
//            "www.163.com"  
    };  
      
    @Override  
    public void onCreate(Bundle savedInstanceState)   
    {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_webv); 
		etSearchText = (EditText) this.findViewById(R.id.id_search_text);
        
        /*获取Intent中的Bundle对象*/
        Bundle bundle = this.getIntent().getExtras();
        
        /*获取Bundle中的数据，注意类型和key*/
        String searchContent = bundle.getString("searchContent");        
        String searchFlag = bundle.getString("flag");

        if("search".equals(searchFlag)){
    		etSearchText.setText(searchContent);
        } else {
    		etSearchText.setText("");
        }
        
		
//        booksArray[0] = "www.baidu.com/s?wd=" + searchContent;
//    	tst = Toast.makeText(this, searchContent, Toast.LENGTH_SHORT);      
//    	tst.show();
        final Activity activity = this;  
                  
        show = (WebView)findViewById(R.id.show);  
        show.getSettings().setJavaScriptEnabled(true);  
        show.getSettings().setBuiltInZoomControls(true);  
        //show.getSettings().setDisplayZoomControls(false);  
        show.setWebViewClient(new WebViewClient()  
        {  
            public boolean shouldOverrideUrlLoading(WebView view, String strUrl)  
            {  
                view.loadUrl(strUrl);  
                url.setText(strUrl);  
                return false;  
            }  
              
            public void onPageStarted(WebView view, String strUrl, Bitmap favicon)  
            {  
                super.onPageStarted(view, strUrl, favicon);  
                url.setText(strUrl);  
            }  
              
            public void onPageFinished(WebView view, String strUrl)  
            {  
            }  
              
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl)   
            {  
                 Toast.makeText(activity, "Oh no! " + description, Toast.LENGTH_SHORT).show();  
            }  
        });  
          
        
                  
        url = (AutoCompleteTextView)findViewById(R.id.url);  
        ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, booksArray);  
        url.setAdapter(aa);  
        url.setOnKeyListener(new View.OnKeyListener()   
        {             
            public boolean onKey(View v, int keyCode, KeyEvent ev)   
            {  
                if (keyCode == KeyEvent.KEYCODE_ENTER)  
                {  
                    String strUrl = url.getText().toString();  
                      
                    Pattern p = Pattern.compile("http://([\\w-]+\\.)+[\\w-]+(/[\\w-\\./?%=]*)?");  
                    Matcher m = p.matcher(strUrl);  
                    if (!m.find())  
                    {  
                        strUrl = "http://" + strUrl;  
                    }  
                      
                    show.loadUrl(strUrl);  
                      
                    return true;  
                }  
                  
                return false;  
            }  
        });  

        //sssssssssssss

        String strUrl = "";
        if("search".equals(searchFlag)){
        	
//        	strUrl = "www.baidu.com/s?wd=" + searchContent;//url.getText().toString();  
        	strUrl = this.getString(R.string.search_base_url) + searchContent;//url.getText().toString();  
        } else {
        	strUrl = searchContent;
        }
        Pattern p = Pattern.compile("http://([\\w-]+\\.)+[\\w-]+(/[\\w-\\./?%=]*)?");  
        Matcher m = p.matcher(strUrl);  
        if (!m.find())  
        {  
            strUrl = "http://" + strUrl;  
        }  
          
        show.loadUrl(strUrl);  

        final Button btnSearch = (Button)findViewById(R.id.btn_search); 

        btnSearch.setOnClickListener(new OnClickListener()  
        {  
            public void onClick(View v)  
            {  
            	String searchContent = etSearchText.getText().toString();
            	wb.CloseKeyBoard();
            	String strUrl = wb.getString(R.string.search_base_url) + searchContent;//url.getText().toString();  
                
                Pattern p = Pattern.compile("http://([\\w-]+\\.)+[\\w-]+(/[\\w-\\./?%=]*)?");  
                Matcher m = p.matcher(strUrl);  
                if (!m.find())  
                {  
                    strUrl = "http://" + strUrl;  
                }  
                  
                show.loadUrl(strUrl);  
            }  
        });  
        
        //eeeeeeeeeee
        // button   
        final Button backBtn = (Button)findViewById(R.id.back);  
        final Button forwardBtn = (Button)findViewById(R.id.forward);  
        Button refreshBtn = (Button)findViewById(R.id.refresh);  
        Button homeBtn = (Button)findViewById(R.id.home);  
        backBtn.setEnabled(false);  
        forwardBtn.setEnabled(false);  
          
        backBtn.setOnClickListener(new OnClickListener()  
        {  
            public void onClick(View v)  
            {  
                show.goBack();  
            }  
        });  
          
        forwardBtn.setOnClickListener(new OnClickListener()  
        {  
            public void onClick(View v)  
            {  
                // TODO  
                show.goForward();  
            }  
        });  
          
        refreshBtn.setOnClickListener(new OnClickListener()  
        {  
            public void onClick(View v)  
            {  
                // TODO  
                String strUrl = url.getText().toString();  
                show.loadUrl(strUrl);  
            }  
        });  
          
        homeBtn.setOnClickListener(new OnClickListener()  
        {  
            public void onClick(View v)  
            {  
                // TODO  
                //show.loadUrl("http://maps.google.com");  
            	Intent i = new Intent(WebBrowser.this,IndexMainActivity.class);
            	startActivity(i);
            }  
        });  
          
        final Handler handler = new Handler()  
        {  
            @Override  
            public void handleMessage(Message msg)  
            {  
                if (msg.what == 0x1111)  
                {  
                    // whether can go back    
                    if (show.canGoBack())  
                    {  
                        backBtn.setEnabled(true);  
                    }  
                    else  
                    {  
                        backBtn.setEnabled(false);  
                    }  
                      
                    // whether can go forward  
                    if (show.canGoForward())  
                    {  
                        forwardBtn.setEnabled(true);  
                    }  
                    else  
                    {  
                        forwardBtn.setEnabled(false);  
                    }  
                }  
                  
                super.handleMessage(msg);  
            }  
        };  
          
        // create thread to change button states   
        new Timer().schedule(new TimerTask()  
        {  
            public void run()  
            {  
                Message msg = new Message();  
                msg.what = 0x1111;  
                handler.sendMessage(msg);  
            }  
        }, 0, 100);  
    }  
      
    @Override  
    public boolean onKeyDown(int keyCode, KeyEvent event) {  
        // Check if the key event was the Back button and if there's history  
        if ((keyCode == KeyEvent.KEYCODE_BACK) && show.canGoBack())  
        {  
            show.goBack();  
            return true;  
        }  
        // If it wasn't the Back key or there's no web page history, bubble up to the default  
        // system behavior (probably exit the activity)  
        return super.onKeyDown(keyCode, event);  
    }  
  
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