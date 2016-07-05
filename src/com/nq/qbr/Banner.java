package com.nq.qbr;

import java.util.List;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout.LayoutParams;

import com.nq.qbr.BannerViewPagerAdapter;
import com.nq.qbr.R;
import com.nq.qbr.Banner.OnSingleTouchListener;

public class Banner extends LinearLayout implements Runnable {
	private ViewPager viewPager;
	private LinearLayout viewDots;
	private List<View> views;
	// ������еĵ�
	private ImageView[] dotsImageViews;
	// ��ǰѡ�е��ImageView
	private ImageView tempImageView;
	// ��ǰѡ��λ��
	private int index = 0;
	// ��ǰ�Ƿ����
	private boolean isContinue = true;
	// Banner����¼�
	private OnSingleTouchListener mListener;
	/** ����ʱ���µĵ� **/
	private PointF downP = new PointF();
	/** ����ʱ��ǰ�ĵ� **/
	private PointF curP = new PointF();
	/** ���� */
	private float dotsViewHeight;// ����View�ĸ߶�
	private float dotsSpacing;// �����֮��ļ��
	private boolean autoChange;// �Ƿ��Զ��л�Banner
	private int changeInterval;// �л�Banner��ʱ��
	private Drawable dotsFocusImage;// ��ǰѡ�е�Dots
	private Drawable dotsBlurImage;// δѡ�е�Dots
	private Drawable dotsBackground;// ����View�ı���
	private ScaleType scaleType;// view�����ͼƬ�Ļ�ʹ��
	private boolean adjustViewBounds;// view�����ͼƬ�Ļ�ʹ��
	private int dotsGravity;//

	public Banner(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray a = context.obtainStyledAttributes(attrs,				
				R.styleable.BannerImageViewPager, 0, 0);		
		try {
			dotsViewHeight = a.getDimension(
					R.styleable.BannerImageViewPager_dotsViewHeight,
					dp2px(context, 45));
			dotsSpacing = a.getDimension(
					R.styleable.BannerImageViewPager_dotsSpacing,
					dp2px(context, 10));
			changeInterval = a.getInteger(
					R.styleable.BannerImageViewPager_changeInterval, 3000);
			autoChange = a.getBoolean(
					R.styleable.BannerImageViewPager_autoChange, true);
			dotsFocusImage = a
					.getDrawable(R.styleable.BannerImageViewPager_dotsFocusImage);
			dotsBlurImage = a
					.getDrawable(R.styleable.BannerImageViewPager_dotsBlurImage);
			dotsBackground = a
					.getDrawable(R.styleable.BannerImageViewPager_dotsBackground);
			ScaleType[] values = ScaleType.values();
			int val = a.getInt(
					R.styleable.BannerImageViewPager_android_scaleType, 0);
			scaleType = values[val];
			adjustViewBounds = a.getBoolean(
					R.styleable.BannerImageViewPager_android_adjustViewBounds,
					false);
			dotsGravity = a.getInt(
					R.styleable.BannerImageViewPager_android_gravity,
					Gravity.CENTER);
		} finally {
			// ����TypedArray���Ա��������
			a.recycle();
		}
		InitBannerView();
	}

	private void InitBannerView() {
		viewPager = new ViewPager(getContext());
		viewDots = new LinearLayout(getContext());
		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		addView(viewPager, lp);

		if (dotsBackground != null) {
			viewDots.setBackground(dotsBackground);
		}
		LayoutParams dotlp = new LayoutParams(LayoutParams.MATCH_PARENT,
				(int) dotsViewHeight);
		viewDots.setPadding((int) dotsSpacing, 0, 0, 0);
		viewDots.setGravity(dotsGravity);
		addView(viewDots, dotlp);
	}

	@SuppressWarnings("deprecation")
	public void setViewPagerViews(List<View> views) {
		this.views = views;
		dotsImageViews = new ImageView[views.size()];
		addDots(views.size());
		viewPager.setAdapter(new BannerViewPagerAdapter(views, scaleType,
				adjustViewBounds));

		viewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						// TODO Auto-generated method stub
						super.onPageSelected(position);
						index = position;
						switchToDot(index);
					}

					private void switchToDot(int index) {
						if (tempImageView != null) {
							tempImageView.setImageDrawable(dotsBlurImage);
						}
						dotsImageViews[index].setImageDrawable(dotsFocusImage);
						tempImageView = dotsImageViews[index];
					}
				});

		viewPager.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// ÿ�ν���onTouch�¼�����¼��ǰ�İ��µ�����
				curP.x = event.getX();
				curP.y = event.getY();

				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					// �мǲ����� downP = curP �������ڸı�curP��ʱ��downPҲ��ı�
					downP.x = event.getX();
					downP.y = event.getY();

				case MotionEvent.ACTION_MOVE:
					isContinue = false;
					break;

				case MotionEvent.ACTION_UP:
					isContinue = true;
					// ��upʱ�ж��Ƿ��º����ֵ�����Ϊһ����
					// �����һ���㣬��ִ�е���¼����������Լ�д�ĵ���¼���������onclick
					if (downP.x == curP.x && downP.y == curP.y) {
						mListener.onSingleTouch(index);
						return true;
					}
					break;

				default:
					isContinue = true;
					break;
				}
				return false;
			}
		});
		new Thread(this).start();
	}

	private void addDots(int size) {
		for (int i = 0; i < size; i++) {
			ImageView imageView = new ImageView(getContext());
			LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT);
			params.setMargins(0, 0, (int) dotsSpacing, 0);
			imageView.setLayoutParams(params);
			dotsImageViews[i] = imageView;
			// Ĭ��ѡ�е��ǵ�һ��ͼƬ����ʱ��һ��СԲ����ѡ��״̬����������
			if (i == 0) {
				dotsImageViews[i].setImageDrawable(dotsFocusImage);
				tempImageView = dotsImageViews[i];
			} else {
				dotsImageViews[i].setImageDrawable(dotsBlurImage);
			}
			viewDots.addView(dotsImageViews[i]);
		}
	}

	@Override
	public void run() {
		while (autoChange) {
			if (isContinue) {
				pageHandler.sendEmptyMessage(index);
				index = (index + 1) % views.size();
				try {
					Thread.sleep(changeInterval);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	Handler pageHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			viewPager.setCurrentItem(msg.what);
			super.handleMessage(msg);
		}
	};

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		View child = this.getChildAt(0);
		child.layout(0, 0, getWidth(), getHeight());
		if (changed) {
			child = this.getChildAt(1);
			child.measure(r - l, (int) dotsViewHeight);
			child.layout(0, getHeight() - (int) dotsViewHeight, getWidth(),
					getHeight());
		}
	}

	public void setOnSingleTouchListener(OnSingleTouchListener mListener) {
		this.mListener = mListener;
	}

	public interface OnSingleTouchListener {
		public void onSingleTouch(int position);
	}

	/**
	 * �����ֻ��ķֱ��ʴ� dp �ĵ�λ ת��Ϊ px(����)
	 */
	public static int dp2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

}
