/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package openmuonline.connectserver;

import openmuonline.network.ClientSocket;
import openmuonline.utils.Logger;
import openmuonline.packages.Package;
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
        Package welcome = WelcomePackage.create();
        this.client.write(welcome);
        try {
            Package read = this.client.read();
            Logger.log("[" + id + "]: " + read.toString());
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

}
