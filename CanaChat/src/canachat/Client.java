/*
 * GNU License.
 */
package canachat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 * A simple client for the chat server.
 *
 * The client follows the Chat Protocol which is as follows. When the server
 * sends "SUBMITNAME" the client replies with the desired screen name. The
 * server will keep sending "SUBMITNAME" requests as long as the client submits
 * screen names that are already in use. When the server sends a line beginning
 * with "NAMEACCEPTED" the client is now allowed to start sending the server
 * arbitrary strings to be broadcast to all chatters connected to the server.
 * When the server sends a line beginning with "MESSAGE" then all characters
 * following this string should be displayed in its message area.
 *
 * @author Breno Viana
 * @version 04/04/2017
 */
public class Client {

    // Send messages
    BufferedReader in;
    // Print messages
    PrintWriter out;
    // Chat GUI
    ChatWindow frame;
    // Client name
    String name;

    /**
     * Constructs the client.
     */
    public Client() {
    }

    /**
     * Prompt for and return the address of the server.
     */
    private String getServerAddress() {
        return JOptionPane.showInputDialog(
                frame,
                "Enter IP Address of the Server:",
                "Welcome to the Chatter",
                JOptionPane.QUESTION_MESSAGE);
    }

    /**
     * Prompt for and return the desired screen name.
     */
    private String setName() {
        return JOptionPane.showInputDialog(
                frame,
                "Choose a screen name:",
                "Screen name selection",
                JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Get client name.
     */
    private String getName() {
        return name;
    }

    /**
     * Connects to the server then enters the processing loop.
     */
    public void run() throws IOException {
        // Make connection
        String serverAddress = getServerAddress();
        Socket socket = new Socket(serverAddress, 9001);
        // Initialize streams
        in = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        // Layout
        frame = new ChatWindow(out);

        /* Display the chat window. */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                frame.setVisible(true);
            }
        });

        // Process all messages from server, according to the protocol.
        while (true) {
            // Get message
            String line = in.readLine();
            // Check protocol
            if (line.startsWith("SUBMITNAME")) {
                // Send messages
                this.name = setName();
                out.println(getName());
                frame.setTitle("CanaChat: " + getName());
            } else if (line.startsWith("NAMEACCEPTED")) {
                // Turn on sending messages
                this.frame.getjTextField().setEditable(true);
            } else if (line.startsWith("MESSAGE")) {
                // Print received messages
                this.frame.getjMessageArea().append(line.substring(8) + "\n");
            }
        }
    }
}
