/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cosmos.packages;

import cosmos.utils.ByteArray;
import cosmos.exceptions.UnkownPackageException;
import cosmos.utils.log.Logger;

/**
 *
 * @author masch
 */
public class Package {

    public static IMessage parse(ByteArray in) throws UnkownPackageException
    {
        byte msg_type = in.get(0);
        switch(msg_type)
        {
            case (byte)0xC1: return new MessageTypeC1(in);
            case (byte)0xC2: return new MessageTypeC2(in);
                    default:  throw new UnkownPackageException();
        }
    }
}
