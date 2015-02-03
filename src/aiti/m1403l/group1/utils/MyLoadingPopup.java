package aiti.m1403l.group1.utils;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;

@SuppressLint("HandlerLeak")
public class MyLoadingPopup {

	private ProgressDialog pDialog;

	public MyLoadingPopup(Context context) {
		pDialog = ProgressDialog.show(context, "Loading data..", "Please wait",
				true, false);
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					SystemClock.sleep(2000);
				} catch (Exception e) {
					Log.e("tag", e.getMessage());
				}
				handler.sendEmptyMessage(0);
			}
		}).start();
	}

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			pDialog.dismiss();
		}
	};

}
