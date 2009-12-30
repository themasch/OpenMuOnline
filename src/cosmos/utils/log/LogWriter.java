/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cosmos.utils.log;

/**
 *
 * @author masch
 */
public interface LogWriter {
    
    public boolean open();
    public void close();
    public void write(String msg, int level);

}
