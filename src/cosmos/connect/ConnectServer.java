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

package cosmos.connect;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

/**
 *
 * @author masch
 */
public class ConnectServer extends Thread {

    private ServerSocket sock;
    protected Logger logger;
    public ConnectServer(int port)
    {
        // threading stuff
        this.setName("ConnectServer");
        // loggind stuff
        this.logger = Logger.getLogger("cosmos.connect");
        try {
            this.sock = new ServerSocket(port);
        }
        catch(java.io.IOException e)
        {
            this.logger.warning(e.getLocalizedMessage());
        }
    }


    @Override
    public void run()
    {
        this.logger.entering("ConnectServer", "run");
        try {
            while(true)
            {
                Socket newClient = this.sock.accept();
                this.logger.info("new connection from: " + newClient.getInetAddress().getHostAddress());
                ConnectHandler hdl = new ConnectHandler(newClient);
                hdl.start();
            }
        }
        catch(java.io.IOException e)
        {
            this.logger.warning(e.getLocalizedMessage());
        }
        this.logger.exiting("ConnectServer", "run");
    }
}
