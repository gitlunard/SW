package legolu.servicescheduler;

import android.net.wifi.WifiManager;

/**
 * Application globals.
 *
 * Created by Davide on 24/10/2015.
 *
 */
public class Globals {

    private static Globals ourInstance = new Globals();

    public static Globals getInstance() {
        return ourInstance;
    }

    private WifiManager.WifiLock wifiLock;

    private Globals() {
    }

    //TODO usare Optional
    public WifiManager.WifiLock getWifiLock() {
        return wifiLock;
    }
    public void setWifiLock(WifiManager.WifiLock lock) {
        this.wifiLock = lock;
    }
}
