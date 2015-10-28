package legolu.servicescheduler;

import android.net.wifi.WifiManager;

import com.neovisionaries.ws.client.WebSocket;

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
    private final String destinationNumber;

    private WebSocket webSocket;

    private Globals() {

        this.destinationNumber = "0123456789";
    }

    //TODO usare Optional
    public WifiManager.WifiLock getWifiLock() {
        return wifiLock;
    }
    public void setWifiLock(WifiManager.WifiLock lock) {
        this.wifiLock = lock;
    }

    public String getDestinationNumber() {
        return destinationNumber;
    }

    public WebSocket getWebSocket() {
        return webSocket;
    }
    public void setWebSocket(WebSocket webSocket) {
        this.webSocket = webSocket;
    }
}
