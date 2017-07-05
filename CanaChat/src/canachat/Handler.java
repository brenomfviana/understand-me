/*
 * GNU License.
 */
package canachat;

import java.io.IOException;

/**
 * Client Handler.
 *
 * @author Breno Viana
 * @version 04/07/2017
 */
public class Handler {

    // Client
    private final Client client;

    /**
     * Constructor of client handler.
     *
     * @param client client
     */
    public Handler(Client client) {
        this.client = client;
    }

    /**
     * Set the server address.
     *
     * @param serverAddress Server address
     */
    public void setIPAddress(String serverAddress) {
        this.client.setServerAddress(serverAddress);
    }

    /**
     * Set the client name.
     *
     * @param name Client name
     */
    public void setName(String name) {
        this.client.setName(name);
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
     * Print the received messages.
     *
     * @param message Received message
     */
    public void setOut(String message) {
        this.client.printMessage(message);
    }

    /**
     * Run the chat client.
     *
     * @throws java.io.IOException Error while receiving or sending messages
     */
    public void run() throws IOException {
        this.client.run();
    }
}
