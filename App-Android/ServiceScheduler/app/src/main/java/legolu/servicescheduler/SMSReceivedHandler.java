package legolu.servicescheduler;

import android.util.Log;

import com.neovisionaries.ws.client.WebSocket;

/**
 * Created by Davide on 26/10/15.
 */
public class SMSReceivedHandler implements RequestedAction {

    private final static String TAG = "SMSReceivedHandler";
    private final WebSocket webSocket;

    public SMSReceivedHandler(WebSocket webSocket) {

        this.webSocket = webSocket;
    }

    @Override
    public void executeRequest(String message) {

        this.webSocket.sendText("SMS:" + message);
        Log.d(TAG, "message sent to websocket");
    }
}
