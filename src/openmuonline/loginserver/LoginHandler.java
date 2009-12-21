/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package openmuonline.loginserver;

import java.net.Socket;
import openmuonline.exceptions.ClientTimeoutException;
import openmuonline.network.ClientSocket;
import openmuonline.utils.Logger;
import openmuonline.packages.Package;
/**
 *
 * @author masch
 */
public class LoginHandler extends Thread {

    private ClientSocket client;

    public LoginHandler(Socket client)
    {
        this.client = new ClientSocket(client);
    }

    @Override
    public void run()
    {
        String id = String.valueOf(this.getId());
        Logger.log("[" + id + "]: loginhandler started");
        // do login stuff here
        try {
            Package read = this.client.read();
            Logger.log("[" + id + "]: " + read.toString());
        }
        catch(openmuonline.exceptions.ClientTimeoutException e)
        {
            Logger.error("[" + id + "]: client timed out");
        }
        this.client.shutdown();
        Logger.log("[" + id + "]: loginhandler stopped");
    }

}
