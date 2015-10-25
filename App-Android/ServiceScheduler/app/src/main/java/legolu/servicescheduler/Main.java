package legolu.servicescheduler;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import legolu.servicelegolu.R;


public class Main extends Activity {


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
            Log.d("Main", "wifi lock acquired");
        }
    }



    public void btnStartSchedule(View v) {

        try {

            new Thread(new ThreadServer(getApplicationContext())).start();

            /*LB: Quello che segue è commentato perchè non funziona la ricezione sulla socket
            * Sarebbe meglio fare andare questo ed eliminare ThreadServer.java*/
            //new ServerAsyncNotify().execute();

            toast("Start Service...");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void btnCancelSchedules(View v) {

        toast("Cancel...");

    }
    public void btnExitSchedules(View v) {

        android.os.Process.killProcess(android.os.Process.myPid());

    }

    public void toast(String message) {

        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

    }

}

