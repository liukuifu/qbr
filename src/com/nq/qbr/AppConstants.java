package com.nq.qbr;

import android.annotation.SuppressLint;

@SuppressLint("SdCardPath")
public class AppConstants {
    /** �����NG�� */
    public static int NG = 0;
    /** �����OK�� */
    public static int OK = 1;

	public static final int REQUEST_SIGNIN = 1;
	
	/** ������ϸ */
	public static final int REQUIEST_ORDER_DETAIL = 2;
	
	/** ����ͳ�� */
	public static final int REQUIEST_ORDER_STATISTICS = 1;
	
	/** �����б� */
	public static final int REQUIEST_ORDER_LIST = 1;
	
	/** ҩƷ�б� */
	public static final int REQUIEST_MEDICINE_LIST = 1;
	
	/** Activity����Code Error */
	public static final int ACTIVITY_RESULT_ERROR = 99;
	
	/** Activity����Code ��ȡ�����淵�� */
	public static final int ACTIVITY_RESULT_SETTLE = 1;
	
	/** Activity����Code ����Ƭ���淵�� */
	public static final int ACTIVITY_RESULT_PIC = 2;
	
	/** Activity����Code NG */
	public static final int ACTIVITY_RESULT_NG = 0;
	
	/** ���ñ���ͼƬ��С */
	public static final float SAVE_PIC_SIZE = 480.0f;
	
	/** ���ñ���ͼƬ��С */
	public static final float SAVE_PIC_SIZE_HEADER = 240.0f;
	
	/** ��������ͼƬ��С */
	public static final float SIMPLE_PIC_SIZE = 150.0f;
	
	/** ҩ���ͼ�����ű��� */
	public static final float ZOOM_FOR_DRUG_STORE_MAP = 16f;
	
	/** ·���滮�����ű��� */
	public static final float ZOOM_FOR_ROUTE_PLANE_MAP = 16f;
	
	/** �ָ���������*/
	public static final String SPLITER_COMMA = ",";
	
	/** �ָ���������*/
	public static final String SPLITER_SEMICOLON = ";";
	
	/** �ָ�����@��*/
	public static final String SPLITER_AT = "@";
	
	/** δ�충��״̬ */
	public final static String STATUS_NO_ACCEPTED_ORDER = "4";
	
	/** �����ж���״̬ */
	public final static String STATUS_SHIPPING_ORDER = "5";
	
	/** ��ɶ���״̬ */
	public final static String STATUS_COMPLETED_ORDER = "6";
	
	/** ����ȡ��״̬ */
	public final static String STATUS_ORDER_CANCEL = "7";
	
	/** �������º���Ҫˢ�� */
	public final static int UPDATE_AND_NO_GET_LIST = 0;
	
	/** �������º���Ҫˢ�� */
	public final static int UPDATE_AND_GET_LIST = 1;
	
	/** δ��¼ */
	public final static String NO_DATA = "δ��¼";
	
	/** ȡ�����ɣ������� */
	public final static String CANCEL_REASON_OTHER = "����";
	
	/** ����ѡ�񣨿�ʼ�� */
	public final static String DATE_CHOOSEN_START = "��ѡ����ʼ����";
	
	/** ����ѡ�񣨽����� */
	public final static String DATE_CHOOSEN_END = "��ѡ���������";
	
	/** �Ի���ť���� */
	public final static String DIALOG_BUTTON_TEXT_OK = "ȷ  ��";
	
	/** POI�ܱ�ҩ�����Key */
	public final static String POI_SEARCH_KEY_DRUG_STORE = "ҩ��";
	
	/** ����ͼǰ׺ */
	public final static String SAMPLE_PIC_PERFIX = "S_";
	
	/** ��Ƭ�ļ����ָ��� */
	public final static String PIC_FILE_SPLITER = "_";
	
	/** ���ò��Ž���ʱ���ݵĹؼ��� */
	public final static String DIAL_ACTIVITY_KEYWORD = "tel:";
	
	/** ��Ҫ����Ĵ����� */
	public final static int ERROR_MSG_20002 = 20002;
	
	/** ���ַ��� */
	public final static String STRING_EMPTY = "";
	
	/** Doubl�����ʼֵ */
	public final static Double DOUBLE_INIT_VALUE = 0.0;
	
	/** Integer�����ʼֵ */
	public final static Integer INTEGER_INIT_VALUE = 0;
	
	/** ��Ұ�App��־λ */
	public final static int APP_FLAG_BUYER = 1;
	/** ��Ұ�汾�ļ� */
	public final static String APP_XML_BUYER = "/AppVersionBuyer.xml";
	/** ��Ұ�APK�ļ� */
	public final static String APP_APK_BUYER = "qbrapp.apk";
	/** ��Ұ�֪ͨ��̧ͷ */
	public final static String APP_NOTI_TITLE_BUYER = "Go Online download start";
	
	/** ���Ұ�App��־λ */
	public final static int APP_FLAG_SELLER = 2;
	/** ���Ұ�汾�ļ� */
	public final static String APP_XML_SELLER = "/AppVersionSeller.xml";
	/** ���Ұ�APK�ļ� */
	public final static String APP_APK_SELLER = "qbrappseller.apk";
	/** ���Ұ�֪ͨ��̧ͷ */
	public final static String APP_NOTI_TITLE_SELLER = "Go Online download start";
	
	/** ����Ա��App��־λ */
	public final static int APP_FLAG_DELIVERY = 3;
	/** ����Ա��汾�ļ� */
	public final static String APP_XML_DELIVERY = "/AppVersionDelivery.xml";
	/** ����Ա��APK�ļ� */
	public final static String APP_APK_DELIVERY = "qbrapp_delivery.apk";
	/** ����Ա��֪ͨ��̧ͷ */
	public final static String APP_NOTI_TITLE_DELIVERY = "Go Online download start";
	
	/** ����APK�����ر���·�� */
	public final static String DOWNLOAD_APK_SAVE_PATH = "/sdcard/qbr/update/";
	
	/** ������ */
	public final static String DOWNLOADING = "������...";
	/** ������� */
	public final static String DOWNLOADED = "�������";
	
	/** �û���Ϣ */
	public static final String KEY_USER_INFO = "userInfo";
	/** �û�IDS */
	public static final String KEY_USER_INFO_IDS = "userInfo_ids";
	/** �û�ID */
	public static final String KEY_USER_INFO_USER_IDS = "userInfo_user_ids";
	/** �û����� */
	public static final String KEY_USER_INFO_USER_NM = "userInfo_user_nm";
	/** �û��绰 */
	public static final String KEY_USER_INFO_TELEPHONE = "userInfo_telephone";
	/** �û����� */
	public static final String KEY_USER_INFO_USER_TYPE = "userInfo_user_type";
	/** �û��������� */
	public static final String KEY_USER_INFO_BIRTHDAY = "userInfo_birthday";
	/** �û��Ա� */
	public static final String KEY_USER_INFO_SEX = "userInfo_sex";
	/** �û����� */
	public static final String KEY_USER_INFO_PASSWORD = "userInfo_password";
	/** �û�ͷ�� */
	public static final String KEY_USER_INFO_PICTURE = "userInfo_picture";
	/** �û����� */
	public static final String KEY_USER_INFO_BELONG = "userInfo_belong";
	/** �������� */
	public static final String KEY_USER_INFO_DELI_AREA = "userInfo_deli_area";
	/** ���°����� */
	public static final String KEY_USER_INFO_DUTY_STATUS = "userInfo_duty_status";
	/** �ٶ�ID */
	public static final String KEY_USER_INFO_BAIDU_ID = "userInfo_baidu_id";
	/** �ٶ�ͨ��ID */
	public static final String KEY_USER_INFO_BAIDU_CHANNEL_ID = "userInfo_baidu_channel_id";
	/** �ٶ�TAG */
	public static final String KEY_USER_INFO_BAIDU_TAG = "userInfo_baidu_tag";
	/** �Ƿ��Ѿ����˰ٶ�ID�ı�־ */
	public static final String KEY_USER_INFO_IS_BD_BINDED = "userInfo_is_bd_binded";
	/** �Ƿ��Ѿ���¼�ı�־ */
	public static final String KEY_USER_INFO_LOGIN_FLAG = "userInfo_loginFlag";
	
	/** ������Ϣ */
	public static final String KEY_AREA_INFO = "areaInfo";
	/** ������� */
	public static final String KEY_AREA_INFO_CITY = "area_city";
	/** �������� */
	public static final String KEY_AREA_INFO_NAME = "area_nm";
    /** �û���� */
	public static final String USER_TYPE_DELIVERY = "2";
	
	/** pushMessageInfo */
	public static final String KEY_PUSH_MESSAGE = "pushMessageInfo";
	/** pushMessageInfo_flag */
	public static final String KEY_PUSH_MESSAGE_FLAG = "pushMessageInfo_flag";
}