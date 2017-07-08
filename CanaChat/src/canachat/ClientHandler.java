/*
 * GNU License.
 */
package canachat;

import canachat.gui.ChatWindow;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Client Handler. This class is responsible for handling the client
 * application.
 *
 * @author Breno Viana
 * @version 07/07/2017
 */
public class ClientHandler {

    // Client
    private final Client client;
    // Cana Chat GUI
    private ChatWindow frame;

    /**
     * Constructor of client handler.
     *
     * @param client client
     */
    public ClientHandler(Client client) {
        this.client = client;
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
     * Set the client name.
     *
     * @param name Client name
     *
     * @throws java.lang.InterruptedException Thread was interrupted
     * @throws java.io.IOException Error on socket
     */
    public void setName(String name) throws InterruptedException, IOException {
        this.client.login(name);
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
     * Send message.
     *
     * @param message Message to be sent
     */
    public void send(String message) {
        this.client.sendMessage(message);
        frame.getJMessagesTextArea().setText(client.getConversation());
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
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException ex) {
                    System.err.println("Error in running CanaChat. Waiting time error.");
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
                frame.getJMessagesTextArea().setText(client.getConversation());
            }
        });
        receivedMessages.start();
    }

    /**
     * Run client Cana Chat application.
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
                IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
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
        this.client.receiveMessages();
        printReceivedMessages();
    }
}
