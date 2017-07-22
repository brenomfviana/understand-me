/*
 * GNU License.
 */
package br.com.brenov.chatserver.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Arrays;
import java.util.Map;
import java.net.Socket;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.translate.Translate;
import com.google.api.services.translate.model.TranslationsListResponse;
import com.google.api.services.translate.model.TranslationsResource;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import java.util.AbstractMap;

/**
 * Server handler class.
 *
 * When a client connects to the server, the server requests the client login by
 * sending "SUBMITNAME" messages until the client enters a name that was not
 * used. When the client chooses a unique name, the server sends a message
 * confirming the login "NAMEACCEPTED". Then, messages sent by that client will
 * be forwarded to other clients, translating them to the respective languages
 * of each logged client.
 *
 * Messages sent to this client must be sent with the word "MESSAGE" as
 * protocol, and will be forwarded with the word "MESSAGE" prefixed.
 *
 * @author Breno Viana
 * @version 12/07/2017
 */
public class ChatServerHandler extends Thread {

    // Receive client messages
    private BufferedReader in;
    // Forward client messages
    private PrintWriter out;

    // Client name
    private String name;
    // Client language
    private String language;

    // Socket
    private Socket socket;
    // API Key
    private String api;

    /**
     * The set of chat clients, with their names and languages. This is the set
     * clients logged in to chat, it is used to not allow users to not use
     * equal.
     */
    private static HashMap<String, String> names = new HashMap<String, String>();

    /**
     * The set of all the print writers for all the clients. This set is kept so
     * we can easily broadcast messages.
     */
    private static HashMap<PrintWriter, HashMap.Entry<String, String>> writers
            = new HashMap<PrintWriter, HashMap.Entry<String, String>>();

    /**
     * Construct a server handler.
     *
     * @param socket Server socket
     */
    public ChatServerHandler(Socket socket, String api) {
        this.socket = socket;
        this.api = api;
    }

    /**
     * Run Cana Char Server. It requests clients login and resends messages,
     * translating them into each client's language if necessary.
     */
    @Override
    public void run() {
        try {
            // Create character streams for the socket
            this.in = new BufferedReader(new InputStreamReader(
                    this.socket.getInputStream()));
            this.out = new PrintWriter(this.socket.getOutputStream(), true);

            // Request the clients login (client name and language), the request
            // is retained until the client chooses a name that was not used.
            while (true) {
                // Send request
                this.out.println("SUBMITNAME");
                // Check protocol
                String protocol = this.in.readLine();
                if (protocol.equals("LOGIN")) {
                    // Get name and language of the client
                    this.name = this.in.readLine();
                    this.language = this.in.readLine();
                    // Check if is a valid value and a valid language
                    if (this.name == null || this.language == null) {
                        return;
                    }
                    // Client login
                    synchronized (this.names) {
                        // Check if the name is already used
                        if (!this.names.keySet().contains(this.name)) {
                            // Adds the client to chat
                            this.names.put(this.name, this.language);
                            break;
                        }
                    }
                }
            }

            // Send successful message
            this.out.println("NAMEACCEPTED");
            // Creates a print writer for the client
            this.writers.put(this.out,
                    (new AbstractMap.SimpleEntry<String, String>(this.name,
                            this.language)));

            // Receive messages from each client and forward to other clients
            while (true) {
                // Check protocol
                String protocol = this.in.readLine();
                if (protocol.equals("MESSAGE")) {
                    // Get message
                    String input = this.in.readLine();
                    // Server Log
                    System.out.println("Sent by: " + this.name);
                    System.out.println("Original message: " + input);
                    // Check if the message is valid
                    if (input == null) {
                        return;
                    }
                    // Send message to all chat clients and if necessary
                    // translate the message into the respective language
                    for (Map.Entry<PrintWriter, HashMap.Entry<String, String>> writer
                            : this.writers.entrySet()) {
                        // Ignores the client who sent the message
                        if (writer.getValue().getKey().equals(this.name)) {
                            continue;
                        }
                        // Checks if the language of this client is the same as
                        // the client that sent the message
                        if (!writer.getValue().getValue().equals(this.language)) {
                            try {
                                // Initialize the translator
                                Translate t = new Translate.Builder(
                                        GoogleNetHttpTransport.newTrustedTransport(),
                                        GsonFactory.getDefaultInstance(), null)
                                        .setApplicationName("CanaChat")
                                        .build();
                                // Prepare to translate
                                Translate.Translations.List list = t.new Translations().list(
                                        Arrays.asList(input),
                                        // Target language
                                        writer.getValue().getValue());
                                // Server Log
                                System.out.println(writer.getValue());
                                // Google Cloud API
                                list.setKey(this.api);
                                // Translate message
                                TranslationsListResponse response = list.execute();
                                // Send messages
                                for (TranslationsResource tr : response.getTranslations()) {
                                    // Send translated message
                                    writer.getKey().println("MESSAGE "
                                            + this.name + ": " + tr.getTranslatedText());
                                    // Server Log
                                    System.out.println("Message translated ("
                                            + writer.getValue().getValue() + "): " + tr.getTranslatedText());
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            try {
                                // Send original message
                                writer.getKey().println("MESSAGE " + this.name + ": " + input);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            // The client left the chat, removes it and it's print writer from
            // the sets, and close it's socket
            if (this.name != null) {
                this.names.remove(this.name);
            }
            if (this.out != null) {
                this.writers.remove(this.out);
            }
            try {
                this.socket.close();
            } catch (IOException e) {
            }
        }
    }
}
