package android.financialmanagement.financialdata;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

public class FinancialDatabaseOpenHelper extends SQLiteOpenHelper {
	
	static public String DATABASE_NAME = "FinancialDatabase";
	static public String TABLE_TRANSACTIONS = "Transactions";
	static public String TABLE_USERS = "Users";
	
	
	
	public FinancialDatabaseOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, 0 );
	}
	
	
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_USERS + "( id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT );");
		db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_TRANSACTIONS + "( id INTEGER PRIMARY KEY AUTOINCREMENT, type INTEGER, value NUMERIC, userid INTEGER, description TEXT,FOREIGN KEY( userid ) REFERENCES " + TABLE_USERS+"(id));");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int initVersion, int newVersion) {

	}

}
