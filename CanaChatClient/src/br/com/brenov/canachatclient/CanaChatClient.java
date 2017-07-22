/*
 * GNU License.
 */
package br.com.brenov.canachatclient;

import br.com.brenov.canachatclient.control.ClientHandler;
import br.com.brenov.canachatclient.model.Client;

/**
 * Cana Chat main class.
 *
 * @author Breno Viana
 * @version 22/07/2017
 */
public class CanaChatClient {

    /**
     * Runs the Cana Chat client application.
     *
     * @param args Arguments
     */
    public static void main(String[] args) {
        // Create the client handler and starts client
        (new ClientHandler(Client.getInstance())).run();
    }
}
