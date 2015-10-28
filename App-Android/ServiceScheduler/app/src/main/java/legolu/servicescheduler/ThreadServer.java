package legolu.servicescheduler;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;


/**
 * The class extends the Thread class so we can receive and send messages at the same time
 * @deprecated
 */
public class ThreadServer implements Runnable {

    public static final int SERVERPORT = 4444;
    private boolean running = false;
    private PrintWriter mOut;
    private final Context context;


    /**
     * Constructor of the class
     * @param
     */
    public ThreadServer(Context context) {

        this.context = context;
    }

    @Override
    public void run() {

        running = true;

        try {

            Log.d("MyApp","S: Connecting...");

            //create a server socket. A server socket waits for requests to come in over the network.
            ServerSocket serverSocket = new ServerSocket(SERVERPORT);

            //create client socket... the method accept() listens for a connection to be made to this socket and accepts it.
            Socket client = serverSocket.accept();

            try {

                ExecuteAction executeAction = new ExecuteAction(context);
                //sends the message to the client
                mOut = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())), true);

                //read the message received from client
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

                //in this while we wait to receive messages from client (it's an infinite loop)
                //this while it's like a listener for messages
                while (running) {

                    String message = in.readLine();

                    Log.d("MyApp", message);

                    mOut.println("received: " + message);

                    executeAction.notify(message);

                }

            } catch (Exception e) {
                System.out.println("S: Error");
                e.printStackTrace();
            } finally {
                client.close();
                System.out.println("S: Done.");
            }

        } catch (Exception e) {
            System.out.println("S: Error");
            e.printStackTrace();
        }

    }

}
