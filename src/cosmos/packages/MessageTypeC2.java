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
        int dlength = this.data.size();
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
