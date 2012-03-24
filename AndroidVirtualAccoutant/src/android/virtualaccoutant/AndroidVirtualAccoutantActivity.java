package android.virtualaccoutant;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.virtualaccoutant.data.*;
import android.virtualaccoutant.data.TransactionWrapper.Type;

public class AndroidVirtualAccoutantActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        DatabaseInterface db = new DatabaseInterface(this);
        TransactionWrapper wrapper = new TransactionWrapper();
        try {
			wrapper.type(Type.Income).date("now").description("Sample Transaction").value(100).category(1);
			db.insertTransaction(wrapper);
		} catch (InvalidTransactionDefinition e) {
			Log.e("Error", e.toString());
			e.printStackTrace();
		}
        
    }
}