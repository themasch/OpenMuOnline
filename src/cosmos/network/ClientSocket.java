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

    protected Socket client;
    protected ClientStatus status;

    public ClientSocket(Socket sock)
    {
        this.client = sock;
        this.status = ClientStatus.CONNECTED;
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
        int cnt = 0;
        try {
            while(this.status == ClientStatus.CONNECTED)
            {
                // for the first byte, we wait 10 seconds
                int timeout = 10000;
                if(cnt >= 1)
                {
                    // for the next only 0.2seconds
                    timeout = 200;
                }
                this.client.setSoTimeout(timeout);
                read = (byte)this.client.getInputStream().read();
                in.append(read);
                cnt++;
            }
        }
        catch(java.net.SocketTimeoutException e)
        {
            if(cnt == 0)
            {
                // if this was the first byte, there seems to be no client
                this.status = ClientStatus.DISCONNECTED;
                throw new ClientTimeoutException();
            } // else: he has nothing to say
        }
        catch(java.net.SocketException e)
        {
            if(e.getMessage().equals("Connection reset"))
            {
                this.status = ClientStatus.DISCONNECTED;
                throw new ClientTimeoutException();
            }
            Logger.error(e.getLocalizedMessage());
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
    public void send(IMessage msg) throws ClientTimeoutException
    {
        try {
            if(this.status == ClientStatus.CONNECTED)
            {
                this.client.getOutputStream().write(msg.get().getBytes());
                this.client.getOutputStream().flush();
            } else {
                throw new ClientTimeoutException();
            }
        }
        catch(java.io.IOException e)
        {
            Logger.error(e.getLocalizedMessage());
        }
    }


    /**
     * closes the socket
     */
    public void shutdown()
    {
        try {
            this.client.close();
            this.status = ClientStatus.DISCONNECTED;
        }
        catch(java.io.IOException e) {
            Logger.error(e.getLocalizedMessage());
        }
    }
}
