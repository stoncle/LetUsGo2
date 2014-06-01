package com.superman.letusgo.base;

public final class C {
	
	////////////////////////////////////////////////////////////////////////////////////////////////
	// core settings (important)
	
	public static final class dir {
		public static final String base				= "/sdcard/demos";
		public static final String faces				= base + "/faces";
		public static final String images			= base + "/images";
	}
	
	public static final class api {
		public static final String base					= "http://www.uniqueguoqi.com:4000";
		public static final String login					= "/account/login";//登陆
		public static final String register				= "/account/reg";//注册
		public static final String verify				= "/account/verify";//短信验证
		public static final String profile_userinfo		= "/account/profile";//修改个人信息
		public static final String modify_pwd			= "/account/pwd";//修改密码
		public static final String avatar				= "/account/avatar";//上传头像
		public static final String get_account			= "/account/info？uid=";//获取指定用户信息
		public static final String launch_activity		= "/activity/release";//发布新活动
		public static final String upload_acti_image		= "/activity/upload";//上传介绍图片
		public static final String profile_activity		= "/activity/profile";//修改活动信息
		public static final String delete_activity			= "/activity/delete";//删除活动
		public static final String near_activity			= "/activity/near?";//获取附近活动列表
		public static final String list_activity			= "/activity/list?s=";//获取活动列表
		public static final String search_activity		= "/activity/search?q=";//搜索活动
		public static final String get_actor				= "/activity/participants?aid=";//获取活动参与者信息
		public static final String join_activity			= "/activity/join";//参加活动
		public static final String like_activity			= "/activity/voteup";//赞活动
		public static final String get_comment			= "/review/list?";//获取指定活动的评论信息
		public static final String comment_activity		= "/review/release";//评论指定的活动
	}
	
	public static final class task {
		public static final int login					= 1001;
		public static final int register					= 1002;
		public static final int verify					= 1003;
		public static final int profile_userinfo			= 1004;
		public static final int modify_pwd				= 1005;
		public static final int avatar					= 1006;
		public static final int get_account				= 1007;
		public static final int launch_activity			= 1008;
		public static final int upload_acti_image		= 1009;
		public static final int profile_activity			= 1010;
		public static final int delete_activity			= 1011;
		public static final int near_activity				= 1012;
		public static final int list_activity				= 1013;
		public static final int get_actor				= 1014;
		public static final int join_activity				= 1015;
		public static final int like_activity				= 1015;
		public static final int get_comment			= 1016;
		public static final int comment_activity			= 1017;
	}
	
	public static final class err {
		public static final String network			= "网络错误";
		public static final String message			= "消息错误";
		public static final String jsonFormat		= "消息格式错误";
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////
	// intent & action settings
	
	public static final class intent {
		public static final class action {
			public static final String EDITTEXT			= "com.app.demos.EDITTEXT";
			public static final String EDITBLOG		= "com.app.demos.EDITBLOG";
		}
	}
	
	public static final class action {
		public static final class edittext {
			public static final int CONFIG				= 2001;
			public static final int COMMENT			= 2002;
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////
	// additional settings
	
	public static final class web {
		public static final String base				= "http://192.168.1.2:8002";
		public static final String index				= base + "/index.php";
		public static final String gomap			= base + "/gomap.php";
	}
}