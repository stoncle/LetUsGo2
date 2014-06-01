package com.superman.letusgo.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.superman.letusgo.R;
import com.superman.letusgo.base.BaseUiAuth;

public class FragmentViewpagerUserinfo extends BaseUiAuth {
	
//	private Actor actor;

	private ImageButton mProfileEdit;
	private TextView mAccountText;
	private TextView mTelText;
	private TextView mAgeText;
	private TextView mSexText;
	private TextView mLocationText;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		
		View rootView = inflater.inflate(
				R.layout.fragment_viewpager_userinfo,
				container, false);
		
		findViewById(rootView);
		setText();//设置控件显示内容
		
		mProfileEdit = (ImageButton) rootView.findViewById(R.id.profile_edit);
		mProfileEdit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				//切换fragment
				transition(R.id.content_frame, new FragmentProfileUserinfo());
			}
		});
		
		
		return rootView;
	}
	/*
	 * 设置界面元素的内容
	 */
	private void setText() {
		mAccountText.setText(actor.getName());
		mTelText.setText(actor.getTel());
		mAgeText.setText(actor.getAge());
		mSexText.setText(actor.getSex());
		mLocationText.setText(actor.getLoc());
	}
	/*
	 * findViewById()
	 */
	private void findViewById(View rootView) {
		mAccountText = (TextView) rootView.findViewById(R.id.textview_account);
		mTelText = (TextView) rootView.findViewById(R.id.textview_phonenumber);
		mAgeText = (TextView) rootView.findViewById(R.id.textview_age);
		mSexText = (TextView) rootView.findViewById(R.id.textview_sex);
		mLocationText = (TextView) rootView.findViewById(R.id.textview_location);
		
	}
}