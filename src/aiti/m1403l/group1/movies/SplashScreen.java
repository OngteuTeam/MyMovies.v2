package aiti.m1403l.group1.movies;

import aiti.m1403l.group1.R;
import aiti.m1403l.group1.utils.Loader;
import aiti.m1403l.group1.utils.MyDialog;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class SplashScreen extends Activity {

	private int SPLASH_TIME_OUT = 1000;
	ImageView imgView;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		imgView = (ImageView) findViewById(R.id.imgLogo);
		Loader loader = new Loader(this);
		
		if (loader.isFirstRun()) {
			SPLASH_TIME_OUT = 3500;
		}
		
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				Intent i = new Intent(SplashScreen.this, MainActivity.class);
				startActivity(i);
				finish();
			}
		}, SPLASH_TIME_OUT);

	}
}
