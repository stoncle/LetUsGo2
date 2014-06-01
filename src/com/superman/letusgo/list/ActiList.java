package com.superman.letusgo.list;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.superman.letusgo.R;
import com.superman.letusgo.base.BaseList;
import com.superman.letusgo.base.BaseUi;
import com.superman.letusgo.model.ActiEvent;
import com.superman.letusgo.util.AppCache;
import com.superman.letusgo.util.TimeUtil;

public class ActiList extends BaseList{

	private Context context;
	private LayoutInflater inflater;
	private ArrayList<ActiEvent> actiList;
	
	public final class ActiListItem {
		public ImageView avatar;
		public TextView host;
		public TextView title;
		public TextView time;
		public TextView place;
		public TextView parter;
		public TextView detail;
		public Button like;
		public Button some;
	}
	
	public ActiList (Context context, ArrayList<ActiEvent> actiList) {
		this.context = context;
		this.inflater = LayoutInflater.from(this.context);
		this.actiList = actiList;
	}
	
	@Override
	public int getCount() {
		return actiList.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int p, View v, ViewGroup parent) {
		// init tpl
		ActiListItem  actiItem = null;
		// if cached expired
		if (v == null) {
			v = inflater.inflate(R.layout.acti_event_listview_model, null);
			actiItem = new ActiListItem();
			actiItem.avatar = (ImageView) v.findViewById(R.id.img_avatar);
			actiItem.host = (TextView) v.findViewById(R.id.tv_host);
			actiItem.title = (TextView) v.findViewById(R.id.tv_acti_title);
			actiItem.time = (TextView) v.findViewById(R.id.tv_acti_time);
			actiItem.place = (TextView) v.findViewById(R.id.tv_acti_place);
			actiItem.parter = (TextView) v.findViewById(R.id.tv_acti_parter);
			actiItem.detail = (TextView) v.findViewById(R.id.tv_acti_detail);
			actiItem.like = (Button) v.findViewById(R.id.bt_like);
			actiItem.some = (Button) v.findViewById(R.id.bt_some);
			v.setTag(actiItem);
		} else {
			actiItem = (ActiListItem) v.getTag();
		}
		// fill data
		actiItem.host.setText(actiList.get(p).getHost().getName());
		// fill html data
		actiItem.title.setText(actiList.get(p).getTitle());
		actiItem.time.setText(TimeUtil.TimeStamp2Date(actiList.get(p).getTime(), "yyyy-MM-dd hh:mm:ss"));
		actiItem.place.setText(actiList.get(p).getPlace());
		actiItem.parter.setText(actiList.get(p).getActual_parter() + "/" + actiList.get(p).getActual_parter());
		actiItem.detail.setText(actiList.get(p).getDetail());
		// load face image
		String avatarUrl = actiList.get(p).getHost().getBigAvatar();
//		Bitmap faceImage = AppCache.getImage(avatarUrl);
//		if (faceImage != null) {
//			actiItem.avatar.setImageBitmap(faceImage);
//		}
		return v;
	}
}