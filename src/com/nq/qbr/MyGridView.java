package com.nq.qbr;

import android.widget.GridView;
/**
 * 鑷畾涔塯ridView
 * @author jayqiu
 *
 */
public class MyGridView extends GridView
{
	public MyGridView(android.content.Context context,
			android.util.AttributeSet attrs)
	{
		super(context, attrs);
	}

	/**
	 * 璁剧疆涓嶆粴鍔�
	 */
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);

	}
}
