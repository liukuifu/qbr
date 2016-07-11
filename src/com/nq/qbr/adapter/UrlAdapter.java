package com.nq.qbr.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nq.qbr.R;
import com.nq.qbr.R.color;
import com.nq.qbr.R.drawable;

public class UrlAdapter extends BaseAdapter{ 
    //�����Ķ��� 
    private Context context; 
    
    public UrlAdapter(Context context){ 
    	this.context = context; 
    	strUrl[0] = context.getString(R.string.hot_name_1);
    	strUrl[1] = context.getString(R.string.hot_name_2);
    	strUrl[2] = context.getString(R.string.hot_name_3);
    	strUrl[3] = context.getString(R.string.hot_name_4);
    	strUrl[4] = context.getString(R.string.hot_name_5);
    	strUrl[5] = context.getString(R.string.hot_name_6);
    	strUrl[6] = context.getString(R.string.hot_name_7);
    	strUrl[7] = context.getString(R.string.hot_name_8);
    	strUrl[8] = context.getString(R.string.hot_name_9);
    	strUrl[9] = context.getString(R.string.hot_name_10);
    	strUrl[10] = context.getString(R.string.hot_name_11);
    	strUrl[11] = context.getString(R.string.hot_name_12);
    	strUrl[12] = context.getString(R.string.hot_name_13);
    	strUrl[13] = context.getString(R.string.hot_name_14);
    	strUrl[14] = context.getString(R.string.hot_name_15);
//    	strUrl[15] = context.getString(R.string.hot_name_16);
//    	strUrl[16] = context.getString(R.string.hot_name_17);
//    	strUrl[17] = context.getString(R.string.hot_name_18);
//    	strUrl[18] = context.getString(R.string.hot_name_19);
//    	strUrl[19] = context.getString(R.string.hot_name_20);
    } 
    public int getCount() { 
//        return imgs.length; 
        return strUrl.length; 
    } 

    public Object getItem(int item) { 
        return item; 
    } 

    public long getItemId(int id) { 
        return id; 
    } 
 
    private Integer[] imgs = {
            R.drawable.hot_image_1, R.drawable.hot_image_2, R.drawable.hot_image_3,  
            R.drawable.hot_image_4, R.drawable.hot_image_5, R.drawable.hot_image_6,                
            R.drawable.hot_image_7, R.drawable.hot_image_8, R.drawable.hot_image_9,  
            R.drawable.hot_image_10, R.drawable.hot_image_11, R.drawable.hot_image_12,  
            R.drawable.hot_image_13, R.drawable.hot_image_14, R.drawable.hot_image_15,                
//            R.drawable.hot_image_16, R.drawable.hot_image_17, R.drawable.hot_image_18,                
//            R.drawable.hot_image_19, R.drawable.hot_image_20, 
    };
	private String[] strUrl = new String[15];
//	= {
//			context.getString(R.string.hot_name_1),
//			context.getString(R.string.hot_name_2),
//			context.getString(R.string.hot_name_3),
//			context.getString(R.string.hot_name_4),
//			context.getString(R.string.hot_name_5),
//			context.getString(R.string.hot_name_6),
//			context.getString(R.string.hot_name_7),
//			context.getString(R.string.hot_name_8),
//			context.getString(R.string.hot_name_9),
//			context.getString(R.string.hot_name_10),
//			context.getString(R.string.hot_name_11),
//			context.getString(R.string.hot_name_12),
//			context.getString(R.string.hot_name_13),
//			context.getString(R.string.hot_name_14),
//			context.getString(R.string.hot_name_15),
//			context.getString(R.string.hot_name_16),
//			context.getString(R.string.hot_name_17),
//			context.getString(R.string.hot_name_18),
//			context.getString(R.string.hot_name_19),
//			context.getString(R.string.hot_name_20),
//			};
     
    public View getView(int position, View convertView, ViewGroup parent) { 
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.urlitem, null);
            }
     
            ImageView imageView = (ImageView) convertView.findViewById(R.id.ItemImage);
            TextView textView = (TextView) convertView.findViewById(R.id.ItemText);
     
            imageView.setImageResource(imgs[position]);
            textView.setText(strUrl[position]);
            return convertView;
    } 
} 