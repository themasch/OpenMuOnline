/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package openmuonline.packages;

/**
 *
 * @author masch
 */
public class WelcomePackage {

    public static Package create()
    {
        IMessage msg = new MessageTypeC1();
        msg.setAction((byte)0, (byte)1);
        return new Package(msg);
    }
}
