package com.nq.qbr;

import com.nq.qbr.R.color;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter{ 
    //上下文对象 
    private Context context; 
    //图片数组 
//    private Integer[] imgs = { 
//            R.drawable.pic0, R.drawable.pic1, R.drawable.pic2,  
//            R.drawable.pic3, R.drawable.pic4, R.drawable.pic5,                
//            R.drawable.pic6, R.drawable.pic7, R.drawable.pic8,  
//            R.drawable.pic0, R.drawable.pic1, R.drawable.pic2,  
//            R.drawable.pic3, R.drawable.pic4, R.drawable.pic5,                
//            R.drawable.pic6, R.drawable.pic7, R.drawable.pic8, 
//    };
    //文字数组 
//    private String[] strHotSpot = { 
//    		"学生道歉拖后腿",
//    		"多女遭泼不明液体",
//    		"李易峰现身广州",
//    		"女球迷喝哭三里屯",
//    		"陈思诚青涩旧照",
//    		"剧版致青春将播",
//    		"汽车坠河救5条命",
//    		"小车追撞奔驰车", 
//    };
    MyAdapter(Context context){ 
    	this.context = context; 
    } 
    public int getCount() { 
//        return imgs.length; 
        return strHotSpot.length; 
    } 

    public Object getItem(int item) { 
        return item; 
    } 

    public long getItemId(int id) { 
        return id; 
    } 
     
    public String[] strHotSpot;
//    //创建View方法 
//    public View getView(int position, View convertView, ViewGroup parent) { 
//         ImageView imageView; 
//            if (convertView == null) { 
//                imageView = new ImageView(context); 
//                imageView.setLayoutParams(new GridView.LayoutParams(75, 75));//设置ImageView对象布局 
//                imageView.setAdjustViewBounds(false);//设置边界对齐 
//                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);//设置刻度的类型 
//                imageView.setPadding(8, 8, 8, 8);//设置间距 
//            }  
//            else { 
//                imageView = (ImageView) convertView; 
//            } 
//            imageView.setImageResource(imgs[position]);//为ImageView设置图片资源 
//            return imageView; 
//    } 
    //创建View方法 
    public View getView(int position, View convertView, ViewGroup parent) { 
    	TextView textView; 
            if (convertView == null) { 
            	textView = new TextView(context); 
            	textView.setLayoutParams(new GridView.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));//设置ImageView对象布局 
            	textView.setTextColor(color.default_title_indicator_text_color);
                //imageView.setAdjustViewBounds(false);//设置边界对齐 
//                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);//设置刻度的类型 
            	textView.setPadding(8, 8, 8, 8);//设置间距 
            }  
            else { 
            	textView = (TextView) convertView; 
            } 
            textView.setText(strHotSpot[position]);//为ImageView设置图片资源 
            return textView; 
    } 
} 