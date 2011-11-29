package com.vt.vtrition.utils;

import java.io.File;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

public class DBHelper {

	/* DB Strings */
	private static final String DB_NAME = "vtdb";
	private static final String RECIPE_TBL = "recipes";
	//	private static final String WEEK_TBL = "week";
	private static final int DB_VER = 1;

	/* Recipe table strings */
	private static final String KEY_RECIPEID = "_id";
	private static final String KEY_RECIPENAME = "recipe_name";
	private static final String KEY_RECIPEIMG = "recipe_img";
	private static final String KEY_RECIPEDESC = "recipe_desc";
	private static final String KEY_RECIPE = "recipe";

	private static final String RECIPETBL_CREATE = 
			"create table recipes (_id INTEGER primary key autoincrement, " +
					"recipe_name TEXT, " +
					"recipe_img TEXT" +
					"recipe_desc TEXT" +
					"recipe TEXT);"; 

	/* End table creation strings */

	public static class DB {
		private SQLiteDatabase db;

		public void open() {
			File dbDir = null;
			File dbFile = null;
			// Detect if there's an SD Card present and create folder (for db) in it if true

			if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
				dbDir = new File (Environment.getExternalStorageDirectory(), /*"." + */DB_NAME);
				dbFile = new File(dbDir, DB_NAME);
			} else {
				// code for showing error coz there's no sdcard
			}

			// If the db folder doesn't exist already, create it
			if (!dbDir.exists()) {
				dbDir.mkdir();
				Log.i("SQLiteHelper", "Created directory at " + dbDir);
			}

			// If the db file already exists, open the database and update if older version
			if (dbFile.exists()) {
				Log.i("SQLiteHelper", "Opening only database at " + dbFile);
				db = SQLiteDatabase.openOrCreateDatabase(dbFile, null);
				if (DB_VER > db.getVersion()) {
					upgrade();
				}

				// If the db file doesn't exist then create it
			} else {
				Log.i("SQLiteHelper", "Creating database at " + dbFile);
				db = SQLiteDatabase.openOrCreateDatabase(dbFile, null);
				Log.i("SQLiteHelper", "Opened database at " + dbFile);
				create();
			}
		}

		public void create() {
			db.execSQL(RECIPETBL_CREATE);

			ContentValues values = new ContentValues();

			values.put("_id", "0");
			values.put("recipe_name", "Recipe 1");
			values.put("recipe_img", "img\\path");
			values.put("recipe_desc", "This is a bad tasting meal, enjoy");
			values.put("recipe", "actual recipe");
			db.insert(RECIPE_TBL, null, values);
			values.clear();

			values.put("_id", "1");
			values.put("recipe_name", "Recipe 2");
			values.put("recipe_img", "img\\path2");
			values.put("recipe_desc", "This is worse, enjoy");
			values.put("recipe", "actual recipe 2");
			db.insert(RECIPE_TBL, null, values);
			values.clear();

			values.put("_id", "2");
			values.put("recipe_name", "Recipe 3");
			values.put("recipe_img", "img\\path3");
			values.put("recipe_desc", "This is good actually, enjoy");
			values.put("recipe", "actual recipe 3");
			db.insert(RECIPE_TBL, null, values);
			values.clear();

			values.put("_id", "3");
			values.put("recipe_name", "Recipe 4");
			values.put("recipe_img", "img\\path4");
			values.put("recipe_desc", "Enjoy the broccoli");
			values.put("recipe", "actual recipe 4");
			db.insert(RECIPE_TBL, null, values);
			values.clear();

			values.put("_id", "4");
			values.put("recipe_name", "Recipe 5");
			values.put("recipe_img", "img\\path5");
			values.put("recipe_desc", "This is isn't healthy at all.");
			values.put("recipe", "actual recipe 5");
			db.insert(RECIPE_TBL, null, values);
			values.clear();

			db.setVersion(DB_VER);
		}

		public void upgrade() {
			Log.w("" + this, "Upgrading database "+ db.getPath() + " from version " + db.getVersion() + " to "
					+ DB_VER + ", which will destroy all old data");
			db.execSQL("DROP TABLE IF EXISTS agencies");
			db.execSQL("DROP TABLE IF EXISTS stops");
			create();

		}

		public void close() {
			db.close();
		}

		// insert a recipe into the database (for later I guess)
		public long insertRecipe(String recipe_name, String recipe_img, String recipe_desc, String recipe) {
			ContentValues initialValues = new ContentValues();
			initialValues.put(KEY_RECIPENAME, recipe_name);
			initialValues.put(KEY_RECIPEIMG, recipe_img);
			initialValues.put(KEY_RECIPEDESC, recipe_desc);
			initialValues.put(KEY_RECIPE, recipe);
			return db.insert(RECIPE_TBL, null, initialValues);
		}
		
		public boolean deleteRecipe(long rowId) {
			int recipe = db.delete(RECIPE_TBL, KEY_RECIPEID + "=" + rowId, null);
			return (recipe > 0);
		}
		
		public Cursor getAllRecipes() {
			return db.query(RECIPE_TBL, new String[] {
					// importing only needed columns instead of all
					KEY_RECIPEID,
					KEY_RECIPENAME},
					null,
					null,
					null,
					null,
					null);
		}
		
		public Cursor getRecipe(long rowId) throws SQLException {
			Cursor mCursor =
					db.query(true, RECIPE_TBL, new String[] {
							KEY_RECIPEID,
							KEY_RECIPENAME,
							KEY_RECIPEIMG,
							KEY_RECIPEDESC,
							KEY_RECIPE},
							KEY_RECIPEID + "=" + rowId,
							null,
							null,
							null,
							null,
							null);
			
			if (mCursor != null) {
				mCursor.moveToFirst();
			}
			return mCursor;
		}
	}
}
