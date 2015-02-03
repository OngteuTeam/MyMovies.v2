package aiti.m1403l.group1.data;

import aiti.m1403l.group1.data.orm.*;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseWrapper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "m1403l_movie.sqlite";
	private static final int DATABASE_VERSION = 1;

	public DatabaseWrapper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		//Log.i("DW_CREATED", "DatabaseWapper was create");
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.i("DB_TAG", "CREATE SUCCESS");
		db.execSQL(CategoryORM.SQL_CREATE_TABLE);
		db.execSQL(FilmORM.SQL_CREATE_TABLE);
		db.execSQL(FilmCategoryORM.SQL_CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w("LOG_TAG", "Upgrading database from version " + oldVersion
				+ " to " + newVersion + ", which will destroy all old data");
		db.execSQL(FilmCategoryORM.SQL_DROP_TABLE);
		db.execSQL(FilmORM.SQL_DROP_TABLE);
		db.execSQL(FilmORM.SQL_DROP_TABLE);
		onCreate(db);
	}
	
}
