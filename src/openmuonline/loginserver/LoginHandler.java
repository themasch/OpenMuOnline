/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package openmuonline.loginserver;

import java.net.Socket;
import openmuonline.exceptions.UnkownPackageException;
import openmuonline.network.ClientSocket;
import openmuonline.utils.Logger;
import openmuonline.packages.IMessage;
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
            IMessage in = this.client.read();
            Logger.log("[" + id + "]: " + in.toString());
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
        Logger.log("[" + id + "]: loginhandler stopped");
    }

}
