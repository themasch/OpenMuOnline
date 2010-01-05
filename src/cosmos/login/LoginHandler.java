/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cosmos.login;

import java.net.Socket;
import cosmos.exceptions.UnkownPackageException;
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
        catch(cosmos.exceptions.UnkownPackageException e)
        {
            Logger.error("[" + id + "]: received unknown package");
        }
        this.client.shutdown();
        Logger.log("[" + id + "]: loginhandler stopped");
    }

}
