package legolu.servicescheduler;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;

/**
 * Created by Davide on 26/10/15.
 *
 * This receiver is always active when the app is running.
 *
 * To test SMS:
 * telnet localhost 5554
 * sms send senderPhoneNumber textMessage
 */
public class SMSReceiver extends BroadcastReceiver {

    private final static String TAG = "SMSReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d(TAG, "something received");

        if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {

            Log.d(TAG, "SMS received");

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {  // API level 19

                SmsMessage[] msgs = Telephony.Sms.Intents.getMessagesFromIntent(intent);
                SmsMessage smsMessage = msgs[0];
                //TODO
            }
            else {

                Bundle bundle = intent.getExtras();
                SmsMessage[] messages = null;
                if (bundle != null) {

                    try {
                        Object[] pdus = (Object[]) bundle.get("pdus");
                        messages = new SmsMessage[pdus.length];
                        for (int i = 0; i < messages.length; i++) {
                            messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                            String originatingAddress = messages[i].getOriginatingAddress();
                            Log.d(TAG, "SMS message from " + originatingAddress);
                            String messageBody = messages[i].getMessageBody();
                            Log.d(TAG, messageBody);
                            if (messageBody.equals(Actions.HANDLE_SMS)) {
                                SMSReceivedHandler handler = new SMSReceivedHandler(
                                        Globals.getInstance().getWebSocket());
                                handler.executeRequest(messageBody);
                            }
                            else {
                                Log.d(TAG, "Unknown SMS");
                            }
                        }
                    } catch (Exception e) {
                        Log.d("Exception", e.getMessage());
                    }
                }
            }
        }
    }
}
