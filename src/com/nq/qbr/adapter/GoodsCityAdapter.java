package com.nq.qbr.adapter;

import java.util.ArrayList;

import com.nq.qbr.R;
import com.nq.qbr.entity.GoodsAddressEntity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * 城市适配
 * 
 * @author jayqiu
 * 
 */
public class GoodsCityAdapter extends BaseAdapter {
	private ArrayList<GoodsAddressEntity> addressList;
	private Context mContext;

	public GoodsCityAdapter(Context context,
			ArrayList<GoodsAddressEntity> addressList) {
		this.mContext = context;
		this.addressList = addressList;
	}

	@Override
	public int getCount() {
		return addressList.size();
	}

	@Override
	public Object getItem(int position) {
		return addressList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHodler viewHodler = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.item_goods_type, null);
			viewHodler = new ViewHodler();
			viewHodler.mTvType = (TextView) convertView
					.findViewById(R.id.tv_item_goods_type);
			convertView.setTag(viewHodler);
		} else {
			viewHodler = (ViewHodler) convertView.getTag();
		}
		GoodsAddressEntity addressEntity = addressList.get(position);
		viewHodler.mTvType.setText(addressEntity.getAddress());
		return convertView;
	}

	private class ViewHodler {
		private TextView mTvType;
	}


}
