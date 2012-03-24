package android.virtualaccoutant.data;

@SuppressWarnings("serial")
public class InvalidTransactionDefinition extends Exception {
	
	private String mError;
	
	public InvalidTransactionDefinition (String value) {
		mError = value;
	}
	
	public String toString() {
		return mError;
	}
	
}
