/*
 * GNU License.
 */
package canachatserver;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import com.google.api.services.translate.Translate;
import com.google.api.services.translate.model.TranslationsListResponse;
import com.google.api.services.translate.model.TranslationsResource;
import java.util.Arrays;
import java.util.Map;

/**
 * A handler thread class. Handlers are spawned from the listening loop and are
 * responsible for a dealing with a single client and broadcasting its messages.
 *
 * @author Breno Viana
 * @version 05/04/2017
 */
public class Handler extends Thread {

    // Receive client messages
    private BufferedReader in;
    // Send client messages
    private PrintWriter out;

    // Client name
    private String name;
    // Client language
    private int language;

    // Socket
    private Socket socket;

    // Google API key
    private static String GOOGLE_APPLICATION_CREDENTIALS
            = "AIzaSyAy1cWAHTruRbvTqCFdxUbgundOxr7YEks";

    /**
     * The set of all names of clients in the chat room. Maintained so that we
     * can check that new clients are not registering name already in use.
     */
    private static HashMap<String, Integer> names = new HashMap<String, Integer>();

    /**
     * The set of all the print writers for all the clients. This set is kept so
     * we can easily broadcast messages.
     */
    private static HashMap<PrintWriter, Integer> writers = new HashMap<PrintWriter, Integer>();

    /**
     * Constructs a handler thread, squirreling away the socket. All the
     * interesting work is done in the run method.
     */
    public Handler(Socket socket) {
        this.socket = socket;
    }

    /**
     * Services this thread's client by repeatedly requesting a screen name
     * until a unique one has been submitted, then acknowledges the name and
     * registers the output stream for the client in a global set, then
     * repeatedly gets inputs and broadcasts them.
     */
    public void run() {
        try {
            // Create character streams for the socket.
            in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            // Request a name from this client.  Keep requesting until
            // a name is submitted that is not already used.  Note that
            // checking for the existence of a name and adding the name
            // must be done while locking the set of names.
            while (true) {
                out.println("SUBMITNAME");
                name = in.readLine();
                language = Integer.valueOf(in.readLine());
                if (name == null) {
                    return;
                }
                synchronized (names) {
                    if (!names.keySet().contains(name)) {
                        names.put(name, language);
                        break;
                    }
                }
            }

            // Now that a successful name has been chosen, add the
            // socket's print writer to the set of all writers so
            // this client can receive broadcast messages.
            out.println("NAMEACCEPTED");
            writers.put(out, language);

            // Accept messages from this client and broadcast them.
            // Ignore other clients that cannot be broadcasted to.
            while (true) {
                String input = in.readLine();
                if (input == null) {
                    return;
                }
                for (Map.Entry<PrintWriter, Integer> writer : writers.entrySet()) {
                    try {
                        // 
                        Translate t = new Translate.Builder(
                                GoogleNetHttpTransport.newTrustedTransport(),
                                GsonFactory.getDefaultInstance(), null)
                                // Need to update this to your App-Name
                                .setApplicationName("CanaChat")
                                .build();
                        Translate.Translations.List list = t.new Translations().list(
                                Arrays.asList(input),
                                //Target language
                                ((writer.getValue()) == Languages.ENGLISH) ? "EN"
                                        : (writer.getValue() == Languages.PORTUGUESE ? "PT" : "ES"));
                        // Google Cloud API
                        list.setKey(GOOGLE_APPLICATION_CREDENTIALS);
                        // Translate
                        TranslationsListResponse response = list.execute();
                        // Send messages
                        for (TranslationsResource tr : response.getTranslations()) {
                            writer.getKey().println("MESSAGE " + name + ": " + tr.getTranslatedText());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            // This client is going down!  Remove its name and its print
            // writer from the sets, and close its socket.
            if (name != null) {
                names.remove(name);
            }
            if (out != null) {
                writers.remove(out);
            }
            try {
                socket.close();
            } catch (IOException e) {
            }
        }
    }
}
