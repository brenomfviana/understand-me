/*
 * GNU License.
 */
package canachat;

/**
 * Supported languages.
 *
 * @author Breno Viana
 * @version 24/04/2017
 */
public enum Language {

    UNKNOW(""),
    ENGLISH("EN"),
    PORTUGUESE("PT"),
    SPANISH("ES");

    // Language ID
    private String value;

    /**
     * Constructor.
     *
     * @param value Language ID
     */
    private Language(String value) {
        this.value = value;
    }

    /**
     * Get language ID.
     *
     * @return Language ID
     */
    public String getValue() {
        return value;
    }
}

