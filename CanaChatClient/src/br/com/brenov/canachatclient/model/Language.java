/*
 * GNU License.
 */
package br.com.brenov.canachatclient.model;

/**
 * Supported languages.
 *
 * @author Breno Viana
 * @version 12/07/2017
 */
public enum Language {

    UNKNOW("", ""),
    ENGLISH("EN", "English"),
    PORTUGUESE("PT", "Português"),
    SPANISH("ES", "Español"),
    FRENCH("FR", "Français"),
    GERMAN("DE", "Deutsch"),
    ITALIAN("IT", "Italiano"),
    DUTCH("NL", "Nederlands");

    // Language ID
    private String id;

    // Language Name
    private String name;

    /**
     * Constructor.
     *
     * @param id Language ID
     * @param name Language Name
     */
    private Language(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Get language ID.
     *
     * @return Language ID
     */
    public String getLanguageID() {
        return this.id;
    }

    /**
     * Get language name.
     *
     * @return Language name
     */
    public String getName() {
        return this.name;
    }
}
