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
public class EmptyMessage implements IMessage {

    public ByteArray get() {
        return new ByteArray();
    }

    public ByteArray getAction() {
        return new ByteArray();
    }

    public ByteArray getData() {
        return new ByteArray();
    }

    public int getLenght() {
        return 0;
    }

    public void setAction(byte one, byte two) {
    }

    public void setAction(int one, int two) {
    }

    public void setData(ByteArray data) {
    }

    public void setData(byte[] data) {
    }

}
