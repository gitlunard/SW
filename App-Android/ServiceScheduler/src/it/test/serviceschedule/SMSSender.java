package it.test.serviceschedule;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.SmsManager;
import android.widget.Toast;

public class SMSSender extends BroadcastReceiver {


	public void sendSMS (final Context ctx) {
		
		String SENT = "sent";
		
		String phoneNumber = "0123456789";
		String message = "Hello World!";

		Intent sentIntent = new Intent(SENT);
		
		/*Create Pending Intent*/
		PendingIntent sentPI = PendingIntent.getBroadcast(
				ctx, 0, sentIntent,
				PendingIntent.FLAG_UPDATE_CURRENT);


		/* Register for SMS send action 
		 * 
		 * Si pu˜ anche fare a meno, ma cos“ si ha un feedback sull'esito dell'invio
		 * 
		 */		
		ctx.getApplicationContext().registerReceiver(this, new IntentFilter(SENT));
		

		/*Send SMS*/
		SmsManager smsManager = SmsManager.getDefault();
		smsManager.sendTextMessage(phoneNumber, null, message, sentPI, null);
	
	}
	
	
	@Override
	public void onReceive(Context context, Intent intent) {
		String result = "";

		switch (getResultCode()) {

		case Activity.RESULT_OK:
			result = "Transmission successful";
			break;
		case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
			result = "Transmission failed";
			break;
		case SmsManager.RESULT_ERROR_RADIO_OFF:
			result = "Radio off";
			break;
		case SmsManager.RESULT_ERROR_NULL_PDU:
			result = "No PDU defined";
			break;
		case SmsManager.RESULT_ERROR_NO_SERVICE:
			result = "No service";
			break;
		}

		Toast.makeText(context, result, Toast.LENGTH_LONG).show();
	}
}
