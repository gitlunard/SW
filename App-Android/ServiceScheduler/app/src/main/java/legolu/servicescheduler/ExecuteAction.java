package legolu.servicescheduler;

import android.content.Context;
import android.util.Log;

/**
 * Created by leo on 23/10/15.
 */
public class ExecuteAction {

    private final static String TAG = "ExecuteAction";
    private final static String SEND_SMS = "SendSMS";
    private final static String SEND_EMAIL = "SendEmail";

    private final Context context;

    public ExecuteAction(Context context) {
        this.context = context;
    }

    public void notify(String message) {

        switch (message) {

            case("prova"): {

                Log.d("switch", message);
                break;
            }

            case (SEND_SMS) : {

                Log.d(TAG, "must send SMS");
                SMSSender smsSender = new SMSSender(context);
                smsSender.executeRequest();
                break;
            }

            case (SEND_EMAIL) : {

                Log.d(TAG, "must send Email");
                EmailSender emailSender = new EmailSender();
                emailSender.executeRequest();
                break;
            }

            default : {

                Log.d(TAG, "don't know what to do");
            }
        }
    }
}

