/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cosmos.utils.log;

/**
 *
 * @author masch
 */
public class LogWriterStdOut implements LogWriter {

    public void close() {}

    public boolean open() { return true; }

    public void write(String msg, int level) {
        System.out.print(System.currentTimeMillis());
        System.out.println(" | " + msg);
    }


}
