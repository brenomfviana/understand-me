/*
 * GNU License.
 */
package br.com.brenov.chatclient.model;

import java.io.BufferedReader;
import java.io.PrintWriter;

/**
 * A simple client for the chat server. This chat follows the Chat Protocol
 * which is explained in the documentation for the login and receiveMessages.
 *
 * @author Breno Viana
 * @version 23/07/2017
 */
public class Client {

    // Client instance
    private static Client instance = new Client();

    /**
     * Get client instance.
     *
     * @return Client
     */
    public static Client getInstance() {
        return instance;
    }

    // Send messages
    private BufferedReader in;
    // Print messages
    private PrintWriter out;
    // Server Address
    private String serverAddress;

    // Client name
    private String name;
    // Client language
    private Language language;
    // True if the client is ready
    private boolean ready;
    // Conversation
    private String conversation;

    /**
     * Constructs the client.
     */
    private Client() {
        this.language = Language.UNKNOW;
        this.ready = false;
        this.conversation = "";
    }

    /**
     * Get input reader.
     *
     * @return Input reader
     */
    public BufferedReader getIn() {
        return in;
    }

    /**
     * Set input reader.
     *
     * @param in Input reader
     */
    public void setIn(BufferedReader in) {
        this.in = in;
    }

    /**
     * Get output writer.
     *
     * @return Output writer.
     */
    public PrintWriter getOut() {
        return out;
    }

    /**
     * Set output writer.
     *
     * @param out Output writer
     */
    public void setOut(PrintWriter out) {
        this.out = out;
    }

    /**
     * Get the server address.
     *
     * @return Server Address
     */
    public String getServerAddress() {
        return serverAddress;
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
     * Set client name.
     *
     * @param name Client name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get client name.
     *
     * @return Client name
     */
    public String getName() {
        return this.name;
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
     * Check if the client is ready.
     *
     * @return True if the client is ready
     */
    public boolean isReady() {
        return this.ready;
    }

    /**
     * Set ready status.
     *
     * @param ready If the client is ready
     */
    public void setReady(boolean ready) {
        this.ready = ready;
    }

    /**
     * Get conversation.
     *
     * @return Conversation
     */
    public String getConversation() {
        return this.conversation;
    }

    /**
     * Set conversation.
     *
     * @param conversation Conversation
     */
    public void setConversation(String conversation) {
        this.conversation = conversation;
    }
}
