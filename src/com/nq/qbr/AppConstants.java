package com.nq.qbr;

import android.annotation.SuppressLint;

@SuppressLint("SdCardPath")
public class AppConstants {
    /** 结果（NG） */
    public static int NG = 0;
    /** 结果（OK） */
    public static int OK = 1;

	public static final int REQUEST_SIGNIN = 1;
	
	/** 订单详细 */
	public static final int REQUIEST_ORDER_DETAIL = 2;
	
	/** 订单统计 */
	public static final int REQUIEST_ORDER_STATISTICS = 1;
	
	/** 订单列表 */
	public static final int REQUIEST_ORDER_LIST = 1;
	
	/** 药品列表 */
	public static final int REQUIEST_MEDICINE_LIST = 1;
	
	/** Activity返回Code Error */
	public static final int ACTIVITY_RESULT_ERROR = 99;
	
	/** Activity返回Code 从取消画面返回 */
	public static final int ACTIVITY_RESULT_SETTLE = 1;
	
	/** Activity返回Code 从相片画面返回 */
	public static final int ACTIVITY_RESULT_PIC = 2;
	
	/** Activity返回Code NG */
	public static final int ACTIVITY_RESULT_NG = 0;
	
	/** 设置保存图片大小 */
	public static final float SAVE_PIC_SIZE = 480.0f;
	
	/** 设置保存图片大小 */
	public static final float SAVE_PIC_SIZE_HEADER = 240.0f;
	
	/** 设置缩略图片大小 */
	public static final float SIMPLE_PIC_SIZE = 150.0f;
	
	/** 药店地图的缩放比例 */
	public static final float ZOOM_FOR_DRUG_STORE_MAP = 16f;
	
	/** 路径规划的缩放比例 */
	public static final float ZOOM_FOR_ROUTE_PLANE_MAP = 16f;
	
	/** 分隔符“，”*/
	public static final String SPLITER_COMMA = ",";
	
	/** 分隔符“；”*/
	public static final String SPLITER_SEMICOLON = ";";
	
	/** 分隔符“@”*/
	public static final String SPLITER_AT = "@";
	
	/** 未领订单状态 */
	public final static String STATUS_NO_ACCEPTED_ORDER = "4";
	
	/** 配送中订单状态 */
	public final static String STATUS_SHIPPING_ORDER = "5";
	
	/** 完成订单状态 */
	public final static String STATUS_COMPLETED_ORDER = "6";
	
	/** 订单取消状态 */
	public final static String STATUS_ORDER_CANCEL = "7";
	
	/** 订单更新后不需要刷新 */
	public final static int UPDATE_AND_NO_GET_LIST = 0;
	
	/** 订单更新后需要刷新 */
	public final static int UPDATE_AND_GET_LIST = 1;
	
	/** 未登录 */
	public final static String NO_DATA = "未登录";
	
	/** 取消理由（其他） */
	public final static String CANCEL_REASON_OTHER = "其他";
	
	/** 日期选择（开始） */
	public final static String DATE_CHOOSEN_START = "请选择起始日期";
	
	/** 日期选择（结束） */
	public final static String DATE_CHOOSEN_END = "请选择结束日期";
	
	/** 对话框按钮文字 */
	public final static String DIALOG_BUTTON_TEXT_OK = "确  定";
	
	/** POI周边药店检索Key */
	public final static String POI_SEARCH_KEY_DRUG_STORE = "药店";
	
	/** 缩略图前缀 */
	public final static String SAMPLE_PIC_PERFIX = "S_";
	
	/** 照片文件名分隔符 */
	public final static String PIC_FILE_SPLITER = "_";
	
	/** 调用拨号界面时传递的关键字 */
	public final static String DIAL_ACTIVITY_KEYWORD = "tel:";
	
	/** 需要捕获的错误编号 */
	public final static int ERROR_MSG_20002 = 20002;
	
	/** 空字符串 */
	public final static String STRING_EMPTY = "";
	
	/** Doubl对象初始值 */
	public final static Double DOUBLE_INIT_VALUE = 0.0;
	
	/** Integer对象初始值 */
	public final static Integer INTEGER_INIT_VALUE = 0;
	
	/** 买家版App标志位 */
	public final static int APP_FLAG_BUYER = 1;
	/** 买家版版本文件 */
	public final static String APP_XML_BUYER = "/AppVersionBuyer.xml";
	/** 买家版APK文件 */
	public final static String APP_APK_BUYER = "qbrapp.apk";
	/** 买家版通知栏抬头 */
	public final static String APP_NOTI_TITLE_BUYER = "Go Online download start";
	
	/** 卖家版App标志位 */
	public final static int APP_FLAG_SELLER = 2;
	/** 卖家版版本文件 */
	public final static String APP_XML_SELLER = "/AppVersionSeller.xml";
	/** 卖家版APK文件 */
	public final static String APP_APK_SELLER = "qbrappseller.apk";
	/** 卖家版通知栏抬头 */
	public final static String APP_NOTI_TITLE_SELLER = "Go Online download start";
	
	/** 配送员版App标志位 */
	public final static int APP_FLAG_DELIVERY = 3;
	/** 配送员版版本文件 */
	public final static String APP_XML_DELIVERY = "/AppVersionDelivery.xml";
	/** 配送员版APK文件 */
	public final static String APP_APK_DELIVERY = "qbrapp_delivery.apk";
	/** 配送员版通知栏抬头 */
	public final static String APP_NOTI_TITLE_DELIVERY = "Go Online download start";
	
	/** 跟新APK的下载保存路径 */
	public final static String DOWNLOAD_APK_SAVE_PATH = "/sdcard/qbr/update/";
	
	/** 下载中 */
	public final static String DOWNLOADING = "下载中...";
	/** 下载完成 */
	public final static String DOWNLOADED = "下载完成";
	
	/** 用户信息 */
	public static final String KEY_USER_INFO = "userInfo";
	/** 用户IDS */
	public static final String KEY_USER_INFO_IDS = "userInfo_ids";
	/** 用户ID */
	public static final String KEY_USER_INFO_USER_IDS = "userInfo_user_ids";
	/** 用户姓名 */
	public static final String KEY_USER_INFO_USER_NM = "userInfo_user_nm";
	/** 用户电话 */
	public static final String KEY_USER_INFO_TELEPHONE = "userInfo_telephone";
	/** 用户类型 */
	public static final String KEY_USER_INFO_USER_TYPE = "userInfo_user_type";
	/** 用户出生日期 */
	public static final String KEY_USER_INFO_BIRTHDAY = "userInfo_birthday";
	/** 用户性别 */
	public static final String KEY_USER_INFO_SEX = "userInfo_sex";
	/** 用户密码 */
	public static final String KEY_USER_INFO_PASSWORD = "userInfo_password";
	/** 用户头像 */
	public static final String KEY_USER_INFO_PICTURE = "userInfo_picture";
	/** 用户所属 */
	public static final String KEY_USER_INFO_BELONG = "userInfo_belong";
	/** 配送区域 */
	public static final String KEY_USER_INFO_DELI_AREA = "userInfo_deli_area";
	/** 上下班区分 */
	public static final String KEY_USER_INFO_DUTY_STATUS = "userInfo_duty_status";
	/** 百度ID */
	public static final String KEY_USER_INFO_BAIDU_ID = "userInfo_baidu_id";
	/** 百度通道ID */
	public static final String KEY_USER_INFO_BAIDU_CHANNEL_ID = "userInfo_baidu_channel_id";
	/** 百度TAG */
	public static final String KEY_USER_INFO_BAIDU_TAG = "userInfo_baidu_tag";
	/** 是否已经绑定了百度ID的标志 */
	public static final String KEY_USER_INFO_IS_BD_BINDED = "userInfo_is_bd_binded";
	/** 是否已经登录的标志 */
	public static final String KEY_USER_INFO_LOGIN_FLAG = "userInfo_loginFlag";
	
	/** 区域信息 */
	public static final String KEY_AREA_INFO = "areaInfo";
	/** 区域城市 */
	public static final String KEY_AREA_INFO_CITY = "area_city";
	/** 区域名称 */
	public static final String KEY_AREA_INFO_NAME = "area_nm";
    /** 用户类别 */
	public static final String USER_TYPE_DELIVERY = "2";
	
	/** pushMessageInfo */
	public static final String KEY_PUSH_MESSAGE = "pushMessageInfo";
	/** pushMessageInfo_flag */
	public static final String KEY_PUSH_MESSAGE_FLAG = "pushMessageInfo_flag";
}