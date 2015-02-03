package aiti.m1403l.group1.data.orm;

import java.util.ArrayList;

import aiti.m1403l.group1.data.DatabaseWrapper;
import aiti.m1403l.group1.data.model.Category;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class FilmCategoryORM {

	public static final String TABLE_NAME = "FilmCategory";

	public static final String COL_FILM_ID = "film_id";
	public static final String COL_CATEGORY_ID = "category_id";

	/*
	 * SQL CREATE TABLE
	 */
	public static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
			+ " (" + COL_FILM_ID + " INTEGER, " + COL_CATEGORY_ID
			+ " INTEGER);";
	/*
	 * SQL DROP TABLE
	 */
	public static final String SQL_DROP_TABLE = "DROP TABLE IF EXISTS "
			+ TABLE_NAME;

	/*
	 * @Decription: Function for Film
	 */

	public static long add(Context context, int filmId, int categoryId) {
		long result = -1;
		DatabaseWrapper dw = new DatabaseWrapper(context);
		SQLiteDatabase mDB = dw.getWritableDatabase();
		try {
			if (mDB != null) {
				ContentValues values = new ContentValues();
				values.put(COL_FILM_ID, filmId);
				values.put(COL_CATEGORY_ID, categoryId);
				result = mDB.insert(TABLE_NAME, null, values);
			}
		} catch (Exception e) {
			Log.e("SQL_ADD_FilmCategory =>", "Failed: " + e.getStackTrace());
		} finally {
			if (mDB != null) {
				mDB.close();
			}
		}
		return result;
	}

	public static long deleteByFilmId(Context context, int filmId) {
		long result = -1;
		DatabaseWrapper dw = new DatabaseWrapper(context);
		SQLiteDatabase mDB = dw.getWritableDatabase();
		try {
			if (mDB != null) {
				result = mDB.delete(TABLE_NAME, COL_FILM_ID + "=" + filmId,
						null);
			}
		} catch (Exception e) {
			Log.e("SQL_DEL_FilmCategory =>", "Failed: " + e.getStackTrace());
		} finally {
			if (mDB != null) {
				mDB.close();
			}
		}
		return result;
	}

	public static ArrayList<Category> getListCategoryByFilmId(Context context,
			int filmId) {
		ArrayList<Category> list = new ArrayList<Category>();

		DatabaseWrapper dw = new DatabaseWrapper(context);
		SQLiteDatabase mDB = dw.getWritableDatabase();

		if (mDB != null) {
			Cursor c = mDB.rawQuery("SELECT * FROM "
					+ FilmCategoryORM.TABLE_NAME + " WHERE "
					+ FilmCategoryORM.COL_FILM_ID + " = " + filmId, null);
			if (c.getColumnCount() > 0) {
				c.moveToFirst();
				while (!c.isAfterLast()) {
					list.add(CategoryORM.getCategoryById(context,
							c.getInt(c.getColumnIndex(COL_CATEGORY_ID))));
					c.moveToNext();
				}
			}
			Log.i("FilmCategory_GetCategoriesByID =>", "SUCCESS");
			mDB.close();
		}

		return list;
	}

}
