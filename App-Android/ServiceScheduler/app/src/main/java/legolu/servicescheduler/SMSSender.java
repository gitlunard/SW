package legolu.servicescheduler;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;


/**
 * SMS sender.
 *
 * Created by Davide on 25/10/2015.
 *
 */
public class SMSSender extends BroadcastReceiver implements RequestedAction {

    private final static String TAG = "SMSSender";
    private final static String ACTION = "sendSMS";

    private final Context context;

    public SMSSender (final Context context) {
        this.context = context;
    }

    @Override
    public void executeRequest(String message) {

        Intent sentIntent = new Intent(ACTION);

		/*Create Pending Intent*/
        PendingIntent sentPI = PendingIntent.getBroadcast(
                context, 0, sentIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);


		/* Register for SMS send action */
        context.getApplicationContext().registerReceiver(this, new IntentFilter(ACTION));

		/*Send SMS*/
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(Globals.getInstance().getDestinationNumber(), null, message,
                sentPI, null);
    }
	
	
	@Override
	public void onReceive(Context context, Intent intent) {

        String result;

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
            default:
                result = "No info";
		}

        Log.d(TAG, result);
		Toast.makeText(context, result, Toast.LENGTH_LONG).show();
	}
}
