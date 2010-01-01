/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cosmos.network;

import java.net.Socket;

import cosmos.packages.IMessage;
import cosmos.packages.Package;
import cosmos.utils.ByteArray;
import cosmos.utils.log.Logger;
import cosmos.exceptions.ClientTimeoutException;
import cosmos.exceptions.UnkownPackageException;


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

    /**
     * reads a complete message from the client
     *
     * @return message from the Client
     * @throws ClientTimeoutException
     * @throws UnkownPackageException
     */
    public IMessage read() throws ClientTimeoutException, UnkownPackageException
    {
        ByteArray in = new ByteArray();
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
                // stop reading!
            }
        }
        catch(java.net.SocketException e)
        {
            Logger.error(e.getLocalizedMessage());
            if(e.getMessage().equals("Connection reset"))
            {
                throw new ClientTimeoutException();
            }
        }
        catch(java.io.IOException e)
        {
            Logger.error(e.getLocalizedMessage());
        }
        return Package.parse(in);
    }

    /**
     * Sends a message to the client
     *
     * @param msg Message to send
     */
    public void send(IMessage msg)
    {
        try {
            this.client.getOutputStream().write(msg.get().getBytes());
        }
        catch(java.io.IOException e)
        {
            Logger.error(e.getLocalizßedMessage());
        }
    }


    /**
     * closes the socket
     */
    public void shutdown()
    {
        try {
            this.client.close();
        }
        catch(java.io.IOException e) {
            Logger.error(e.getLocalizedMessage());
        }
    }
}
