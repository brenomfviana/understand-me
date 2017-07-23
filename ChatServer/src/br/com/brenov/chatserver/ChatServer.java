/*
 * GNU License.
 */
package br.com.brenov.chatserver;

import br.com.brenov.chatserver.control.ChatServerHandler;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A multithreaded chat room server.
 *
 * @author Breno Viana
 * @version 07/07/2017
 */
public class ChatServer {

    /**
     * The port that the server listens on.
     */
    private static final int PORT = 9001;

    /**
     * The appplication main method, which just listens on a port and spawns
     * chat handler threads.
     *
     * @param args The first argument will be used to read the Google API Key
     */
    public static void main(String[] args) {
        try {
            // Server Log
            System.out.println("The Understand Me server is running on "
                    + "port: " + PORT + ".");
            // Initialize server
            ServerSocket listener = new ServerSocket(PORT);
            // Run server and create a chat handler to each client
            while (true) {
                new ChatServerHandler(listener.accept(), args[0]).start();
            }
        } catch (IOException ex) {
            // Error message
            System.err.println("Error in running Understand Me server. "
                    + "The socket could not be created.");
            Logger.getLogger(ChatServer.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

}
