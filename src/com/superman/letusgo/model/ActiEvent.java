package com.superman.letusgo.model;

import java.util.ArrayList;

import com.superman.letusgo.base.BaseModel;

public class ActiEvent extends BaseModel{
	public static final String COL_AID = "aid";
	public static final String COL_HOST = "host";
	public static final String COL_TITLE = "title";
	public static final String COL_TIME = "time";
	public static final String COL_PLACE = "place";
	public static final String COL_DETAIL = "detail";
	public static final String COL_REQUIRED_PARTER = "required_parter";
	public static final String COL_ACTUAL_PARTER = "actual_parter";
	public static final String COL_PARTERLIST = "parterList";
	
	private int aid;
	private OtherActor host;
	private String title;
	private long time;
	private String place;
	private String detail;
	private int required_parter;
	private int actual_parter;
	private ArrayList<OtherActor> parterList;
	
	public ActiEvent(int aid, OtherActor host, String title, long time, String place,
			String detail, int required_parter, int actual_parter,
			ArrayList<OtherActor> parterList) {
		super();
		this.aid = aid;
		this.host = host;
		this.title = title;
		this.time = time;
		this.place = place;
		this.detail = detail;
		this.required_parter = required_parter;
		this.actual_parter = actual_parter;
		this.parterList = parterList;
	}
	public int getAid() {
		return aid;
	}
	public OtherActor getHost() {
		return host;
	}
	public void setHost(OtherActor host) {
		this.host = host;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public long getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public int getRequired_parter() {
		return required_parter;
	}
	public void setRequired_parter(int required_parter) {
		this.required_parter = required_parter;
	}
	public int getActual_parter() {
		return actual_parter;
	}
	public void setActual_parter(int actual_parter) {
		this.actual_parter = actual_parter;
	}
	public ArrayList<OtherActor> getParterList() {
		return parterList;
	}
	public void setParterList(ArrayList<OtherActor> parterList) {
		this.parterList = parterList;
	}
	
}
