package android.virtualaccoutant.data;

import android.content.Context;
import android.util.Log;
import android.virtualaccoutant.data.TransactionWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.database.SQLException;
import android.content.ContentValues;
import java.util.HashMap;

/**
 * @author Barney Stinson
 */

public class DatabaseInterface {
	
	public static final String TABLE_TRANSACTIONS = "transactions";
	public static final String TABLE_CATEGORY = "category";
	public static final String TRANSACTIONS_TYPE = "type";
	public static final String TRANSACTIONS_ID = "id";
	public static final String TRANSACTIONS_DATE = "date";
	public static final String TRANSACTIONS_DESCRIPTION = "description";
	public static final String TRANSACTIONS_VALUE = "value";
	public static final String TRANSACTIONS_CATEGORY = "category";
	public static final String CATEGORY_ID = "categeory";
	public static final String CATEGORY_NAME = "name";
	public static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = "VirtualAccountant";
	
	
	private DatabaseHelper mDBHelper;
	private SQLiteDatabase mDatabase;
	private final Context mContext;
	private HashMap<Integer, String> mCategoryMap;
	
	
	private static class  DatabaseHelper extends SQLiteOpenHelper {

		public DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			
		}
		
		@Override
		public void onCreate(SQLiteDatabase db) {
			try {
				db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_TRANSACTIONS + " ( " +
						TRANSACTIONS_ID + " integer primary key autoincrement, " +
						TRANSACTIONS_VALUE + " real, " +
						TRANSACTIONS_TYPE + " integer not null, " +
						TRANSACTIONS_DATE + " text, " + 
						TRANSACTIONS_DESCRIPTION + " text, " +
						TRANSACTIONS_CATEGORY + " integer not null" +
						")" );
				
				db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_CATEGORY + " ( " +
						CATEGORY_ID + " integer primary key autoincrement, " + 
						CATEGORY_NAME + " text" +
						")" );
				
				ContentValues initialValue = new ContentValues();
				initialValue.put(CATEGORY_NAME, "Unspecified");
				
				db.insert(TABLE_CATEGORY, null, initialValue);
				
						
			}
			catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSACTIONS );
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORY );
		}
		
	}
	
	
	public DatabaseInterface(Context context) {
		mContext = context;
		mDBHelper = new DatabaseHelper(context);
		mCategoryMap = new HashMap<Integer, String>();
		
		open();
		setupCategories();
		
	}
	
	private void setupCategories() {
		Cursor dbResult = mDatabase.query(TABLE_CATEGORY, null, null, null, null, null, null);

		if (dbResult != null ) {
			dbResult.moveToFirst();
			
			int idIndex = dbResult.getColumnIndex(CATEGORY_ID);
			int nameIndex = dbResult.getColumnIndex(CATEGORY_NAME);

			do {
				mCategoryMap.put(dbResult.getInt(idIndex), dbResult.getString(nameIndex));
			} while (dbResult.moveToNext());	
		}

	}

	private void open() throws SQLException {
		mDatabase = mDBHelper.getWritableDatabase();
	} 
	
	public void close() {
		mDBHelper.close();
	}
	
	public void insertTransaction(TransactionWrapper wrapper) {
		ContentValues values  = new ContentValues();
		values.put(TRANSACTIONS_VALUE, wrapper.value());
		values.put(TRANSACTIONS_TYPE, wrapper.intType());
		values.put(TRANSACTIONS_DATE, wrapper.date());
		values.put(TRANSACTIONS_CATEGORY, wrapper.category());
		values.put(TRANSACTIONS_DESCRIPTION, wrapper.description());
		
		mDatabase.insert(TABLE_TRANSACTIONS, null, values);
	}
	
	public void insertCategory(String category) {
		ContentValues values = new ContentValues();
		values.put(CATEGORY_NAME, category);
		
		mDatabase.insert(TABLE_CATEGORY, null, values);
	}
	
}