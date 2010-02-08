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
import cosmos.network.ClientSocket;
import cosmos.utils.log.Logger;
import cosmos.packages.IMessage;
/**
 *
 * @author masch
 */
public class LoginHandler extends Thread {

    private ClientSocket client;

    public LoginHandler(Socket client)
    {
        // threading stuff
        String id = String.valueOf(this.getId());
        this.setName("LoginHandler [" + id + "]");
        this.client = new ClientSocket(client);
    }

    @Override
    public void run()
    {
        String id = String.valueOf(this.getId());
        Logger.log("[" + id + "]: loginhandler started");
        // do login stuff here
        try {
            IMessage in = this.client.read();
            Logger.log("[" + id + "]: " + in.toString());
        }
        catch(cosmos.exceptions.ClientTimeoutException e)
        {
            Logger.error("[" + id + "]: client timed out");
        }
        catch(cosmos.exceptions.UnknownPackageException e)
        {
            Logger.error("[" + id + "]: received unknown package");
        }
        this.client.shutdown();
        Logger.log("[" + id + "]: loginhandler stopped");
    }

}
