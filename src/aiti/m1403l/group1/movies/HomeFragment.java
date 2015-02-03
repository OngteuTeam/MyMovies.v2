package aiti.m1403l.group1.movies;

import java.util.ArrayList;
import java.util.Arrays;

import aiti.m1403l.group1.R;
import aiti.m1403l.group1.data.model.Film;
import aiti.m1403l.group1.movies.adapter.HomeListAdapter;
import aiti.m1403l.group1.utils.MyLoadingPopup;
import android.app.Fragment;
import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class HomeFragment extends Fragment {

	public HomeFragment() {
	}

	ListView lvHome;
	TextView tv;
	HomeFragment homefragment;
	Button btnClick;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.fragment_home, container,
				false);

		lvHome = (ListView) rootView.findViewById(R.id.lvHome);
//		MyLoadingPopup mlp = new MyLoadingPopup(this.getActivity());
		lvHome.setAdapter(new HomeListAdapter(this.getActivity()));

		lvHome.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Film filmItem = (Film) lvHome.getItemAtPosition(position);
				Intent intent = new Intent(getActivity(),
						FilmDetailsActivity.class);
				intent.putExtra("FILMID", filmItem.getId());
				startActivity(intent);

			}
		});
		
		return rootView;

	}

}