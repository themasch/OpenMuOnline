/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cosmos.config;

/**
 *
 * @author masch
 */
public abstract class Config {

    public abstract String get(String key);
    public abstract int getInt(String key);
    public abstract float getFloat(String key);
    public abstract boolean getBoolean(String key);
}
