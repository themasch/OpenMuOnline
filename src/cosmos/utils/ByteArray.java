/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cosmos.utils;

/**
 *
 * @author masch
 */
public class ByteArray {

    private byte[] buff = new byte[16];
    private int ptr = 0;

    public ByteArray(ByteArray org, int start)
    {
        int max    = org.size();
        int size   = max - start;
        byte[] tmp = new byte[size];
        for(int i=0;i<size;i++)
        {
            tmp[i] = org.get(i+start);
        }
        this.buff = tmp;
    }

    public ByteArray()
    {
        
    }

    public ByteArray(String in)
    {
        buff = in.getBytes();
    }

    public ByteArray(byte[] in)
    {
        this.buff = in;
        int len = in.length;
        int i = 0;
        // set the pointer to the element after the last not-null-char
        for(i=0;i<len;i++)
        {
            if(in[i] > 0)
            {
               ptr = i+1;
            }
        }


    }

    private void resizeBuffer()
    {
        int len = this.buff.length;
        int newLen = len*2;
        byte[] tmp = new byte[newLen];
        for(int i = 0;i<len;i++)
        {
            tmp[i] = this.buff[i];
        }
        this.buff = tmp;
    }

    public void append(byte value)
    {
        if(this.ptr >= this.buff.length)
        {
            // double the buffer size
            this.resizeBuffer();
        }
        // append the new value
        this.buff[this.ptr] = value;
        // move the pointer
        this.ptr++;
    }

    public void clear()
    {
        this.buff = new byte[16];
        this.ptr  = 0;
    }

    public byte get(int index)
    {
        if(index >= this.ptr)
        {
            throw new java.lang.IndexOutOfBoundsException();
        }
        return this.buff[index];
    }

    public int size()
    {
        return this.ptr;
    }

    public byte[] getBytes()
    {
        byte[] all = new byte[this.ptr];
        int i = 0;
        // copy buffer to new array
        for(i=0;i<this.ptr;i++)
        {
            all[i] = this.buff[i];
        }
        return all;
    }

    @Override
    public String toString()
    {
        int i;
        int max = this.ptr;
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
