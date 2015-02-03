package aiti.m1403l.group1.movies.adapter;

import java.util.ArrayList;

import aiti.m1403l.group1.R;
import aiti.m1403l.group1.data.model.Film;
import aiti.m1403l.group1.movies.adapter.HomeListAdapter.ViewHolder;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class FilmByCategoryAdapter extends BaseAdapter  {

	
	private ArrayList<Film> listFilmByCategory;
	private LayoutInflater layoutInflater;

	public FilmByCategoryAdapter(Context context,ArrayList<Film> listFilmByCategory){
		this.listFilmByCategory = listFilmByCategory;
		layoutInflater = LayoutInflater.from(context);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listFilmByCategory.size();

	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listFilmByCategory.get(position);
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

		holder.name.setText(( listFilmByCategory.get(position)).getName());
		holder.duration.setText(( listFilmByCategory.get(position)).getDuration());

		return convertView;
	}
	
	static class ViewHolder {
		TextView name;
		TextView duration;
	}


	

}
