package legolu.servicescheduler;

import android.content.Context;
import android.util.Log;

/**
 * Created by leo on 23/10/15.
 */
public class ExecuteAction {

    private final static String TAG = "ExecuteAction";

    private final Context context;

    public ExecuteAction(Context context) {
        this.context = context;
    }

    public void notify(String message) {

        switch (message) {

            case (Actions.SEND_SMS) : {

                Log.d(TAG, "must send SMS");
                SMSSender smsSender = new SMSSender(context);
                smsSender.executeRequest("Testo SMS");
                break;
            }

            case (Actions.SEND_EMAIL) : {

                Log.d(TAG, "must send Email");
                EmailSender emailSender = new EmailSender();
                emailSender.executeRequest("Testo Email");
                break;
            }

            default : {

                Log.d(TAG, "don't know what to do");
            }
        }
    }
}

