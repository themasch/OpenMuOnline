/*
 * OpenMuOnline - a open source mu online server
 * Copyright (C) 2009 Mark Schmale
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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

    public static synchronized void log(String msg, int lvl)
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
