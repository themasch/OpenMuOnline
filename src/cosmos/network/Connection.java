/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cosmos.network;

import java.net.Socket;
import java.net.InetAddress;

import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author masch
 */
public class Connection {

    protected Socket sock;

    public Connection(InetAddress host, int port) throws java.io.IOException
    {
        this.sock = new Socket(host, port);
    }

    public Connection(Socket sock)
    {
        this.sock = sock;
    }

    public boolean isConnected()
    {
        return this.sock.isConnected();
    }

    public String readLine() throws java.io.IOException
    {
        InputStream     is   = this.sock.getInputStream();
        BufferedReader  read = new BufferedReader(new InputStreamReader(is));
        return read.readLine();
    }

    public Byte readByte() throws java.io.IOException
    {
        InputStream is = this.sock.getInputStream();
        return Byte.valueOf((byte)is.read());
    }

    public void write(String msg) throws java.io.IOException
    {
        byte[] nmsg = msg.getBytes();
        this.write(nmsg);
    }

    public void write(byte[] msg) throws java.io.IOException
    {
        this.sock.getOutputStream().write(msg);
        this.sock.getOutputStream().flush();
    }

    public void close() throws java.io.IOException
    {
        this.sock.close();
    }

    public void setTimeout(int msec) throws java.net.SocketException
    {
        this.sock.setSoTimeout(msec);
    }
}
