package android.virtualaccoutant.data;


import android.virtualaccoutant.data.InvalidTransactionDefinition;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class TransactionWrapper {
	
	static public enum Type { Expense, Income };
	
	private int mId;
	private double mValue;
	private String mDate;
	private Type mType;
	private String mDescription;
	private int mCategory; // TODO implement categorization of different transaction
	
	public TransactionWrapper() {
		mId = 0;
		mValue = 0;
		mDate = "";
		mType = Type.Income;
		mDescription = "";
		mCategory = 1;
	}
	
	public TransactionWrapper id(int id) throws InvalidTransactionDefinition {
		
		if (id < 0) {
			throw new InvalidTransactionDefinition("Cannot Set Negetive ID for Transaction");
		}
		
		mId = id;
		return this; 
	}
	
	public TransactionWrapper value(int value) throws InvalidTransactionDefinition {
		
		if (value < 0) {
			throw new InvalidTransactionDefinition("Cannot Set Negetive Value for Transaction");
		}
		
		mValue = value;
		return this;
	}
	
	public TransactionWrapper type(Type type) {
		mType = type;
		return this;
	}
	public TransactionWrapper description(String description) {
		mDescription = description;
		return this;
	}
	
	public TransactionWrapper category(int category) {
		mCategory = category;
		return this;
	}
	
	public TransactionWrapper date(String date) throws InvalidTransactionDefinition {
		
		if ( ! ( date.matches("\\d\\d\\d\\d-\\d\\d-\\d\\d") || date.matches("now") )) {
			throw new InvalidTransactionDefinition("Date String is Invalid, Format Must be YYYY-MM-DD");
		}
		
		if(date.matches("now")) {
			Calendar currentDate = Calendar.getInstance();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			date = formatter.format(currentDate.getTime());
		
		}
		mDate =  date;
		return this;
	}
	
	public int id() {
		return mId;
	}
	
	public double value() {
		return mValue;
	}
	
	public String date() {
		return mDate;
	}
	
	public String description() {
		return mDescription;
	}
	
	public int intType() {
		return mType == Type.Income ? 0 : 1;
	}
	
	public int category() {
		return mCategory;
	}
	
}