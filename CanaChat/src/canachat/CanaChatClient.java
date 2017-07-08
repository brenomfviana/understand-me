/*
 * GNU License.
 */
package canachat;

/**
 * Cana chat main class.
 *
 * @author Breno Viana
 * @version 07/07/2017
 */
public class CanaChatClient {

    /**
     * Runs the Cana Chat client application.
     *
     * @param args Arguments
     */
    public static void main(String[] args) {
        // Create the client
        Client client = new Client();
        // Create the client handler
        ClientHandler handler = new ClientHandler(client);
        // Start client
        handler.run();
    }
}
