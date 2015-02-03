package aiti.m1403l.group1.data.orm;

import java.util.ArrayList;

import aiti.m1403l.group1.data.DatabaseWrapper;
import aiti.m1403l.group1.data.model.Category;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class CategoryORM {

	public static final String TABLE_NAME = "Category";

	public static final String COL_ID = "id";
	public static final String COL_NAME = "name";
	public static final String COL_IMG = "image";

	/*
	 * SQL CREATE TABLE
	 */
	public static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
			+ " (" + COL_ID + " INTEGER PRIMARY KEY, " + COL_NAME + " TEXT," + COL_IMG + " TEXT);";
	/*
	 * SQL DROP TABLE
	 */
	public static final String SQL_DROP_TABLE = "DROP TABLE IF EXISTS "
			+ TABLE_NAME;

	/*
	 * @Decription: Function for Category
	 */

	public static long addCategory(Context context, Category category) {
		long result = -1;

		DatabaseWrapper databaseWrapper = new DatabaseWrapper(context);
		SQLiteDatabase mDB = databaseWrapper.getWritableDatabase();

		try {
			if (mDB != null) {
				ContentValues values = CategoryORM
						.convertToContentValues(category);
				result = mDB.insertOrThrow(TABLE_NAME, null, values);
			}
		} catch (Exception e) {
			Log.e("SQL_ADD_Category =>", "Failed: " + e.getMessage());
		} finally {
			if (mDB != null) {
				mDB.close();
			}
		}

		return result;
	}

	private static ContentValues convertToContentValues(Category category) {
		ContentValues values = new ContentValues();
		values.put(CategoryORM.COL_ID, category.getId());
		values.put(CategoryORM.COL_NAME, category.getName());
		values.put(CategoryORM.COL_IMG, category.getImage());
		return values;
	}

	private static Category convertToCategory(Cursor c) {
		Category cate = new Category();
		cate.setId(c.getInt(c.getColumnIndex(COL_ID)));
		cate.setName(c.getString(c.getColumnIndex(COL_NAME)));
		cate.setImage(c.getString(c.getColumnIndex(COL_IMG)));
		return cate;
	}

	public static Category getCategoryById(Context context, int id) {
		Category cate = new Category();

		DatabaseWrapper dw = new DatabaseWrapper(context);
		SQLiteDatabase mDB = dw.getWritableDatabase();

		if (mDB != null) {
			Cursor c = mDB.rawQuery("SELECT * FROM " + CategoryORM.TABLE_NAME
					+ " WHERE " + CategoryORM.COL_ID + " = " + id, null);
			c.moveToFirst();
			cate = CategoryORM.convertToCategory(c);
			mDB.close();
		}
		return cate;
	}

	public static ArrayList<Category> getListCategory(Context context) {
		ArrayList<Category> list = new ArrayList<Category>();

		DatabaseWrapper databaseWrapper = new DatabaseWrapper(context);
		SQLiteDatabase mDB = databaseWrapper.getWritableDatabase();

		if (mDB != null) {
			Cursor c = mDB
					.query(TABLE_NAME, null, null, null, null, null, null);
			if (c.moveToFirst()) {
				do {
					Category category = convertToCategory(c);
					list.add(category);
				} while (c.moveToNext());
			}
			mDB.close();
		}

		return list;
	}

}
