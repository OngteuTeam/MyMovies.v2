package aiti.m1403l.group1.movies;

import java.util.ArrayList;

import aiti.m1403l.group1.R;
import aiti.m1403l.group1.R.id;
import aiti.m1403l.group1.R.layout;
import aiti.m1403l.group1.R.menu;
import aiti.m1403l.group1.data.model.Film;
import aiti.m1403l.group1.movies.adapter.ListFilmByCategoryAdapter;
import aiti.m1403l.group1.movies.adapter.HomeListAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class CategoryDetailsActivity extends Activity {

	ListView lvCates;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_category_details);
		ArrayList<Film> cates_detais = getListFilm();
		lvCates = (ListView)findViewById(R.id.lvCateDetails);
		lvCates.setAdapter(new ListFilmByCategoryAdapter(this));
        
		lvCates.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView parent, View view, int position, long id) {
					// TODO Auto-generated method stub
					Object obj = lvCates.getItemAtPosition(position);
//					Film filmData = (Film) obj;
					
					Intent intent = new Intent(CategoryDetailsActivity.this, FilmDetailsActivity.class);
					startActivity(intent);
					
				}
			});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.category, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	private ArrayList<Film> getListFilm() {
		ArrayList<Film> results = new ArrayList<Film>();
		Film filmdata = new Film();
		filmdata.setName("Stay");
		filmdata.setDuration("20:23");
		results.add(filmdata);

		filmdata = new Film();
		filmdata.setName("Maps");
		filmdata.setDuration("20:23");
		results.add(filmdata);

		filmdata = new Film();
		filmdata.setName("Wreking Ball");
		filmdata.setDuration("20:23");
		results.add(filmdata);

		filmdata = new Film();
		filmdata.setName("Rolling In The Deep");
		filmdata.setDuration("20:23");
		results.add(filmdata);

		filmdata = new Film();
		filmdata.setName("Some One Like You");
		filmdata.setDuration("20:23");
		results.add(filmdata);

		filmdata = new Film();
		filmdata.setName("Trouble Maker");
		filmdata.setDuration("20:23");
		results.add(filmdata);

		return results;
	}
}
