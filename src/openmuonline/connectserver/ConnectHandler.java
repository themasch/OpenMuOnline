/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package openmuonline.connectserver;

import openmuonline.network.ClientSocket;
import openmuonline.utils.Logger;
import openmuonline.utils.ByteArray;
import openmuonline.packages.IMessage;
import openmuonline.packages.WelcomePackage;
import java.net.Socket;

/**
 *
 * @author masch
 */
public class ConnectHandler extends Thread {
    private ClientSocket client;

    public ConnectHandler(Socket client)
    {
        this.client = new ClientSocket(client);
    }

    @Override
    public void run()
    {
        String id = String.valueOf(this.getId());
        Logger.log("[" + id + "]: ConnectHandler started");
        // do connect stuff here
        // send welcome package
        IMessage welcome = new WelcomePackage();
        this.client.send(welcome);
        try {
            while(true)
            {
                IMessage in = this.client.read();
                Logger.log("[" + id + "] said " + in.toString());
                this.handleMessage(in);
            }
        }
        catch(openmuonline.exceptions.ClientTimeoutException e)
        {
            Logger.error("[" + id + "]: client timed out");
        }
        catch(openmuonline.exceptions.UnkownPackageException e)
        {
            Logger.error("[" + id + "]: received unknown package");
        }
        this.client.shutdown();
        Logger.log("[" + id + "]: ConnectHandler stopped");
    }

    protected void handleMessage(IMessage msg)
    {
        ByteArray action = msg.getAction();
        if(action.get(0) == (byte)0xF4 && action.get(1) == (byte)0x06)
        {   // ask for server list
            // TODO: build & send serverlist
            
        }

    }

}
