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
public class MessageTypeC1 extends AbstractMessage {

    private byte      msg_type = (byte)193;


    public MessageTypeC1(byte[] data)
    {
        this(new ByteArray(data));
    }

    public MessageTypeC1(ByteArray data)
    {
        this.buffer   = data;
        this.action_1 = data.get(2);
        this.action_2 = data.get(3);
        if(data.size() > 4)
        {
            this.data = new ByteArray(data, 4);
        }
    }

    public MessageTypeC1()
    {}

    private void build()
    {
        int i;
        int len = this.getLenght();
        byte[] tmp = new byte[len];
        int dlength = len - 5;
        tmp[0] = this.msg_type;
        tmp[1] = (byte)len;
        tmp[2] = this.action_1;
        tmp[3] = this.action_2;
        for(i=0;i<dlength;i++)
        {
            tmp[4+i] = this.data.get(i);
        }
        this.buffer = new ByteArray(tmp);
    }

    public ByteArray get()
    {
        this.build();
        return this.buffer;
    }

    public int getLenght() {
        return 4 + this.data.size();
    }
}
