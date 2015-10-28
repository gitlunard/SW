package legolu.servicescheduler;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * Il metodo doInBackgroud di AsyncTask viene eseguito su un altro thread.
 *
 * @author leo
 * @deprecated
 *
 */
public class ServerAsyncNotify extends AsyncTask{

    public static final int SERVERPORT = 5555;
    private boolean running = false;
    private PrintWriter mOut;

    @Override
    protected Object doInBackground(Object... arg0) {
        try {

            Log.d("ServerNotify", "S: Connecting...");

            //create a server socket. A server socket waits for requests to come in over the network.
            ServerSocket serverSocket = new ServerSocket(SERVERPORT);

            //create client socket... the method accept() listens for a connection to be made to this socket and accepts it.
            Socket client = serverSocket.accept();

            Log.d("MyApp", "S: Receiving...");

            try {

                //sends the message to the client
                mOut = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())), true);

                //read the message received from client
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

                //in this while we wait to receive messages from client (it's an infinite loop)
                //this while it's like a listener for messages
                while (running) {

                    String message = in.readLine();




                    Log.d("MyApp",message);

                    mOut.println("received: " + message);
                }

            } catch (Exception e) {
                System.out.println("S: Error");
                e.printStackTrace();
            } finally {
                client.close();
                System.out.println("S: Done.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;


    }



}
