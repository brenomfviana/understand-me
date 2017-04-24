/*
 * GNU License.
 */
package canachatserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A multithreaded chat room server.
 *
 * @author Breno Viana
 * @version 24/04/2017
 */
public class CanaChatServer {

    /**
     * The port that the server listens on.
     */
    private static final int PORT = 9001;

    /**
     * The appplication main method, which just listens on a port and spawns
     * handler threads.
     */
    public static void main(String[] args) {
        try {
            // Message
            System.out.println("The CanaChat server is running on port: " + PORT + ".");
            // Initialize server
            ServerSocket listener = new ServerSocket(PORT);
            // Run
            while (true) {
                new Handler(listener.accept()).start();
            }
        } catch (IOException ex) {
            // Error message
            System.err.println("Error in running CanaChat. The socket could not be created.");
            Logger.getLogger(CanaChatServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
