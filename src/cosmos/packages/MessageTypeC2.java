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
public class MessageTypeC2 extends AbstractMessage {

    private byte      msg_type = (byte)0xC2;

    public MessageTypeC2(byte[] data)
    {
        this(new ByteArray(data));
    }

    public MessageTypeC2(ByteArray data)
    {
        this.buffer   = data;
        this.action_1 = data.get(3);
        this.action_2 = data.get(4);
        if(data.size() > 5)
        {
            this.data = new ByteArray(data, 5);
        }
    }

    public MessageTypeC2() {}

    private void build()
    {
        int i;
        int len = this.getLenght();
        byte len_2 = (byte)(len % 256);
        byte len_1 = (byte)((len-len_2) / 256);
        byte[] tmp = new byte[len];
        int dlength = len - 5;
        tmp[0] = this.msg_type;
        tmp[1] = len_1;
        tmp[2] = len_2;
        tmp[3] = this.action_1;
        tmp[4] = this.action_2;
        for(i=0;i<dlength;i++)
        {
            tmp[5+i] = this.data.get(i);
        }
        this.buffer = new ByteArray(tmp);
    }

    public ByteArray get()
    {
        this.build();
        return this.buffer;
    }

    public int getLenght()
    {
        return 5 + this.data.size();
    }
}
