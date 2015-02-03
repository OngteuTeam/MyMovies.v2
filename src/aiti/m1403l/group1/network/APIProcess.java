package aiti.m1403l.group1.network;

import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import aiti.m1403l.group1.data.model.Category;
import aiti.m1403l.group1.data.model.Film;
import aiti.m1403l.group1.data.model.ListFilm;
import aiti.m1403l.group1.data.orm.CategoryORM;
import aiti.m1403l.group1.data.orm.FilmORM;
import aiti.m1403l.group1.utils.Defines;
import aiti.m1403l.group1.utils.MyLoadingPopup;
import aiti.m1403l.group1.utils.MySPManager;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class APIProcess {

	private static GetCategory getCate;
	private static GetFilm getFilm;

	public static boolean sendRequestFirstRun(final Context context) {

		boolean ok = false;

		GetData gdCate = new GetData(context);
		GetData gdFilm = new GetData(context);
		final MySPManager spManager = new MySPManager(context);

		getCate = new GetCategory() {
			@Override
			public void success(String json) {
				super.success(json);
				for (Category category : listCategory) {
					CategoryORM.addCategory(context, category);
				}
				Log.i("INSERTED_CATEGORY ======>", "SUCCESS");
			}
		};

		getFilm = new GetFilm() {
			@Override
			public void success(String json) {
				super.success(json);
				for (Film film : listFilm) {
					FilmORM.add(context, film);
				}
				spManager.setLatestTime(now);
				Log.i("INSERTED_FILM ======>", "SUCCESS");
			}
		};

		gdCate.execute(getPackedParameters(Defines.URL_GET_ALL_CATEGORY, null,
				getCate));
		gdFilm.execute(getPackedParameters(Defines.URL_GET_ALL_FILMS, null,
				getFilm));

		spManager.setFirstRun(false);
		Log.i("API_1ST_RUN ========>", "Success 1st");

		return ok;
	}

	public static boolean sendRequestUpdate(final Context context) {

		boolean ok = false;
		GetData gd = new GetData(context);
		final MySPManager spManager = new MySPManager(context);

		getFilm = new GetFilm() {
			@Override
			public void success(String json) {
				super.success(json);
				if (!listFilm.isEmpty()) {
					for (Film film : listFilm) {
						FilmORM.add(context, film);
					}
				}
				if (!listDeleted.isEmpty()) {
					for (int delId : listDeleted) {
						FilmORM.delete(context, delId);
					}
				}
				spManager.setLatestTime(now);
				Log.i("UPDATED FILM ======>", "SUCCESS");
			}
		};
		String url = Defines.URL_GET_UPDATE_FILMS + spManager.getLatestTime();

		gd.execute(getPackedParameters(url, null, getFilm));
		Log.i("API_INSTALLED_RUN ========>", "installed run");
		ok = true;

		return ok;

	}

	/*
	 * Bundles any additional parameters you want to pass to the server with the
	 * authentication parameters. Also adds the required RESTResponse callback
	 * class, and server URL to the dictionary.
	 */
	private static HashMap<String, Object> getPackedParameters(
			String serverURL, Map<String, Object> additionalParams,
			IResponse response) {
		HashMap<String, Object> packedParams = new HashMap<String, Object>();

		packedParams.put(GetData.SERVER_URL, serverURL);

		if (additionalParams != null) {
			packedParams.putAll(additionalParams);
		}

		packedParams.put(GetData.CALLBACK_CLASS, response);

		return packedParams;
	}

	private static class GetData extends
			AsyncTask<HashMap<String, Object>, Void, String> {

		public static final String TAG = "GETDATA";

		public static final String CALLBACK_CLASS = "CallbackClass";
		public static final String SERVER_URL = "ServerURL";

		private Context context;

		/**
		 * Takes and InputStream and reads it's contents into a String.
		 * 
		 * @param is
		 * @return
		 */
		private static String convertStreamToString(InputStream is) {
			java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
			return s.hasNext() ? s.next() : "";
		}

		public GetData(Context context) {
			this.context = context;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

		}

		@Override
		protected void onProgressUpdate(Void... values) {
			super.onProgressUpdate(values);
			
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			
		}

		@Override
		protected String doInBackground(HashMap<String, Object>... params) {

			// Get callback class
			IResponse callback = null;
			if (params[0].containsKey(CALLBACK_CLASS)) {
				callback = (IResponse) params[0].get(CALLBACK_CLASS);
			} else {
				callback = new IResponse() {

					@Override
					public void success(String json) {
						Log.i(TAG, "Callback not implemented!");
					}

					@Override
					public void fail(Exception ex) {
						Log.i(TAG, "Callback not implemented!");
					}
				};
			}

			// Get url data
			String url = null;
			if (params[0].containsKey(SERVER_URL)) {
				url = params[0].get(SERVER_URL).toString();
			} else {
				Log.e(TAG, "No server URL provided.");
				callback.fail(new Exception("No server URL provided"));
				return null;
			}

			try {
				HttpClient client = new DefaultHttpClient();
				HttpGet get = new HttpGet(url);

				HttpResponse response = client.execute(get);
				StatusLine statusLine = response.getStatusLine();

				if (statusLine.getStatusCode() == 200) {
					HttpEntity entity = response.getEntity();
					InputStream content = entity.getContent();

					try {
						callback.success(GetData.convertStreamToString(content));
					} catch (Exception ex) {
						Log.e(TAG, "Failed to parse JSON due to: " + ex);
						callback.fail(ex);
					}
				} else {
					Log.e(TAG, "Server responded with status code: "
							+ statusLine.getStatusCode());
					callback.fail(new Exception(
							"Server responded with response code: "
									+ statusLine.getStatusCode()));
				}

			} catch (Exception ex) {
				Log.e(TAG, "Failed to send HTTP POST request due to: " + ex);
				callback.fail(ex);
			}

			return null;
		}

	}

	public static class GetCategory implements IResponse {

		protected List<Category> listCategory = null;

		@Override
		public void success(String json) {
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.setDateFormat("M/d/yy hh:mm a");
			Gson gson = gsonBuilder.create();

			listCategory = Arrays.asList(gson.fromJson(json, Category[].class));
		}

		@Override
		public void fail(Exception ex) {

		}

	}

	public static class GetFilm implements IResponse {

		private ListFilm objListFilm = null;
		protected List<Film> listFilm = null;
		protected List<Integer> listDeleted = null;
		protected int now = 0;

		@Override
		public void success(String json) {
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.setDateFormat("M/d/yy hh:mm a");
			Gson gson = gsonBuilder.create();

			objListFilm = gson.fromJson(json, ListFilm.class);
			listFilm = objListFilm.getFilms();
			now = objListFilm.getNow();
			// Log.e("NOW ======>", now + "");
			listDeleted = objListFilm.getDeletedId();

		}

		@Override
		public void fail(Exception ex) {
			Log.e("GET_LIST_FILM ====>", "Get list fail :/ " + ex.getMessage());
		}

	}

}