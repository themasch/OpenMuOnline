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
import cosmos.packages.*;
import java.net.Socket;

/**
 * Handels the connections to a single client
 *
 * For every Client connection to the ConnectServer
 * a new ConnectHandler is spawned
 *
 * @author  Mark Schmale <ma.schmale@googlemail.com>
 */
public class ConnectHandler extends Thread {
    private ClientSocket client;

    private String myId;

    public ConnectHandler(Socket client)
    {
        // threading stuff
        this.myId = String.valueOf(this.getId());
        this.setName("ConnectHandler [" + this.myId + "]");
        this.client = new ClientSocket(client);
    }

    @Override
    public void run()
    {
        Logger.log("[" + this.myId + "]: ConnectHandler started");
        // do connect stuff here
        try {
            // send welcome package
            IMessage welcome = new WelcomePackage();
            this.client.send(welcome);
            while(true)
            {
                try {
                    IMessage in = this.client.read();
                    if(!(in instanceof EmptyMessage))
                    {
                        Logger.log("[" + this.myId + "] said " + in.toString());
                        this.handleMessage(in);
                    }
                }
                catch(cosmos.exceptions.UnkownPackageException e)
                {
                    Logger.error("[" + this.myId + "]: received unknown package");
                }
            }
        }
        catch(cosmos.exceptions.ClientTimeoutException e)
        {
            Logger.error("[" + this.myId + "]: client timed out");
        }
  
        this.client.shutdown();
        Logger.log("[" + this.myId + "]: ConnectHandler stopped");
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
            Logger.log("clients requests the serverlist");
            this.sendServerlist();
        }
    }

    protected void sendServerlist()
    {
        // TODO: build & send serverlist
        // FIXME: this is a static server list.. thats uncool!
        byte load_1 = (byte) (Math.random() * 100);
        byte load_2 = (byte) (Math.random() * 100);
        byte[] sl = {   (byte)0x00, (byte)0x02,
                        (byte)0x00, (byte)0x00, load_1, (byte)0x77,
                        (byte)0x01, (byte)0x00, load_2, (byte)0x77 };
        IMessage rmsg = new MessageTypeC2();
        rmsg.setAction(0xF4, 0x06);
        rmsg.setData(sl);
        
        try {
            this.client.send(rmsg);
        }
        catch(cosmos.exceptions.ClientTimeoutException e)
        {
            Logger.error(e.getLocalizedMessage());
        }
    }

}
