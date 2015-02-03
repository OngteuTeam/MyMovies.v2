package aiti.m1403l.group1.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class MySPManager {

	public static final String KEY_FIRST_RUN = "isFirstRun";
	public static final String KEY_LATEST_DATE = "latestDate";
	public static final String KEY_PLAYED_VIDEOS = "playedVideos";
	public static final String KEY_LAST_SEARCHED_VIDEOS = "searchedVideos";

	private SharedPreferences mySharedPreferences;

	public MySPManager(Context context) {
		mySharedPreferences = context.getSharedPreferences(Defines.SHARE_PREF,
				Context.MODE_PRIVATE);
		if (!mySharedPreferences.contains(KEY_FIRST_RUN)) {
			setFirstRun(true);
		}
	}

	public void setFirstRun(boolean isFirstTime) {
		mySharedPreferences.edit().putBoolean(KEY_FIRST_RUN, isFirstTime).commit();
	}

	public boolean isFirstRun() {
		boolean flag = mySharedPreferences.getBoolean(KEY_FIRST_RUN, false);
		return flag;
	}

	public void setLatestTime(int latestTime) {
		mySharedPreferences.edit().putInt(KEY_LATEST_DATE, latestTime).commit();
	}

	public int getLatestTime() {
		return mySharedPreferences.getInt(KEY_LATEST_DATE, 0);
	}

}
