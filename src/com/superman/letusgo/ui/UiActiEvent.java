package com.superman.letusgo.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnLastItemVisibleListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.State;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.extras.SoundPullEventListener;
import com.superman.letusgo.R;
import com.superman.letusgo.base.BaseFragment;
import com.superman.letusgo.base.BaseHandler;
import com.superman.letusgo.base.BaseMessage;
import com.superman.letusgo.base.BaseTask;
import com.superman.letusgo.base.BaseUi;
import com.superman.letusgo.base.C;
import com.superman.letusgo.list.ActiList;
import com.superman.letusgo.model.ActiEvent;
import com.superman.letusgo.model.Actor;
import com.superman.letusgo.model.OtherActor;
import com.superman.letusgo.util.AppCache;
import com.superman.letusgo.util.TimeUtil;

public class UiActiEvent extends BaseFragment {

	private ActiList mAdapter;
	private ArrayList<ActiEvent> mListItems;
	private PullToRefreshListView mPullRefreshListView;
	
	private ImageButton mBtnLaunchActi;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setHandler(new ActiEventHandler(this));
	}
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_acti_event,
				container, false);
		mPullRefreshListView = (PullToRefreshListView) rootView.findViewById(R.id.acti_event_ietm_listview);
		mBtnLaunchActi = (ImageButton)rootView.findViewById(R.id.launch_acti_event_btn);
		mBtnLaunchActi.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_VIEW);
//				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
				intent.setClass(getActivity(), UiLaunchActi.class);
				startActivity(intent);
			}
			
		});
		// Set a listener to be invoked when the list should be
		// refreshed.
		mPullRefreshListView
				.setOnRefreshListener(new OnRefreshListener<ListView>() {
					@Override
					public void onRefresh(
							PullToRefreshBase<ListView> refreshView) {
						String label = DateUtils
								.formatDateTime(getActivity().getApplicationContext(),
										System.currentTimeMillis(),
										DateUtils.FORMAT_SHOW_TIME
												| DateUtils.FORMAT_SHOW_DATE
												| DateUtils.FORMAT_ABBREV_ALL);

						// Update the LastUpdatedLabel
						refreshView.getLoadingLayoutProxy()
								.setLastUpdatedLabel(
										label);

						// Do work to refresh the list
						// here.
						new GetDataTask().execute();
					}
				});

		// Add an end-of-list listener
		mPullRefreshListView
				.setOnLastItemVisibleListener(new OnLastItemVisibleListener() {

					@Override
					public void onLastItemVisible() {
						Toast.makeText(getActivity(),
								"End of List!",
								Toast.LENGTH_SHORT)
								.show();
					}
				});

		ListView actualListView = mPullRefreshListView
				.getRefreshableView();

		// Need to use the Actual ListView when registering for Context
		// Menu
		registerForContextMenu(actualListView);


		

		/**
		 * Add Sound Event Listener
		 */
		SoundPullEventListener<ListView> soundListener = new SoundPullEventListener<ListView>(
				getActivity());
		soundListener.addSoundEvent(State.PULL_TO_REFRESH,
				R.raw.pull_event);
		soundListener.addSoundEvent(State.RESET, R.raw.reset_sound);
		soundListener.addSoundEvent(State.REFRESHING,
				R.raw.refreshing_sound);
		mPullRefreshListView.setOnPullEventListener(soundListener);

		// You can also just use setListAdapter(mAdapter) or
		// mPullRefreshListView.setAdapter(mAdapter)
		actualListView.setAdapter(mAdapter);
		HashMap<String, String> actiParams = new HashMap<String, String>();
		Float l = new Float(23.23);
		Float b = new Float(21.23);
		actiParams.put("l", l.toString());
		actiParams.put("b", b.toString());
		this.doTaskAsync(C.task.near_activity, C.api.near_activity, actiParams);

		return rootView;
	}

	@Override
	public void onTaskComplete(int taskId, BaseMessage message) {
		// TODO Auto-generated method stub
		super.onTaskComplete(taskId, message);
		switch (taskId) {
		
		case C.task.near_activity:
			try {
				@SuppressWarnings("unchecked")
				final ArrayList<ActiEvent> actiList = (ArrayList<ActiEvent>) message.getResultList("result");
				mAdapter = new ActiList(this.getContext(), actiList);
			} catch(Exception e) {
				e.printStackTrace();
			}
			break;
	}
	}

	private class GetDataTask extends AsyncTask<Void, Void, String[]> {

		@Override
		protected String[] doInBackground(Void... params) {
			// Simulates a background job.
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
			}
			return new String[1];
		}

		@Override
		protected void onPostExecute(String[] result) {
			mAdapter.notifyDataSetChanged();

			// Call onRefreshComplete when the list has been
			// refreshed.
			mPullRefreshListView.onRefreshComplete();

			super.onPostExecute(result);
		}
	}
	private class ActiEventHandler extends BaseHandler {
		public ActiEventHandler(BaseFragment fragment) {
			super(fragment);
		}
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			try {
//				switch (msg.what) {
//					case BaseTask.LOAD_IMAGE:
//						Bitmap face = AppCache.getImage(faceImageUrl);
//						faceImage.setImageBitmap(face);
//						break;
//				}
			} catch (Exception e) {
				e.printStackTrace();
				ui.toast(e.getMessage());
			}
		}
	}

}
