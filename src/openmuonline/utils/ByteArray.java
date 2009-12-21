/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package openmuonline.utils;


/**
 *
 * @author masch
 */
public class ByteArray {

    private byte[] buff = new byte[16];
    private int ptr = 0;

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
        return this.buff;
    }

}
