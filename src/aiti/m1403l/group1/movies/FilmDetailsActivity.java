package aiti.m1403l.group1.movies;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubePlayer.Provider;

import aiti.m1403l.group1.R;
import aiti.m1403l.group1.R.id;
import aiti.m1403l.group1.R.layout;
import aiti.m1403l.group1.R.menu;
import aiti.m1403l.group1.data.model.Film;
import aiti.m1403l.group1.data.orm.FilmORM;
import aiti.m1403l.group1.utils.Defines;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RatingBar.OnRatingBarChangeListener;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class FilmDetailsActivity extends YouTubeFailureRecoveryActivity implements YouTubePlayer.OnFullscreenListener {

	private static final int PORTRAIT_ORIENTATION = Build.VERSION.SDK_INT < 9 ? ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
			: ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT;

	private LinearLayout baseLayout;
	private YouTubePlayerView playerView;
	private YouTubePlayer player;
	private View otherViews;

	private boolean fullscreen;
	
	Film fDetails = new Film();
	
	private TextView tvFilmName;
	
	RatingBar ratingBar;
	ImageButton btnBm;
	static int count=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_film_details);
		ActionBar actbar = getActionBar();
		actbar.setDisplayHomeAsUpEnabled(true);
		
		int receivedId = this.getIntent().getIntExtra("FILMID", 0);
		fDetails = FilmORM.getFilmById(this, receivedId);
		
		baseLayout = (LinearLayout) findViewById(R.id.details_layout);
		playerView = (YouTubePlayerView) findViewById(R.id.player_yt);
		otherViews = findViewById(R.id.other_detail_views);

		playerView.initialize(Defines.YOUTUBE_API_KEY, this);
		
		tvFilmName = (TextView) findViewById(R.id.tvFilmName);
		tvFilmName.setText(fDetails.getName());
		
		addListenerOnRatingBar();
		btnBm = (ImageButton)findViewById(R.id.btnBm);
		btnBm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				count++;
				if(count%2==1){
					btnBm.setBackground(getResources().getDrawable(R.drawable.ic_bm));
				}
				else btnBm.setBackground(getResources().getDrawable(R.drawable.ic_nonebm));
			}
		});

		doLayout();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.film_details, menu);
	
		return true;
	}
	
	public void addListenerOnRatingBar() {
		 
		ratingBar = (RatingBar) findViewById(R.id.rtFilm);
	 
		//if rating value is changed,
		//display the current rating value in the result (textview) automatically
		ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
			public void onRatingChanged(RatingBar ratingBar, float rating,
				boolean fromUser) {
				
	 
			}
		});
	  }
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
//		int id = item.getItemId();
//		if (id == R.id.action_settings) {
//			return true;
//		}
//		return super.onOptionsItemSelected(item);
//	
		switch (item.getItemId()){
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onInitializationSuccess(YouTubePlayer.Provider provider,
			YouTubePlayer player, boolean wasRestored) {
		this.player = player;
		setMyFullScreen();

		player.addFullscreenControlFlag(YouTubePlayer.FULLSCREEN_FLAG_CUSTOM_LAYOUT);
		player.setOnFullscreenListener(this);
		if (!wasRestored) {
			player.cueVideo(fDetails.getYoutubeId());
		}
	}
	
	@Override
	protected YouTubePlayer.Provider getYouTubePlayerProvider() {
		return playerView;
	}

	public void setMyFullScreen() {
		int controlFlags = player.getFullscreenControlFlags();
		setRequestedOrientation(PORTRAIT_ORIENTATION);
		controlFlags |= YouTubePlayer.FULLSCREEN_FLAG_ALWAYS_FULLSCREEN_IN_LANDSCAPE;
		player.setFullscreenControlFlags(controlFlags);
	}

	private void doLayout() {
		LinearLayout.LayoutParams playerParams = (LinearLayout.LayoutParams) playerView
				.getLayoutParams();
		if (fullscreen) {
			
			playerParams.width = LayoutParams.MATCH_PARENT;
			playerParams.height = LayoutParams.MATCH_PARENT;
			otherViews.setVisibility(View.GONE);
			
		} else {
			
			otherViews.setVisibility(View.VISIBLE);
			ViewGroup.LayoutParams otherViewsParams = otherViews
					.getLayoutParams();
			if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
				playerParams.width = otherViewsParams.width = 0;
				playerParams.height = WRAP_CONTENT;
				otherViewsParams.height = MATCH_PARENT;
				playerParams.weight = 1;
				baseLayout.setOrientation(LinearLayout.HORIZONTAL);
			} else {
				playerParams.width = otherViewsParams.width = MATCH_PARENT;
				playerParams.height = WRAP_CONTENT;
				playerParams.weight = 0;
				otherViewsParams.height = 0;
				baseLayout.setOrientation(LinearLayout.VERTICAL);
			}
		}
	}

	@Override
	public void onFullscreen(boolean isFullscreen) {
		fullscreen = isFullscreen;
		doLayout();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		doLayout();
	}
}
