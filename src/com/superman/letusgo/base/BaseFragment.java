package com.superman.letusgo.base;

import java.util.HashMap;

import com.superman.letusgo.util.AppCache;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.SimpleAdapter.ViewBinder;

public class BaseFragment extends Fragment {
	protected BaseApp app;
	protected BaseHandler handler;
	protected BaseTaskPool taskPool;

	private FragmentManager fragmentManager;
	private FragmentTransaction fragmentTransaction;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = super.onCreateView(inflater, container,
				savedInstanceState);
		fragmentManager = getFragmentManager();
		fragmentTransaction = fragmentManager.beginTransaction();

		// async task handler
		this.handler = new BaseHandler((BaseUi) getActivity());
		// init task pool
		/*
		 * 在BaseUi中初始化线程池，即在登陆中就初始化了线程池
		 */
		this.taskPool = new BaseTaskPool((BaseUi) getActivity());
		// init application
		this.app = (BaseApp) getActivity().getApplicationContext();
		return view;
	}

	// //////////////////////////////////////////////////////////////////////////////////////////////
	// util method
	public void toast(String msg) {
		Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
	}

	public void transition(int id, Fragment fragment) {
		fragmentTransaction.replace(id, fragment);
//		fragmentTransaction.addToBackStack(null);
		fragmentTransaction.commit();
	}
	
	public void transition(int id, Fragment fragment, Bundle params) {
		
		fragment.setArguments(params);
		fragmentTransaction.replace(id, fragment);
//		fragmentTransaction.addToBackStack(null);
		fragmentTransaction.commit();
	}

	public void forward(Class<?> classObj) {
		Intent intent = new Intent();
		intent.setClass(getActivity(), classObj);
		/*
		 * FLAG_ACTIVITY_CLEAR_TOP
		 * 当打开classobj时，该进程中的所有已经打开的activity都会被清除，
		 * 相当于现在堆栈中就这一个classObj的activity，其他的都被清除了。
		 */
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		this.startActivity(intent);
		getActivity().finish();
	}
	
	
	public Context getContext() {
		return getActivity();
	}

	public BaseHandler getHandler() {
		return this.handler;
	}

	public void setHandler(BaseHandler handler) {
		this.handler = handler;
	}
	
	/*
	 * 对于一个没有没载入或者想要动态载入的界面，都需要使用layoutinflate来载入
	 * 对于一个已经载入的Activity，可以使用实现了Activity的findviewbyid方法来获得其中的界面元素
	 * 这就是inflate的作用
	 */
	public LayoutInflater getLayout() {
		return (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	/*
	 * 按id来加载某个布局文件
	 */
	public View getLayout(int layoutId) {
		return getLayout().inflate(layoutId, null);
	}

	/*
	 * 获得布局中的界面元素
	 */
	public View getLayout(int layoutId, int itemId) {
		return getLayout(layoutId).findViewById(itemId);
	}

	public BaseTaskPool getTaskPool() {
		return this.taskPool;
	}

	public void loadImage(final String url) {
		taskPool.addTask(0, new BaseTask() {

			@Override
			public void onComplete() {
				AppCache.getCachedImage(getContext(), url);
				sendMessage(BaseTask.LOAD_IMAGE);
			}
		}, 0);
	}

	// //////////////////////////////////////////////////////////////////////////////////////////////
	// logic method

	public void doFinish() {
		getActivity().finish();
	}
	
	
	/*
	 * 退出登陆
	 */
	public void doLogout() {
		BaseAuth.setLogin(false);
	}

	public void sendMessage(int what) {
		Message m = new Message();
		m.what = what;
		handler.sendMessage(m);
	}

	/*
	 * 发送带数据的message，由handleMessage来处理
	 */
	public void sendMessage(int what, String data) {
		Bundle b = new Bundle();
		b.putString("data", data);
		Message m = new Message();
		m.what = what;
		m.setData(b);
		/*
		 * sendMessage发动封装好的message，由handleMessage做处理。
		 */
		handler.sendMessage(m);
	}

	/*
	 * 让线程池中为taskid的任务线程来处理
	 */
	public void sendMessage(int what, int taskId, String data) {
		Bundle b = new Bundle();
		b.putInt("task", taskId);
		b.putString("data", data);
		Message m = new Message();
		m.what = what;
		m.setData(b);
		handler.sendMessage(m);
	}
	
	public void doTaskAsync(int taskId, int delayTime) {
		taskPool.addTask(taskId, new BaseTask() {
			/*
			 * (non-Javadoc)
			 * @see com.app.demos.base.BaseTask#onComplete()
			 * 重写父类的onComplete方法，进行消息的发送
			 */
			@Override
			public void onComplete() {
				sendMessage(BaseTask.TASK_COMPLETE,
						this.getId(), null);
			}

			@Override
			public void onError(String error) {
				sendMessage(BaseTask.NETWORK_ERROR,
						this.getId(), null);
			}
		}, delayTime);
	}

	public void doTaskAsync(int taskId, BaseTask baseTask, int delayTime) {
		taskPool.addTask(taskId, baseTask, delayTime);
	}

	public void doTaskAsync(int taskId, String taskUrl) {
		// showLoadBar();
		taskPool.addTask(taskId, taskUrl, new BaseTask() {
			@Override
			public void onComplete(String httpResult) {
				sendMessage(BaseTask.TASK_COMPLETE,
						this.getId(), httpResult);
			}

			@Override
			public void onError(String error) {
				sendMessage(BaseTask.NETWORK_ERROR,
						this.getId(), null);
			}
		}, 0);
	}

	public void doTaskAsync(int taskId, String taskUrl,
			HashMap<String, String> taskArgs) {
		// showLoadBar();
		taskPool.addTask(taskId, taskUrl, taskArgs, new BaseTask() {
			@Override
			public void onComplete(String httpResult) {
//				toast(httpResult);
				sendMessage(BaseTask.TASK_COMPLETE,
						this.getId(), httpResult);
			}

			@Override
			public void onError(String error) {
				sendMessage(BaseTask.NETWORK_ERROR,
						this.getId(), null);
			}
		}, 0);
	}

	public void onTaskComplete(int taskId, BaseMessage message) {

	}

	public void onTaskComplete(int taskId) {

	}

	public void onNetworkError(int taskId) {
		toast(C.err.network);
	}

	// //////////////////////////////////////////////////////////////////////////////////////////////
	// debug method

	/*public void debugMemory(String tag) {
		if (this.showDebugMsg) {
			Log.w(this.getClass().getSimpleName(), tag + ":"
					+ AppUtil.getUsedMemory());
		}
	}*/

	// //////////////////////////////////////////////////////////////////////////////////////////////
	// common classes

	public class BitmapViewBinder implements ViewBinder {
		//
		@Override
		public boolean setViewValue(View view, Object data,
				String textRepresentation) {
			if ((view instanceof ImageView)
					& (data instanceof Bitmap)) {
				ImageView iv = (ImageView) view;
				Bitmap bm = (Bitmap) data;
				iv.setImageBitmap(bm);
				return true;
			}
			return false;
		}
	}
	
	
}
