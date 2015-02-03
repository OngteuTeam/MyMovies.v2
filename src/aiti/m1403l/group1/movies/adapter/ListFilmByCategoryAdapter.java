package aiti.m1403l.group1.movies.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import aiti.m1403l.group1.R;
import aiti.m1403l.group1.data.model.Film;
import aiti.m1403l.group1.data.orm.FilmORM;
import aiti.m1403l.group1.movies.HomeFragment;
import aiti.m1403l.group1.utils.MyLoadingPopup;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListFilmByCategoryAdapter extends BaseAdapter implements OnClickListener{

	private ArrayList<Film> listFilm;
	private LayoutInflater layoutInflater;
	
	public ListFilmByCategoryAdapter(Context context){
		this.listFilm = FilmORM.getList(context);
		layoutInflater = LayoutInflater.from(context);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listFilm.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listFilm.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if (convertView == null) {
			convertView = layoutInflater.inflate(R.layout.list_film_item, null);
			holder = new ViewHolder();
			holder.name = (TextView) convertView.findViewById(R.id.tvName);
			holder.duration = (TextView) convertView.findViewById(R.id.tvDuration);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.name.setText(( listFilm.get(position)).getName());
		holder.duration.setText(( listFilm.get(position)).getDuration());

		return convertView;
		
	}
	
	static class ViewHolder {
		TextView name;
		TextView duration;
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
