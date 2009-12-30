/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cosmos.utils.log;

import java.util.ArrayList;

/**
 *
 * @author masch
 */
public class Logger {

    public final static int INFO   = 1;
    public final static int ERROR  = 2;
    public final static int STATUS = 4;
    public final static int WARN   = 8;

    private static ArrayList<LogWriter> logger = new ArrayList<LogWriter>();
    
    public static void attachLogger(LogWriter logger)
    {
        Logger.logger.add(logger);
    }

    public static void log(String msg)
    {
        Logger.log(msg, Logger.INFO);
    }

    public static void log(String msg, int lvl)
    {
        for(LogWriter wr: Logger.logger)
        {
            wr.open();
            wr.write(msg, lvl);
            wr.close();
        }
    }

    public static void status(String msg)
    {
        Logger.log(msg, Logger.STATUS);
    }

    public static void error(String msg)
    {
        Logger.log(msg, Logger.ERROR);
    }

}
