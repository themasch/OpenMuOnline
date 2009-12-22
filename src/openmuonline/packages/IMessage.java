/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package openmuonline.packages;


import openmuonline.utils.ByteArray;

/**
 *
 * @author masch
 */
public interface IMessage {

    public ByteArray getData();
    public void setData(ByteArray data);
    public void setData(byte[] data);

    public int getLenght();

    public ByteArray get();

    public void setAction(byte one, byte two);
}
