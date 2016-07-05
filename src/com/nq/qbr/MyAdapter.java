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
    //�����Ķ��� 
    private Context context; 
    //ͼƬ���� 
//    private Integer[] imgs = { 
//            R.drawable.pic0, R.drawable.pic1, R.drawable.pic2,  
//            R.drawable.pic3, R.drawable.pic4, R.drawable.pic5,                
//            R.drawable.pic6, R.drawable.pic7, R.drawable.pic8,  
//            R.drawable.pic0, R.drawable.pic1, R.drawable.pic2,  
//            R.drawable.pic3, R.drawable.pic4, R.drawable.pic5,                
//            R.drawable.pic6, R.drawable.pic7, R.drawable.pic8, 
//    };
    //�������� 
//    private String[] strHotSpot = { 
//    		"ѧ����Ǹ�Ϻ���",
//    		"��Ů���ò���Һ��",
//    		"���׷��������",
//    		"Ů���Ժȿ�������",
//    		"��˼����ɬ����",
//    		"������ഺ����",
//    		"����׹�Ӿ�5����",
//    		"С��׷ײ���۳�", 
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
//    //����View���� 
//    public View getView(int position, View convertView, ViewGroup parent) { 
//         ImageView imageView; 
//            if (convertView == null) { 
//                imageView = new ImageView(context); 
//                imageView.setLayoutParams(new GridView.LayoutParams(75, 75));//����ImageView���󲼾� 
//                imageView.setAdjustViewBounds(false);//���ñ߽���� 
//                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);//���ÿ̶ȵ����� 
//                imageView.setPadding(8, 8, 8, 8);//���ü�� 
//            }  
//            else { 
//                imageView = (ImageView) convertView; 
//            } 
//            imageView.setImageResource(imgs[position]);//ΪImageView����ͼƬ��Դ 
//            return imageView; 
//    } 
    //����View���� 
    public View getView(int position, View convertView, ViewGroup parent) { 
    	TextView textView; 
            if (convertView == null) { 
            	textView = new TextView(context); 
            	textView.setLayoutParams(new GridView.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));//����ImageView���󲼾� 
            	textView.setTextColor(color.default_title_indicator_text_color);
                //imageView.setAdjustViewBounds(false);//���ñ߽���� 
//                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);//���ÿ̶ȵ����� 
            	textView.setPadding(8, 8, 8, 8);//���ü�� 
            }  
            else { 
            	textView = (TextView) convertView; 
            } 
            textView.setText(strHotSpot[position]);//ΪImageView����ͼƬ��Դ 
            return textView; 
    } 
} 