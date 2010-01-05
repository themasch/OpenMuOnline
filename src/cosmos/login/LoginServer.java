/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cosmos.login;

import java.io.IOException;
import java.net.ServerSocket;
import cosmos.utils.log.Logger;
import java.net.Socket;

/**
 *
 * @author masch
 */
public class LoginServer extends Thread {

    private ServerSocket sock;

    public LoginServer(int port)
    {
        // threading stuff
        this.setName("LoginServer");
        try {
            this.sock = new ServerSocket(port);
        }
        catch(IOException e)
        {
            Logger.error(e.getLocalizedMessage());
        }
    }

    @Override
    public void run()
    {
        Logger.status("LoginServer running");
        try {
            while(true)
            {
                    Socket newClient = this.sock.accept();
                    Logger.log("new connection from: " + newClient.getInetAddress().getHostAddress());
                    LoginHandler hdl = new LoginHandler(newClient);
                    hdl.start();

            }
        }
        catch(IOException e)
        {
            Logger.error(e.getLocalizedMessage());
        }
        Logger.status("LoginServer stopped");
    }
}
