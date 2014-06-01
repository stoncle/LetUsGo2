package com.superman.letusgo.base;

import android.os.Bundle;

import com.superman.letusgo.model.Actor;
import com.superman.letusgo.ui.UiLogin;

public class BaseUiAuth extends BaseFragment {
	

	
	protected static Actor actor = null;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if (!BaseAuth.isLogin()) {
			((BaseUi) getActivity()).forward(UiLogin.class);
			this.onStop();
		} else {
			actor = BaseAuth.gerActor();
		}
	}

}