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


package cosmos.login;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Logger;
import java.net.Socket;

/**
 *
 * @author masch
 */
public class LoginServer extends Thread {

    private ServerSocket sock;
    protected Logger logger;
    public LoginServer(int port)
    {
        // threading stuff
        this.setName("LoginServer");
        // setup logger
        this.logger = Logger.getLogger("cosmos.login");
        try {
            this.sock = new ServerSocket(port);
        }
        catch(IOException e)
        {
            this.logger.warning(e.getLocalizedMessage());
        }
    }

    @Override
    public void run()
    {
        this.logger.info("LoginServer running");
        try {
            while(true)
            {
                Socket newClient = this.sock.accept();
                this.logger.info(" + new connection from: " + newClient.getInetAddress().getHostAddress());
                LoginHandler hdl = new LoginHandler(newClient);
                hdl.start();
            }
        }
        catch(IOException e)
        {
            this.logger.info(e.getLocalizedMessage());
        }
        this.logger.info("LoginServer stopped");
    }
}
