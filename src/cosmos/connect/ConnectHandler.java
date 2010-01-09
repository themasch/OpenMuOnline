package cosmos.connect;

import cosmos.network.ClientSocket;
import cosmos.utils.log.Logger;
import cosmos.utils.ByteArray;
import cosmos.packages.IMessage;
import cosmos.packages.WelcomePackage;
import java.net.Socket;

/**
 *
 * @author masch
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
