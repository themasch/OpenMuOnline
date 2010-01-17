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


    public void setAction(int one, int two)
    {
        this.action_1 = (byte)one;
        this.action_2 = (byte)two;
    }



    @Override
    public String toString()
    {
        return this.get().toString();
    }
}
