/*
 * GNU License.
 */
package canachat;

/**
 * Cana chat main class.
 *
 * @author Breno Viana
 * @version 04/04/2017
 */
public class CanaChatClient {

    /**
     * Runs the client as an application with a closeable frame.
     */
    public static void main(String[] args) throws Exception {
        // Create the client
        Client client = new Client();
        // Get client information
        client.start();
        // Run chat
        client.getHandler().run();
    }
}
