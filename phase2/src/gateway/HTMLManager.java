package gateway;

import exceptions.HTMLWriteErrorException;

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
    private String htmlFileName;
    private String htmlTitle;
    private String htmlBody;
    private String currDir;

    /**
     * Constructs a HTMLManager object
     * @param hw HTML writable object to initialize with
     */
    public HTMLManager(HTMLWritable hw){
        String sep = System.getProperty("file.separator");
        currDir = System.getProperty("user.dir") + sep;

        htmlFileName =  hw.getHTMLFileName();
        htmlTitle = hw.getHTMLFileName();
        htmlBody = hw.getHTMLBody();
    }

    /**
     * Gets an absolute path to a downloaded HTML file
     * @return an absolute path to a downloaded HTML file
     */
    public String getDownloadLocation(){
        return currDir + htmlFileName;
    }

    /**
     * Generates HTML contents
     * @throws HTMLWriteErrorException is thrown if a HTML file cannot be created
     */
    public void generateHTML() throws HTMLWriteErrorException {
        String fullHTML =
            "<html>" +
            "<head>" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">" +
                "<title>" + htmlTitle + "</title>" +
            "</head>" +
            "<body> " + htmlBody + "</body>" +
            "</html>";

        BufferedWriter bw = null;

        try {
            bw = new BufferedWriter(new FileWriter(htmlFileName));
            bw.write(fullHTML) ;
        } catch (IOException e) {
            throw new HTMLWriteErrorException();
        } finally{
            try {
                bw.close();
            } catch (IOException e){
                throw new HTMLWriteErrorException();
            }
        }
    }

    /**
     * Tries to open a created HTML in a browser
     * @throws HTMLWriteErrorException is thrown if a HTML file location is invalid
     */
    public void openHTML() throws HTMLWriteErrorException {

        // output a html in a browser
        try {
            URI uri = new URI(htmlFileName);
            Desktop desktop = Desktop.getDesktop();
            desktop.browse(uri);
        } catch (URISyntaxException e) {
            throw new HTMLWriteErrorException();
        } catch (IOException e){
            // Fail to open a browser. Nothing to do.
        }
    }
}

