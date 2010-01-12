/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cosmos.utils;

import java.util.ArrayList;

/**
 *
 * @author masch
 */
public class ByteArray {

    private ArrayList<Byte> buff = new ArrayList<Byte>();
    private int ptr = 0;

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

    public void append(byte value)
    {
        this.buff.add(value);
    }

    public void clear()
    {
        this.buff.clear();
    }

    public byte get(int index)
    {
        return this.buff.get(index).byteValue();
    }

    public int size()
    {
        return this.buff.size();
    }

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
