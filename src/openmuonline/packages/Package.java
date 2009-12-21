/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package openmuonline.packages;

import openmuonline.utils.ByteArray;

/**
 *
 * @author masch
 */
public class Package {

    private ByteArray buffer = new ByteArray();

    public Package(byte[] in)
    {
        this.buffer = new ByteArray(in);
    }

    public Package()
    {

    }

    public void append(byte chr)
    {
        this.buffer.append(chr);
    }

    public byte[] getBytes()
    {
        return this.buffer.getBytes();
    }

    public String toString()
    {
        byte[] tmp = this.buffer.getBytes();
        StringBuilder str = new StringBuilder();
        for(int i=0;i<tmp.length;i++)
        {
            str.append(Integer.toHexString(tmp[i]) + " ");
        }
        return str.toString();
    }
}
