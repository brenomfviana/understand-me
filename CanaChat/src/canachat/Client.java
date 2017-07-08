/*
 * GNU License.
 */
package canachat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A simple client for the chat server.
 *
 * @author Breno Viana
 * @version 07/07/2017
 */
public class Client {

    // Send messages
    private BufferedReader in;
    // Print messages
    private PrintWriter out;

    // Client name
    private String name;
    // Client language
    private Language language;
    // True if the client is ready
    private boolean ready;
    // Conversation
    private String conversation;

    // Lock
    private Lock lock = new ReentrantLock();

    // Server Address
    private String serverAddress;

    /**
     * Constructs the client.
     */
    public Client() {
        this.language = Language.UNKNOW;
        this.ready = false;
        this.conversation = "";
    }

    /**
     * Get client name.
     *
     * @return Client name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get client language.
     *
     * @return Client language.
     */
    public Language getLanguage() {
        return this.language;
    }

    /**
     * Set client language.
     *
     * @param language Client language
     */
    public void setLanguage(Language language) {
        this.language = language;
    }

    /**
     * Set the server address.
     *
     * @param serverAddress Server Address
     */
    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    /**
     * Get conversation.
     *
     * @return Conversation
     */
    public String getConversation() {
        return conversation;
    }

    /**
     * Check if the client is ready.
     *
     * @return True if the client is ready
     */
    public boolean isReady() {
        return this.ready;
    }

    /**
     * Connects to the server and logs in the Cana Chat user.
     *
     * Explain.
     *
     * @param name Client name
     *
     * @throws java.lang.InterruptedException Thread was interrupted
     * @throws java.io.IOException Error on socket
     */
    public void login(String name) throws InterruptedException, IOException {
        // Make connection
        Socket socket = new Socket(this.serverAddress, 9001);
        // Initialize streams
        this.in = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream(), true);
        // Set name
        this.name = name;
        // Send your name and selected language to server
        this.out.println("LOGIN\n" + getName() + "\n" + getLanguage().getLanguageID());
        // Login attempts
        for (int i = 0; ((i < 5) && (!this.ready)); i++) {
            // Wait
            TimeUnit.MILLISECONDS.sleep(10);
            // Get server message
            String line = in.readLine();
            // Check protocol
            if (line.startsWith("SUBMITNAME")) {
                // Send your name and selected language to server
                this.out.println("LOGIN\n" + getName() + "\n" + getLanguage().getLanguageID());
            } else if (line.startsWith("NAMEACCEPTED")) {
                // The client is ready
                this.ready = true;
            }
        }
    }

    /**
     * Send message.
     *
     * @param message Message to be sent
     */
    public void sendMessage(String message) {
        this.conversation = this.conversation.concat("You: " + message + "\n");
        this.out.println("MESSAGE\n" + message);
    }

    /**
     * Receive messages.
     *
     * Explain.
     */
    public void receiveMessages() {
        Thread getMessages = new Thread(new Runnable() {
            @Override
            public void run() {
                // Receive messages
                while (true) {
                    try {
                        // Wait
                        TimeUnit.MILLISECONDS.sleep(10);
                    } catch (InterruptedException ex) {
                        System.err.println("Error in running CanaChat. Waiting time error.");
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    // Check if the client is ready
                    if (ready) {
                        try {
                            // Get server message
                            String line = in.readLine();
                            // Check protocol
                            if (line.startsWith("MESSAGE")) {
                                lock.lock();
                                // Print received messages
                                conversation = conversation.concat(line.substring(8) + "\n");
                                lock.unlock();
                            }
                        } catch (IOException ex) {
                            System.err.println("Error in running CanaChat. The socket could not be created.");
                            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        });
        getMessages.start();
    }
}
