/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package openmuonline.packages;

import openmuonline.utils.ByteArray;
import openmuonline.exceptions.UnkownPackageException;
import openmuonline.utils.Logger;

/**
 *
 * @author masch
 */
public class Package {

    private byte      msg_type = (byte)0xC1;
    private IMessage msg;


    public Package(ByteArray in) throws UnkownPackageException
    {
        this.parse(in);
    }
    
    private void parse(ByteArray in) throws UnkownPackageException
    {
        this.msg_type = in.get(0);
        switch(this.msg_type)
        {
            case (byte)0xC1:
                // msg_type 1
                this.msg = new MessageTypeC1(in);
                break;

            case (byte)0xC2:
                // msg_type 2
                this.msg = new MessageTypeC2(in);
                break;

            default:
                throw new UnkownPackageException();
        }
    }

    public int getLenght()
    {
        return this.msg.getLenght();
    }

    public byte getMessageType()
    {
        return this.msg_type;
    }

    public byte[] getBytes()
    {
        return this.msg.get().getBytes();
    }

    public Package(byte[] in)  throws UnkownPackageException
    {
        this(new ByteArray(in));
    }

    public Package(IMessage msg)
    {
        this.msg = msg;
    }

    @Override
    public String toString()
    {
        byte[] tmp = this.msg.get().getBytes();
        StringBuilder str = new StringBuilder();
        for(int i=0;i<tmp.length;i++)
        {
            str.append(Integer.toHexString(tmp[i]) + " ");
        }
        return str.toString();
    }
}
