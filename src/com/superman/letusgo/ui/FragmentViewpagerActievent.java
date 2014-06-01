package com.superman.letusgo.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.superman.letusgo.R;
import com.superman.letusgo.base.BaseFragment;

public class FragmentViewpagerActievent extends BaseFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.fragment_viewpager_my_actievent, container,false);
		
		return rootView;
	}

}
