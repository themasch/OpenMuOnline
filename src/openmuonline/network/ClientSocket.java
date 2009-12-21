/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package openmuonline.network;

import java.net.Socket;

import openmuonline.packages.Package;
import openmuonline.utils.Logger;
import openmuonline.exceptions.ClientTimeoutException;


/**
 *
 * @author masch
 */
public class ClientSocket {

    private Socket client;

    public ClientSocket(Socket sock)
    {
        this.client = sock;
    }

    public Package read() throws ClientTimeoutException
    {
        Package in = new Package();
        byte read;
        try {
            try {
                // for the first byte, we wait 10 seconds
                this.client.setSoTimeout(10000);
                read = (byte)this.client.getInputStream().read();
                in.append(read);
            }
            catch(java.net.SocketTimeoutException e)
            {
                throw new ClientTimeoutException();
            }
            try {
                // for the next only 0.2seconds
                this.client.setSoTimeout(200);
                while(true)
                {
                    read = (byte)this.client.getInputStream().read();
                    in.append(read);
                }
            }
            catch(java.net.SocketTimeoutException e)
            {
                return in;
            }
        }
        catch(java.net.SocketException e)
        {
            Logger.error(e.getLocalizedMessage());
        }
        catch(java.io.IOException e)
        {
            Logger.error(e.getLocalizedMessage());
        }
        return in;
    }

    public void write(Package msg)
    {
        try {
            this.client.getOutputStream().write(msg.getBytes());
        }
        catch(java.io.IOException e)
        {
            Logger.error(e.getLocalizedMessage());
        }
    }

    public void shutdown()
    {
        try {
            this.client.close();
        }
        catch(java.io.IOException e)
        {
            Logger.error(e.getLocalizedMessage());
        }
        
    }
}
