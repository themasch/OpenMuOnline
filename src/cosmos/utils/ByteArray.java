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

package cosmos.utils;

import java.util.ArrayList;

/**
 * Class for handling lists of bytes more simple
 *
 * @author Mark Schmale <ma.schmale@googlemail.com>
 */
public class ByteArray {

    private ArrayList<Byte> buff = new ArrayList<Byte>();

    public ByteArray(ByteArray org, int start)
    {
        int max    = org.size();
        int size   = max - start;
        for(int i=0;i<size;i++)
        {
            this.buff.add(org.get(i+start));
        }
    }

    public ByteArray()
    {
        
    }

    public ByteArray(String in)
    {
        this(in.getBytes());
    }

    public ByteArray(byte[] in)
    {
        int len = in.length;
        int i;
        for(i=0;i<len;i++)
        {
            this.buff.add(in[i]);
        }
    }


    /**
     * appends the given value at the end of the list
     * 
     * @param value byte to append
     */
    public void append(byte value)
    {
        this.buff.add(value);
    }


    /**
     * removes all elements from this ByteArray
     */
    public void clear()
    {
        this.buff.clear();
    }

    /**
     * returns the byte at the specified position in this list
     *
     * @param index
     * @return element at the specified index
     */
    public byte get(int index)
    {
        return this.buff.get(index).byteValue();
    }

    /**
     * Returns the number of elements in the ByteArray
     * @return number of elements in the ByteArray
     */
    public int size()
    {
        return this.buff.size();
    }

    /**
     * returns a new array of bytes containing all
     * bytes stored in the ByteArray
     * 
     * @return all bytes in the ByteArray
     */
    public byte[] getBytes()
    {
        int size   = this.size();
        int i      = 0;
        byte[] all = new byte[this.size()];
        for(i=0;i<size;i++)
        {
            all[i] = this.get(i);
        }
        return all;
    }

    /**
     * transforms the bytes in the array into
     * a hexadecimal string representation
     * 
     * @return string representation of the array
     */
    @Override
    public String toString()
    {
        int i;
        int max = this.size();
        String str = "";
        String tmp = "";
        for(i=0;i<max;i++)
        {
            tmp = Integer.toHexString(this.get(i));
            if(tmp.length() <= 1)
            {
                tmp = "0" + tmp.charAt(tmp.length()-1);
            }
            if(tmp.length() >= 3)
            {
                tmp = tmp.substring(tmp.length()-2);
            }
            str = str + tmp.toUpperCase();
        }
        return str;
    }

}
