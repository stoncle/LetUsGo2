package com.superman.letusgo.model;

import com.superman.letusgo.base.BaseModel;

public class OtherActor extends BaseModel{
	// model columns
		public final static String COL_UID = "uid";
		public final static String COL_NAME = "name";
		public final static String COL_PWD = "pwd";
		
		public final static String COL_TEL = "tel";
		public final static String COL_SEX = "sex";
		public final static String COL_AGE = "age";
		public final static String COL_SMALL_AVATAR = "small_avatar";
		public final static String COL_BIG_AVATAR = "big_avatar";
		public final static String COL_LOC = "loc";
		
		private String uid;
		private String name;
		private String pwd;
		private String loc;
		private String tel;
		private String sex;
		private String age;
		private String small_avatar;
		private String big_avatar;
		
		public OtherActor(String uid, String name, String pwd, String loc,
				String tel, String sex, String age, String small_avatar,
				String big_avatar) {
			super();
			this.uid = uid;
			this.name = name;
			this.pwd = pwd;
			this.loc = loc;
			this.tel = tel;
			this.sex = sex;
			this.age = age;
			this.small_avatar = small_avatar;
			this.big_avatar = big_avatar;
		}
		public String getUid() {
			return uid;
		}
		public void setUid(String uid) {
			this.uid = uid;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getPwd() {
			return pwd;
		}
		public void setPwd(String pwd) {
			this.pwd = pwd;
		}
		public String getLoc() {
			return loc;
		}
		public void setLoc(String loc) {
			this.loc = loc;
		}
		public String getTel() {
			return tel;
		}
		public void setTel(String tel) {
			this.tel = tel;
		}
		public String getSex() {
			return sex;
		}
		public void setSex(String sex) {
			this.sex = sex;
		}
		public String getAge() {
			return age;
		}
		public void setAge(String age) {
			this.age = age;
		}
		public String getSmall_avatar() {
			return small_avatar;
		}
		public void setSmall_avatar(String small_avatar) {
			this.small_avatar = small_avatar;
		}
		public String getBig_avatar() {
			return big_avatar;
		}
		public void setBig_avatar(String big_avatar) {
			this.big_avatar = big_avatar;
		}
		
}
