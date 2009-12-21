/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package openmuonline.utils;

/**
 *
 * @author masch
 */
public class Logger {

    public final static int INFO   = 1;
    public final static int ERROR  = 2;
    public final static int STATUS = 4;
    public final static int WARN   = 8;


    public static void log(String msg)
    {
        Logger.log(msg, Logger.INFO);
    }

    public static void log(String msg, int lvl)
    {
        System.out.print(System.currentTimeMillis());
        System.out.println("| " + msg);
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
