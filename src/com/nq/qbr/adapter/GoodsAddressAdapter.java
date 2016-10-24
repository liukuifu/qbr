package com.nq.qbr.adapter;

import java.util.ArrayList;

import com.nq.qbr.MyGridView;
import com.nq.qbr.R;
import com.nq.qbr.UrlActivity;
import com.nq.qbr.WebBrowser;
import com.nq.qbr.entity.AddressProvincesEntity;
import com.nq.qbr.entity.GoodsAddressEntity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 
 * @author 地址选择适配
 * 
 */
public class GoodsAddressAdapter extends BaseExpandableListAdapter implements
		OnItemClickListener {
	private Context mContext;
	private ArrayList<AddressProvincesEntity> addressProvincesList;
	private LayoutInflater inflater;
	private MyGridView toolbarGrid;

	public GoodsAddressAdapter(Context mContext,
			ArrayList<AddressProvincesEntity> addressProvincesList) {
		this.mContext = mContext;
		this.addressProvincesList = addressProvincesList;
		inflater = LayoutInflater.from(mContext);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return addressProvincesList.get(groupPosition).getAddressEntity();
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
//		if (convertView == null) {

			convertView = inflater.inflate(R.layout.item_address_view, null);
			toolbarGrid = (MyGridView) convertView
					.findViewById(R.id.gv_toolbar);
			toolbarGrid.setNumColumns(3);// 设置每行列数
			toolbarGrid.setGravity(Gravity.CENTER);// 位置居中
//			toolbarGrid.setHorizontalSpacing(10);// 水平间隔
			toolbarGrid
					.setAdapter(new GoodsCityAdapter(mContext,
							addressProvincesList.get(groupPosition)
									.getAddressEntity()));// 设置菜单Adapter
			toolbarGrid.setOnItemClickListener(this);
//		}
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return addressProvincesList.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return addressProvincesList.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {

		GroupViewHolde groupViewHolde = null;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.itme_address_provinces,
					null);
			groupViewHolde = new GroupViewHolde();
			groupViewHolde.mTvName = (TextView) convertView
					.findViewById(R.id.tv_item_goods_pro);
			convertView.setTag(groupViewHolde);
			
        
		} else {
			groupViewHolde = (GroupViewHolde) convertView.getTag();
		}
        ImageView parentImageViw=(ImageView) convertView.findViewById(R.id.arrow);
//判断isExpanded就可以控制是按下还是关闭，同时更换图片 
        if(isExpanded){
//            parentImageViw.setBackgroundResource(R.drawable.down);
            parentImageViw.setImageResource(R.drawable.up);
//            imageView.setImageResource(imgs[position]);
        }else{
//            parentImageViw.setBackgroundResource(R.drawable.up);
            parentImageViw.setImageResource(R.drawable.down);
        } 
		groupViewHolde.mTvName.setText(addressProvincesList.get(groupPosition)
				.getProvinces());
		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return false;
	}

	class GroupViewHolde {
		TextView mTvName;
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view,
			int position, long id) {
		GoodsAddressEntity goodsAddressEntity = (GoodsAddressEntity) adapterView
				.getItemAtPosition(position);
		if (goodsAddressEntity != null) {
//			Toast.makeText(mContext,
//					"当前选中的是:" + goodsAddressEntity.getAddress(),
//					Toast.LENGTH_SHORT).show();
//			Toast.makeText(mContext,
//					"当前选中的是:" + goodsAddressEntity.getUrl(),
//					Toast.LENGTH_SHORT).show();
			Intent intent = new Intent();
			intent.setClass(mContext, WebBrowser.class);
			/* 通过Bundle对象存储需要传递的数据 */
			Bundle bundle = new Bundle();
			/* 字符、字符串、布尔、字节数组、浮点数等等，都可以传 */
			bundle.putString("flag", "UrlUrl");
			bundle.putString("searchContent", goodsAddressEntity.getUrl());
			intent.putExtras(bundle);
		
			mContext.startActivity(intent);
		}

	}

}

