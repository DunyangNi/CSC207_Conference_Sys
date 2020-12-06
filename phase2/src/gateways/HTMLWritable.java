package gateways;

/**
 * Allows to generate a downloadable HTML
 */
public interface HTMLWritable {
    /**
     * @return A HTML file name to be generated
     */
    String getHTMLFileName();

    /**
     * @return A title for the HTML to be generated
     */
    String getHTMLTitle();

    /**
     * @return A body for the HTML to be generated
     */
    String getHTMLBody();
}
