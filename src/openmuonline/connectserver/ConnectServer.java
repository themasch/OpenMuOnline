/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package openmuonline.connectserver;

import java.net.ServerSocket;
import java.net.Socket;
import openmuonline.utils.Logger;

/**
 *
 * @author masch
 */
public class ConnectServer extends Thread {

    private ServerSocket sock;

    public ConnectServer(int port)
    {
        try {
            this.sock = new ServerSocket(port);
        }
        catch(java.io.IOException e)
        {
            Logger.error(e.getLocalizedMessage());
        }
    }


    @Override
    public void run()
    {
        Logger.status("ConnectServer running");
        try {
            while(true)
            {
                    Socket newClient = this.sock.accept();
                    Logger.log("new connection from: " + newClient.getInetAddress().getHostAddress());
                    ConnectHandler hdl = new ConnectHandler(newClient);
                    hdl.start();

            }
        }
        catch(java.io.IOException e)
        {
            Logger.error(e.getLocalizedMessage());
        }
        Logger.status("ConnectServer stopped");
    }
}
