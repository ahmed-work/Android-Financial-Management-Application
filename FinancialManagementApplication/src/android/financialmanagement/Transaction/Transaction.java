package android.financialmanagement.Transaction;

public abstract class Transaction {
	
	private long mCurrentValue;
	
	protected enum TransactionType {
		TransactionType_Income, 
		TransactionType_Expense
	}
	
	abstract TransactionType getType();
	
	public long getCurrentValue() {
		return mCurrentValue;
	}
	
	public void setCurrentValue(long currentValue) {
		this.mCurrentValue = currentValue;
	}
	
}

