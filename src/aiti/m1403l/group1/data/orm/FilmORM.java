package aiti.m1403l.group1.data.orm;

import java.util.ArrayList;

import aiti.m1403l.group1.data.DatabaseWrapper;
import aiti.m1403l.group1.data.model.Film;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class FilmORM {

	public static final String TABLE_NAME = "Film";

	public static final String COL_ID = "id";
	public static final String COL_NAME = "name";
	public static final String COL_YEAR = "year";
	public static final String COL_IMAGE = "image";
	public static final String COL_YOUTUBEID = "youtube_id";
	public static final String COL_VIEW = "view_count";
	public static final String COL_LIKE = "like";
	public static final String COL_DURATION = "duration";
	public static final String COL_MARK = "is_bookmark";

	/*
	 * SQL CREATE TABLE
	 */
	public static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
			+ " (" + COL_ID + " INTEGER PRIMARY KEY, " + COL_NAME + " TEXT, "
			+ COL_YEAR + " INTEGER, " + COL_IMAGE + " TEXT, " + COL_YOUTUBEID
			+ " TEXT, " + COL_VIEW + " INTEGER, " + COL_LIKE + " INTEGER, "
			+ COL_DURATION + " TEXT, " + COL_MARK + " INTEGER);";
	/*
	 * SQL DROP TABLE
	 */
	public static final String SQL_DROP_TABLE = "DROP TABLE IF EXISTS "
			+ TABLE_NAME;

	/*
	 * @Decription: Function for Film
	 */

	public static long add(Context context, Film film) {
		long result = -1;

		DatabaseWrapper databaseWrapper = new DatabaseWrapper(context);
		SQLiteDatabase mDB = databaseWrapper.getWritableDatabase();

		try {
			if (mDB != null) {
				ContentValues values = convertToContentValues(film, 0);
				for (int categoryId : film.getCategories()) {
					FilmCategoryORM.add(context, film.getId(), categoryId);
				}
				result = mDB.insert(TABLE_NAME, null, values);
			}
		} catch (Exception e) {
			Log.e("SQL_ADD_Film =>", "Failed: " + e.getMessage());
		} finally {
			if (mDB != null) {
				mDB.close();
			}
		}

		return result;
	}

	public static long update(Context context, Film film, int flagBookmark) {
		long result = -1;

		DatabaseWrapper databaseWrapper = new DatabaseWrapper(context);
		SQLiteDatabase mDB = databaseWrapper.getWritableDatabase();

		try {
			if (mDB != null) {
				ContentValues values = FilmORM.convertToContentValues(film, flagBookmark);

				FilmCategoryORM.deleteByFilmId(context, film.getId());

				result = mDB.update(TABLE_NAME, values, FilmORM.COL_ID + " = "
						+ film.getId(), null);

				for (int categoryId : film.getCategories()) {
					FilmCategoryORM.add(context, film.getId(), categoryId);
				}
			}
		} catch (Exception e) {
			Log.e("SQL_UPDATE_Film =>", "Failed: " + e.getStackTrace());
		} finally {
			if (mDB != null) {
				mDB.close();
			}
		}
		return result;
	}

	public static long updateBookmark(Context context, int filmId, int flag) {
		long result = -1;

		DatabaseWrapper databaseWrapper = new DatabaseWrapper(context);
		SQLiteDatabase mDB = databaseWrapper.getWritableDatabase();

		try {
			if (mDB != null) {
				ContentValues values = FilmORM.convertToContentValues(FilmORM.getFilmById(context, filmId), flag);
				result = mDB.update(TABLE_NAME, values, FilmORM.COL_ID + " = " + filmId + "", null);
			}
		} catch (Exception e) {
			Log.e("SQL_UPDATE_Film =>", "Failed: " + e.getStackTrace());
		} finally {
			if (mDB != null) {
				mDB.close();
			}
		}
		return result;
	}

	public static long delete(Context context, int id) {
		long result = -1;

		DatabaseWrapper databaseWrapper = new DatabaseWrapper(context);
		SQLiteDatabase mDB = databaseWrapper.getWritableDatabase();

		try {
			if (mDB != null) {
				FilmCategoryORM.deleteByFilmId(context, id);
				result = mDB.delete(TABLE_NAME, COL_ID + "=" + id, null);
			}
		} catch (Exception e) {
			Log.e("SQL_DEL_Film =>", "Failed: " + e.getStackTrace());
		} finally {
			if (mDB != null) {
				mDB.close();
			}
		}

		return result;
	}

	private static ContentValues convertToContentValues(Film film, int bookmark) {
		ContentValues values = new ContentValues();
		values.put(FilmORM.COL_ID, film.getId());
		values.put(FilmORM.COL_NAME, film.getName());
		values.put(FilmORM.COL_YEAR, film.getYear());
		values.put(FilmORM.COL_IMAGE, film.getImage());
		values.put(FilmORM.COL_YOUTUBEID, film.getYoutubeId());
		values.put(FilmORM.COL_VIEW, film.getViewCount());
		values.put(FilmORM.COL_LIKE, film.getLike());
		values.put(FilmORM.COL_DURATION, film.getDuration());
		values.put(FilmORM.COL_MARK, bookmark);
		return values;
	}

	private static Film convertToFilm(Cursor c) {
		Film f = new Film();
		f.setId(c.getInt(c.getColumnIndex(COL_ID)));
		f.setName(c.getString(c.getColumnIndex(COL_NAME)));
		f.setYear(c.getInt(c.getColumnIndex(COL_YEAR)));
		f.setImage(c.getString(c.getColumnIndex(COL_IMAGE)));
		f.setYoutubeId(c.getString(c.getColumnIndex(COL_YOUTUBEID)));
		f.setViewCount(c.getInt(c.getColumnIndex(COL_VIEW)));
		f.setLike(c.getInt(c.getColumnIndex(COL_LIKE)));
		f.setDuration(c.getString(c.getColumnIndex(COL_DURATION)));
		return f;
	}

	public static boolean isExisting(Context context, int id) {
		boolean isExisting = false;
		DatabaseWrapper databaseWrapper = new DatabaseWrapper(context);
		SQLiteDatabase mDB = databaseWrapper.getReadableDatabase();

		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_ID
				+ " = '" + id + "'";
		Cursor c = mDB.rawQuery(sql, null);
		if (c.getCount() > 0) {
			isExisting = true;
		}
		return isExisting;
	}

	public static Film getFilmById(Context context, int id) {
		Film film = new Film();
		DatabaseWrapper databaseWrapper = new DatabaseWrapper(context);
		SQLiteDatabase mDB = databaseWrapper.getReadableDatabase();
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_ID
				+ " = '" + id + "'";
		Cursor c = mDB.rawQuery(sql, null);
		if (c != null) {
			c.moveToFirst();
		}
		film = convertToFilm(c);

		return film;
	}

	public static ArrayList<Film> getList(Context context) {
		ArrayList<Film> list = new ArrayList<Film>();

		DatabaseWrapper databaseWrapper = new DatabaseWrapper(context);
		SQLiteDatabase mDB = databaseWrapper.getReadableDatabase();

		if (mDB != null) {
			Cursor c = mDB
					.query(TABLE_NAME, null, null, null, null, null, null);
			if (c.moveToFirst()) {
				do {
					Film film = convertToFilm(c);
					list.add(film);
				} while (c.moveToNext());
			}
			mDB.close();
		}
		return list;
	}

	public static ArrayList<Film> getListBookmark(Context context) {
		ArrayList<Film> list = new ArrayList<Film>();

		DatabaseWrapper databaseWrapper = new DatabaseWrapper(context);
		SQLiteDatabase mDB = databaseWrapper.getReadableDatabase();

		if (mDB != null) {
			String selection = FilmORM.COL_MARK + "= ?";
			String[] selectionArgs = new String[] { String.valueOf(1) };
			Cursor c = mDB
					.query(TABLE_NAME, null, selection, selectionArgs, null, null, null);
			if (c.moveToFirst()) {
				do {
					Film film = convertToFilm(c);
					list.add(film);
				} while (c.moveToNext());
			}
			mDB.close();
		}
		return list;
	}
	
	public static ArrayList<Film> getListByCategoryId(Context context,
			int categoryId) {
		ArrayList<Film> list = new ArrayList<Film>();
		DatabaseWrapper databaseWrapper = new DatabaseWrapper(context);
		SQLiteDatabase mDB = databaseWrapper.getReadableDatabase();

		if (mDB != null) {
			String sql = "SELECT * FROM " + TABLE_NAME + " WHERE "
					+ CategoryORM.COL_ID + " = " + categoryId;
			Cursor c = mDB.rawQuery(sql, null);
			if (c.moveToFirst()) {
				do {
					Film film = convertToFilm(c);
					list.add(film);
				} while (c.moveToNext());
			}
			mDB.close();
		}
		return list;
	}

	public static ArrayList<Film> getSearch(Context context, String filter) {
		ArrayList<Film> list = new ArrayList<Film>();

		DatabaseWrapper databaseWrapper = new DatabaseWrapper(context);
		SQLiteDatabase mDB = databaseWrapper.getWritableDatabase();

		if (mDB != null) {
			String sql = " SELECT * FROM " + TABLE_NAME + " WHERE " + COL_NAME
					+ " LIKE '%" + filter + "%'";
			Cursor c = mDB.rawQuery(sql, null);

			while (c.isAfterLast() == false) {
				Film film = convertToFilm(c);
				list.add(film);
			}

			mDB.close();
		}

		return list;
	}

}
