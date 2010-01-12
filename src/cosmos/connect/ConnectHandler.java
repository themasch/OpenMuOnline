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

import cosmos.network.ClientSocket;
import cosmos.utils.log.Logger;
import cosmos.utils.ByteArray;
import cosmos.packages.IMessage;
import cosmos.packages.WelcomePackage;
import java.net.Socket;

/**
 *
 * @author  Mark Schmale <ma.schmale@googlemail.com>
 */
public class ConnectHandler extends Thread {
    private ClientSocket client;

    public ConnectHandler(Socket client)
    {
        // threading stuff
        String id = String.valueOf(this.getId());
        this.setName("ConnectHandler [" + id + "]");
        this.client = new ClientSocket(client);
    }

    @Override
    public void run()
    {
        String id = String.valueOf(this.getId());
        Logger.log("[" + id + "]: ConnectHandler started");
        // do connect stuff here
        try {
            // send welcome package
            IMessage welcome = new WelcomePackage();
            this.client.send(welcome);
            while(true)
            {
                try {
                    IMessage in = this.client.read();
                    Logger.log("[" + id + "] said " + in.toString());
                    this.handleMessage(in);
                }
                catch(cosmos.exceptions.UnkownPackageException e)
                {
                    Logger.error("[" + id + "]: received unknown package");
                }
            }
        }
        catch(cosmos.exceptions.ClientTimeoutException e)
        {
            Logger.error("[" + id + "]: client timed out");
        }
  
        this.client.shutdown();
        Logger.log("[" + id + "]: ConnectHandler stopped");
    }

    /**
     * handels a Message for the client
     * @param msg incoming message
     */
    protected void handleMessage(IMessage msg)
    {
        ByteArray action = msg.getAction();
        if(action.get(0) == (byte)0xF4 && action.get(1) == (byte)0x06)
        {   // ask for server list
            // TODO: build & send serverlist
            Logger.log("clients requests the serverlist");
            
        }

    }

}
