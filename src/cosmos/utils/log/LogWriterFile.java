/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cosmos.utils.log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
/**
 *
 * @author masch
 */
public class LogWriterFile implements LogWriter {

    FileWriter fw;
    String   path;

    public LogWriterFile(String path) throws java.io.IOException
    {
       this.path = path;
    }

    public void close() {
        try {
            this.fw.close();
        }
        catch(IOException e)
        {
            System.err.println(e.getLocalizedMessage());
        }
    }

    public boolean open() {
        try {
            this.fw = new FileWriter(path, true);
        }
        catch(IOException e)
        {
            System.err.println(e.getLocalizedMessage());
            return false;
        }
        return true;
    }

    public void write(String msg, int level) {
        try {
            String timestamp = Long.toString(System.currentTimeMillis());
            this.fw.write(timestamp + " | " + msg + System.getProperty("line.separator"));
        }
        catch(IOException e)
        {
            System.err.println(e.getLocalizedMessage());
        }
    }

}
