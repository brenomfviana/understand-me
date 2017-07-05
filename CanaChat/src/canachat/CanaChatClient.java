/*
 * GNU License.
 */
package canachat;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    public static void main(String[] args) {
        try {
            // Create the client
            Client client = new Client();
            // Get client information
            client.start();
            // Run chat
            client.getHandler().run();
        } catch (IOException ex) {
            // Error message
            Logger.getLogger(CanaChatClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            // Error message
            Logger.getLogger(CanaChatClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
