/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cosmos.packages;

import cosmos.utils.ByteArray;

/**
 *
 * @author masch
 */
public abstract class AbstractMessage implements IMessage {

    protected ByteArray buffer  = new ByteArray();
    protected ByteArray data    = new ByteArray();
    protected byte      action_1;
    protected byte      action_2;
    
    public ByteArray getData()
    {
        return this.data;
    }

    public ByteArray getAction()
    {
        byte[] tmp = new byte[2];
        tmp[0] = this.action_1;
        tmp[1] = this.action_2;
        return new ByteArray(tmp);
    }

    public void setData(ByteArray data) {
        this.data = data;
    }

    public void setData(byte[] data) {
        this.data = new ByteArray(data);
    }


    public void setAction(byte one, byte two)
    {
        this.action_1 = one;
        this.action_2 = two;
    }


    @Override
    public String toString()
    {
        return this.get().toString();
    }
}
