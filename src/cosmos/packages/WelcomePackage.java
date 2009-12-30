/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cosmos.packages;

/**
 *
 * @author masch
 */
public class WelcomePackage extends MessageTypeC1 {

    public WelcomePackage()
    {
        super();
        this.setAction((byte)0, (byte)1);
    }
}
