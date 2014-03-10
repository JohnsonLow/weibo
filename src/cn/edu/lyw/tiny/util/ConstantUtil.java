package cn.edu.lyw.tiny.util;

import weibo4j.util.WeiboConfig;

/**
 * @version 1.0
 * 
 */
public interface ConstantUtil {

	/** 新浪微博的APP KEY */
	public static String CONSUMER_KEY = WeiboConfig.getValue("client_ID");//TODO 调整

	/** 新浪微博的APP SECRET */
	public static String CONSUMER_SECRET = WeiboConfig.getValue("client_SERCRET");
	/**
	 * 权限
	 */
	public static final String SCOPE = 
	            "email,direct_messages_read,direct_messages_write,"
	            + "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
	            + "follow_app_official_microblog," + "invitation_write";

	/** 新浪微博内容的最大长度 */
	public static final int WEIBO_MAX_LENGTH = 140;

	/** 使用的SharedPreference的名称 */
	public static final String TINYWEIBO = "TineyWeibo";

	/** 新浪微博授权之后的回调地址，和开放平台中应用填写的回调地址一致 */
	public static final String REDIRECT_URL =  WeiboConfig.getValue("redirect_URI");
	
	/** 新浪微博手机注册地址 */
	public static final String REGISTER_URL = "http://3g.sina.com.cn/prog/wapsite/sso/register.php?vt=3&revalid=2&ns=1&type=m&fw=1&UA=Mozilla&m=mJ402p4K6x89&mCnt=2";

	/** 讯飞语音的APP KEY URL：http://open.voicecloud.cn/developer.php */
	public static final String MSC_KEY = "50d19dae";

	/** aviary的APP KEY URL：http://www.aviary.com/dashboard/apps */
	public static final String AVIARY_KEY = "RZXgRSkNg0WxHA_jkUPJZQ";// a402c5831

	/** 开发者的新浪微博账号ID */
	public static final String AUTHOR_UID = "1752877703";

	/** 意见反馈的内容前缀 */
	public static final String SUGGESTION_PREFIX = "@csuitelite #微微博Android客户端# 版本 1.0 ";

	/* Activity之间传递值 */

	/** 微博ID */
	public static final String STATUS_ID = "statusid";

	/** 图片的URI */
	public final String IMAGEURI = "imageuri";

	/** 图片的存储地址 */
	public final String IMAGEPATH = "imagepath";

	/** 图片类型：用户头像，微博图片，缓存照片 */
	public final String PICTURE_TYPE = "picture_type";

	/** 微博列表类型：公共微博，好友微博，我的微博，提到我的 */
	public final String TIMELINE_TYPE = "timeline_type";

	/** 微博类型：音乐，图片，视频 */
	public final String WEIBO_TYPE = "weibo_type";

	/** 摇微博的类型：摇周边动态，摇附近好友，摇好友微博 */
	public final String SHAKE_TYPE = "shake_type";

	/** 写微博的类型：发微博，意见反馈 */
	public final String WRITE_WEIBO_TYPE = "write_weibo_type";

	/* Activity请求码 */

	/** 请求微博列表类型 */
	public static final int REQUEST_WEIBO_TYPE = 0x0;

	/** 请求图片类型 */
	public static final int REQUEST_PICTURE_TYPE = 0x1;

	/** 请求拍照 */
	public static final int REQUEST_TAKE_PICTURE = 0x2;

	/** 请求选择图片 */
	public static final int REQUEST_PICK_PICTURE = 0x3;

	/** 请求打开Aviary进行图片处理 */
	public static final int REQUEST_AVIARY_FEATURE = 0x4;

	/** 请求插入图片写微博的时候 */
	public static final int REQUEST_WEIBOWRITE_IMAGE = 0x5;

	/* 微博列表类型 */

	/** 公共微博 */
	public static final int TIMELINE_PUBLIC = 0x10;

	/** 好友微博 */
	public static final int TIMELINE_FRIENDS = 0x11;

	/** 我的微博 */
	public static final int TIMELINE_USER = 0x12;

	/** 提到我的 */
	public static final int TIMELINE_MENTIONS = 0x13;

	/* Handler处理的Message */

	/** 处理完用户数据的message */
	public final int MESSAGE_TYPE_USERDATA = 0x20;

	/** 处理完微博列表的message */
	public final int MESSAGE_TYPE_TIMELINE = 0x21;

	/** 加载完微博图片的message */
	public final int MESSAGE_TYPE_WEIBOIMAGE = 0x22;

	/** 加载完用户信息界面的message */
	public final int MESSAGE_TYPE_USERINFO = 0x23;

	/** 处理完摇微博的message */
	public final int MESSAGE_TYPE_SHAKE_STATUS = 0x24;

	/** 处理完摇附近好友的message */
	public final int MESSAGE_TYPE_SHAKE_USER = 0x25;

	/* 图片的类型 */

	/** 缓存处理图片 */
	public final int IMAGE_TYPE_PICTURE = 0x30;

	/** 微博用户头像 */
	public final int IMAGE_TYPE_PROFILE = 0x31;

	/** 微博内容中的图片 */
	public final int IMAGE_TYPE_IMAGE = 0x32;

	/* 任务的类型 */
	/** 任务列表*/
	public static final int MISSION_TYPE_LIST = 0x40;
	/* 微博类型值 */

	/** 音乐微博 */
	public final int WEIBO_TYPE_MUSIC = 0x50;

	/** 图片微博 */
	public final int WEIBO_TYPE_PICTURE = 0x51;

	/** 视频微博 */
	public final int WEIBO_TYPE_VIDEO = 0x52;

	/* 写微博的类型 */

	/** 发微博 */
	public final int WRITE_WEIBO_TYPE_WRITEWEIBO = 0x61;

	/** 意见反馈 */
	public final int WRITE_WEIBO_TYPE_SUGGESTION = 0x62;
	
	/** 发送任务绑定的请求*/
	public final int REQUEST_ACCOUNT_BIND = 0x70;

}
