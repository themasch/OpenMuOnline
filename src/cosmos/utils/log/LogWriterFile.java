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
