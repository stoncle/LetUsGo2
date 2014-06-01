package com.superman.letusgo.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.superman.letusgo.R;
import com.superman.letusgo.base.BaseUiAuth;

public class FragmentProfileUserinfo extends BaseUiAuth {
	private EditText mAccountEdit;
	private EditText mSexEdit;
	private EditText mAgeEdit;
	private EditText mLocationEdit;
	private ImageView mBtnSubmit;
	
	private String mAccount;
	private String mSex;
	private String mAge;
	private String mLocation;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(
				R.layout.fragment_profile_userinfo, container,
				false);
		findViewById(rootView);
		setText();
//		getText();
		
		return rootView;
	}

	private void setText() {
		mAccountEdit.setText(actor.getName());
		mSexEdit.setText(actor.getSex());
		mAgeEdit.setText(actor.getAge());
		mLocationEdit.setText(actor.getLoc());
		
	}

	private void findViewById(View rootView) {
		mAccountEdit = (EditText) rootView.findViewById(R.id.edittext_profile_userinfo_new_account);
		mSexEdit = (EditText) rootView.findViewById(R.id.edittext_profile_userinfo_sex);
		mAgeEdit = (EditText) rootView.findViewById(R.id.edittext_profile_userinfo_age);
		mLocationEdit = (EditText) rootView.findViewById(R.id.edittext_profile_userinfo_location);
		
		mBtnSubmit = (ImageView) rootView.findViewById(R.id.btn_submit_profile_userinfo);
		
	}
	
	private void getText() {
		mAccount = mAccountEdit.getText().toString();
		mSex = mSexEdit.getText().toString();
		mAge = mAgeEdit.getTag().toString();
		mLocation = mLocationEdit.getText().toString();
		
	}
}
