package legolu.servicescheduler;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import legolu.servicelegolu.R;


public class MainActivity extends Activity {

    private final static String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout);

        WifiManager.WifiLock wifiLock = Globals.getInstance().getWifiLock();
        if (wifiLock == null) {
            WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            wifiLock = wifiManager.createWifiLock(WifiManager.WIFI_MODE_FULL, "MyWifiLock");
            Globals.getInstance().setWifiLock(wifiLock);
        }

        if(!wifiLock.isHeld()){
            wifiLock.acquire();
            //TODO quando rilasciare?
            Log.d("MainActivity", "wifi lock acquired");
        }
    }


    public void btnStartSocket(View v) {

        try {

            toast("start Socket...");

            new Thread(new ThreadServer(getApplicationContext())).start();

            /*LB: Quello che segue è commentato perchè non funziona la ricezione sulla socket
            * Sarebbe meglio fare andare questo ed eliminare ThreadServer.java*/
            //new ServerAsyncNotify().execute();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void btnStartWebSocket(View v) {


        try {

            /**
             * On server-side I used: https://github.com/ghedipunk/PHP-Websockets
             * testwebsock.php
             *
             * 10.0.2.2 is the development machine loopback address (127.0.0.1)
             */
            WebSocket ws = new WebSocketFactory().createSocket("ws://10.0.2.2:9000/echobot");
            ws.addListener(new WebSocketAdapter() {

                @Override
                public void onConnected(WebSocket websocket, Map<String, List<String>> headers)
                        throws Exception {
                    Log.d(TAG, "web socket connected");
                    toast("web socket connected");
                }

                @Override
                public void onTextMessage(WebSocket websocket, String message) throws Exception {
                    Log.d(TAG, "received message from websocket: " + message);
                    toast("received message from websocket: " + message);

                    ExecuteAction executeAction = new ExecuteAction(getApplicationContext());
                    executeAction.notify(message);
                }

            });

            try {
                ws.connect();
                Globals.getInstance().setWebSocket(ws);
            } catch (WebSocketException e) {
                Log.e(TAG, "Cannot connect Web socket", e);
                toast("Cannot connect Web socket");
            }


        } catch (IOException e) {
            Log.e(TAG, "Exception while creating web socket", e);
        }

    }


    public void btnExitSchedules(View v) {

        android.os.Process.killProcess(android.os.Process.myPid());

    }

    public void toast(final String message) {

        // force execution on UI Thread since this can be called from other threads
        this.runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}

