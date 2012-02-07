package android.financialmanagement.financialdata;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class FinancialDatabase {

	private SQLiteDatabase mSqlDatabase; 
	
	public FinancialDatabase(Context context) {
		FinancialDatabaseOpenHelper mOpenHelper = new FinancialDatabaseOpenHelper(context);
		mSqlDatabase = mOpenHelper.getWritableDatabase();
	}
	
}
