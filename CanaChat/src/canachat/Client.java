/*
 * GNU License.
 */
package canachat;

import canachat.gui.ChatWindow;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * A simple client for the chat server.
 *
 * @author Breno Viana
 * @version 04/07/2017
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
    // Chat GUI
    private ChatWindow frame;
    // Client handler
    private final Handler handler;

    // Server Address
    private String serverAddress;

    /**
     * Constructs the client.
     */
    public Client() {
        this.language = Language.UNKNOW;
        this.handler = new Handler(this);
    }

    /**
     * Get message delivery.
     *
     * @return Message delivery
     */
    public BufferedReader getIn() {
        return this.in;
    }

    /**
     * Get client name.
     */
    private String getName() {
        return this.name;
    }

    /**
     * Set the client name.
     *
     * @param name Client name
     */
    public void setName(String name) {
        this.name = name;
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
     * Get server address.
     *
     * @return Server address
     */
    public String getServerAddress() {
        return this.serverAddress;
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
     * Print the received messsages.
     *
     * @param message Received message
     */
    public void printMessage(String message) {
        this.frame.getJMessageArea().append("You: " + message + "\n");
        this.out.println(message);
    }

    /**
     * Get client handler.
     *
     * @return Client handler
     */
    public Handler getHandler() {
        return this.handler;
    }

    /**
     * Initialize the chat window.
     */
    public void layout() {

        /* Set the GTK look and feel */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info
                    : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("GTK+".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                javax.swing.UnsupportedLookAndFeelException ex) {
            // Error
            java.util.logging.Logger.getLogger(ChatWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        // Layout
        this.frame = new ChatWindow(this.handler);

        /* Display the chat window. */
        java.awt.EventQueue.invokeLater(() -> {
            frame.setVisible(true);
        });
    }

    /**
     * Start the chat client.
     *
     * @throws java.lang.InterruptedException Thread was interrupted
     */
    public void start() throws InterruptedException {
        // Initializes chat window
        layout();
        // Wait for config
        while (this.language.equals(Language.UNKNOW)) {
            TimeUnit.MILLISECONDS.sleep(10);
        }
    }

    /**
     * Connects to the server then enters the processing loop.
     *
     * @throws java.io.IOException Error on socket
     */
    public void run() throws IOException {
        // Make connection
        Socket socket = new Socket(this.serverAddress, 9001);
        // Initialize streams
        this.in = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream(), true);
        // Send your name to server
        this.out.println(getName() + "\n" + getLanguage().getLanguageID());
        // Update title
        this.frame.setTitle("CanaChat: " + getName());

        // Process all messages from server, according to the protocol.
        while (true) {
            // Get message
            String line = in.readLine();
            // Check protocol
            if (line.startsWith("NAMEACCEPTED")) {
                // Turn on sending messages
                this.frame.getJMessageTextField().setEditable(true);
            } else if (line.startsWith("MESSAGE")) {
                // Print received messages
                this.frame.getJMessageArea().append(line.substring(8) + "\n");
            }
        }
    }
}
