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

import java.net.Socket;
import java.util.logging.Logger;
import cosmos.network.ClientSocket;
import cosmos.packages.IMessage;
/**
 *
 * @author masch
 */
public class LoginHandler extends Thread {

    private ClientSocket client;
    private Logger       logger;
    public LoginHandler(Socket client)
    {
        // threading stuff
        String id = String.valueOf(this.getId());
        this.setName("LoginHandler [" + id + "]");
        // set up logging
        this.logger = Logger.getLogger("cosmos.login");
        // set client
        this.client = new ClientSocket(client);
    }

    @Override
    public void run()
    {
        String id = String.valueOf(this.getId());
        this.logger.info("[" + id + "]: loginhandler started");
        // do login stuff here
        try {
            IMessage in = this.client.readMessage();
            this.logger.info("[" + id + "]: " + in.toString());
        }
        catch(cosmos.exceptions.ClientTimeoutException e)
        {
            this.logger.info("[" + id + "]: client timed out");
        }
        catch(cosmos.exceptions.UnknownPackageException e)
        {
            this.logger.info("[" + id + "]: received unknown package");
        }
        this.client.shutdown();
        this.logger.info("[" + id + "]: loginhandler stopped");
    }

}
