package com.superman.letusgo.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnLastItemVisibleListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.State;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.extras.SoundPullEventListener;
import com.superman.letusgo.R;
import com.superman.letusgo.base.BaseFragment;
import com.superman.letusgo.list.ActiList;
import com.superman.letusgo.model.ActiEvent;
import com.superman.letusgo.model.Actor;

public class UiActiEvent extends BaseFragment {

	private ActiList mAdapter;
	private ArrayList<ActiEvent> mListItems;
	private PullToRefreshListView mPullRefreshListView;
	
	private ImageButton mBtnLaunchActi;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_acti_event,
				container, false);
		ActiEvent acti = new ActiEvent(new Actor(), "title", (long)100000000, "place", "detail", 2, 1, new ArrayList<Actor>());
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

		mListItems = new ArrayList<ActiEvent>();
		mListItems.add(acti);
		mListItems.add(acti);
		mListItems.add(acti);

		mAdapter = new ActiList(this.getContext(), mListItems);

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

		return rootView;
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

}
