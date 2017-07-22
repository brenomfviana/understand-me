/*
 * GNU License.
 */
package br.com.brenov.chatclient;

import br.com.brenov.chatclient.control.ClientHandler;
import br.com.brenov.chatclient.model.Client;

/**
 * Chat main class.
 *
 * @author Breno Viana
 * @version 22/07/2017
 */
public class ChatClient {

    /**
     * Runs the chat client application.
     *
     * @param args Arguments
     */
    public static void main(String[] args) {
        // Create the client handler and starts client
        (new ClientHandler(Client.getInstance())).run();
    }
}
