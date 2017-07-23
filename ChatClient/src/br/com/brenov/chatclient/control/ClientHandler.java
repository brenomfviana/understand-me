/*
 * GNU License.
 */
package br.com.brenov.chatclient.control;

import br.com.brenov.chatclient.model.Client;
import br.com.brenov.chatclient.model.Language;
import br.com.brenov.chatclient.view.ChatWindow;
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
import javax.swing.text.BadLocationException;

/**
 * Client Handler. This class is responsible for handling the client
 * application.
 *
 * @author Breno Viana
 * @version 23/07/2017
 */
public class ClientHandler {

    // Client
    private final Client client;
    // Chat GUI
    private ChatWindow frame;

    // If the chat needs to update the conversation
    private boolean update;

    // Lock
    private Lock lock = new ReentrantLock();

    /**
     * Constructor of client handler.
     *
     * @param client client
     */
    public ClientHandler(Client client) {
        this.client = client;
        this.update = false;
    }

    /**
     * Run client application.
     */
    public void run() {
        /* Set the GTK look and feel */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info
                    : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("GTK+".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException |
                IllegalAccessException |
                javax.swing.UnsupportedLookAndFeelException ex) {
            // Error
            java.util.logging.Logger.getLogger(ChatWindow.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        }
        // Layout
        this.frame = new ChatWindow(this);
        /* Display the chat window. */
        java.awt.EventQueue.invokeLater(() -> {
            frame.setVisible(true);
        });
        // Get reveived messages
        receiveMessages();
        printReceivedMessages();
    }

    /**
     * Check if the client is ready.
     *
     * @return True if the client is ready
     */
    public boolean isReady() {
        return this.client.isReady();
    }

    /**
     * Set the server address.
     *
     * @param serverAddress Server address
     */
    public void setServerIPAddress(String serverAddress) {
        this.client.setServerAddress(serverAddress);
    }

    /**
     * Set the client language.
     *
     * @param language Client language
     */
    public void setLanguage(Language language) {
        this.client.setLanguage(language);
    }

    /**
     * Get client name.
     *
     * @return Client name
     */
    public String getName() {
        return this.client.getName();
    }

    /**
     * Connects to the server and logs in the chat user. The login is done by
     * sending a message to the server containing the word "LOGIN", the name and
     * language of the user. So the client waits for 10 milliseconds to respond
     * to the server. (Chat Protocol) If the client receives the message
     * "SUBMITNAME" means that the login could not be done, or the server did
     * not receive the data or the name is not available. When the server sends
     * "NAMEACCEPTED" it means that the login was successful and the client can
     * start to chat.
     *
     * @param name Client name
     *
     * @throws java.lang.InterruptedException Thread was interrupted
     * @throws java.io.IOException Error on socket
     */
    public void login(String name) throws InterruptedException, IOException {
        // Make connection
        Socket socket = new Socket(this.client.getServerAddress(), 9001);
        // Initialize streams
        this.client.setIn(new BufferedReader(new InputStreamReader(
                socket.getInputStream())));
        this.client.setOut(new PrintWriter(socket.getOutputStream(), true));
        // Set name
        this.client.setName(name);
        // Send your name and selected language to server
        this.client.getOut().println("LOGIN\n" + getName() + "\n"
                + this.client.getLanguage().getLanguageID());
        // Login attempts
        for (int i = 0; ((i < 5) && (!this.client.isReady())); i++) {
            // Wait
            TimeUnit.MILLISECONDS.sleep(10);
            // Get server message
            String line = this.client.getIn().readLine();
            // Check protocol
            if (line.startsWith("SUBMITNAME")) {
                // Send your name and selected language to server
                this.client.getOut().println("LOGIN\n" + getName() + "\n"
                        + this.client.getLanguage().getLanguageID());
            } else if (line.startsWith("NAMEACCEPTED")) {
                // The client is ready
                this.client.setReady(true);
            }
        }
    }

    /**
     * Send message.
     *
     * @param message Message to be sent
     */
    public void send(String message) {
        sendMessage(message);
        frame.getJConversation().setText(client.getConversation());
        align();
    }

    /**
     * Send message.
     *
     * @param message Message to be sent
     */
    public void sendMessage(String message) {
        this.client.setConversation(this.client.getConversation()
                .concat("<html><b>You: " + message + "</b><br>"));
        this.client.getOut().println("MESSAGE\n" + message);
    }

    /**
     * Receive messages. When the client is already logged in to the chat, he is
     * then able to receive messages. Messages sent by the server must be
     * preceded by the word "MESSAGE" and all subsequent characters form the
     * message of another client.
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
                        System.err.println("Error in running Understand Me. "
                                + "Waiting time error.");
                        Logger.getLogger(Client.class.getName())
                                .log(Level.SEVERE, null, ex);
                    }
                    // Check if the client is ready
                    if (client.isReady()) {
                        try {
                            // Get server message
                            String line = client.getIn().readLine();
                            // Check protocol
                            if (line.startsWith("MESSAGE")) {
                                lock.lock();
                                // Print received messages
                                client.setConversation(client.getConversation()
                                        .concat(line.substring(8) + "<br>"));
                                update = true;
                                lock.unlock();
                            }
                        } catch (IOException ex) {
                            System.err.println("Error in running Understand Me. "
                                    + "The socket could not be created.");
                            Logger.getLogger(Client.class.getName())
                                    .log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        });
        getMessages.start();
    }

    /**
     * Print received messages.
     */
    private void printReceivedMessages() {
        Thread receivedMessages = new Thread(() -> {
            // Print received messages
            while (true) {
                try {
                    // Wait
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException ex) {
                    System.err.println("Error in running Understand Me."
                            + " Waiting time error.");
                    Logger.getLogger(Client.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
                lock.lock();
                // Check conversation
                if (update && !client.getConversation().equals("")) {
                    frame.getJConversation().setText(client.getConversation());
                    update = false;
                    align();
                }
                lock.unlock();
            }
        });
        receivedMessages.start();
    }

    /**
     * Align the conversation so the user does not miss new messages.
     */
    private void align() {
        try {
            // Wait
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException ex) {
            System.err.println("Error in running Understand Me."
                    + " Waiting time error.");
            Logger.getLogger(Client.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        int end = this.frame.getJConversation().getDocument().getLength();
        try {
            this.frame.getJConversation()
                    .scrollRectToVisible(this.frame
                            .getJConversation().modelToView(end));
        } catch (BadLocationException ex) {
            Logger.getLogger(ChatWindow.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
}
