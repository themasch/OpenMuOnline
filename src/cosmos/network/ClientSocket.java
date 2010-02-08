/*
 * OpenMuOnline - a open source mu online server
 * Copyright (C) 2009 Mark Schmale
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package cosmos.network;

import java.net.Socket;
import java.util.logging.Logger;

import cosmos.packages.IMessage;
import cosmos.packages.Package;
import cosmos.utils.ByteArray;
import cosmos.exceptions.ClientTimeoutException;
import cosmos.exceptions.UnknownPackageException;


/**
 * Abstraction for the connection to the client
 *
 * @author Mark Schmale <ma.schmale@googlemail.com>
 */
public class ClientSocket {

    protected Connection client;
    protected ClientStatus status;
    protected int idleTimeout = 2000; // wait 2s for new messages / missing bytes
    protected Logger log = Logger.getLogger("cosmos.network.ClientSocket");

    public ClientSocket(Socket sock)
    {
        this.client = new Connection(sock);
        this.status = ClientStatus.CONNECTED;
    }

    /**
     * reads a complete message from the client
     *
     * @return message from the Client
     * @throws ClientTimeoutException
     * @throws UnkownPackageException
     */
    @Deprecated
    public IMessage read() throws ClientTimeoutException, UnknownPackageException
    {
        return this.readMessage();
    }

    /**
     * reads a complete message from the client
     *
     * @return message from the Client
     * @throws ClientTimeoutException
     * @throws UnkownPackageException
     */
    public IMessage readMessage() throws ClientTimeoutException, UnknownPackageException
    {
        ByteArray in = new ByteArray();
        Byte read;
        int len;
        int cnt = 0;
        int left;
        try {
            this.client.setTimeout(this.idleTimeout);
            // read the first byte
            read = this.client.readByte();
            in.append(read);
            cnt++;
            // find out the package type and calculate length
            if(read == (byte)0xC1)
            {
                read = this.client.readByte();
                in.append(read);
                cnt++;
                len = read;
            }
            else if(read == (byte)0xC2)
            {
                read = this.client.readByte();
                in.append(read);
                len = read*256;
                read = this.client.readByte();
                in.append(read);
                len = len + read;
                cnt += 2;
            } else {
                throw new UnknownPackageException(in.toString());
            }
            left = len - cnt;
            // read missing bytes
            for(int i=0;i<left;i++)
            {
                read = this.client.readByte();
                in.append(read);
            }
        }
        catch(java.net.SocketTimeoutException timeout)
        {
            // okay.. nothing left to do
            // we only use the timeout exception to leave the loop
        }
        catch(java.net.SocketException sockerr)
        {
            if(sockerr.getMessage().equals("Connection reset"))
            {
                this.shutdown();
                throw new ClientTimeoutException();
            } else {
                this.log.warning(sockerr.getLocalizedMessage());
            }
        }
        catch(java.io.IOException ioexcep)
        {
            this.log.warning(ioexcep.getLocalizedMessage());
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
            if(this.client.isConnected() && this.status == ClientStatus.CONNECTED)
            {
                this.client.write(msg.get().getBytes());
            } else {
                throw new ClientTimeoutException();
            }
        }
        catch(java.io.IOException e)
        {
            this.log.warning(e.getLocalizedMessage());
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
            this.log.warning(e.getLocalizedMessage());
        }
    }
}
