package gateways;

import exceptions.html.OpenBrowserException;
import exceptions.html.HTMLWriteException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.awt.Desktop;
import java.net.URI;

/**
 * Handles HTML writing
 */
public class HTMLManager {
    private final String htmlFileName;
    private final String htmlPath;
    private final HTMLWritable hw;

    /**
     * Constructs a HTMLManager object
     * @param hw HTML writable object to initialize with
     */
    public HTMLManager(HTMLWritable hw){
        String sep = System.getProperty("file.separator");
        this.hw = hw;
        htmlPath = System.getProperty("user.dir") + sep;
        htmlFileName =  hw.getHTMLFileName();
    }

    /**
     * Gets an absolute path to a downloaded HTML file
     * @return an absolute path to a downloaded HTML file
     */
    public String getDownloadLocation(){
        return htmlPath + htmlFileName;
    }

    /**
     * Generates HTML contents
     * @throws HTMLWriteException is thrown if a HTML file cannot be created
     */
    public void generateHTML() throws HTMLWriteException {
        String htmlTitle = hw.getHTMLFileName();
        String htmlBody = hw.getHTMLBody();
        String fullHTML =
            "<html>" +
            "<head>" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">" +
                "<title>" + htmlTitle + "</title>" +
            "</head>" +
            "<body> " + htmlBody + "</body>" +
            "</html>";

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(htmlFileName));
            bw.write(fullHTML);
            bw.close();
        } catch (IOException e) { throw new HTMLWriteException(); }
    }

    /**
     * Tries to open a created HTML in a browser
     * @throws HTMLWriteException is thrown if a HTML file location is invalid
     */
    public void openHTML() throws HTMLWriteException{
        // output a html in a browser
        try {
            URI uri = new URI(htmlFileName);
            Desktop desktop = Desktop.getDesktop();
            desktop.browse(uri);
        }
        catch (URISyntaxException e) { throw new HTMLWriteException(); }
        catch (IOException e){
            // Mac does not open a browser due to the security policy
            // Nothing to do
            //throw new OpenBrowserException();
        }
    }
}

